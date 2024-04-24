package edu.hitsz.aircraft.enemy.factory;

import edu.hitsz.aircraft.enemy.ElitePlusEnemy;
import edu.hitsz.aircraft.enemy.LocationEnemyFactoryInterface;

public class ElitePlusEnemyLocationFactory implements LocationEnemyFactoryInterface {
    public ElitePlusEnemy genEnemy(int locationX, int locationY, int speedX, int speedY) {
        return new ElitePlusEnemy(locationX, locationY, speedX, speedY);
    }
}