package edu.hitsz.bullet;

import edu.hitsz.item.BombObserverInterface;

public class EnemyBullet extends BaseBullet implements BombObserverInterface {
    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }
    public void onExplode() {
        vanish();
    }
}
