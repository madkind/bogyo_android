package com.example.ad4ma.bogyo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class HighScoreActivity extends AppCompatActivity {
    private final String PrefFileName = "Scores";
    private long best = 0;
    private long current = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_high_score);

        this.best = getBestScore();
        Bundle b = getIntent().getExtras();
        if(b != null)
            current = b.getLong("score");

        Log.d("DEBUG current:", Long.toString(this.current));
        Log.d("DEBUG best:", Long.toString(this.best));
        if (this.best < current) {

            setBestScore(current);
            //Így a két érték ugyan az lesz, egyelőre így láthatja a felhasználó hogy megdöntötte az eddigi legjobbját
            this.best = current;
        }

        TextView currentScoreView = (TextView) findViewById(R.id.currentScore);
        currentScoreView.setText(Long.toString(this.current));

        TextView bestScoreView = (TextView) findViewById(R.id.bestScore);
        bestScoreView.setText(Long.toString(this.best));

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

    private void setBestScore(long score) {

        SharedPreferences settings = getSharedPreferences(this.PrefFileName, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong("BestScore", score);
        editor.apply();
    }

    protected void onPause() {
        super.onPause();
    }
}
