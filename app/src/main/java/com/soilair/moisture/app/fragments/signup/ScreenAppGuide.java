package com.soilair.moisture.app.fragments.signup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.soilair.moisture.app.R;

/**
 * Created by User on 16/02/2018.
 */

public class ScreenAppGuide extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frags_signup_screenappguide, container, false);

        Button skip = (Button) view.findViewById(R.id.btn_gapp_skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fr_nt_loggin, new FormAddIdBoard())
                        .addToBackStack("form_id_board")
                        .commit();
            }
        });

        return view;
    }
}
