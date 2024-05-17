package edu.hitsz.application;

import edu.hitsz.aircraft.enemy.factory.*;

import java.awt.*;

public class EasyGame extends GameBase {
    public EasyGame(boolean music) {
        super(music);
    }

    @Override
    protected BossFactoryBase getBossFactory() {
        return new EasyBossFactory(this.score);
    }

    @Override
    protected void drawBackground(Graphics g) {
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
    }

    @Override
    protected int getDifficulty() {
        return 0;
    }
    @Override
    protected void registerEnemyFactories() {
        enemyFactories.add(new ChanceEnemyFactory(0.4, (new RandomLocationEnemyFactory<>(new CommonEnemyLocationFactory()))));
        enemyFactories.add(new ChanceEnemyFactory(0.2, (new RandomLocationEnemyFactory<>(new EliteEnemyLocationFactory()))));
        enemyFactories.add(new ChanceEnemyFactory(0.1, (new RandomLocationEnemyFactory<>(new ElitePlusEnemyLocationFactory()))));
    }

    @Override
    protected boolean checkNewEnemy() {

        return enemyAircrafts.size() < 3;
    }
    @Override
    protected void incrementDifficulty(){}
}
