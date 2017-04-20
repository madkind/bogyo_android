package com.example.ad4ma.bogyo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);
    }

    public void onStartClick(View view) {
        Intent intent = new Intent(this, GameActvity.class);
        startActivity(intent);
    }
}
