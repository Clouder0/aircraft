package edu.hitsz.aircraft;

public class ElitePlusEnemyLocationFactory implements LocationEnemyFactoryInterface {
    public ElitePlusEnemy genEnemy(int locationX, int locationY, int speedX, int speedY) {
        return new ElitePlusEnemy(locationX, locationY, speedX, speedY);
    }
}