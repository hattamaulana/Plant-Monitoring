package com.soilair.moisture.app.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 20/02/2018.
 */

public class SQLiteCreate {
    private Context context;
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;
    private ContentValues content;

    public SQLiteCreate (Context context) {
        this.context = context;
    }

    //
    private ContentValues getQueryString (HashMap<String, String> map, ContentValues content) {
        for (Map.Entry<String, String> entry: map.entrySet()) {
            content.put(entry.getKey(), entry.getValue());
        }

        return content;
    }

    private ContentValues getQueryString (HashMap<String, String> map, ContentValues content, int x) {
        int i = 0;
        for (Map.Entry<String, String> entry: map.entrySet()) {
            if (i == x) {
                content.put(entry.getKey(), Integer.parseInt(entry.getValue()));
            } else {
                content.put(entry.getKey(), entry.getValue());
            }

            i++;
        }

        return content;
    }

    private ContentValues getQueryInteger (HashMap<String, Integer> map, ContentValues content) {
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            content.put(entry.getKey(), entry.getValue());
        }

        return content;
    }

    public void insertString (String table, HashMap<String, String> params) {
        sqLiteHelper = new SQLiteHelper(context);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        sqLiteDatabase.insert(table, null, getQueryString(params, new ContentValues()));
    }

    public void insertString (String table, HashMap<String, String> params, int i) {
        sqLiteHelper = new SQLiteHelper(context);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        sqLiteDatabase.insert(table, null, getQueryString(params, new ContentValues(), i));
    }

    public void insertInteger (String table, HashMap<String, Integer> params) {
        sqLiteHelper = new SQLiteHelper(context);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        sqLiteDatabase.insert(table, null, getQueryInteger(params, new ContentValues()));
    }

}
