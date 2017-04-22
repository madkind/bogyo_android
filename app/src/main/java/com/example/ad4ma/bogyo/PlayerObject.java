package com.example.ad4ma.bogyo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by hallgato on 2017-04-20.
 */

public class PlayerObject extends GameObject {

    GameManager gm;
    double verticalSpeed;
    double horizontalSpeed = 20;

    Paint p;
    public PlayerObject(int x, int y, int radius, GameManager gm) {
        super(x, y, radius, radius);
        this.gm = gm;
        p = new Paint();
        p.setColor(Color.YELLOW);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(getX(),getY(),getRadius(),p);
    }

    @Override
    public void update() {
        if (collisingTile()!=null) {
            this.setY(collisingTile().getY() - this.getRadius());
            verticalSpeed =  - verticalSpeed / 2;
        } //else {
            verticalSpeed = verticalSpeed + 2;
       // }
        this.modY((int)verticalSpeed);

        //mock horizontal movement
        if (this.getX()< 100|| this.getX() > ConfigurationManager.getScreenWidth()-100)
            this.horizontalSpeed = -this.horizontalSpeed;
        this.modX((int)horizontalSpeed);
    }

    public int getRadius(){
        return this.getWidth()/2;
    }

    public TileObject collisingTile(){
        for ( GameObject go : gm.gameObjects){
            if(go.getClass() == TileObject.class){
                if (collisionWithRect( ((TileObject)go).getLeftRec())
                 || collisionWithRect( ((TileObject)go).getRightRec()))
                return (TileObject)go;
            }
        }
        return null;
    }

     private boolean collisionWithRect(Rectangle rect){
         float distX = Math.abs(this.getX() - rect.getX()-rect.getWidth()/2);
         float distY = Math.abs(this.getY()- rect.getY()-rect.getHeight()/2);

         if (distX > (rect.getWidth()/2 + this.getRadius())) { return false; }
         if (distY > (rect.getHeight()/2 + this.getRadius())) { return false; }

         if (distX <= (rect.getWidth()/2)) { return true; }
         if (distY <= (rect.getHeight()/2)) { return true; }

         float dx=distX-rect.getWidth()/2;
         float dy=distY-rect.getHeight()/2;

         return (dx*dx+dy*dy<=(getRadius()*getRadius()));
     }

}
