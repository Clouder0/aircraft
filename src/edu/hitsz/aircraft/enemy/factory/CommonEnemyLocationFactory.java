package edu.hitsz.aircraft.enemy.factory;

import edu.hitsz.aircraft.enemy.CommonEnemy;
import edu.hitsz.aircraft.enemy.LocationEnemyFactoryInterface;

public class CommonEnemyLocationFactory implements LocationEnemyFactoryInterface {
    public CommonEnemy genEnemy(int locationX, int locationY, int speedX, int speedY) {
        return new CommonEnemy(locationX,locationY,speedX,speedY);
    }
}
