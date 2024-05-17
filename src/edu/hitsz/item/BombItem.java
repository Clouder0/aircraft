package edu.hitsz.item;

import edu.hitsz.application.GameBase;
import edu.hitsz.application.MusicManager;

import java.util.ArrayList;
import java.util.List;

public class BombItem extends ItemBase {

    private static final List<BombObserverInterface> observers = new ArrayList<>();
    public static void registerObserver(BombObserverInterface ob) {
        observers.add(ob);
    }
    protected BombItem(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void use(GameBase gameBase) {
        System.out.println("Bomb used.");
        observers.forEach(BombObserverInterface::onExplode);
        MusicManager.play("src/videos/bomb_explosion.wav");
    }
}
