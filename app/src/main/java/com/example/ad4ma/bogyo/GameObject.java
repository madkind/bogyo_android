package com.example.ad4ma.bogyo;

import android.graphics.Canvas;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

public abstract class GameObject {

    protected int width;
    protected int height;
    protected int x;
    protected int y;

    public GameObject(int x, int y, int width, int height)  {
        this.x= x;
        this.y= y;
        this.width = width;
        this.height= height;
    }

    public int getX()  {
        return this.x;
    }

    public int getY()  {
        return this.y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public  void modY(int i){
        y+=i;
    }

    public void setY(int i)
    {
        y = i;
    }

    public abstract void draw(Canvas canvas);
    public abstract void update();

    public void modX(int speed) {
        this.x+=speed;
    }
}
