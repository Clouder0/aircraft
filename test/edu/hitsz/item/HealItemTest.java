package edu.hitsz.item;

import edu.hitsz.aircraft.HeroAircraft;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealItemTest {

    @Test
    void use() {
        HeroAircraft hero = new HeroAircraft(0,0,0,0);
        hero.decreaseHp(100000000);
        assertEquals(hero.getHp(), 0);
        int heal_amount = (int)(Math.random() * 100);
        HealItem item = new HealItem(0,0,0,0,heal_amount);
        item.use(hero);
        assertEquals(hero.getHp(), heal_amount);
    }
}
