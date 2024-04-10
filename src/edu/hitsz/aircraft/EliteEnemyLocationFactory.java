package edu.hitsz.aircraft;

public class EliteEnemyLocationFactory implements LocationEnemyFactoryInterface {
    public EliteEnemy genEnemy(int locationX ,int locationY,int speedX,int speedY) {
        return new EliteEnemy(locationX,locationY,speedX,speedY);
    }
}
