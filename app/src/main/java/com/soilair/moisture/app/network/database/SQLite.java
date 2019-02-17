package com.soilair.moisture.app.network.database;

import android.provider.BaseColumns;

/**
 * Created by User on 15/02/2018.
 */

public final class SQLite {

    // Database SQLite
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "session.db";

    private SQLite() {}

    /**
     * Membuat Table Session Aplikasi
     * Jika di dalam aplikasi sudah pernah mendaftar
     * dan jika user logout dan mengakses aplikasi lagi
     * akan menampilkan sign in view.
     */
    public static class SessionApp implements BaseColumns {
        public static final String TABlE_NAME = "session_app";

        public static final String COLOUMN_PERNAH_SIGNUP = "have_register";
    }

    /**
     * Membuat Table Session user,
     * untuk menyimpan siapa yang pernah signin atau signup
     */
    public static class SessionUser implements BaseColumns {
        public static final String TABLE_NAME = "user";

        public static String ID_USER = "id_user";
        public static String FIRST_NAME = "first_name";
        public static String LAST_NAME = "last_name";
        public static String PHOTO = "photo";
        public static String EMAIL = "email";
        public static String PASSWORD = "password";
        public static String GENDER = "gender";
        public static String COUNTRY = "country";
        public static String CITY = "city";
    }

    /**
     * Properti untuk menyimpan dan memanggil data
     * yang disimpan di dalam session
     * dengan mudah
     */
    public static String SESSION_NAMA_USER;
    public static String SESSION_EMAIL_USER;
    public static String SESSION_USERNAME_USER;
    public static String SESSION_BOARD;
}
