package com.soilair.moisture.app.fragments.signin;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.soilair.moisture.app.ActivityMain;
import com.soilair.moisture.app.R;
import com.soilair.moisture.app.data.database.DataBase;
import com.soilair.moisture.app.data.database.MySQLRequest;
import com.soilair.moisture.app.data.database.SQLiteCreate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 16/02/2018.
 */

public class SignIn extends Fragment {
    private EditText username;
    private EditText password;

    private void response (String s) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(s);
            JSONArray jsonArray   = jsonObject.getJSONArray("user");
            SQLiteCreate create   = new SQLiteCreate(getContext());
            HashMap<String, String> params = new HashMap<>();

            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                params.put(DataBase.SessionUser.COLOUMN_NAME, object.getString(DataBase.JSON_NAMA_USER));
                params.put(DataBase.SessionUser.COLOUMN_EMAIL, object.getString(DataBase.JSON_USERNAME_USER));
                params.put(DataBase.SessionUser.COLOUMN_USERNAME, object.getString(DataBase.JSON_EMAIL_USER));
                params.put(DataBase.SessionUser.COLOUMN_ID_BOARD, object.getString(DataBase.ID_BOARD));

                DataBase.SESSION_NAMA_USER = object.getString(DataBase.JSON_NAMA_USER);
                DataBase.SESSION_EMAIL_USER = object.getString(DataBase.JSON_EMAIL_USER);
                DataBase.SESSION_USERNAME_USER = object.getString(DataBase.JSON_USERNAME_USER);
                DataBase.SESSION_BOARD = object.getString(DataBase.ID_BOARD);
            }

            create.insertString(DataBase.SessionUser.TABLE_NAME, params);
            startActivity(new Intent(getContext(), ActivityMain.class));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void request () {

        class SendRequest extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> param = new HashMap<>();
                param.put("action", "signin");
                param.put("username", username.getText().toString());
                param.put("password", password.getText().toString());

                MySQLRequest sqlRequest = new MySQLRequest();
                String result = sqlRequest.sendPostRequest(DataBase.URL_POST_DATABASE, param);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d(TAG, "response: " + s);

                if (s.equals("Login_Gagal")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Login Gagal");
                    alertDialog.setMessage("Mohon Cek kembali Username atau Password yang anda masukkan.");
                    alertDialog.show();
                } else if (s.equals("Connection_error")) {
                    AlertDialog alert = new AlertDialog.Builder(getContext()).create();
                    alert.setTitle("Koneksi Error");
                    alert.setMessage("Maaf Smartphone anda tidak terhubung dengan board, silahkan cek koneksi anda. Terimakasih.");
                    alert.show();
                } else {
                    response(s);
                }

            }
        }

        SendRequest sendRequest = new SendRequest();
        sendRequest.execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frags_signin, container, false);

        username  = (EditText) view.findViewById(R.id.txt_singin_username);
        password  = (EditText) view.findViewById(R.id.txt_singin_password);

        // klik text lupa password
        TextView textView = (TextView) view.findViewById(R.id.txt_signin_lupapasswd);
        textView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fr_nt_loggin, new LupaPassword())
                                .addToBackStack(null)
                                .commit();
                    }
                }
        );

        view.findViewById(R.id.btn_singin_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });

        return view;
    }
}
