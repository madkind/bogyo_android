package com.example.ad4ma.bogyo;

import android.graphics.Canvas;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

public abstract class GameObject {

    final int width;
    private final int height;
    int y;
    private int x;

    GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    void setY(int i) {
        y = i;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    void modY(int i) {
        y += i;
    }

    void modX(int i) {
        this.x += i;
    }
    public void setX(int x) {
        this.x = x;
    }

    public abstract void draw(Canvas canvas);

    public abstract void update();
}
