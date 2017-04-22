package com.example.ad4ma.bogyo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class HighScoreActivity extends AppCompatActivity {
    private final String PrefFileName = "Scores";
    private int best = 0;
    private int current = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_high_score);

        this.best = getBestScore();
        Random r = new Random();
        current = r.nextInt(100);
        Log.d("DEBUG current:", Integer.toString(this.current));
        Log.d("DEBUG best:", Integer.toString(this.best));
        if (this.best < current) {

            setBestScore(current);
            //Így a két érték ugyan az lesz, egyelőre így láthatja a felhasználó hogy megdöntötte az eddigi legjobbját
            this.best = current;
        }

        TextView currentScoreView = (TextView) findViewById(R.id.currentScore);
        currentScoreView.setText(Integer.toString(this.current));

        TextView bestScoreView = (TextView) findViewById(R.id.bestScore);
        bestScoreView.setText(Integer.toString(this.best));

    }

    public void onStartClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onMenuClick(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    private int getBestScore() {
        SharedPreferences settings = getSharedPreferences(this.PrefFileName, 0);
        return settings.getInt("BestScore", 0);
    }

    private void setBestScore(int score) {

        SharedPreferences settings = getSharedPreferences(this.PrefFileName, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("BestScore", score);
        editor.apply();
    }

}
