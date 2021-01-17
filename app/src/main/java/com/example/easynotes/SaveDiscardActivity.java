package com.example.easynotes;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;


public abstract class SaveDiscardActivity extends AppCompatActivity implements SensorEventListener {
    private static final String LOG_TAG = SaveDiscardActivity.class.getSimpleName();

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;
    private float[] mGravity;
    private float[] mGeomagnetic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer == null) {
            Log.d(LOG_TAG, "accelerometer is null");
        }
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magnetometer == null) {
            Log.d(LOG_TAG, "magnetometer is null");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        start();
    }

    public void start() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stop();
    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Log.d(TAG, "onSensorChanged()");
        if (event.values == null) {
            Log.w(LOG_TAG, "event.values is null");
            return;
        }
        int sensorType = event.sensor.getType();
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                mGravity = event.values;
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mGeomagnetic = event.values;
                break;
            default:
                Log.w(LOG_TAG, "Unknown sensor type " + sensorType);
                return;
        }
        if (mGravity == null) {
            Log.w(LOG_TAG, "mGravity is null");
            return;
        }
        if (mGeomagnetic == null) {
            Log.w(LOG_TAG, "mGeomagnetic is null");
            return;
        }
        float rot[] = new float[9];
        if (!SensorManager.getRotationMatrix(rot, null, mGravity, mGeomagnetic)) {
            Log.w(LOG_TAG, "getRotationMatrix() failed");
            return;
        }

        float[] orientation = new float[9];
        SensorManager.getOrientation(rot, orientation);
        float roll = orientation[2];
        int rollDeg = (int) Math.round(Math.toDegrees(roll));
        if (rollDeg < -30 && rollDeg > -150) {
            Log.i(LOG_TAG, "------------------LEFT---------------");
            stop();
            discardNote();

        }
        if (rollDeg > 30 && rollDeg < 150) {
            Log.i(LOG_TAG, "------------------RIGHT---------------");
            stop();
            saveNote();
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void saveNote(View view) {
        saveNote();
    }

    public void discardNote(View view) {
        discardNote();
    }


    public abstract void saveNote();

    public abstract void discardNote();
}