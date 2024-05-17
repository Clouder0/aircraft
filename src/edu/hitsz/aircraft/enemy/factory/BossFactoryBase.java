package edu.hitsz.aircraft.enemy.factory;

import edu.hitsz.aircraft.enemy.BossEnemy;
import edu.hitsz.aircraft.enemy.EnemyBase;
import edu.hitsz.application.Main;
import edu.hitsz.basic.Wrapper;

public abstract class BossFactoryBase implements EnemyFactoryInterface {
    protected final Wrapper<Integer> score;
    protected int last_score, times;
    private BossEnemy now_boss;

    protected int cooldown = 0;

    public BossFactoryBase(Wrapper<Integer> scoreRef) {
        this.score = scoreRef;
        this.last_score = 0;
        this.times = 0;
    }

    protected abstract boolean checkGenerate();

    public final EnemyBase genEnemy() {
        if (!this.checkGenerate()) return null;
        if (this.now_boss != null && this.now_boss.getHp() > 0) return null; // only generate when boss dies

        this.now_boss = new BossEnemy(nextHp(), Main.WINDOW_WIDTH / 2, Main.WINDOW_HEIGHT / 4);
        times++;
        return this.now_boss;
    }

    protected abstract int nextHp();
}
