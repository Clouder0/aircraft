package edu.hitsz.aircraft;

public interface LocationEnemyFactoryInterface {
    EnemyBase genEnemy(int locationX, int locationY, int speedX, int speedY);
}
