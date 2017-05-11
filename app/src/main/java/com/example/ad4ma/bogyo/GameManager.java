package com.example.ad4ma.bogyo;

import java.util.ArrayList;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

class GameManager {
    final ArrayList<GameObject> gameObjects;
    PlayerObject player;
    GameManager() {
        gameObjects = new ArrayList<>();
        int tileCount = 5;
        int screenHeight = ConfigurationManager.getScreenHeight();
        int screenWidth = ConfigurationManager.getScreenWidth();
        int i = 0;

        while (tileCount > i)
            gameObjects.add(new TileObject(0, i++ * (screenHeight / tileCount), screenWidth, screenHeight / 30));

        player = new PlayerObject(500, 500, screenHeight / 20, this);
        gameObjects.add(player);
    }

    void update() {
        if(player.isAlive()){
            for (GameObject g : gameObjects) {
                g.update();
            }
        }
        else {
            // game over handle
        }
    }
}
