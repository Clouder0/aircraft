package edu.hitsz.bullet;

public class EnemyBulletFactory implements  BulletFactoryInterface {
    public BaseBullet genBullet(int locationX, int locationY, int speedX, int speedY) {
        return new EnemyBullet(locationX,locationY,speedX,speedY, 20);
    }
}
