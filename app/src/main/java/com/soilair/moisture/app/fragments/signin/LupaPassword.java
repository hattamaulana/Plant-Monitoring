package com.soilair.moisture.app.fragments.signin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soilair.moisture.app.R;

/**
 * Created by User on 17/02/2018.
 */

public class LupaPassword extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frags_signin_lupapassword, container, false);

        return view;
    }
}
