package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class RandomLocationEnemyFactory<T extends EnemyBase> implements EnemyFactoryInterface {
    private final LocationEnemyFactoryInterface innerFactory;

    public RandomLocationEnemyFactory(LocationEnemyFactoryInterface innerFactory) {
        this.innerFactory = innerFactory;
    }

    public EnemyBase genEnemy() {
        return this.innerFactory.genEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05), 0, 10);
    }
}
