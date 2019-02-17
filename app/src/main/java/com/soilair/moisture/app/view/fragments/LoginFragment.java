package com.soilair.moisture.app.view.fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.soilair.moisture.app.R;
import com.soilair.moisture.app.fragments.signin.LupaPassword;
import com.soilair.moisture.app.models.Apps;
import com.soilair.moisture.app.models.Users;
import com.soilair.moisture.app.models.pojo.User;
import com.soilair.moisture.app.network.Api;
import com.soilair.moisture.app.network.database.SQLi;
import com.soilair.moisture.app.network.database.SQLite;
import com.soilair.moisture.app.network.database.SQLiteCreate;
import com.soilair.moisture.app.network.database.SQLiteRead;
import com.soilair.moisture.app.view.activities.MainActivity;

import org.json.JSONArray;

import java.util.HashMap;

/**
 * Created by User on 16/02/2018.
 */

public class LoginFragment extends Fragment {
    private EditText mail;
    private EditText pass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frags_login, container, false);
        TextView textView = (TextView) view.findViewById(R.id.txtForgotPassword);
        Button loginButton = (Button) view.findViewById(R.id.btnLogin);

        mail = (EditText) view.findViewById(R.id.edtMail);
        pass = (EditText) view.findViewById(R.id.edtPass);

        textView.setOnClickListener(onClickForgetPassword());
        loginButton.setOnClickListener(onClickLoginButton());

        return view;
    }

    private View.OnClickListener onClickForgetPassword(){
        return new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fr_nt_loggin, new LupaPassword())
                        .addToBackStack(null)
                        .commit();
            }
        };
    }

    private View.OnClickListener onClickLoginButton(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AndroidNetworking.get(Api.Users.HOST)
                        .addQueryParameter(Api.Users.EMAIL, mail.getText().toString())
                        .setTag("login")
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONArray(requestListener());
            }
        };
    }

    private JSONArrayRequestListener requestListener(){
        return new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                // when success do
                if (response.length() < 1)
                    Toast.makeText(getContext(), "Username wrong", Toast.LENGTH_SHORT).show();

                for (User user: Users.getUsersDataBase(response)){
                    if (user.getPassword().equals(pass.getText().toString().trim())) {
                        insertSessionApp(user);

                        SQLiteCreate.getInstance(getContext())
                                .insert(SQLite.SessionUser.TABLE_NAME, addDataToSQLite(user));

                        startActivity(new Intent(getContext(), MainActivity.class));
                    } else {
                        Toast.makeText(getContext(), "Password wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(ANError anError) {
                // handle error
                Toast.makeText(getContext(), "error" + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private SQLi addDataToSQLite(final User user){
        return new SQLi() {
            @Override
            public ContentValues setQuery(ContentValues v) {
                v.put(SQLite.SessionUser.ID_USER, user.getIdUser());
                v.put(SQLite.SessionUser.FIRST_NAME, user.getFirstName());
                v.put(SQLite.SessionUser.LAST_NAME, user.getLastName());
                v.put(SQLite.SessionUser.PHOTO, user.getPhoto());
                v.put(SQLite.SessionUser.EMAIL, user.getEmail());
                v.put(SQLite.SessionUser.PASSWORD, user.getPassword());
                v.put(SQLite.SessionUser.GENDER, user.getGender());
                v.put(SQLite.SessionUser.COUNTRY, user.getCountry());
                v.put(SQLite.SessionUser.CITY, user.getCity());

                return v;
            }
        };
    }

    private void insertSessionApp(User user) {

        Cursor cursor = SQLiteRead.getInstance(getContext())
                .setRead(SQLite.SessionApp.TABlE_NAME, new String[]{"*"})
                .setWhere(new String[]{SQLite.SessionApp.COLOUMN_PERNAH_SIGNUP}, "=", new String[]{user.getIdUser()})
                .getData();

        if (Apps.getAppCache(cursor).size() == 0)
            SQLiteCreate.getInstance(getContext())
                    .insert(SQLite.SessionApp.TABlE_NAME, cachingApp(user));
    }

    private SQLi cachingApp (final User user){
        return new SQLi() {
            @Override
            public ContentValues setQuery(ContentValues v) {
                v.put(SQLite.SessionApp.COLOUMN_PERNAH_SIGNUP, user.getIdUser());

                return v;
            }
        };
    }
}
