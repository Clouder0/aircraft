package edu.hitsz.item;

import edu.hitsz.aircraft.shootstrategy.SectorShootStrategy;
import edu.hitsz.application.GameBase;
import edu.hitsz.application.MusicManager;
import edu.hitsz.bullet.HeroBulletFactory;

public class BulletItem extends ItemBase {
    protected BulletItem(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void use(GameBase gameBase) {
        System.out.println("Bullet used.");
        gameBase.getHeroAircraft().enhanceShoot(100, new SectorShootStrategy(new HeroBulletFactory(), -1));
        MusicManager.play("src/videos/get_supply.wav");
    }
}
