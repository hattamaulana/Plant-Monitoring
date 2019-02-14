package com.soilair.moisture.app.fragments.signup;

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

import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.soilair.moisture.app.ActivityMain;
import com.soilair.moisture.app.R;
import com.soilair.moisture.app.network.Api;
import com.soilair.moisture.app.fragments.signin.LupaPassword;
import com.soilair.moisture.app.fragments.signin.SignIn;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by User on 16/02/2018.
 */

public class RegisterForm extends Fragment {
    private EditText name;
    private EditText mail;
    private EditText pass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_register, container, false);
        HashMap<String, String> datas = new HashMap<>();

        TextView toLogin = (TextView) view.findViewById(R.id.toLogin);
        TextView forgotPassword = (TextView) view.findViewById(R.id.forgotPassword);

        name = (EditText) view.findViewById(R.id.edtName);
        mail = (EditText) view.findViewById(R.id.edtMail);
        pass = (EditText) view.findViewById(R.id.edtPass);
        Button signup = (Button) view.findViewById(R.id.btnRegister);

        datas.put("name", name.getText().toString());
        datas.put("email", mail.getText().toString());
        datas.put("password", pass.getText().toString());

        signup.setOnClickListener(paramOnClickButton(datas));
        toLogin.setOnClickListener(changeFragment(new SignIn()));
        forgotPassword.setOnClickListener(changeFragment(new LupaPassword()));

        return view;
    }

    private View.OnClickListener paramOnClickButton(final HashMap<String, String> params){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Api.getInstance()
//                        .setTag("register")
//                        .setPriority(Priority.MEDIUM)
//                        .post(params, requestListener());
            }
        };
    }

    private JSONObjectRequestListener requestListener(){
        return new JSONObjectRequestListener(){

            @Override
            public void onResponse(JSONObject response) {
                startActivity(new Intent(getContext(), ActivityMain.class));
            }

            @Override
            public void onError(ANError anError) {

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

//    private void addUser () {
//        DataBase.SESSION_NAMA_USER = name.getText().toString();
//        DataBase.SESSION_EMAIL_USER = email.getText().toString();
//        DataBase.SESSION_USERNAME_USER = username.getText().toString();
//        final String pwd = this.password.getText().toString();
//
//        class backEnd extends AsyncTask<Void, Void, String> {
//            ProgressDialog loading;
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show
//                        ( getContext(), "Menambahkan User","Silahkan Tunggu",false,false
//                );
//            }
//
//            @Override
//            protected String doInBackground(Void... voids) {
//                HashMap<String, String> params = new HashMap<>();
//                params.put("action", "signup");
//                params.put(DataBase.NAMA_USER, DataBase.SESSION_NAMA_USER);
//                params.put(DataBase.EMAIL_USER, DataBase.SESSION_EMAIL_USER);
//                params.put(DataBase.USERNAME_USER, DataBase.SESSION_USERNAME_USER);
//                params.put(DataBase.PASSWORD_USER, pwd);
//                params.put(DataBase.ID_BOARD, DataBase.SESSION_BOARD);
//
//                MySQLRequest sendRequest = new MySQLRequest();
//                String result = sendRequest.sendPostRequest(DataBase.URL_POST_DATABASE, params);
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//
//                if (!s.equals("Berhasil Menambahkan User")) {
//                    AlertDialog alert = new AlertDialog.Builder(getContext()).create();
//                    alert.setTitle("Terdapat Kesalahan");
//                    alert.setMessage(s);
//                    alert.show();
//                } else if (s.equals("Connection_error")) {
//                    AlertDialog alert = new AlertDialog.Builder(getContext()).create();
//                    alert.setTitle("Koneksi Error");
//                    alert.setMessage("Maaf Smartphone anda tidak terhubung dengan board, silahkan cek koneksi anda. Terimakasih.");
//                    alert.show();
//                } else {
//                    SQLiteCreate sqLiteCreate = new SQLiteCreate(getContext());
//
//                    HashMap<String, Integer> parami = new HashMap<>();
//                    parami.put(DataBase.SessionApp.COLOUMN_ID_PERNAH_SIGNUP, 1);
//                    parami.put(DataBase.SessionApp.COLOUMN_PERNAH_SIGNUP, 1);
//
//                    sqLiteCreate.insertInteger(DataBase.SessionApp.TABlE_NAME, parami);
//
//                    HashMap<String, String> params = new HashMap<>();
//                    params.put(DataBase.SessionUser.COLOUMN_ID_SESSION_USER, "1");
//                    params.put(DataBase.SessionUser.COLOUMN_NAME, DataBase.SESSION_NAMA_USER);
//                    params.put(DataBase.SessionUser.COLOUMN_EMAIL, DataBase.SESSION_EMAIL_USER);
//                    params.put(DataBase.SessionUser.COLOUMN_USERNAME, DataBase.SESSION_USERNAME_USER);
//                    params.put(DataBase.SessionUser.COLOUMN_ID_BOARD, DataBase.SESSION_BOARD);
//
//                    sqLiteCreate.insertString(DataBase.SessionUser.TABLE_NAME, params, 0);
//
//                    Toast.makeText(getContext(), s, Toast.LENGTH_LONG)
//                            .show();
//
//                    getFragmentManager().beginTransaction()
//                            .replace(R.id.fr_nt_loggin, new VerifyEmail())
//                            .commit();
//                }
//            }
//        }
//
//        backEnd backEnd = new backEnd();
//        backEnd.execute();
//    }

}
