package com.example.ad4ma.bogyo;

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

    public  void Modify_Y(int i){
        y+=i;
    }

    public void setY(int i)
    {
        y = i;
    }
}
