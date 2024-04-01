package edu.hitsz.item;

import edu.hitsz.aircraft.HeroAircraft;

public class BombItem extends ItemBase {

    protected BombItem(int locationX, int locationY, int speedX, int speedY) {
        super(locationX,locationY,speedX,speedY);
    }

    public void use(HeroAircraft hero) {
        System.out.println("Bomb used.");
    }
}
