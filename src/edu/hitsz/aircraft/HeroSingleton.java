package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class HeroSingleton {
    private static final HeroAircraft hero = new HeroAircraft(Main.WINDOW_WIDTH / 2, Main.WINDOW_HEIGHT - ImageManager.CLASSNAME_IMAGE_MAP.get(HeroAircraft.class.getName()).getHeight(), 0, 0);

    public static HeroAircraft getInstance() {
        return hero;
    }
}
