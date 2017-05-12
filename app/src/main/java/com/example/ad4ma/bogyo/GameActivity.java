package com.example.ad4ma.bogyo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private CanvasView cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.Theme_AppCompat_NoActionBar);
         cv = new CanvasView(this);
        setContentView(cv);
    }
}
