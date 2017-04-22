package com.example.ad4ma.bogyo;

import android.graphics.Point;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

     class ConfigurationManager {

        private static Point screenSize;

    private static int tileSpeed;

        public static void Configure(Point screenSize, int tileSpeed)
        {
            ConfigurationManager.screenSize = screenSize;
            ConfigurationManager.tileSpeed = tileSpeed;
        }

    public static Point getScreenSize() {
        return screenSize;
    }
    public static int getTileSpeed() { return tileSpeed; }
}
