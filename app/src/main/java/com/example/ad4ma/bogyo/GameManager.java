package com.example.ad4ma.bogyo;

import java.util.ArrayList;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

public class GameManager {


    CanvasView cv;

    ArrayList<TileObject> tiles;

    public GameManager(){
        tiles = new ArrayList<TileObject>();
        //left top right bottom
        int tileCount = 5;

        int screenheight = ConfigurationManager.getScreenSize().y;
        int screenWidth = ConfigurationManager.getScreenSize().x;
        int iter = 0;

        while(tileCount>iter)
            tiles.add(new TileObject(0,iter++*(screenheight/tileCount),screenWidth,screenheight/30));

    }

    public void update() {
        for ( int i = 0; i< tiles.size();i++) {
            tiles.get(i).update();
        }
    }
}
