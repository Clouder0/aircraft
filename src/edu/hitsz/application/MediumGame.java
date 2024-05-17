package edu.hitsz.application;

import edu.hitsz.aircraft.enemy.factory.*;

import java.awt.*;

public class MediumGame extends GameBase {
    public MediumGame(boolean music) {
        super(music);
    }

    @Override
    protected BossFactoryBase getBossFactory() {
        return new EasyBossFactory(this.score);
    }

    @Override
    protected void drawBackground(Graphics g) {
        g.drawImage(ImageManager.BACKGROUND_IMAGE2, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE2, 0, this.backGroundTop, null);
    }

    @Override
    protected int getDifficulty() {
        return 1;
    }
    @Override
    protected void registerEnemyFactories() {
        enemyFactories.add(new ChanceEnemyFactory(0.6, (new RandomLocationEnemyFactory<>(new CommonEnemyLocationFactory()))));
        enemyFactories.add(new ChanceEnemyFactory(0.3, (new RandomLocationEnemyFactory<>(new EliteEnemyLocationFactory()))));
        enemyFactories.add(new ChanceEnemyFactory(0.1, (new RandomLocationEnemyFactory<>(new ElitePlusEnemyLocationFactory()))));
        enemyFactories.add(getBossFactory());
    }

    @Override
    protected boolean checkNewEnemy() {
        return enemyAircrafts.size() < 5;
    }

    @Override
    protected void incrementDifficulty(){
        if(timeCount == 20) {
            System.out.println("Difficulty increased! more enemy eliteplus");
            enemyFactories.add(new ChanceEnemyFactory(0.1, (new RandomLocationEnemyFactory<>(new ElitePlusEnemyLocationFactory()))));
        } else if(timeCount == 40) {
            System.out.println("Difficulty increased again! more enemy elite");
            enemyFactories.add(new ChanceEnemyFactory(0.3, (new RandomLocationEnemyFactory<>(new EliteEnemyLocationFactory()))));
        }
    }
}
