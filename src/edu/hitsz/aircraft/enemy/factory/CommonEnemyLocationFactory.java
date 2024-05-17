package edu.hitsz.aircraft.enemy.factory;

import edu.hitsz.aircraft.enemy.CommonEnemy;
import edu.hitsz.aircraft.enemy.LocationEnemyFactoryInterface;
import edu.hitsz.item.BombItem;

public class CommonEnemyLocationFactory implements LocationEnemyFactoryInterface<CommonEnemy> {
    public CommonEnemy genEnemy(int locationX, int locationY, int speedX, int speedY) {
        var ret = new CommonEnemy(locationX,locationY,speedX,speedY);
        BombItem.registerObserver(ret);
        return ret;
    }
}
