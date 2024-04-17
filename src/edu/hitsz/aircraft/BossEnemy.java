package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.item.*;

import java.util.ArrayList;
import java.util.List;

public class BossEnemy extends EnemyBase {

    private int direction_duration = 0;

    BossEnemy(int locationX, int locationY) {
        super(locationX, locationY, 4, 0, 300);
        List<ItemFactoryInterface> loots = new ArrayList<>();
        loots.add(new ChanceItemFactory(0.9, new HealItemFactory(10)));
        loots.add(new ChanceItemFactory(0.9, new BulletItemFactory()));
        loots.add(new ChanceItemFactory(0.9, new BombItemFactory()));
        this.genLoot = new ChanceMultipleLootTable(loots, 3);
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
        List<BaseBullet> res = new ArrayList<>();
        final double radius = 150;
        // 射出一个半圆
        for(int theta = 0; theta <= 180; theta += 15) {
            double d = (double) theta / 180 * 3.1415926;
            int bullet_x = (int)(this.locationX + Math.cos(d) * radius);
            int bullet_y = (int)(this.locationY + Math.sin(d) * radius);
            res.add(new EnemyBullet(bullet_x,bullet_y,(int)(Math.cos(d) * 5) ,this.speedY + 10 + (int)(Math.sin(d) * 5),40));
        }
        return res;
    }
}
