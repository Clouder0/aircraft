package edu.hitsz.application;

import edu.hitsz.aircraft.*;
import edu.hitsz.aircraft.enemy.*;
import edu.hitsz.aircraft.enemy.factory.*;
import edu.hitsz.application.score.FileScoreStore;
import edu.hitsz.application.score.ScoreManager;
import edu.hitsz.application.score.ScoreRecord;
import edu.hitsz.basic.Wrapper;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.item.ItemBase;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract class GameBase extends JPanel {

    protected int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;
    protected int timeCount = 0;

    private final HeroAircraft heroAircraft;
    protected final List<EnemyBase> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;

    private final List<ItemBase> items;

    protected final List<EnemyFactoryInterface> enemyFactories;

    /**
     * 当前得分
     */
    protected Wrapper<Integer> score = new Wrapper<>(0);
    /**
     * 当前时刻
     */
    private int time = 0;

    private int cycleTime = 0;


    private MusicHelper BGM;
    /**
     * 游戏结束标志
     */
    private boolean gameOverFlag = false;


    public HeroAircraft getHeroAircraft() {return heroAircraft;}

    protected abstract BossFactoryBase getBossFactory();

    protected abstract void registerEnemyFactories();
    // 0 for easy, 1 for medium, 2 for hard
    public GameBase(boolean music) {
        MusicManager.enabled = music;
        System.out.println(music);
        heroAircraft = HeroSingleton.getInstance();

        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        items = new ArrayList<>();
        ScoreManager.addStore(new FileScoreStore(Path.of("score.txt")));

        enemyFactories = new ArrayList<>();
        registerEnemyFactories();



        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);
    }


    protected abstract boolean checkNewEnemy();
    protected abstract void incrementDifficulty();
    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;


            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                timeCount++;
                incrementDifficulty();
                for (EnemyFactoryInterface factory : this.enemyFactories) {
                    if(!checkNewEnemy()) break;
                    EnemyBase enemy = factory.genEnemy();
                    if(enemy == null) continue;
                    this.enemyAircrafts.add(enemy);
                }
                // 飞机射出子弹
                shootAction();
            }

            TimerManager.nextTick();

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");
                this.gameOver();
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);
        this.BGM = MusicManager.playLoop("src/videos/bgm.wav");
    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        /**
         * 周期（ms)
         * 指示子弹的发射、敌机的产生频率
         */
        int cycleDuration = 600;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        for (EnemyBase enemy : enemyAircrafts) {
            List<BaseBullet> bullets = enemy.shoot();
            if (bullets != null) {
                enemyBullets.addAll(bullets);
            }
        }

        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
                if (heroAircraft.notValid()) {
                    // 游戏结束，直接退出
                    return;
                }
            }
        }

        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (EnemyBase enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();

                }
                // 英雄机 与 敌机 相撞，均损毁
                // 不，英雄机扣 HP 100
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.decreaseHp(10000);
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(100);
                }
            }

        }

        for (ItemBase item : items) {
            if (!item.crash(heroAircraft)) {
                continue;
            }
            item.use(this);
            item.vanish();
        }

        for(EnemyBase enemyAircraft : enemyAircrafts) {
            if (enemyAircraft.notValid()) {
                List<ItemBase> loot = enemyAircraft.genLoot();
                if (loot != null) {
                    items.addAll(loot);
                }
                score.set(score.get() + 10);
            }
        }
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        items.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************


    protected abstract void drawBackground(Graphics g);
    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        this.drawBackground(g);
        // 绘制背景,图片滚动
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, items);
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);
        var hero_image = ImageManager.CLASSNAME_IMAGE_MAP.get(HeroAircraft.class.getName());
        g.drawImage(hero_image, heroAircraft.getLocationX() - hero_image.getWidth() / 2, heroAircraft.getLocationY() - hero_image.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.isEmpty()) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2, object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    protected abstract int getDifficulty();
    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score.get(), x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
        y = y + 20;
        g.drawString("Difficulty:" + this.getDifficulty(), x, y);
    }

    private void gameOver() {
        System.out.println("game over logic");
        if(this.BGM != null)  this.BGM.stop();
        this.BGM = null;
        MusicManager.stopall();
        MusicManager.play("src/videos/game_over.wav");
        NameDialog dialog = new NameDialog((String name) -> {
            ScoreRecord now = new ScoreRecord(name, LocalDateTime.now(), this.score.get());
            ScoreManager.writeScore(now);
            ScoreForm form = new ScoreForm();
            form.getPanel().setVisible(true);
            Main.cardPanel.add(form.getPanel());
            Main.cardLayout.last(Main.cardPanel);
            return true;
        });
        Main.cardPanel.add(dialog.getContentPane());
        Main.cardLayout.last(Main.cardPanel);
    }
}
