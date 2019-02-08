package com.soilair.moisture.app.data.database;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by User on 19/02/2018.
 */

public class MySQLRequest {
    private Context context;

    public String sendPostRequest (String requestUrl, HashMap<String, String> data) {
        String kembalian = null;
        StringBuilder string = new StringBuilder();
        URL url = null;

        try {
            url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStream output = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));
            MySQLHelper mysql = new MySQLHelper();
            String param = mysql.getParamsForPostRequest(data);
            writer.write(param);
            writer.flush();
            writer.close();
            output.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            string = new StringBuilder();
            String response = "";
            while ((response = reader.readLine()) != null) {
                string.append(response);
            }
            kembalian = string.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            kembalian =  "Connection_error";
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return kembalian;
    }

    public String sendGet (String targetURL) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            URL url = new URL(targetURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String s = "";
            while ((s = reader.readLine()) != null) {
                stringBuilder.append(s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
