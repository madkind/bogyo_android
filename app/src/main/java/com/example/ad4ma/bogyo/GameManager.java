package com.example.ad4ma.bogyo;

import java.util.ArrayList;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

class GameManager {
    CanvasView cv;
    ArrayList<GameObject> gameObjects;
    private PlayerObject player;

    GameManager(){
        gameObjects = new ArrayList<>();
        int tileCount = 5;
        int screenHeight = ConfigurationManager.getScreenHeight();
        int screenWidth = ConfigurationManager.getScreenWidth();
        int iter = 0;

        while(tileCount>iter)
            gameObjects.add(new TileObject(0,iter++*(screenHeight/tileCount),screenWidth,screenHeight/30));

        player =  new PlayerObject(500,500,screenHeight/20,this);
        gameObjects.add(player);
    }

    void update() {
        for (GameObject g : gameObjects) {
                g.update();
        }
    }
}
