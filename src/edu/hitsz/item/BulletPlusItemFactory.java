package edu.hitsz.item;

public class BulletPlusItemFactory implements ItemFactoryInterface {
    @Override
    public BulletPlusItem genItem(int locationX, int locationY, int speedX, int speedY) {
        return new BulletPlusItem(locationX, locationY, speedX, speedY);
    }
}