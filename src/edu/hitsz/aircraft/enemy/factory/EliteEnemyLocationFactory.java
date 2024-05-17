package edu.hitsz.aircraft.enemy.factory;

import edu.hitsz.aircraft.enemy.EliteEnemy;
import edu.hitsz.aircraft.enemy.LocationEnemyFactoryInterface;
import edu.hitsz.item.BombItem;

public class EliteEnemyLocationFactory implements LocationEnemyFactoryInterface<EliteEnemy> {
    public EliteEnemy genEnemy(int locationX, int locationY, int speedX, int speedY) {
        var ret = new EliteEnemy(locationX, locationY, speedX, speedY);
        BombItem.registerObserver(ret);
        return ret;
    }
}
