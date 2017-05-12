package com.example.ad4ma.bogyo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by spara on 2017. 05. 11..
 */

public class BoostObject extends GameObject {


    private final Paint p;

    private int boostValue;

    public BoostObject(int x, int y, int radius, int boostType) {
        super(x, y, radius, radius);

        p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.CYAN);
        if(boostType == 1)
        {
            boostValue = -5;
        } else {
            boostValue = +5;
        }
    }

    private int getRadius() {
        return this.getWidth() / 2;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(getX(), getY(), getRadius(), p);
    }

    @Override
    public void update() {

        this.modY(-ConfigurationManager.getTileSpeed());

        if (y < 0){
            setY(ConfigurationManager.getViewHeight());
        }
    }
}
