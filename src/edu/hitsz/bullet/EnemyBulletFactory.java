package edu.hitsz.bullet;

import edu.hitsz.item.BombItem;

public class EnemyBulletFactory implements BulletFactoryInterface {
    public BaseBullet genBullet(int locationX, int locationY, int speedX, int speedY) {
        var ret = new EnemyBullet(locationX, locationY, speedX, speedY, 20);
        BombItem.registerObserver(ret);
        return ret;
    }
}
