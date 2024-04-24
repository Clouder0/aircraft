package edu.hitsz.aircraft.enemy;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class CommonEnemy extends EnemyBase {

    public CommonEnemy(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY, 30, null);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT) {
            vanish();
        }
    }
}


