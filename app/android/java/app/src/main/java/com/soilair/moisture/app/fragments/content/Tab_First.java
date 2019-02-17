package com.soilair.moisture.app.fragments.content;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.soilair.moisture.app.R;
import com.soilair.moisture.app.adapter.SensorListController;
import com.soilair.moisture.app.network.database.SQLite;
import com.soilair.moisture.app.data.object.Sensor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 21/02/2018.
 */

public class Tab_First extends Fragment {
    private ListView listView;
    private String JSON_STRING;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.frags_cont_tab_1, container, false);
        listView = (ListView) view.findViewById(R.id.list_data);
        listView.setClickable(false);
        listView.setFooterDividersEnabled(true);
//        getJson();

//        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.tab1swipe);
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        getJson();
//                        Snackbar.make(view, "Berhasil Syncronisasi data dengan Board Anda", Snackbar.LENGTH_LONG).show();
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 100);
//            }
//        });

        return view;
    }

//    private void showJson() {
//        JSONObject jsonObject = null;
//        List<Sensor> list = new ArrayList<>();
//
//        try {
//            jsonObject = new JSONObject(JSON_STRING);
//            JSONArray result = jsonObject.getJSONArray(SQLite.TAG_JSON_ARRAY_DATA_SENSOR_SUHU);
//
//            for (int i=0; i < result.length(); i++) {
//                JSONObject object = result.getJSONObject(i);
//                String waktu_suhu = object.getString(SQLite.JSON_WAKTU_SENSOR_SUHU);
//                String data_suhu = object.getString(SQLite.JSON_DATA_SENSOR_SUHU);
//                String waktu_kelembaban = object.getString(SQLite.JSON_WAKTU_SENSOR_KELEMBABAN);
//                String data_kelembaban = object.getString(SQLite.JSON_DATA_SENSOR_KELEMBABAN);

//                Sensor add = new Sensor();
//                add.setWaktu_suhu(waktu_suhu);
//                add.setData_suhu(Integer.parseInt(data_suhu));
//                add.setWaktu_kelembaban(waktu_kelembaban);
//                add.setData_kelembaban(Integer.parseInt(data_kelembaban));
//                list.add(add);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        listView.setAdapter(new SensorListController(getContext(), R.layout.list_data_sensor, list));
//    }
//
//    private void getJson () {
//        class GetJSON extends AsyncTask<Void, Void, String> {
//            public ProgressDialog progressDialog;
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                progressDialog = ProgressDialog.show
//                        (       getContext(),
//                                "Mengambil Data", "Silahkan Tunggu Sebentar",
//                                false, false
//                        );
//            }
//
//            @Override
//            protected String doInBackground(Void... voids) {
//                MySQLRequest sqlRequest = new MySQLRequest();
//
//                HashMap<String, String> params = new HashMap<>();
//                params.put("action", "menampilkan_data_sensor");
//                params.put("id_board", "4567");
//
//                String hasil = sqlRequest.sendPostRequest(SQLite.URL_POST_DATABASE, params);
//                return hasil;
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                progressDialog.dismiss();
//                if (s.equals("Connection_error")) {
//                    AlertDialog alert = new AlertDialog.Builder(getContext()).create();
//                    alert.setTitle("Koneksi Error");
//                    alert.setMessage("Maaf Smartphone anda tidak terhubung dengan board, silahkan cek koneksi anda. Terimakasih.");
//                    alert.show();
//                } else {
//                    JSON_STRING = s;
//                    showJson();
//                }
//            }
//        }
//
//        GetJSON json = new GetJSON();
//        json.execute();
//    }
}
