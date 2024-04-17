package edu.hitsz.item;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.HeroSingleton;
import edu.hitsz.item.ItemBase;

public class HealItem extends ItemBase {
    public final int healAmount;

    public HealItem(int locationX, int locationY, int speedX, int speedY, int healAmount) {
        super(locationX,locationY,speedX,speedY);
        this.healAmount = healAmount;
    }

    public void use(HeroAircraft hero) {
        hero.increaseHp(this.healAmount);
    }
}
