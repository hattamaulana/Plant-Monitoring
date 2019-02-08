package com.soilair.moisture.app.fragments.content;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.soilair.moisture.app.R;
import com.soilair.moisture.app.data.database.DataBase;
import com.soilair.moisture.app.data.database.MySQLRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 21/02/2018.
 */

public class Tab_Second extends Fragment {
    private View view;
    private LineChart chart;
    private SwipeRefreshLayout SwipeRefreshLayout;

    private void response(String string) {
        try {
            JSONObject jsonObject = new JSONObject(string);
            JSONArray result      = jsonObject.getJSONArray(DataBase.TAG_JSON_ARRAY_DATA_SENSOR_SUHU);
            List<Entry> entries_suhu   = new ArrayList<Entry>();
            List<Entry> entries_lmbb   = new ArrayList<Entry>();

            for (int i=0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);

                /**
                 * object.getString(DataBase.JSON_WAKTU_SENSOR_SUHU) = ambil waktu suhu
                 * object.getString(DataBase.JSON_DATA_SENSOR_SUHU) = ambil data suhu
                 * object.getString(DataBase.JSON_WAKTU_SENSOR_KELEMBABAN) = ambil data kelembaban
                 * object.getString(DataBase.JSON_DATA_SENSOR_KELEMBABAN) = ambil data kelembaban
                 */

                entries_suhu.add(new Entry(i, object.getInt(DataBase.JSON_DATA_SENSOR_SUHU)));
                entries_lmbb.add(new Entry(i, object.getInt(DataBase.JSON_DATA_SENSOR_KELEMBABAN)));
            }

            LineDataSet dataSet1 = new LineDataSet(entries_suhu, null);
            dataSet1.setColor(R.color.green);
            dataSet1.setValueTextSize(0f);
            dataSet1.setCircleColors(ColorTemplate.MATERIAL_COLORS);

            LineDataSet dataSet2 = new LineDataSet(entries_lmbb, null);
            dataSet2.setColor(R.color.brown);
            dataSet2.setValueTextSize(0f);
            dataSet2.setCircleColors(ColorTemplate.JOYFUL_COLORS);

            LineData data = new LineData(dataSet1, dataSet2);
            chart.getAxisRight().setDrawGridLines(false);
            chart.getAxisRight().setEnabled(false);
            chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

            chart.setTouchEnabled(false);
            chart.setDragEnabled(false);
            chart.setScaleEnabled(false);
            chart.setDescription(null);
            chart.setData(data);
            chart.invalidate();


        } catch (JSONException e) { e.printStackTrace(); }
    }

    /**
     * set & get request ke
     * MySQL Server melalui
     * Web Server PHP
     */
    private void request() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                MySQLRequest sqlRequest         = new MySQLRequest();
                HashMap<String, String> params  = new HashMap<>();
                params.put("action", "menampilkan_data_sensor");
                params.put("id_board", "4567");
                String hasil = sqlRequest
                        .sendPostRequest(DataBase.URL_POST_DATABASE, params);

                return hasil;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equals("Connection_error")) {
                    AlertDialog alert = new AlertDialog.Builder(getContext()).create();
                    alert.setTitle("Koneksi Error");
                    alert.setMessage("Maaf Smartphone anda tidak terhubung dengan board, silahkan cek koneksi anda. Terimakasih.");
                    alert.show();
                } else {
                    response(s);
                }
            }
        }

        GetJSON json = new GetJSON();
        json.execute();
    }

    /**
     * Content View
     * Controller View
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frags_cont_tab_2, container, false);
        SwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipechart);
        chart = (LineChart) view.findViewById(R.id.linechart);

        request();

        SwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        request();
                        Snackbar.make (view,"Berhasil Syncronisasi data dengan Board Anda", Snackbar.LENGTH_LONG )
                                .show();
                        SwipeRefreshLayout.setRefreshing(false);
                    }
                }, 100);
            }
        });

        return view;
    }
}
