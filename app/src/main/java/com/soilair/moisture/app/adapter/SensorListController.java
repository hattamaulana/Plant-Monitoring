package com.soilair.moisture.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.soilair.moisture.app.R;
import com.soilair.moisture.app.data.object.Sensor;

import java.util.List;

/**
 * Created by User on 22/02/2018.
 */

public class SensorListController extends ArrayAdapter<Sensor> {
    public SensorListController(@NonNull Context context, int resource, @NonNull List<Sensor> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_data_sensor, parent, false);
        }

        // Set variabel View Bold
        TextView bld_data_suhu   = (TextView) convertView.findViewById(R.id.txt_list_bold_data_suhu);
        TextView bld_data_lmbb   = (TextView) convertView.findViewById(R.id.txt_list_bold_data_kelembaban);
        TextView bld_stuan_suhu  = (TextView) convertView.findViewById(R.id.txt_list_bold_satuan_suhu);
        TextView bld_stuan_lmbb  = (TextView) convertView.findViewById(R.id.txt_list_bold_satuan_kelembaban);
        TextView bld_snsr_suhu   = (TextView) convertView.findViewById(R.id.txt_list_bold_sensor_suhu);
        TextView bld_snsr_lmbb   = (TextView) convertView.findViewById(R.id.txt_list_bold_sensor_kelembaban);
        TextView bld_plingupdate = (TextView) convertView.findViewById(R.id.lbl_list_plingupdate);

        // Set variabel view normal
        TextView nor_snsr_suhu          = (TextView) convertView.findViewById(R.id.txt_list_normal_sensor_suhu);
        TextView nor_snsr_klmbaban      = (TextView) convertView.findViewById(R.id.txt_list_normal_sensor_kelembaban);
        TextView nor_data_suhu          = (TextView) convertView.findViewById(R.id.txt_list_normal_data_suhu);
        TextView nor_data_lmbb          = (TextView) convertView.findViewById(R.id.txt_list_normal_data_kelebaban);
        TextView nor_sat_suhu           = (TextView) convertView.findViewById(R.id.txt_list_normal_satuan_suhu);
        TextView nor_sat_lmbb           = (TextView) convertView.findViewById(R.id.txt_list_normal_satuan_kelembaban);
        TextView nor_time               = (TextView) convertView.findViewById(R.id.txt_nor_time);

        Sensor data_sensor = getItem(position);
        if (position == 0) {
            nor_snsr_suhu.setVisibility(View.GONE);
            nor_snsr_klmbaban.setVisibility(View.GONE);
            nor_data_suhu.setVisibility(View.GONE);
            nor_data_lmbb.setVisibility(View.GONE);
            nor_sat_suhu.setVisibility(View.GONE);
            nor_sat_lmbb.setVisibility(View.GONE);
            nor_time.setVisibility(View.GONE);

            bld_data_suhu.setText(String.valueOf(data_sensor.getData_suhu()));
            bld_data_lmbb.setText(String.valueOf(data_sensor.getData_kelembaban()));
        } else {
            //
            bld_data_suhu.setVisibility(View.GONE);
            bld_data_lmbb.setVisibility(View.GONE);
            bld_stuan_suhu.setVisibility(View.GONE);
            bld_stuan_lmbb.setVisibility(View.GONE);
            bld_snsr_suhu.setVisibility(View.GONE);
            bld_snsr_lmbb.setVisibility(View.GONE);
            bld_plingupdate.setVisibility(View.GONE);

            if (position == 1) {
                nor_snsr_suhu.setVisibility(View.VISIBLE);
                nor_snsr_klmbaban.setVisibility(View.VISIBLE);
            } else {
                nor_snsr_suhu.setVisibility(View.GONE);
                nor_snsr_klmbaban.setVisibility(View.GONE);
            }

            nor_data_suhu.setVisibility(View.VISIBLE);
            nor_data_lmbb.setVisibility(View.VISIBLE);
            nor_sat_suhu.setVisibility(View.VISIBLE);
            nor_sat_lmbb.setVisibility(View.VISIBLE);
            nor_time.setVisibility(View.VISIBLE);

            nor_data_suhu.setText(String.valueOf(data_sensor.getData_suhu()));
            nor_data_lmbb.setText(String.valueOf(data_sensor.getData_kelembaban()));
        }

        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
