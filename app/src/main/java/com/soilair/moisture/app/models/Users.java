package com.soilair.moisture.app.models;

import android.util.Log;

import com.facebook.stetho.server.LeakyBufferedInputStream;
import com.soilair.moisture.app.models.pojo.User;
import com.soilair.moisture.app.network.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Users {
    public static ArrayList<User> getUsers(JSONArray jsonArray){
        ArrayList<User> list = new ArrayList<>();

        try {
            for (int i=0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                User user = new User()
                        .setId(object.getInt(Api.ID))
                        .setIdUser(object.getString(Api.ID_USER))
                        .setFirstName(object.getString(Api.FIRST_NAME))
                        .setLastName(object.getString(Api.LAST_NAME))
                        .setPhoto(object.getString(Api.PHOTO))
                        .setEmail(object.getString(Api.EMAIL))
                        .setPassword(object.getString(Api.PASSWORD))
                        .setGender(object.getString(Api.GENDER))
                        .setCountry(object.getString(Api.COUNTRY))
                        .setCity(object.getString(Api.CITY));

                list.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
