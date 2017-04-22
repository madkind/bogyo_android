package com.example.ad4ma.bogyo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by hallgato on 2017-04-20.
 */

public class PlayerObject extends GameObject {

    private final GameManager gm;
    private final Paint p;
    private double verticalSpeed;
    private double horizontalSpeed = 25;

    public PlayerObject(@SuppressWarnings("SameParameterValue") int x, @SuppressWarnings("SameParameterValue") int y, int radius, GameManager gm) {
        super(x, y, radius, radius);
        this.gm = gm;
        p = new Paint();
        p.setColor(Color.YELLOW);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(getX(), getY(), getRadius(), p);
    }

    @Override
    public void update() {
        TileObject nextTile = getNextTile();

        if (collision(nextTile)) {
            this.setY(nextTile.getY() - this.getRadius());

            if (nextTile.getY()>this.getY()+getRadius()/4)
                verticalSpeed = -Math.abs(verticalSpeed) /2;
            else
                this.horizontalSpeed = -this.horizontalSpeed;

        } else {

        verticalSpeed = verticalSpeed + 10;
            this.modY((int) verticalSpeed);
         }

        //mock horizontal movement
        if (this.getX() < 200 || this.getX() > ConfigurationManager.getScreenWidth() - 200)
            this.horizontalSpeed = -this.horizontalSpeed;
        this.modX((int) horizontalSpeed);
    }

    private int getRadius() {
        return this.getWidth() / 2;
    }

    private boolean collisionWithRect(Rectangle rect) {
        double distX = Math.abs(this.getX() - rect.getX() - rect.getWidth() / 2);
        double distY = Math.abs(this.getY() - rect.getY() - (rect.getHeight() +this.verticalSpeed) / 2);

        if (distX > (rect.getWidth() / 2 + this.getRadius())) {
            return false;
        }
        if (distY > ((rect.getHeight() +this.verticalSpeed) / 2 + this.getRadius())) {
            return false;
        }

        if (distX <= (rect.getWidth() / 2)) {
            return true;
        }
        if (distY <= ((rect.getHeight() +this.verticalSpeed) / 2)) {
            return true;
        }

        double dx = distX - rect.getWidth() / 2;
        double dy = distY - (rect.getHeight() +(float)this.verticalSpeed) / 2;

        return (dx * dx + dy * dy <= (getRadius() * getRadius()));
    }

    private boolean collision(TileObject to) {
        if (to==null)
            return false;

        return collisionWithRect(to.getLeftRec())
            || collisionWithRect(to.getRightRec());

    }

    private TileObject getNextTile(){
        TileObject toReturn = null;
        int minDistance = Integer.MAX_VALUE;

        for (GameObject go : gm.gameObjects)
            if (go.getClass() == TileObject.class){
                if (go.getY()+go.getHeight() > this.getY() && go.getY()+ go.getHeight() - this.getY() < minDistance){
                    minDistance = go.getY()+ go.getHeight() - this.getY();
                    toReturn = (TileObject) go;
                }
            }
        return toReturn;
    }

}
