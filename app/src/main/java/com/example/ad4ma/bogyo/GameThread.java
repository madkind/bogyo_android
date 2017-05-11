package com.example.ad4ma.bogyo;

/*
  Created by ad4ma on 2017. 04. 19..
 */


import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.Random;

class GameThread extends Thread {

    private final CanvasView canvasView;
    private final SurfaceHolder surfaceHolder;
    private boolean running;
    long startTime;
    long endTime;

    public GameThread(CanvasView canvasView, SurfaceHolder surfaceHolder) {
        this.canvasView = canvasView;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        startTime = System.nanoTime();

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
            long now = System.nanoTime();
            long waitTime = (now - startTime) / 100000000;
            if (waitTime < 5) {
                waitTime = 5; // Millisecond.
            }

            System.out.print(" Wait Time=" + waitTime);

            try {
                sleep(waitTime);
            } catch (InterruptedException e) {
                System.out.print(e.getLocalizedMessage());
            }
            startTime = System.nanoTime();
            System.out.print(".");
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}