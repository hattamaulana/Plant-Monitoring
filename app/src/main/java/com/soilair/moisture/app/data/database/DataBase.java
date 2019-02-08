package com.soilair.moisture.app.data.database;

import android.provider.BaseColumns;

/**
 * Created by User on 15/02/2018.
 */

public final class DataBase {

    // Database SQLite
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "session.db";

    private DataBase () {}

    /**
     * Membuat Table Session Aplikasi
     * Jika di dalam aplikasi sudah pernah mendaftar
     * dan jika user logout dan mengakses aplikasi lagi
     * akan menampilkan sign in view.
     */
    public static class SessionApp implements BaseColumns {
        // Table Name
        public static final String TABlE_NAME = "session_app";

        // Colom
        public static final String COLOUMN_ID_PERNAH_SIGNUP = "id_pernah_signup";
        public static final String COLOUMN_PERNAH_SIGNUP = "pernah_signup";
    }

    /**
     * Membuat Table Session user,
     * untuk menyimpan siapa yang pernah signin atau signup
     */
    public static class SessionUser implements BaseColumns {
        // Table Session User
        public static final String TABLE_NAME = "session_user";

        // Colom di dalam table User
        public static final String COLOUMN_ID_SESSION_USER = "id_session_user";
        public static final String COLOUMN_NAME = "name_user";
        public static final String COLOUMN_EMAIL = "email_user";
        public static final String COLOUMN_USERNAME = "username_user";
        public static final String COLOUMN_ID_BOARD = "id_board_user";
    }

    // Database MySQL
    public static final String URL_POST_DATABASE = "http://192.168.56.1/Model.php";
    public static final String TAG_JSON_ARRAY_DATA_SENSOR_SUHU = "result_sensor_suhu";

    // TABLE hardware
    public static final String ID_DATA_BOARD = "id_data_board";
    public static final String ID_BOARD      = "id_board";

    // Key JSON
    public static final String JSON_ID_DATA_BOARD = "id_data_board";
    public static final String JSON_ID_BOARD      = "id_board";

    // Table Users
    public static final String ID_DATA_USERS    = "id_data_users";
    public static final String NAMA_USER        = "nama_user";
    public static final String EMAIL_USER       = "email_user";
    public static final String USERNAME_USER    = "username_user";
    public static final String PASSWORD_USER    = "password_user";

    // Key JSON Users
    public static final String JSON_ID_DATA_USERS    = "user";
    public static final String JSON_NAMA_USER        = "nama_user";
    public static final String JSON_EMAIL_USER       = "email_user";
    public static final String JSON_USERNAME_USER    = "username_user";
    public static final String JSON_PASSWORD_USER    = "password_user";

    // Table Data SensorListController Suhu
    public static final String ID_DATA_SENSOR_SUHU  = "id_data_sensor_suhu";
    public static final String WAKTU_SENSOR_SUHU    = "waktu_sensor_suhu";
    public static final String DATA_SENSOR_SUHU     = "data_sensor_suhu";

    // Key Data JSON SensorListController Suhu
    public static final String JSON_ID_DATA_SENSOR_SUHU  = "json_id_data_sensor_suhu";
    public static final String JSON_WAKTU_SENSOR_SUHU    = "waktu_suhu";
    public static final String JSON_DATA_SENSOR_SUHU     = "data_suhu";

    // Table Data SensorListController Kelembaban Tanah
    public static final String ID_DATA_SENSOR_KELEMBABAN = "id_data_sensor_kelembaban";
    public static final String WAKTU_SENSOR_KELEMBABAN   = "waktu_sensor_kelembaban";
    public static final String DATA_SENSOR_KELEMBABAN    = "data_sensor_kelembaban";

    public static final String JSON_ID_DATA_SENSOR_KELEMBABAN = "json_id_data_sensor_kelembaban";
    public static final String JSON_WAKTU_SENSOR_KELEMBABAN   = "waktu_kelembaban";
    public static final String JSON_DATA_SENSOR_KELEMBABAN    = "data_kelembaban";


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
