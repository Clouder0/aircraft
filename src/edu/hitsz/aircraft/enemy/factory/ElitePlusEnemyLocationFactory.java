package edu.hitsz.aircraft.enemy.factory;

import edu.hitsz.aircraft.enemy.EliteEnemy;
import edu.hitsz.aircraft.enemy.ElitePlusEnemy;
import edu.hitsz.aircraft.enemy.LocationEnemyFactoryInterface;
import edu.hitsz.item.BombItem;

public class ElitePlusEnemyLocationFactory implements LocationEnemyFactoryInterface<ElitePlusEnemy> {
    public ElitePlusEnemy genEnemy(int locationX, int locationY, int speedX, int speedY) {
        var ret = new ElitePlusEnemy(locationX, locationY, speedX, speedY);
        BombItem.registerObserver(ret);
        return ret;
    }

}