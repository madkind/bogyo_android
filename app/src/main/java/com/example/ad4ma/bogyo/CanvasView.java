package com.example.ad4ma.bogyo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

public class CanvasView extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gt;
    GameManager gm;


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
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawColor(Color.BLACK);
       for ( int i = 0; i< gm.gameObjects.size()-1;i++){
           gm.gameObjects.get(i).draw(canvas);
        }
        if (!gm.player.collidionWithTile(gm.gameObjects)){
            gm.player.draw(canvas);
        }
    }
    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.gm = new GameManager();
        this.gt = new GameThread(this,holder);
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
        boolean retry= true;
        while(retry) {
            try {
                this.gt.setRunning(false);
                // Parent thread must wait until the end of GameThread.
                this.gt.join();
            }catch(InterruptedException e)  {
                e.printStackTrace();
            }
            retry= true;
        }
    }

}
