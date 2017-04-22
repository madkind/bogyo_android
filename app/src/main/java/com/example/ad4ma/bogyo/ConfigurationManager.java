package com.example.ad4ma.bogyo;

import android.graphics.Point;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

class ConfigurationManager {

    private static Point screenSize;

    private static int tileSpeed;

    public static void Configure(Point screenSize, @SuppressWarnings("SameParameterValue") int tileSpeed) {
        ConfigurationManager.screenSize = screenSize;
        ConfigurationManager.tileSpeed = tileSpeed;
    }


    public static int getScreenHeight() {
        return screenSize.y;
    }

    public static int getScreenWidth() {
        return screenSize.x;
    }

    public static int getTileSpeed() {
        return tileSpeed;
    }
}
