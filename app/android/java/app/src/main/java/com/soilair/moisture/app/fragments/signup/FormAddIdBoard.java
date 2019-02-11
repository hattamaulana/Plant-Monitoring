package com.soilair.moisture.app.fragments.signup;

import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.soilair.moisture.app.R;
import com.soilair.moisture.app.data.Validation;
import com.soilair.moisture.app.data.database.DataBase;
import com.soilair.moisture.app.data.database.MySQLRequest;

import java.util.HashMap;

/**
 * Created by User on 16/02/2018.
 */

public class FormAddIdBoard extends Fragment {
    private EditText id_board;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frags_singup_formaddidboard, container, false);

        ImageView image = (ImageView) view.findViewById(R.id.img_signup_idboard);
        image.setBackgroundColor(R.color.colorAccent);

        id_board = (EditText) view.findViewById(R.id.txt_add_id_board);
        Button button = (Button) view.findViewById(R.id.btn_form_id_board);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validation.isEmpty(id_board)) {
                    DataBase.SESSION_BOARD = id_board.getText().toString();
                    cekId();
                } else {
                    Toast.makeText(getContext(), "Maaf, isi id board yang benar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void cekId () {

        class cekId extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show
                        ( getContext(), "Mengecek Id Board", "Silahkan Tunggu", false, false );
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put("action", "cek_id");
                params.put(DataBase.ID_BOARD, DataBase.SESSION_BOARD);

                MySQLRequest sendRequest = new MySQLRequest();
                String result = sendRequest.sendPostRequest(DataBase.URL_POST_DATABASE, params);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                if (s.equals("found")) {
                    Toast.makeText(getContext(), "Terima Kasih Telah menggunakan Product Kami", Toast.LENGTH_LONG).show();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fr_nt_loggin, new RegisterForm())
                            .commit();
                } else if (s.equals("notFound")) {
                    AlertDialog alert = new AlertDialog.Builder(getContext()).create();
                    alert.setTitle("Hasil Mengecek Id Board");
                    alert.setMessage("Maaf, Id Board yang anda Masukkan tidak cocok dengan data board yang ada," +
                            " silahkan teliti ulang");
                    alert.show();
                } else if (s.equals("Connection_error")){
                    AlertDialog alert = new AlertDialog.Builder(getContext()).create();
                    alert.setTitle("Koneksi Error");
                    alert.setMessage("Maaf Smartphone anda tidak terhubung dengan board, silahkan cek koneksi anda. Terimakasih.");
                    alert.show();
                }
            }
        }

        cekId cekId = new cekId();
        cekId.execute();
    }
}
