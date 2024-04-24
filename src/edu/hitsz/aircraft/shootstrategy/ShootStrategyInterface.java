package edu.hitsz.aircraft.shootstrategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public interface ShootStrategyInterface {
    List<BaseBullet> shoot(AbstractAircraft sender);
}
