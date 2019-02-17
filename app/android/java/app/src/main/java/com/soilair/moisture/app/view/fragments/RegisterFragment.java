package com.soilair.moisture.app.view.fragments;

import android.content.ContentValues;
import android.content.Intent;
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

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.soilair.moisture.app.network.Api;
import com.soilair.moisture.app.network.database.SQLi;
import com.soilair.moisture.app.network.database.SQLite;
import com.soilair.moisture.app.network.database.SQLiteCreate;
import com.soilair.moisture.app.view.activities.MainActivity;
import com.soilair.moisture.app.R;
import com.soilair.moisture.app.fragments.signin.LupaPassword;

import org.json.JSONArray;

import java.util.HashMap;

/**
 * Created by User on 16/02/2018.
 */

public class RegisterFragment extends Fragment {
    private EditText name;
    private EditText mail;
    private EditText pass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_register, container, false);
        TextView toLogin = (TextView) view.findViewById(R.id.toLogin);
        TextView forgotPassword = (TextView) view.findViewById(R.id.forgotPassword);

        name = (EditText) view.findViewById(R.id.edtName);
        mail = (EditText) view.findViewById(R.id.edtMail);
        pass = (EditText) view.findViewById(R.id.edtPass);
        Button signup = (Button) view.findViewById(R.id.btnRegister);

        signup.setOnClickListener(paramOnClickButton(new HashMap<String, String>()));
        toLogin.setOnClickListener(changeFragment(new LoginFragment()));
        forgotPassword.setOnClickListener(changeFragment(new LupaPassword()));

        return view;
    }

    private View.OnClickListener paramOnClickButton(final HashMap<String, String> params){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                params.put(Api.Users.FIRST_NAME, name.getText().toString());
                params.put(Api.Users.EMAIL, mail.getText().toString());
                params.put(Api.Users.PASSWORD, pass.getText().toString());
                AndroidNetworking.post(Api.Users.HOST)
                        .addBodyParameter(params)
                        .setTag("register")
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
                // success access and receive json
                SQLiteCreate.getInstance(getContext())
                        .insert(SQLite.SessionApp.TABlE_NAME, new SQLi() {
                            @Override
                            public ContentValues setQuery(ContentValues v) {
                                v.put(SQLite.SessionApp.COLOUMN_PERNAH_SIGNUP, 1);
                                return v;
                            }
                        });

                SQLiteCreate.getInstance(getContext())
                        .insert(SQLite.SessionUser.TABLE_NAME, caching());
            }

            @Override
            public void onError(ANError anError) {
                // error
            }
        };
    }

    private SQLi caching(){
        return new SQLi() {
            @Override
            public ContentValues setQuery(ContentValues v) {
                // save data
                return v;
            }
        };
    }

    private View.OnClickListener changeFragment(final Fragment fragment){
        return new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fr_nt_loggin, fragment)
                        .addToBackStack(null)
                        .commit();
            }

        };
    }
}
