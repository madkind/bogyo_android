package com.example.ad4ma.bogyo;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

class ConfigurationManager {

    private static int viewWidth;
    private static int viewHeight;

    private static int tileSpeed;
    private static int defaultTileSpeed;

    public static boolean boosted = false;

    public static long lastboosted = 0;
    public static long gameStarted;

    public static void Configure(int width, int height, int tileSpeed) {
       ConfigurationManager.viewWidth = width;
        ConfigurationManager.viewHeight = height;
        ConfigurationManager.tileSpeed = tileSpeed;
        ConfigurationManager.defaultTileSpeed = tileSpeed;
        ConfigurationManager.gameStarted = System.currentTimeMillis();

    }

    public static void setTileSpeed(int boost) {
        if( tileSpeed + boost > 0) {
            tileSpeed += boost;
            boosted = true;
            lastboosted = System.currentTimeMillis();
        }
    }

    public static void setDefaultTileSpeed() { tileSpeed = defaultTileSpeed; boosted = false; }


    public static void speedUp() {
        gameStarted = System.currentTimeMillis();
        defaultTileSpeed += 1;
        tileSpeed += 1;
    }


    public static int getViewHeight() {
       return viewHeight;
    }

    public static int getViewWidth() {
       return viewWidth;
    }

    public static int getTileSpeed() {
        return tileSpeed;
    }
}
