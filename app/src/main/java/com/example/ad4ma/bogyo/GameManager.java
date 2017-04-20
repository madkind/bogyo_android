package com.example.ad4ma.bogyo;

import java.util.ArrayList;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

public class GameManager {


    CanvasView cv;

    ArrayList<GameObject> gameObjects;
    PlayerObject player;

    public GameManager(){
        gameObjects = new ArrayList<>();
        //left top right bottom
        int tileCount = 5;

        int screenHeight = ConfigurationManager.getScreenSize().y;
        int screenWidth = ConfigurationManager.getScreenSize().x;
        int iter = 0;

        while(tileCount>iter)
            gameObjects.add(new TileObject(0,iter++*(screenHeight/tileCount),screenWidth,screenHeight/30));

        player =  new PlayerObject(500,500,200,200);
        gameObjects.add(player);
    }

    public void update() {
        for ( int i = 0; i< gameObjects.size();i++) {
            gameObjects.get(i).update();
        }
    }
}
