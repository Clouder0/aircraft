package edu.hitsz.item;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.shootstrategy.HalfCircleShootStrategy;
import edu.hitsz.application.MusicHelper;
import edu.hitsz.application.MusicManager;
import edu.hitsz.bullet.HeroBulletFactory;

public class BombItem extends ItemBase {

    protected BombItem(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void use(HeroAircraft hero) {
        System.out.println("Bomb used.");
        hero.enhanceShoot(100, new HalfCircleShootStrategy(new HeroBulletFactory(), -1));
        MusicManager.play("src/videos/bomb_explosion.wav");
    }
}
