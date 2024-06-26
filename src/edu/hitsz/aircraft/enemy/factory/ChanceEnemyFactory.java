package edu.hitsz.aircraft.enemy.factory;

import edu.hitsz.aircraft.enemy.EnemyBase;

public class ChanceEnemyFactory implements EnemyFactoryInterface {
    private final double chance;
    private final EnemyFactoryInterface innerFactory;
    public ChanceEnemyFactory(double chance, EnemyFactoryInterface innerFactory) {
        this.chance = chance;
        this.innerFactory = innerFactory;
    }
    public EnemyBase genEnemy() {
        return Math.random() < this.chance ? this.innerFactory.genEnemy() : null;
    }
}
