package edu.hitsz.aircraft.shootstrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.MusicManager;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.BulletFactoryInterface;
import edu.hitsz.bullet.EnemyBullet;

import java.util.ArrayList;
import java.util.List;

public class SectorShootStrategy implements ShootStrategyInterface {
    private final BulletFactoryInterface bullet;
    private final int direction;

    public SectorShootStrategy(BulletFactoryInterface genBullet, int direction) {
        this.bullet = genBullet;
        this.direction = direction;
    }

    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        MusicManager.play("src/videos/bullet.wav");
        MusicManager.play("src/videos/bullet.wav");
        MusicManager.play("src/videos/bullet.wav");
        ArrayList<BaseBullet> res = new ArrayList<>();
        res.add(bullet.genBullet(aircraft.getLocationX() - 10 * direction, aircraft.getLocationY(), aircraft.getSpeedX() - 2 * direction, aircraft.getSpeedY() + 3 * direction));
        res.add(bullet.genBullet(aircraft.getLocationX(), aircraft.getLocationY(), aircraft.getSpeedX(), aircraft.getSpeedY() + 3 * direction));
        res.add(bullet.genBullet(aircraft.getLocationX() + 10 * direction, aircraft.getLocationY(), aircraft.getSpeedX() + 2 * direction, aircraft.getSpeedY() + 3 * direction));
        return res;
    }
}