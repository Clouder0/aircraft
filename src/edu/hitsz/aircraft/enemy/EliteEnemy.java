package edu.hitsz.aircraft.enemy;

import edu.hitsz.aircraft.shootstrategy.SingleShootStrategy;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.EnemyBulletFactory;
import edu.hitsz.item.*;

import java.util.ArrayList;
import java.util.List;

public class EliteEnemy extends EnemyBase implements BombObserverInterface{
    public EliteEnemy(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY, 60, new SingleShootStrategy(new EnemyBulletFactory(), 1));
        List<ItemFactoryInterface> loots = new ArrayList<ItemFactoryInterface>();
        loots.add(new ChanceItemFactory(0.5, new HealItemFactory(10)));
        loots.add(new ChanceItemFactory(0.5, new BulletItemFactory()));
        loots.add(new ChanceItemFactory(0.5, new BombItemFactory()));
        loots.add(new ChanceItemFactory(0.5, new BulletPlusItemFactory()));
        this.genLoot = new ChanceLootTable(loots);
    }

    @Override
    public void forward() {
        super.forward();
        if (locationY >= Main.WINDOW_HEIGHT) {
            this.vanish();
        }
    }

    public void onExplode() {
        decreaseHp(1000);
    }
}
