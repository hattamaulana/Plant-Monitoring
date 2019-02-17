package com.soilair.moisture.app.network.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.soilair.moisture.app.models.pojo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 15/02/2018.
 */

public class SQLiteRead {
    private static SQLiteRead instance = null;
    private Context context;
    private String query = "";
    private SQLiteHelper helper;
    private Cursor cursor;
    private String[] whereClause = null;

    public static SQLiteRead getInstance(Context context){
        instance = new SQLiteRead(context);

        return instance;
    }

    private SQLiteRead(Context context) {
        this.context    = context;
        helper = new SQLiteHelper(context);
    }

    public Cursor getData(){
        SQLiteDatabase database = helper.getWritableDatabase();

        Log.e(TAG, "getData: "+ this.query);

        return database.rawQuery(this.query, whereClause);
    }

    public SQLiteRead setWhere(String[] param, String operation, String[] val){
        String query = "";

        whereClause = val;

        for (int i = 0; i < param.length; i++) {
            query += param[i] + operation + "? ";
        }

        this.query += " WHERE " + query;

        return instance;
    }

    public SQLiteRead setRead(String table, String[] coloumn){
        for (int i=0; i < coloumn.length; i++)
            query += (coloumn.length == 1 || i < coloumn.length - 1) ? coloumn[i] : coloumn[i] + ",";

        query = "SELECT "+ query +" FROM "+ table;

        return instance;
    }

    public Cursor getSession(String[][] params, String[] where_value) {
        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();

        String table_name = params[0][0];
        String[] output   = new String[params[1].length];

        for (int i=0; i<params[1].length; i++)
            output[i] = params[1][i];

        String where_key = (params[2][0].length() > 0) ? params[2][0] : null;
        String row       = (params[4][0].length() > 0) ? params[3][0] : null;
        String filterRow = (params[5][0].length() > 0) ? params[5][0] : null;
        String order = (params[6][1].equals("ASC")) ? params[6][0] + " ASC" : params[6][0] + " DESC";
        if ((params[0][0].length() > 0)) {
            try {
                cursor = sqLiteDatabase.query( table_name, output, where_key, where_value, row, filterRow, order );
            } catch (IllegalArgumentException e) { e.printStackTrace(); }
        }

        return cursor;
    }
}
