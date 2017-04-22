package com.example.ad4ma.bogyo;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

public class GameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Bitmap tileBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tile);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        ConfigurationManager.Configure(size, 5);
        CanvasView cv = new CanvasView(this);
        setContentView(cv);
    }


}