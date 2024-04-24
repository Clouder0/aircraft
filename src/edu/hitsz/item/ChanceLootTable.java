package edu.hitsz.item;

public class ChanceLootTable extends ChanceMultipleLootTable {
    public ChanceLootTable(Iterable<ItemFactoryInterface> items) {
        super(items, 1);
    }
}
