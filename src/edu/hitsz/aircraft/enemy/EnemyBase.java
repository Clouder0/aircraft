package edu.hitsz.aircraft.enemy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.shootstrategy.ShootStrategyInterface;
import edu.hitsz.item.ItemBase;

import java.util.List;

public abstract class EnemyBase extends AbstractAircraft {
    protected EnemyLoot genLoot;

    protected EnemyBase(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategyInterface shoot_strategy) {
        super(locationX, locationY, speedX, speedY, hp, shoot_strategy);
    }

    public List<ItemBase> genLoot() {
        if (this.genLoot != null) return this.genLoot.genLoot(this);
        return null;
    }
}
