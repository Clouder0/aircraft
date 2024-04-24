package edu.hitsz.aircraft.enemy.factory;

import edu.hitsz.aircraft.enemy.EnemyBase;
import edu.hitsz.aircraft.enemy.LocationEnemyFactoryInterface;
import edu.hitsz.aircraft.enemy.factory.EnemyFactoryInterface;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class RandomLocationEnemyFactory<T extends EnemyBase> implements EnemyFactoryInterface {
    private final LocationEnemyFactoryInterface<T> innerFactory;

    public RandomLocationEnemyFactory(LocationEnemyFactoryInterface<T> innerFactory) {
        this.innerFactory = innerFactory;
    }

    public EnemyBase genEnemy() {
        return this.innerFactory.genEnemy(
                (int) (Math.random() * Main.WINDOW_WIDTH),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05), 0, 10);
    }
}
