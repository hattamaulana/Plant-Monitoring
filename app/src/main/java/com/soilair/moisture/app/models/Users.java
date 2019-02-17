package com.soilair.moisture.app.models;

import android.database.Cursor;

import com.soilair.moisture.app.models.pojo.User;
import com.soilair.moisture.app.network.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Users {
    public static ArrayList<User> getUserCache (Cursor cursor){
        ArrayList<User> list = new ArrayList<>();

        while (cursor.moveToNext()){
            User user = new User();

            user.setIdUser(cursor.getString(0));
            user.setFirstName(cursor.getString(1));
            user.setLastName(cursor.getString(2));
            user.setPhoto(cursor.getString(3));
            user.setEmail(cursor.getString(4));
            user.setPassword(cursor.getString(5));
            user.setCountry(cursor.getString(6));
            user.setCity(cursor.getString(7));

            list.add(user);
        }

        return list;
    }

    public static ArrayList<User> getUsersDataBase (JSONArray jsonArray){
        ArrayList<User> list = new ArrayList<>();

        try {
            for (int i=0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                User user = new User()
                        .setId(object.getInt(Api.Users.ID))
                        .setIdUser(object.getString(Api.Users.ID_USER))
                        .setFirstName(object.getString(Api.Users.FIRST_NAME))
                        .setLastName(object.getString(Api.Users.LAST_NAME))
                        .setPhoto(object.getString(Api.Users.PHOTO))
                        .setEmail(object.getString(Api.Users.EMAIL))
                        .setPassword(object.getString(Api.Users.PASSWORD))
                        .setGender(object.getString(Api.Users.GENDER))
                        .setCountry(object.getString(Api.Users.COUNTRY))
                        .setCity(object.getString(Api.Users.CITY));

                list.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
