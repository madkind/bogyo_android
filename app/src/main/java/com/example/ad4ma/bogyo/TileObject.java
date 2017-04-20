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
        gapStart = rand.nextInt(width)-(width/10);
        gapEnd = gapStart+width/10;
    }

    public void update()  {
        this.modY(+3);

        if (y >ConfigurationManager.getScreenSize().y) {
            setY(0);
        }
    }
    public void draw(Canvas canvas)  {
        canvas.save();
        canvas.translate(getX(),getY());
        canvas.drawRect(0,0,gapStart,getHeight(),p);
        canvas.restore();

        canvas.save();
        canvas.translate(getX()+gapEnd,getY());
        canvas.drawRect(0,0,getWidth()-gapStart,getHeight(),p);
        canvas.restore();
    }
}
