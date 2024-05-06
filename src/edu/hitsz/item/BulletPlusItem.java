package edu.hitsz.item;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.shootstrategy.HalfCircleShootStrategy;
import edu.hitsz.application.MusicManager;
import edu.hitsz.bullet.HeroBulletFactory;

public class BulletPlusItem extends ItemBase {
    public BulletPlusItem(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void use(HeroAircraft hero) {
        hero.enhanceShoot(100, new HalfCircleShootStrategy(new HeroBulletFactory(), -1));
        MusicManager.play("src/videos/get_supply.wav");
    }
}
