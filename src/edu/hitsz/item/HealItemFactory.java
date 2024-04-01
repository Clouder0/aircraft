package edu.hitsz.item;

import edu.hitsz.item.HealItem;
import edu.hitsz.item.ItemFactoryInterface;

public class HealItemFactory implements ItemFactoryInterface {

    private final int healAmount;

    public HealItemFactory(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public HealItem genItem(int locationX, int locationY, int speedX, int speedY) {
        return new HealItem(locationX, locationY, speedX, speedY, this.healAmount);
    }
}
