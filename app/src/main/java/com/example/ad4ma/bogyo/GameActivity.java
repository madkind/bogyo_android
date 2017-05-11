package com.example.ad4ma.bogyo;

import android.graphics.Point;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

public class GameActivity extends AppCompatActivity {

    CanvasView cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        ConfigurationManager.Configure(size, 11);
         cv = new CanvasView(this);
        setContentView(cv);
    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cv.Pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cv.Resume();
    }




}
