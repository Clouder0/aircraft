package edu.hitsz.application;


import edu.hitsz.aircraft.*;
import edu.hitsz.aircraft.enemy.BossEnemy;
import edu.hitsz.aircraft.enemy.CommonEnemy;
import edu.hitsz.aircraft.enemy.EliteEnemy;
import edu.hitsz.aircraft.enemy.ElitePlusEnemy;
import edu.hitsz.item.BombItem;
import edu.hitsz.item.BulletItem;
import edu.hitsz.item.BulletPlusItem;
import edu.hitsz.item.HealItem;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author hitsz
 */
public class ImageManager {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    public static final Map<String, BufferedImage> CLASSNAME_IMAGE_MAP = new HashMap<>();

    public static BufferedImage BACKGROUND_IMAGE;
    public static BufferedImage HERO_IMAGE;
    public static BufferedImage MOB_ENEMY_IMAGE;

    public static <T> void load(Class<T> c, String img_name) throws IOException {
        CLASSNAME_IMAGE_MAP.put(c.getName(), ImageIO.read(new FileInputStream("src/images/" + img_name)));
    }

    static {
        try {

            BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg.jpg"));

            load(HeroAircraft.class, "hero.png");
            load(CommonEnemy.class, "mob.png");
            load(HeroBullet.class, "bullet_hero.png");
            load(EnemyBullet.class, "bullet_enemy.png");
            load(EliteEnemy.class, "elite.png");
            load(BossEnemy.class, "boss.png");
            load(ElitePlusEnemy.class, "elitePlus.png");
            load(HealItem.class, "prop_blood.png");
            load(BombItem.class, "prop_bomb.png");
            load(BulletItem.class, "prop_bullet.png");
            load(BulletPlusItem.class, "prop_bulletPlus.png");

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static BufferedImage get(String className) {
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    public static BufferedImage get(Object obj) {
        if (obj == null) {
            return null;
        }
        return get(obj.getClass().getName());
    }

}
