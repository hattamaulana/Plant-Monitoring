package com.soilair.moisture.app.data.database;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 19/02/2018.
 */

public class MySQLHelper {

    public String getParamsForPostRequest (HashMap<String, String> map) throws UnsupportedEncodingException {
        StringBuilder string = new StringBuilder();
        boolean pertama = true;

        for (Map.Entry<String, String> entry: map.entrySet()) {
            if (pertama)
                pertama = false;
            else
                string.append("&");

            string.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            string.append("=");
            string.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return string.toString();
    }
}
