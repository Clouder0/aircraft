package edu.hitsz.aircraft;

import edu.hitsz.item.ItemBase;

public abstract class EnemyBase extends AbstractAircraft {
    protected EnemyLoot genLoot;

    protected EnemyBase(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    public ItemBase genLoot() {
        if (this.genLoot != null) return this.genLoot.genLoot(this);
        return null;
    }
}
