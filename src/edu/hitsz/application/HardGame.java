package edu.hitsz.application;

import edu.hitsz.aircraft.enemy.factory.*;

import java.awt.*;

public class HardGame extends GameBase {
    public HardGame(boolean music) {
        super(music);
    }

    @Override
    protected BossFactoryBase getBossFactory() {
        return new HardBossFactory(this.score);
    }

    @Override
    protected void drawBackground(Graphics g) {
        g.drawImage(ImageManager.BACKGROUND_IMAGE3, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE3, 0, this.backGroundTop, null);
    }

    @Override
    protected int getDifficulty() {
        return 2;
    }

    @Override
    protected void registerEnemyFactories() {
        enemyFactories.add(new ChanceEnemyFactory(0.8, (new RandomLocationEnemyFactory<>(new CommonEnemyLocationFactory()))));
        enemyFactories.add(new ChanceEnemyFactory(0.4, (new RandomLocationEnemyFactory<>(new EliteEnemyLocationFactory()))));
        enemyFactories.add(new ChanceEnemyFactory(0.2, (new RandomLocationEnemyFactory<>(new ElitePlusEnemyLocationFactory()))));
        enemyFactories.add(getBossFactory());
    }
    @Override
    protected boolean checkNewEnemy() {
        return enemyAircrafts.size() < 10;
    }

    @Override
    protected void incrementDifficulty(){
        if(timeCount == 20) {
            System.out.println("Difficulty increased! more enemy eliteplus");
            enemyFactories.add(new ChanceEnemyFactory(0.1, (new RandomLocationEnemyFactory<>(new ElitePlusEnemyLocationFactory()))));
        } else if(timeCount == 40) {
            System.out.println("Difficulty increased again! more enemy elite");
            enemyFactories.add(new ChanceEnemyFactory(0.3, (new RandomLocationEnemyFactory<>(new EliteEnemyLocationFactory()))));
        } else if(timeCount == 60) {
            System.out.println("Difficulty increased again again! double boss!");
            enemyFactories.add(getBossFactory());
        }
    }
}
