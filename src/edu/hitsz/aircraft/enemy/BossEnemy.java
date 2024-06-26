package edu.hitsz.aircraft.enemy;

import edu.hitsz.aircraft.shootstrategy.HalfCircleShootStrategy;
import edu.hitsz.aircraft.shootstrategy.SectorShootStrategy;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicManager;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.EnemyBulletFactory;
import edu.hitsz.item.*;

import java.util.ArrayList;
import java.util.List;

public class BossEnemy extends EnemyBase implements BombObserverInterface {

    private int direction_duration = 0;

    public void onExplode() {
        
    }

    public BossEnemy(int hp, int locationX, int locationY) {
        super(locationX, locationY, 4, 0, hp, new HalfCircleShootStrategy(new EnemyBulletFactory(), 1));
        List<ItemFactoryInterface> loots = new ArrayList<>();
        loots.add(new ChanceItemFactory(0.9, new HealItemFactory(10)));
        loots.add(new ChanceItemFactory(0.9, new BulletItemFactory()));
        loots.add(new ChanceItemFactory(0.9, new BombItemFactory()));
        loots.add(new ChanceItemFactory(0.9, new BulletPlusItemFactory()));
        var helper = MusicManager.playLoop("src/videos/bgm_boss.wav");
        this.genLoot = (EnemyBase e) -> {
            var t = new ChanceMultipleLootTable(loots, 3);
            assert helper != null;
            helper.stop();
            return t.genLoot(e);
        };
    }

    private void changeDirection() {
        if ((this.locationX < 0 && this.speedX < 0) || (this.locationX > Main.WINDOW_WIDTH && this.speedX > 0) || --this.direction_duration <= 0) {
            this.speedX *= -1;
            this.direction_duration = (int) (Math.random() * 20);
        }
    }

    @Override
    public void forward() {
        this.changeDirection();
        this.locationX += this.speedX;
    }

    @Override
    public List<BaseBullet> shoot() {
        if(Math.random() < 0.6) return null;
        // chance shoot
        return super.shoot();
    }
}
