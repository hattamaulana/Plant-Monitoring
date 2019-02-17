package com.soilair.moisture.app.models;

import android.database.Cursor;

import com.soilair.moisture.app.models.pojo.App;

import java.util.ArrayList;

public class Apps {
    public static ArrayList<App> getAppCache (Cursor cursor){
        ArrayList<App> list = new ArrayList<>();

        while (cursor.moveToNext()){
            App app = new App();
            app.setHaveRegistered(cursor.getString(0));

            list.add(app);
        }

        return list;
    }
}
