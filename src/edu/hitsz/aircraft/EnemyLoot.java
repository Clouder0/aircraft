package edu.hitsz.aircraft;

import edu.hitsz.item.ItemBase;

public interface EnemyLoot {
    public ItemBase genLoot(EnemyBase enemy);
}
