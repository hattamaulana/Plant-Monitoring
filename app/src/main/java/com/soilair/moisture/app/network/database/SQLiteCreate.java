package com.soilair.moisture.app.network.database;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by User on 20/02/2018.
 */

public class SQLiteCreate {
    private Context context;
    private ContentValues content;

    private SQLiteCreate(Context context, ContentValues content){
        this.context = context;
        this.content = content;
    }

    public static SQLiteCreate getInstance(Context context){
        return new SQLiteCreate(context, new ContentValues());
    }

    public void insert(String table, SQLi sql) {
        new SQLiteHelper(context).getWritableDatabase()
            .insert(table, null, sql.setQuery(this.content));
    }
}
