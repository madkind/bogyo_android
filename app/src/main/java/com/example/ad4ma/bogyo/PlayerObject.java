package com.example.ad4ma.bogyo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Debug;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by hallgato on 2017-04-20.
 */

public class PlayerObject extends GameObject implements SensorEventListener {

    private final GameManager gm;
    private final Paint p;
    private double verticalSpeed;
    private double horizontalSpeed = 25;
    private boolean alive;

    private SensorManager sensorMgr;
    private final Sensor mAccelerometer;
    private double speedfromsensor;

    public PlayerObject(@SuppressWarnings("SameParameterValue") int x, @SuppressWarnings("SameParameterValue") int y, int radius, GameManager gm, Context context ) {
        super(x, y, radius, radius);
        this.gm = gm;
        p = new Paint();
        p.setColor(Color.YELLOW);
        this.alive = true;

        sensorMgr=(SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = this.sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.sensorMgr.registerListener((SensorEventListener) this,
                mAccelerometer,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(getX(), getY(), getRadius(), p);
    }

    @Override
    public void update() {
        TileObject nextTile = getNextTile();

        if (this.getY()< this.getRadius())
        {
            this.alive = false;
            return;
        }

        if(this.getY()>ConfigurationManager.getScreenHeight()){
            this.verticalSpeed = 0;
            this.setX(ConfigurationManager.getScreenWidth()/2);
            this.setY(ConfigurationManager.getScreenHeight()/2);
            return;
        }
        //TODO booster felvételét megoldani
        if (collision(nextTile)) {
            this.setY(nextTile.getY() - this.getRadius());

            if (nextTile.getY()>this.getY()+getRadius()/4)
                verticalSpeed = -Math.abs(verticalSpeed) /2;
            //else
            //    this.horizontalSpeed = -this.horizontalSpeed;

        } else {

            verticalSpeed = verticalSpeed + 5;
            this.modY((int) verticalSpeed);
         }

        //mock horizontal movement
        /*if (this.getX() < 200 || this.getX() > ConfigurationManager.getScreenWidth() - 200)
            this.horizontalSpeed = -this.horizontalSpeed;
        this.modX((int) horizontalSpeed);*/
        this.modX((int)speedfromsensor*-4);
    }

    private int getRadius() {
        return this.getWidth() / 2;
    }

    private boolean collisionWithRect(Rectangle rect) {
        //double speedAndHeight = rect.getHeight() ;// - this.verticalSpeed;
        double distX = Math.abs(this.getX() - rect.getX() - rect.getWidth() / 2);
        double distY = Math.abs(this.getY() + verticalSpeed - rect.getY() -  rect.getHeight() / 2);

        if (distX > (rect.getWidth() / 2 + this.getRadius())) {
            return false;
        }
        if (distY > ( rect.getHeight() / 2 + this.getRadius())) {
            return false;
        }

        if (distX <= (rect.getWidth() / 2)) {
            return true;
        }
        if (distY <= (rect.getHeight() / 2)) {
            return true;
        }

        double dx = distX - rect.getWidth() / 2;
        double dy = distY - rect.getHeight() / 2;

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

    public boolean isAlive() {
        return this.alive;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        //update((int)event.values[2]);
        speedfromsensor=event.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onPause()
    {
        sensorMgr.unregisterListener((SensorEventListener) this);
    }

    public void onResume()
    {
        sensorMgr.registerListener((SensorEventListener) this,
                mAccelerometer,
                SensorManager.SENSOR_DELAY_GAME);
    }
}
