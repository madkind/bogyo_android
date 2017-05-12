package com.example.ad4ma.bogyo;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

class ConfigurationManager {

    private static int viewWidth;
    private static int viewHeight;

    private static int tileSpeed;

    public static void Configure(int width, int height, int tileSpeed) {
       ConfigurationManager.viewWidth = width;
        ConfigurationManager.viewHeight = height;
        ConfigurationManager.tileSpeed = tileSpeed;
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
