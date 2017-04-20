package com.example.ad4ma.bogyo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by hallgato on 2017-04-20.
 */

public class PlayerObject extends GameObject {
    public int speed;
    Paint p;
    public PlayerObject(int x, int y, int width, int height) {
        super(x, y, width, height);
        p = new Paint(Color.RED);

    }

    @Override
    public void draw(Canvas canvas) {
       // canvas.save();
      // canvas.translate(getX(),getY());
       // canvas.drawCircle(0,0,getWidth()/2,p);
       // canvas.restore();
        canvas.drawCircle(getX(),getY(),getWidth()/2,p);
    }

    @Override
    public void update() {

    }

    public void moveLeft(){
        if (getY()>0)
            this.y-=speed;
    }
    public void moveRight(){
        if (getY()<1000)
            this.y+=speed;
    }


}
