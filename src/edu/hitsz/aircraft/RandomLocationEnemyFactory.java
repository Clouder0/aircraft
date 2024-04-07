package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import java.lang.reflect.Constructor;

public class RandomLocationEnemyFactory<T extends EnemyBase> implements  EnemyFactoryInterface {
    private final Class<T> enemyClass;
    public RandomLocationEnemyFactory(Class<T> enemyClass) {
        this.enemyClass = enemyClass;
    }
    public EnemyBase genEnemy() {
        try {
            Constructor<T> con = (this.enemyClass.getConstructor(int.class,int.class,int.class,int.class));
            return con.newInstance(
                    (int)(Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())), (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05), 0, 10);
        } catch(Exception ignored) {}
        return null;
    }
}
