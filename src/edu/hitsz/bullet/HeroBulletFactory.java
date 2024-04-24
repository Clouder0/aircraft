package edu.hitsz.bullet;

public class HeroBulletFactory implements BulletFactoryInterface {
    public BaseBullet genBullet(int locationX, int locationY, int speedX, int speedY) {
        return new HeroBullet(locationX, locationY, speedX, speedY, 40);
    }
}
