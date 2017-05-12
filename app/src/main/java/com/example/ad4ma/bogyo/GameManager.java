package com.example.ad4ma.bogyo;

import android.content.Context;

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
    }


    private void addBooster(int posX, int posY, int type) {
        gameObjects.add(new BoostObject(posX, posY, screenHeight / 20, type));
    }

    void update() {
        if(player.isAlive()) {
            for (GameObject g : gameObjects) {
                g.update();
                if (g instanceof TileObject && g.y == screenHeight && r.nextInt(10) == 1) {
                    addBooster(r.nextInt(screenWidth), screenHeight - g.getHeight(), r.nextInt(2));
                }
                if (g instanceof BoostObject && g.y < 0) {
                    this.gameObjects.remove(g);
                }
            }
        }
    }
}
