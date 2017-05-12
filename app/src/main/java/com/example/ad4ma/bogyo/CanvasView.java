package com.example.ad4ma.bogyo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

public class CanvasView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gt;
    private GameManager gm;


    public CanvasView(Context context) {
        super(context);

        this.setFocusable(true);
        this.getHolder().addCallback(this);
    }

    @Override

    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
    }

    public void update() {
        gm.update();
        if(!gm.player.isAlive())
        {
            gt.setRunning(false);
            Intent intent = new Intent(super.getContext(), HighScoreActivity.class);
            Bundle b = new Bundle();
            //TODO nem jó a pont számolás valamiért. ki kell debugolni
            b.putInt("key", (int) ((System.nanoTime() - gt.startTime )/1000000));
            intent.putExtras(b);
            super.getContext().startActivity(intent);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLACK);
        for (int i = 0; i < gm.gameObjects.size(); i++) {
            gm.gameObjects.get(i).draw(canvas);
        }
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        ConfigurationManager.Configure(this.getWidth(), this.getHeight(), 5);
        this.gm = new GameManager(this.getContext());
        this.gt = new GameThread(this, holder);
        this.gt.setRunning(true);
        this.gt.start();
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        try {
            this.gt.setRunning(false);
        // Parent thread must wait until the end of GameThread.
        this.gt.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
