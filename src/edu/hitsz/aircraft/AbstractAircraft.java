package edu.hitsz.aircraft;

import edu.hitsz.aircraft.shootstrategy.ShootStrategyInterface;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;
    protected ShootStrategyInterface shoot_strategy;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategyInterface shoot_strategy) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
        this.shoot_strategy = shoot_strategy;
    }

    public void decreaseHp(int decrease) {
        hp -= decrease;
        if (hp <= 0) {
            hp = 0;
            vanish();
        }
    }

    public void increaseHp(int increase) {
        hp += increase;
        if (hp > maxHp) hp = maxHp;
    }

    public int getHp() {
        return hp;
    }


    /**
     * 飞机射击方法，可射击对象必须实现
     *
     * @return 可射击对象需实现，返回子弹
     * 非可射击对象空实现，返回null
     */
    public List<BaseBullet> shoot() {
        if (this.shoot_strategy == null) return null;
        return this.shoot_strategy.shoot(this);
    }

}


