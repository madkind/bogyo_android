package com.example.ad4ma.bogyo;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

     class ConfigurationManager {
        private static  Bitmap tileBitmap;
        private static Point screenSize;

        public static void Configure(Bitmap tileBitmap, Point screenSize)
        {
            ConfigurationManager.tileBitmap = tileBitmap;
            ConfigurationManager.screenSize = screenSize;
        }

    public static Bitmap getTileBitmap() {
        return tileBitmap;
    }

    public static Point getScreenSize() {
        return screenSize;
    }
}
