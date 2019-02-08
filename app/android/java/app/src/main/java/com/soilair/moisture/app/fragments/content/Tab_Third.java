package com.soilair.moisture.app.fragments.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soilair.moisture.app.R;

/**
 * Created by User on 21/02/2018.
 */

public class Tab_Third extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frags_cont_tab_3, container, false);

//        TextView nama = (TextView) view.findViewById(R.id.txt_tab3_nama);
//        TextView username = (TextView) view.findViewById(R.id.txt_tab3_username);
//
//        nama.setText(DataBase.SESSION_NAMA_USER);
//        username.setText(DataBase.SESSION_USERNAME_USER);

        return view;
    }
}
