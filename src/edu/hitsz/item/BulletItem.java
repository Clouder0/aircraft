package edu.hitsz.item;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.shootstrategy.SectorShootStrategy;
import edu.hitsz.application.TimerManager;
import edu.hitsz.bullet.HeroBulletFactory;

public class BulletItem extends ItemBase {
    protected BulletItem(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void use(HeroAircraft hero) {
        System.out.println("Bullet used.");
        hero.enhanceShoot(100, new SectorShootStrategy(new HeroBulletFactory(), -1));
    }
}
