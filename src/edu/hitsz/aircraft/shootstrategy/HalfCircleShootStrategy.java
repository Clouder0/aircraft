package edu.hitsz.aircraft.shootstrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.BulletFactoryInterface;
import edu.hitsz.bullet.EnemyBullet;

import java.util.ArrayList;
import java.util.List;

public class HalfCircleShootStrategy implements ShootStrategyInterface {
    private final BulletFactoryInterface bullet;
    private final int direction;

    public HalfCircleShootStrategy(BulletFactoryInterface genBullet, int direction) {
        this.bullet = genBullet;
        this.direction = direction;
    }

    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        ArrayList<BaseBullet> res = new ArrayList<>();
        final double radius = 150;
        // 射出一个半圆
        for (int theta = 0; theta <= 180; theta += 15) {
            double d = (double) theta / 180 * 3.1415926;
            int bullet_x = (int) (aircraft.getLocationX() + Math.cos(d) * radius * direction);
            int bullet_y = (int) (aircraft.getLocationY() + Math.sin(d) * radius * direction);
            res.add(bullet.genBullet(bullet_x, bullet_y, (int) (Math.cos(d) * 5) * direction, aircraft.getSpeedY() + (10 + (int) (Math.sin(d) * 5)) * direction));
        }
        return res;
    }
}
