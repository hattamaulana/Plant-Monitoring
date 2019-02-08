package com.soilair.moisture.app.data.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by User on 15/02/2018.
 */

public class SQLiteRead {
    private Context context;
    private SQLiteHelper SQLiteHelper;
    private Cursor cursor;

    public SQLiteRead(Context context) {
        this.context    = context;
        SQLiteHelper = new SQLiteHelper(context);
    }

    public Cursor getSession(String[][] params, String[] where_value) {
        SQLiteDatabase sqLiteDatabase = SQLiteHelper.getReadableDatabase();

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
                cursor = sqLiteDatabase
                        .query( table_name, output, where_key, where_value, row, filterRow, order );
            } catch (IllegalArgumentException e) { e.printStackTrace(); }
        }

        return cursor;
    }

    public void delete (String table_name) {
        SQLiteDatabase sqLiteDatabase = SQLiteHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + table_name);
        sqLiteDatabase.close();
    }
}
