package com.example.ad4ma.bogyo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by ad4ma on 2017. 04. 19..
 */

public class TileObject extends GameObject{

    Paint p;
    public TileObject(int x, int y, int width, int height) {
        super( x, y, width, height);
        p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLUE);
    }

    public void update()  {
        this.Modify_Y(+3);

        if (y>ConfigurationManager.getScreenSize().y)
            setY(0);

    }
    public void draw(Canvas canvas)  {

        //canvas.drawRect(getX(),getY(),getWidth(),getHeight(),p);
        //canvas.drawBitmap(tileBmp,new Rect(0,0,0,0),new Rect(getX(),getY(),getWidth(),getHeight()),p);
        //canvas.drawLine(getX(),getY(),getWidth()+getX(),getHeight()+getY(),p);
        canvas.drawCircle(getX(),getY(),100,p);

    }
}
