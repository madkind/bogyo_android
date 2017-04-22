package com.example.ad4ma.bogyo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by hallgato on 2017-04-20.
 */

public class PlayerObject extends GameObject {
    public int speed;
    Paint p;
    public PlayerObject(int x, int y, int radius) {
        super(x, y, radius, radius);
        p = new Paint();
        p.setColor(Color.YELLOW);

    }

    @Override
    public void draw(Canvas canvas) {
       // canvas.save();
      // canvas.translate(getX(),getY());
       // canvas.drawCircle(0,0,getWidth()/2,p);
       // canvas.restore();

        canvas.drawCircle(getX(),getY(),getRadius(),p);
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
    public int getRadius(){
        return this.getWidth()/2;
    }

    public boolean collidionWithTile(ArrayList<GameObject> gameObjects){
        for ( GameObject go : gameObjects){
            if(go.getClass() == TileObject.class){
                if (collidionWithTile((TileObject)go))
                    return true;
            }
        }
        return false;
    }
    //TODO gap
     private boolean collidionWithTile(TileObject to){
         float distX = Math.abs(this.getX() - to.getX()-to.getWidth()/2);
         float distY = Math.abs(this.getY()- to.getY()-to.getHeight()/2);

         if (distX > (to.getWidth()/2 + this.getRadius())) { return false; }
         if (distY > (to.getHeight()/2 + this.getRadius())) { return false; }

         if (distX <= (to.getWidth()/2)) { return true; }
         if (distY <= (to.getHeight()/2)) { return true; }

         float dx=distX-to.getWidth()/2;
         float dy=distY-to.getHeight()/2;

         return (dx*dx+dy*dy<=(getRadius()*getRadius()));
     }
}
