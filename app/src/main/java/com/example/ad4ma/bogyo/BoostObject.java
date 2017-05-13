package com.example.ad4ma.bogyo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by spara on 2017. 05. 11..
 */

public class BoostObject extends GameObject {


    private final Paint p;

    public final int boostValue;

    public BoostObject(int x, int y, int radius, int boostType) {
        super(x, y, radius, radius);

        p = new Paint();
        p.setStyle(Paint.Style.FILL);
        if(boostType == 1)
        {
            p.setColor(Color.GREEN);
            boostValue = -2;
        } else {
            p.setColor(Color.RED);
            boostValue = +2;
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
    }
}
