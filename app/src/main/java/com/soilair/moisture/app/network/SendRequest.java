package com.soilair.moisture.app.network;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.soilair.moisture.app.models.Users;

import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SendRequest {
    private static SendRequest instance = null;
    private String HOST = "http://192.168.1.12:3000/";
    private String host;
    private String tag;
    private Priority priority;
    private HashMap<String, String> pathParam = null;
    private HashMap<String, String> queryParam = null;
    private HashMap<String, String> header = null;

    public static SendRequest getInstance(){
        if (SendRequest.instance == null){
            SendRequest.instance = new SendRequest();
        }

        return SendRequest.instance;
    }

    public void get(ParsedRequestListener<List<Users>> requestListener) {
        Log.d(TAG, "get: "+ getHost());
        ANRequest.GetRequestBuilder networking = AndroidNetworking.get(getHost());

        if (this.pathParam != null) {
            Log.d(TAG, "get: "+ this.pathParam);
            networking.addPathParameter(getPathParam());
        }

        if (this.queryParam != null) {
            Log.d(TAG, "get: "+ this.queryParam);
            networking.addQueryParameter(getQueryParam());
        }

        if (this.header != null)
            networking.addHeaders(getHeader());

        networking.setTag(getTag())
                .setPriority(getPriority())
                .build()
                .getAsObjectList(Users.class, requestListener);
    }

    public void post(HashMap<String, String> param, JSONObjectRequestListener requestListener) {
        AndroidNetworking.post(getHost())
                .addBodyParameter(param)
                .setTag(getTag())
                .setPriority(getPriority())
                .build()
                .getAsJSONObject(requestListener);
    }

    public String getHost() {
        return host;
    }

    public SendRequest setHost(String host) {
        this.host = this.HOST + host;

        return SendRequest.instance;
    }

    public String getTag() {
        return tag;
    }

    public SendRequest setTag(String tag) {
        this.tag = tag;

        return SendRequest.instance;
    }

    public Priority getPriority() {
        return priority;
    }

    public SendRequest setPriority(Priority priority) {
        this.priority = priority;

        return SendRequest.instance;
    }

    public HashMap<String, String> getPathParam() {
        return pathParam;
    }

    public SendRequest setPathParam(HashMap<String, String> pathParam) {
        this.pathParam = pathParam;
        return this;
    }

    public HashMap<String, String> getQueryParam() {
        return queryParam;
    }

    public SendRequest setQueryParam(HashMap<String, String> queryParam) {
        this.queryParam = queryParam;
        return this;
    }

    public HashMap<String, String> getHeader() {
        return header;
    }

    public SendRequest setHeader(HashMap<String, String> header) {
        this.header = header;
        return this;
    }
}
