package edu.hitsz.aircraft.enemy;

import edu.hitsz.item.ItemBase;

import java.util.List;

public interface EnemyLoot {
    List<ItemBase> genLoot(EnemyBase enemy);
}
