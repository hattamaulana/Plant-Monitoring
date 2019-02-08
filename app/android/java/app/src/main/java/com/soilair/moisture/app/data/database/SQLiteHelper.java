package com.soilair.moisture.app.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 15/02/2018.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context) {
        super(context, DataBase.DATABASE_NAME, null, DataBase.DATABASE_VERSION);
    }

    // Membuat Table Session APK
    private static final String CREATE_TABLE_SESSION_APP =
            "CREATE TABLE " + DataBase.SessionApp.TABlE_NAME + " (" +
                    DataBase.SessionApp.COLOUMN_ID_PERNAH_SIGNUP + " INT, " +
                    DataBase.SessionApp.COLOUMN_PERNAH_SIGNUP + " INT " +
            ")";

    // Membuat Table Session User
    private static final String CREATE_TABLE_SESSION_USER =
            "CREATE TABLE " + DataBase.SessionUser.TABLE_NAME + " (" +
                    DataBase.SessionUser.COLOUMN_ID_SESSION_USER     + " INTEGER PRIMARY KEY,  " +
                    DataBase.SessionUser.COLOUMN_NAME   + " TEXT, " +
                    DataBase.SessionUser.COLOUMN_EMAIL + " TEXT, " +
                    DataBase.SessionUser.COLOUMN_USERNAME + " TEXT, " +
                    DataBase.SessionUser.COLOUMN_ID_BOARD + " INTEGER " +
            ")";

    // Menghapus Table Session Apk
    private static final String DELETE_TABLE_SESSION_APP =
            "DROP TABLE IF EXISTS " + DataBase.SessionApp.TABlE_NAME;

    // Menghapus Tabel Session User
    private static final String DELETE_TABLE_SESSION_USER =
            "DROP TABLE IF EXISTS " + DataBase.SessionUser.TABLE_NAME;

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
