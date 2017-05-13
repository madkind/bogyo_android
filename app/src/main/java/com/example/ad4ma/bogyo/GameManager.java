package com.example.ad4ma.bogyo;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

class GameManager {
    final ArrayList<GameObject> gameObjects;
    private final int screenHeight = ConfigurationManager.getViewHeight();
    private final int screenWidth = ConfigurationManager.getViewWidth();
    private final Random r = new Random();
    final PlayerObject player;
    private final Context context;
    public final long startTime;

    GameManager(Context context) {
        gameObjects = new ArrayList<>();
        int tileCount = 5;
        this.context = context;
        //int screenHeight = ConfigurationManager.getViewHeight();
        //int screenWidth = ConfigurationManager.getViewWidth();
        int i = 0;

        while (tileCount > i)
            gameObjects.add(new TileObject(0, i++ * (screenHeight / tileCount), screenWidth, screenHeight / 30));

        player = new PlayerObject(500, 500, screenHeight / 20, this, context);
        gameObjects.add(player);
        startTime = System.currentTimeMillis();
    }


    private void addBooster(int posX, int posY, int type) {
        gameObjects.add(new BoostObject(posX, posY, screenHeight / 20, type));
    }

    void update() {
        if(player.isAlive()) {
            long now = System.currentTimeMillis();
            if(ConfigurationManager.boosted && (now - ConfigurationManager.lastboosted) > 10000 )
            {
                ConfigurationManager.setDefaultTileSpeed();
            }
            if((now - ConfigurationManager.gameStarted)> 25000 ) {
                Log.d("levelUp", "levelup");
                ConfigurationManager.speedUp();
            }
            for (GameObject g : gameObjects) {
                g.update();
                if (g instanceof TileObject && g.y == screenHeight && r.nextInt(10) == 1) {
                    addBooster(r.nextInt(screenWidth), screenHeight - g.getHeight(), r.nextInt(2));
                }
                if (g instanceof BoostObject) {

                    if(player.collisionWithBooster(g))
                    {
                        int boost = ((BoostObject)g).boostValue;
                        ConfigurationManager.setTileSpeed(boost);
                        this.gameObjects.remove(g);

                    }
                    else if (g.y < 0) {
                        this.gameObjects.remove(g);
                    }
                }
            }
        }
    }
}
