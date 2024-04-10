package edu.hitsz.aircraft;

public class CommonEnemyLocationFactory implements  LocationEnemyFactoryInterface {
    public CommonEnemy genEnemy(int locationX,int locationY,int speedX,int speedY) {
        return new CommonEnemy(locationX,locationY,speedX,speedY);
    }
}
