package com.soilair.moisture.app.network;

public final class Api {
    public static String HOST = "http://192.168.1.6:3000/";

    public static class Users {
        public static String HOST = Api.HOST + "users";

        public static String ID = "_id";
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
}
