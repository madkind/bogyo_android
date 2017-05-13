package com.example.ad4ma.bogyo;

/*
  Created by ad4ma on 2017. 04. 19..
 */


import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

class GameThread extends Thread {

    private final CanvasView canvasView;
    private final SurfaceHolder surfaceHolder;
    private boolean running;

    public GameThread(CanvasView canvasView, SurfaceHolder surfaceHolder) {
        this.canvasView = canvasView;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        while (running) {
            Canvas canvas = null;
            try {

                canvas = this.surfaceHolder.lockCanvas();

                synchronized (canvas) {
                    this.canvasView.update();
                    this.canvasView.draw(canvas);
                }
            } catch (Exception e) {
                System.out.print(e.getLocalizedMessage());
            } finally {
                if (canvas != null) {
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            long now = System.currentTimeMillis();
            long waitTime = (now - startTime) / 100;
            if (waitTime < 5) {
                waitTime = 5; // Millisecond.
            }
            Log.d("Wait Time=", Long.toString(waitTime));

            try {
                sleep(waitTime);
            } catch (InterruptedException e) {
                System.out.print(e.getLocalizedMessage());
            }
            startTime = System.currentTimeMillis();
            System.out.print(".");
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}