package com.soilair.moisture.app.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.soilair.moisture.app.R;


/**
 * Created by User on 15/02/2018.
 */

public class AfterInstallFragment extends Fragment
{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frags_afterinstall_1, container, false);

        Button button = (Button) view.findViewById(R.id.btn_splash_screen_not_logged_in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fr_nt_loggin, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
