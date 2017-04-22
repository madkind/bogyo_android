package com.example.ad4ma.bogyo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

public class TileObject extends GameObject{

    Paint p;
    int gapStart;
    int gapEnd;
    static Random rand = new Random();

    public TileObject(int x, int y, int width, int height) {
        super( x, y, width, height);
        p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLUE);

        Random rand = new Random();
        gapStart = rand.nextInt(width)-(width/4);
        gapEnd = gapStart+width/4;
    }

    public void update()  {
        this.modY(-ConfigurationManager.getTileSpeed());

        if (y < 0)
            setY(ConfigurationManager.getScreenSize().y);
    }
    public void draw(Canvas canvas)  {
        Rectangle left = getLeftRec();
        canvas.save();
        canvas.translate(left.getX(),left.getY());
        canvas.drawRect(0,0,left.getWidth(),left.getHeight(),p);
        canvas.restore();

        Rectangle right = getRightRec();
        canvas.save();
        canvas.translate(right.getX(),getY());
        canvas.drawRect(0,0,right.getWidth(),right.getHeight(),p);
        canvas.restore();
    }

    public Rectangle getLeftRec() {
        return new Rectangle(getX(),getY(),gapStart,getHeight());
    }

    public Rectangle getRightRec() {
        return new Rectangle(getX()+gapEnd,getY(),getWidth()-gapStart,getHeight());
    }

}
