package com.soilair.moisture.app.fragments.signup;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.soilair.moisture.app.R;
import com.soilair.moisture.app.data.Validation;
import com.soilair.moisture.app.data.database.DataBase;
import com.soilair.moisture.app.data.database.MySQLRequest;
import com.soilair.moisture.app.data.database.SQLiteCreate;

import java.util.HashMap;

/**
 * Created by User on 16/02/2018.
 */

public class FormAddUser extends Fragment
{
    private EditText nama;
    private EditText email;
    private EditText username;
    private EditText password;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frags_signup_formadduser, container, false);

        nama     = (EditText) view.findViewById(R.id.txt_singup_name);
        email    = (EditText) view.findViewById(R.id.txt_singup_email);
        username = (EditText) view.findViewById(R.id.txt_singin_username);
        password = (EditText) view.findViewById(R.id.txt_singin_password);

        Button signup = (Button) view.findViewById(R.id.btn_singin_login);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validation.isEmpty(nama, email, username, password)) {
                    addUser();
                } else {
                    Toast.makeText(
                            getContext(), "Mohon Maaf Lengkapi Form diatas dengan Benar", Toast.LENGTH_SHORT
                         ).show();
                }
            }
        });

        return view;
    }

    private void addUser () {
        DataBase.SESSION_NAMA_USER = nama.getText().toString();
        DataBase.SESSION_EMAIL_USER = email.getText().toString();
        DataBase.SESSION_USERNAME_USER = username.getText().toString();
        final String pwd = this.password.getText().toString();

        class backEnd extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show
                        ( getContext(), "Menambahkan User","Silahkan Tunggu",false,false
                );
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put("action", "signup");
                params.put(DataBase.NAMA_USER, DataBase.SESSION_NAMA_USER);
                params.put(DataBase.EMAIL_USER, DataBase.SESSION_EMAIL_USER);
                params.put(DataBase.USERNAME_USER, DataBase.SESSION_USERNAME_USER);
                params.put(DataBase.PASSWORD_USER, pwd);
                params.put(DataBase.ID_BOARD, DataBase.SESSION_BOARD);

                MySQLRequest sendRequest = new MySQLRequest();
                String result = sendRequest.sendPostRequest(DataBase.URL_POST_DATABASE, params);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                if (!s.equals("Berhasil Menambahkan User")) {
                    AlertDialog alert = new AlertDialog.Builder(getContext()).create();
                    alert.setTitle("Terdapat Kesalahan");
                    alert.setMessage(s);
                    alert.show();
                } else if (s.equals("Connection_error")) {
                    AlertDialog alert = new AlertDialog.Builder(getContext()).create();
                    alert.setTitle("Koneksi Error");
                    alert.setMessage("Maaf Smartphone anda tidak terhubung dengan board, silahkan cek koneksi anda. Terimakasih.");
                    alert.show();
                } else {
                    SQLiteCreate sqLiteCreate = new SQLiteCreate(getContext());

                    HashMap<String, Integer> parami = new HashMap<>();
                    parami.put(DataBase.SessionApp.COLOUMN_ID_PERNAH_SIGNUP, 1);
                    parami.put(DataBase.SessionApp.COLOUMN_PERNAH_SIGNUP, 1);

                    sqLiteCreate.insertInteger(DataBase.SessionApp.TABlE_NAME, parami);

                    HashMap<String, String> params = new HashMap<>();
                    params.put(DataBase.SessionUser.COLOUMN_ID_SESSION_USER, "1");
                    params.put(DataBase.SessionUser.COLOUMN_NAME, DataBase.SESSION_NAMA_USER);
                    params.put(DataBase.SessionUser.COLOUMN_EMAIL, DataBase.SESSION_EMAIL_USER);
                    params.put(DataBase.SessionUser.COLOUMN_USERNAME, DataBase.SESSION_USERNAME_USER);
                    params.put(DataBase.SessionUser.COLOUMN_ID_BOARD, DataBase.SESSION_BOARD);

                    sqLiteCreate.insertString(DataBase.SessionUser.TABLE_NAME, params, 0);

                    Toast.makeText(getContext(), s, Toast.LENGTH_LONG)
                            .show();

                    getFragmentManager().beginTransaction()
                            .replace(R.id.fr_nt_loggin, new VerifyEmail())
                            .commit();
                }
            }
        }

        backEnd backEnd = new backEnd();
        backEnd.execute();
    }

}
