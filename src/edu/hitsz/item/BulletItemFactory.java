package edu.hitsz.item;

public class BulletItemFactory implements ItemFactoryInterface {
    @Override
    public BulletItem genItem(int locationX, int locationY, int speedX, int speedY) {
        return new BulletItem(locationX, locationY, speedX, speedY);
    }
}