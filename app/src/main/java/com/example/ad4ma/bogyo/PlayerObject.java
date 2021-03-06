package com.example.ad4ma.bogyo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Debug;
import android.provider.Settings;
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

    private final SensorManager sensorMgr;
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
        this.sensorMgr.registerListener(this,
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

        if(this.getY()>ConfigurationManager.getViewHeight()){
            this.verticalSpeed = 0;
            //this.setX(ConfigurationManager.getViewWidth()/2);
            this.setY(ConfigurationManager.getViewHeight());
            //return;
        }

        if(this.getX()>ConfigurationManager.getViewWidth()){
            this.setX(ConfigurationManager.getViewWidth());
            //return;
        }
        if(this.getX()<0){
            this.setX(0);
            //return;
        }

        if (collision(nextTile)) {
            this.setY(nextTile.getY() - this.getRadius());

            if (nextTile.getY()>this.getY()+getRadius()/4)
                verticalSpeed = (-Math.abs(verticalSpeed) /2);
            //else
            //    this.horizontalSpeed = -this.horizontalSpeed;

        } else {

            verticalSpeed = (verticalSpeed + 5);
            this.modY((int) verticalSpeed);
         }

        //mock horizontal movement
        /*if (this.getX() < 200 || this.getX() > ConfigurationManager.getViewWidth() - 200)
            this.horizontalSpeed = -this.horizontalSpeed;
        this.modX((int) horizontalSpeed);*/
        this.modX((int)speedfromsensor*-4);
    }

    private int getRadius() {
        return this.getWidth() / 2;
    }

    public boolean collisionWithBooster(GameObject obj) {
        double distX = Math.abs(this.getX() - obj.getX() - obj.getWidth() / 2);
        double distY = Math.abs(this.getY() + verticalSpeed - obj.getY() -  obj.getHeight() / 2);

        if (distX > (obj.getWidth() / 2 + this.getRadius())) {
            return false;
        }
        if (distY > ( obj.getHeight() / 2 + this.getRadius())) {
            return false;
        }

        if (distX <= (obj.getWidth() / 2)) {
            return true;
        }
        if (distY <= (obj.getHeight() / 2)) {
            return true;
        }

        double dx = distX - obj.getWidth() / 2;
        double dy = distY - obj.getHeight() / 2;

        return (dx * dx + dy * dy <= (getRadius() * getRadius()));
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
        return to != null && (collisionWithRect(to.getLeftRec()) || collisionWithRect(to.getRightRec()));

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
        sensorMgr.unregisterListener(this);
    }

    public void onResume()
    {
        sensorMgr.registerListener(this,
                mAccelerometer,
                SensorManager.SENSOR_DELAY_GAME);
    }
}
