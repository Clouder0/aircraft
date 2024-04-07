package edu.hitsz.aircraft;

public class RandomEnemyFactory  implements EnemyFactoryInterface{
    private final double chance;
    private final EnemyFactoryInterface innerFactory;
    public RandomEnemyFactory(double chance, EnemyFactoryInterface innerFactory) {
        this.chance = chance;
        this.innerFactory = innerFactory;
    }
    public EnemyBase genEnemy() {
        return Math.random() < this.chance ? this.innerFactory.genEnemy() : null;
    }
}
