package com.soilair.moisture.app.fragments.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.soilair.moisture.app.ActivityMain;
import com.soilair.moisture.app.R;
import com.soilair.moisture.app.data.database.DataBase;

/**
 * Created by User on 17/02/2018.
 */

public class VerifyEmail extends Fragment {
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frags_signup_verifikasiemail, container, false);

        TextView nama = (TextView) view.findViewById(R.id.txt_veriakun_name);
        TextView username = (TextView) view.findViewById(R.id.txt_veriakun_username);
        nama.setText(DataBase.SESSION_NAMA_USER.toUpperCase());
        username.setText(DataBase.SESSION_USERNAME_USER.toLowerCase());
        Button verify = (Button) view.findViewById(R.id.btn_veriakun_verify);
        Button launch = (Button) view.findViewById(R.id.btn_veriakun_launch_app);

        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ActivityMain.class));
            }
        });

        return view;
    }
}
