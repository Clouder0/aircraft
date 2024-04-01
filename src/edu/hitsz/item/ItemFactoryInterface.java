package edu.hitsz.item;

import edu.hitsz.item.ItemBase;

public interface ItemFactoryInterface {
    ItemBase genItem(int locationX, int locationY, int speedX, int speedY);
}
