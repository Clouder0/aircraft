package edu.hitsz.aircraft;

import edu.hitsz.item.HealItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {
    @Test
    void decreaseHp() {
        HeroAircraft hero = new HeroAircraft(0,0,0,0);
        int before = hero.getHp();
        System.out.println(hero.getHp());
        int decrease = (int)(Math.random() * 100);
        hero.decreaseHp(decrease);
        System.out.println(hero.getHp());
        assertEquals(hero.getHp(), before - decrease);
        hero.decreaseHp(100000000);
        System.out.println(hero.getHp());
        assertEquals(hero.getHp(),0);
    }
    @Test
    void increaseHp() {
        HeroAircraft hero = new HeroAircraft(0,0,0,0);
        int before = hero.getHp();
        System.out.println(hero.getHp());
        // test overflow
        hero.increaseHp(100);
        System.out.println(hero.getHp());
        assertEquals(hero.getHp(), before);
        hero.decreaseHp(100);
        System.out.println(hero.getHp());
        assertEquals(hero.getHp(), before - 100);
        int increase = (int)(Math.random() * 100);
        hero.increaseHp(increase);
        System.out.println(hero.getHp());
        assertEquals(hero.getHp(), before - 100 + increase);
    }

    @Test
    void crash() {
        HeroAircraft hero = new HeroAircraft(100,100,0,0);
        HealItem item = new HealItem(100, 100, 0,0,100);
        assert(hero.crash(item));
        HealItem item2 = new HealItem(200,200,0,0,0);
        assertFalse(hero.crash(item2));
    }
}
