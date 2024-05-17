package edu.hitsz.item;

import edu.hitsz.application.GameBase;
import edu.hitsz.application.MusicManager;

public class HealItem extends ItemBase {
    public final int healAmount;

    public HealItem(int locationX, int locationY, int speedX, int speedY, int healAmount) {
        super(locationX,locationY,speedX,speedY);
        this.healAmount = healAmount;
    }

    public void use(GameBase gameBase) {
        gameBase.getHeroAircraft().increaseHp(this.healAmount);
        MusicManager.play("src/videos/get_supply.wav");
    }
}
