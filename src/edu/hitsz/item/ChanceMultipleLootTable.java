package edu.hitsz.item;

import edu.hitsz.aircraft.EnemyBase;
import edu.hitsz.aircraft.EnemyLoot;

import java.util.ArrayList;
import java.util.List;

public class ChanceMultipleLootTable implements EnemyLoot {
    private final Iterable<ItemFactoryInterface> items;
    private final int max_drop;

    public ChanceMultipleLootTable(Iterable<ItemFactoryInterface> items, int max_drop) {
        this.items = items;
        this.max_drop = max_drop;
    }

    private int getShift(int radius) {
        return (int)((Math.random() - 0.5) * radius);
    }

    public List<ItemBase> genLoot(EnemyBase enemy) {
        List<ItemBase> res = new ArrayList<>();
        for (ItemFactoryInterface item : this.items) {
            ItemBase generated = item.genItem(enemy.getLocationX() + getShift(100), enemy.getLocationY() + getShift(150), enemy.getSpeedX(), enemy.getSpeedY());
            if(generated == null) continue;
            res.add(generated);
            if(res.size() >= this.max_drop) return res;
        }
        return res;
    }
}
