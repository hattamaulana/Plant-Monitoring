package com.soilair.moisture.app.network.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 15/02/2018.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context) {
        super(context, SQLite.DATABASE_NAME, null, SQLite.DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_SESSION_APP =
            "CREATE TABLE " + SQLite.SessionApp.TABlE_NAME + " (" +
                    SQLite.SessionApp.COLOUMN_PERNAH_SIGNUP + " TEXT " +
            ")";

    private static final String CREATE_TABLE_SESSION_USER =
            "CREATE TABLE " + SQLite.SessionUser.TABLE_NAME + " (" +
                    SQLite.SessionUser.ID_USER     + " INTEGER PRIMARY KEY,  " +
                    SQLite.SessionUser.FIRST_NAME   + " TEXT, " +
                    SQLite.SessionUser.LAST_NAME + " TEXT, " +
                    SQLite.SessionUser.EMAIL + " TEXT, " +
                    SQLite.SessionUser.PASSWORD + " TEXT, " +
                    SQLite.SessionUser.PHOTO + " TEXT, " +
                    SQLite.SessionUser.GENDER + " TEXT, " +
                    SQLite.SessionUser.COUNTRY + " TEXT, " +
                    SQLite.SessionUser.CITY + " TEXT " +
            ")";

    private static final String DELETE_TABLE_SESSION_APP =
            "DROP TABLE IF EXISTS " + SQLite.SessionApp.TABlE_NAME;

    private static final String DELETE_TABLE_SESSION_USER =
            "DROP TABLE IF EXISTS " + SQLite.SessionUser.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SESSION_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_SESSION_APP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_TABLE_SESSION_USER);
        sqLiteDatabase.execSQL(DELETE_TABLE_SESSION_APP);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
