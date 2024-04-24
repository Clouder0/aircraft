package edu.hitsz.aircraft;

import edu.hitsz.aircraft.shootstrategy.ShootStrategyInterface;
import edu.hitsz.aircraft.shootstrategy.SingleShootStrategy;
import edu.hitsz.application.TimerManager;
import edu.hitsz.bullet.HeroBulletFactory;
import edu.hitsz.utils.TickTimer;
import edu.hitsz.utils.TickTimerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 *
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY    英雄机射出的子弹的基准速度（英雄机无特定速度）
     */
    public HeroAircraft(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY, 200, new SingleShootStrategy(new HeroBulletFactory(), -1));
        this.shoot_queue = new ArrayList<>();
        // add default shoot
        ShootStrategyInterface default_shoot = this.shoot_strategy;
        this.shoot_queue.add(TickTimerFactory.setAlways(() -> {
            this.setShootStrategy(default_shoot);
        }));
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    protected List<TickTimer> shoot_queue;

    public void enhanceShoot(int duration, ShootStrategyInterface s) {
        // pause old top
        TickTimer last = this.shoot_queue.getLast();
        TickTimer now = TickTimerFactory.setLasting(duration, () -> {
            last.pause();
            System.out.println("pre_start");
            this.setShootStrategy(s);
        }, () -> {
            last.resume();
            System.out.println("after_finished");
            this.shoot_queue.removeLast();
        }, () -> {
            System.out.println("pre_resume");
            this.setShootStrategy(s);
        }, null);
        this.shoot_queue.add(now);
        TimerManager.addTimer(now);
        now.start();
    }

    public void setShootStrategy(ShootStrategyInterface s) {
        this.shoot_strategy = s;
    }
}
