package edu.hitsz.item;

public class BombItemFactory implements  ItemFactoryInterface {
    @Override
    public BombItem genItem(int locationX, int locationY, int speedX, int speedY) {
        return new BombItem(locationX, locationY, speedX, speedY);
    }
}
