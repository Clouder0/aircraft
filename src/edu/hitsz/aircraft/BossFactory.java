package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.basic.Wrapper;

public class BossFactory implements EnemyFactoryInterface{
    private final Wrapper<Integer> score;
    private BossEnemy now_boss;

    protected int cooldown = 0;
    public BossFactory(Wrapper<Integer> scoreRef) {
        this.score = scoreRef;
    }

    public EnemyBase genEnemy() {
        System.out.println(score);
        if(score.get() < 100) return null;
        if(this.now_boss != null && this.now_boss.hp > 0) return null; // only generate when boss dies
        this.cooldown += 1;
        System.out.println(this.cooldown);
        if(this.cooldown < 3)
            return null;
        this.cooldown = 0;
        this.now_boss = new BossEnemy(Main.WINDOW_WIDTH / 2, Main.WINDOW_HEIGHT / 4);
        return this.now_boss;
    }
}
