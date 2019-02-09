package com.soilair.moisture.app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ProgressBar;

import com.soilair.moisture.app.data.database.DataBase;
import com.soilair.moisture.app.data.database.SQLiteRead;

public class ActivityLauncher extends AppCompatActivity {
    public static boolean SESSION_APP;
    public static boolean SESSION_USER;

    private int progres_pembanding = 0;
    private Cursor cursor;
    private SQLiteRead sqLite;
    private ProgressBar proggress;

    private boolean cekSessionApp () {
        boolean signUp = false;
        String[][] params = {
                { DataBase.SessionApp.TABlE_NAME },
                { DataBase.SessionApp.COLOUMN_ID_PERNAH_SIGNUP, DataBase.SessionApp.COLOUMN_PERNAH_SIGNUP },
                { "" }, {""}, {""}, {""},
                { DataBase.SessionApp.COLOUMN_ID_PERNAH_SIGNUP , "ASC" }
        };

        cursor = sqLite.getSession(params, null);
        if (cursor.getCount() > 0) {
            signUp = true;
        } else {
            signUp = false;
        }

        return signUp;
    }

    private boolean cekSessionUser () {
        boolean signIn;
        String[][] paramsCekSessionUser = {
                { DataBase.SessionUser.TABLE_NAME },
                {
                        DataBase.SessionUser.COLOUMN_NAME,
                        DataBase.SessionUser.COLOUMN_EMAIL,
                        DataBase.SessionUser.COLOUMN_USERNAME,
                        DataBase.SessionUser.COLOUMN_ID_BOARD
                }, { "" }, {null}, {""}, {""},
                { DataBase.SessionUser.COLOUMN_NAME, "ASC" }
        };

        sqLite = new SQLiteRead(this);
        cursor = sqLite.getSession(paramsCekSessionUser, null);

        if (cursor.getCount() > 0) {
            signIn = true;
            while (cursor.moveToNext()) {
                DataBase.SESSION_NAMA_USER = cursor.getString(0);
                DataBase.SESSION_EMAIL_USER = cursor.getString(1);
                DataBase.SESSION_USERNAME_USER = cursor.getString(2);
                DataBase.SESSION_BOARD = cursor.getString(3);
            }
        } else {
            signIn = false;
        }

        return signIn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.primaryDark));
        }

        setContentView(R.layout.activity_launcher);
        SESSION_USER = cekSessionUser();
        SESSION_APP = cekSessionApp();

        final String action = (SESSION_USER == true) ? "launch_application" :
                              (SESSION_USER == false && SESSION_APP == true) ? "signIn" :
                              (SESSION_APP  == false) ? "signUp" : "error";

        proggress  = (ProgressBar) findViewById(R.id.pb_launcher);
        proggress.setProgress(0);

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (progres_pembanding == proggress.getMax()) {
                    if (action == "launch_application"){
                        startActivity(new Intent(getApplicationContext(), ActivityMain.class));
                    } else {
                        Intent intent = new Intent(getApplicationContext(), ActivityNotLoggedIn.class);
                        intent.putExtra("action", action);
                        startActivity(intent);
                    }
                }
                progres_pembanding++;
            }
        };

        Thread thread =  new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    for (int i=0; i<=proggress.getMax(); i++) {
                        proggress.setProgress(i);
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(25);
                    }
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });
        thread.start();
    }

}
