package edu.hitsz.item;

import edu.hitsz.aircraft.EnemyBase;
import edu.hitsz.aircraft.EnemyLoot;

public class ChanceLootTable implements EnemyLoot {
    private final Iterable<ItemFactoryInterface> items;

    public ChanceLootTable(Iterable<ItemFactoryInterface> items) {
        this.items = items;
    }

    public ItemBase genLoot(EnemyBase enemy) {
        System.out.println("chance loot table run.");
        for (ItemFactoryInterface item : this.items) {
            ItemBase generated = item.genItem(enemy.getLocationX(), enemy.getLocationY(), enemy.getSpeedX(), enemy.getSpeedY());
            if (generated != null) return generated;
        }
        return null;
    }
}
