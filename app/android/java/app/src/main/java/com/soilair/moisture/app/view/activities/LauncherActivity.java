package com.soilair.moisture.app.view.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import com.soilair.moisture.app.R;
import com.soilair.moisture.app.models.Apps;
import com.soilair.moisture.app.models.Users;
import com.soilair.moisture.app.network.database.SQLite;
import com.soilair.moisture.app.network.database.SQLiteRead;

public class LauncherActivity extends AppCompatActivity {
    private int progres_pembanding = 0;
    private Cursor cursor;
    private SQLiteRead sqLite;
    private ProgressBar proggress;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.primaryDark));

        setContentView(R.layout.activity_launcher);

        proggress  = (ProgressBar) findViewById(R.id.pb_launcher);
        proggress.setVisibility(View.INVISIBLE);

        Cursor appCursor = SQLiteRead.getInstance(this)
                .setRead(SQLite.SessionApp.TABlE_NAME, new String[]{"*"})
                .getData();

        Cursor userCursor = SQLiteRead.getInstance(this)
                .setRead(SQLite.SessionUser.TABLE_NAME, new String[]{"*"})
                .getData();

        if (Apps.getAppCache(appCursor).size() > 0){
            if (Users.getUserCache(userCursor).size() > 0)
                intent = new Intent(this, MainActivity.class);
            else {
                intent = new Intent(this, NotLoggedActivity.class);
                intent.putExtra("action", "signIn");
            }
        } else {
            intent = new Intent(this, NotLoggedActivity.class);
            intent.putExtra("action", "signUp");
        }

        newThread().start();
    }

    private Thread newThread(){
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (progres_pembanding == proggress.getMax())
                    start();

                progres_pembanding++;
            }
        };

        return new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    for (int i=0; i<=proggress.getMax(); i++) {
                        proggress.setProgress(i);
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(15);
                    }
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });
    }

    private void start (){
        startActivity(intent);
    }

}
