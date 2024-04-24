package edu.hitsz.aircraft.enemy;

import edu.hitsz.aircraft.shootstrategy.SectorShootStrategy;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.EnemyBulletFactory;
import edu.hitsz.item.*;

import java.util.ArrayList;
import java.util.List;

public class ElitePlusEnemy extends EnemyBase {

    private int direction_duration = 0;
    public ElitePlusEnemy(int locationX, int locationY, int speedX, int speedY) {
        super(locationX,locationY,speedX,speedY,90, new SectorShootStrategy(new EnemyBulletFactory(), 1));
        List<ItemFactoryInterface> loots = new ArrayList<>();
        loots.add(new ChanceItemFactory(0.8, new HealItemFactory(10)));
        loots.add(new ChanceItemFactory(0.8, new BulletItemFactory()));
        loots.add(new ChanceItemFactory(0.8, new BombItemFactory()));
        loots.add(new ChanceItemFactory(0.8, new BulletPlusItemFactory()));
        this.genLoot = new ChanceLootTable(loots);
    }

    private void changeDirection() {
        if(--this.direction_duration <= 0) {
            // change direction
            this.speedX *= -1;
            if(Math.abs(this.speedX) < 3) this.speedX = this.speedX > 0 ? 3 : -3;
            this.direction_duration = (int) (Math.random() * 50);
        }
    }

    @Override
    public void forward() {
        this.changeDirection();
        super.forward();
        if(locationY >= Main.WINDOW_HEIGHT) {
            this.vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        if(Math.random() < 0.3) return null;
        return super.shoot();
    }
}
