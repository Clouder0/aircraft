package edu.hitsz.aircraft.enemy.factory;

import edu.hitsz.aircraft.enemy.EliteEnemy;
import edu.hitsz.aircraft.enemy.LocationEnemyFactoryInterface;

public class EliteEnemyLocationFactory implements LocationEnemyFactoryInterface {
    public EliteEnemy genEnemy(int locationX , int locationY, int speedX, int speedY) {
        return new EliteEnemy(locationX,locationY,speedX,speedY);
    }
}
