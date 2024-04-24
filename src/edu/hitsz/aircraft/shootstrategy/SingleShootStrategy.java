package edu.hitsz.aircraft.shootstrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.BulletFactoryInterface;

import java.util.ArrayList;
import java.util.List;

public class SingleShootStrategy implements ShootStrategyInterface {
    private final BulletFactoryInterface bullet;
    private final int direction;

    public SingleShootStrategy(BulletFactoryInterface genBullet, int direction) {
        this.bullet = genBullet;
        this.direction = direction;
    }

    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        ArrayList<BaseBullet> res = new ArrayList<>();
        res.add(this.bullet.genBullet(aircraft.getLocationX(), aircraft.getLocationY() + direction * 2, aircraft.getSpeedX(), aircraft.getSpeedY() + direction * 5));
        return res;
    }
}
