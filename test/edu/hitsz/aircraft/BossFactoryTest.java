package edu.hitsz.aircraft;

import edu.hitsz.aircraft.enemy.BossEnemy;
import edu.hitsz.aircraft.enemy.factory.BossFactory;
import edu.hitsz.basic.Wrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BossFactoryTest {


    @Test
    void genEnemy() {
        Wrapper<Integer> scoreRef = new Wrapper<>(0);
        // test threshold
        BossFactory bf = new BossFactory(scoreRef);
        // score < 100, return null
        assertNull(bf.genEnemy());
        scoreRef.set(110);
        // cooldown
        assertNull(bf.genEnemy());
        assertNull(bf.genEnemy());
        BossEnemy boss = (BossEnemy) bf.genEnemy();
        assertNotNull(boss);
        assertNull(bf.genEnemy()); // boss alive, don't generate
        assertNull(bf.genEnemy());
        boss.decreaseHp(1000); // boss die
        // cooldown
        assertNull(bf.genEnemy());
        assertNull(bf.genEnemy());
        assertNotNull(bf.genEnemy());
    }
}