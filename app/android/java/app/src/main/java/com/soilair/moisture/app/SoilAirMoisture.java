package com.soilair.moisture.app;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.facebook.stetho.Stetho;

import okhttp3.OkHttpClient;

/**
 * Created by User on 20/02/2018.
 */

public class SoilAirMoisture extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * initialize library
         * Facebook Stetho
         * Android Fast Networking
         */
        Stetho.initializeWithDefaults(this);

        AndroidNetworking.initialize(this);
        AndroidNetworking.enableLogging();
        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.HEADERS);
    }
}
