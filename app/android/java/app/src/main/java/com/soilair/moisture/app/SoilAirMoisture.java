package com.soilair.moisture.app;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by User on 20/02/2018.
 */

public class SoilAirMoisture extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
