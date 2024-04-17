package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.item.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EliteEnemy extends EnemyBase {
    public EliteEnemy(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY, 60);
        List<ItemFactoryInterface> loots = new ArrayList<ItemFactoryInterface>();
        loots.add(new ChanceItemFactory(0.5, new HealItemFactory(10)));
        loots.add(new ChanceItemFactory(0.5, new BulletItemFactory()));
        loots.add(new ChanceItemFactory(0.5, new BombItemFactory()));
        this.genLoot = new ChanceLootTable(loots);
    }

    @Override
    public void forward() {
        super.forward();
        if (locationY >= Main.WINDOW_HEIGHT) {
            this.vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        if(Math.random() < 0.3) return null;
        // TODO: shoot for elite
        List<BaseBullet> res = new ArrayList<>();
        int x = this.getLocationX();
        final int power = 30;
        final int direction = 1;  // 1 向下，-1 向上
        final int shootNum = 1;
        int y = this.getLocationY() + direction * 2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction * 5;
        BaseBullet bullet;
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            /*
             * 子弹伤害
             */
            bullet = new EnemyBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }
}
