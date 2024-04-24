package edu.hitsz.aircraft.enemy;

public interface LocationEnemyFactoryInterface<T extends EnemyBase> {
    T genEnemy(int locationX, int locationY, int speedX, int speedY);
}
