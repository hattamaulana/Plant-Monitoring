package com.soilair.moisture.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.soilair.moisture.app.R;
import com.soilair.moisture.app.data.object.Sensor;

import java.util.ArrayList;
import java.util.List;

/**
 * DiBuat Oleh
 * Mahatta Maulana
 * Social :
 * https://github.com/hattamaulana
 *
 * @mhattam6 di Instagram
 * Pada 24/02/2018.
 */

public class ChartLine extends ArrayAdapter<Sensor> {
    private List<Sensor> DATA;
    private LineChart chart;

    public ChartLine(@NonNull Context context, int resource, @NonNull List<Sensor> objects) {
        super(context, resource, objects);
        DATA = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_line_chart, parent, false);
        }

        // Log.d(TAG "getView: "+this.getCount());
        setDataChart();

        if (position == this.getCount()) {
            chart.setData(setDataChart());
            chart.invalidate();
        }

        return convertView;
    }



    private LineData setDataChart () {
        LineData data = null;

        for (int i=0; i<=DATA.size(); i++) {
            Sensor data_sensor  = getItem(i);
            List<Entry> entries = new ArrayList<Entry>();
            entries.add(new Entry(0, data_sensor.getData_suhu()));
            Log.d("x-"+i, ""+data_sensor.getWaktu_suhu());

            LineDataSet dataSet = new LineDataSet(entries, "label");
            data = new LineData(dataSet);
        }

        return data;
    }
}
