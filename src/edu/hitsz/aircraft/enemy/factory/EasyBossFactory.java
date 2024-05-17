package edu.hitsz.aircraft.enemy.factory;

import edu.hitsz.basic.Wrapper;

public class EasyBossFactory extends BossFactoryBase {
    public EasyBossFactory(Wrapper<Integer> scoreRef) {
        super(scoreRef);
    }

    @Override
    protected int nextHp() {
        System.out.println("this time");
        System.out.println(300);
        return 300;
    }

    @Override
    protected boolean checkGenerate() {
        System.out.println(score.get());
        if (score.get() - last_score < 300) return false;
        this.cooldown += 1;
        System.out.println(this.cooldown);
        if (this.cooldown < 3) return false;
        this.cooldown = 0;
        last_score = score.get();
        return true;
    }
}
