package edu.hitsz.item;

import edu.hitsz.aircraft.EnemyBase;
import edu.hitsz.aircraft.EnemyLoot;

import java.util.ArrayList;
import java.util.List;

public class ChanceLootTable extends ChanceMultipleLootTable {
    public ChanceLootTable(Iterable<ItemFactoryInterface> items) {
        super(items, 1);
    }
}
