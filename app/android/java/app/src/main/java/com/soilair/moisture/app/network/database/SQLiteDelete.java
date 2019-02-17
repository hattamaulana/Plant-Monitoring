package com.soilair.moisture.app.network.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteDelete {
    private SQLiteHelper helper;

    public static SQLiteDelete getInstance(Context context){
        SQLiteHelper helper = new SQLiteHelper(context);
        return new SQLiteDelete();
    }

    public void delete (String table) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + table);
        sqLiteDatabase.close();
    }
}
