package com.example.ad4ma.bogyo;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

     class ConfigurationManager {

        private static Point screenSize;

        public static void Configure(Bitmap tileBitmap, Point screenSize)
        {
            ConfigurationManager.screenSize = screenSize;
        }



    public static Point getScreenSize() {
        return screenSize;
    }
}
