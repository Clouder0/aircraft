package edu.hitsz.item;

import edu.hitsz.item.ItemBase;
import edu.hitsz.item.ItemFactoryInterface;

import java.util.Random;

public class ChanceItemFactory implements ItemFactoryInterface {
    private final ItemFactoryInterface innerFactory;
    private final Random rand;
    private final double chance;

    public ChanceItemFactory(double chance, ItemFactoryInterface itemFactory) {
        this.chance = chance;
        this.innerFactory = itemFactory;
        this.rand = new Random();
    }

    public ItemBase genItem(int locationX, int locationY, int speedX, int speedY) {
        if (this.rand.nextDouble() < this.chance) {
            return this.innerFactory.genItem(locationX, locationY, speedX, speedY);
        }
        return null;
    }
}
