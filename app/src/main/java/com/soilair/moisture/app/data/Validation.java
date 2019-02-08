package com.soilair.moisture.app.data;

import android.widget.EditText;

/**
 * Created by User on 18/02/2018.
 */

public class Validation {

    public static boolean isEmpty (EditText... params) {
        boolean kembalian = true;
        for (int i=0; i<params.length; i++) {
            if (params[0].getText().toString().equals("")) {
                kembalian = false;
            }
        }

        return kembalian;
    }

}
