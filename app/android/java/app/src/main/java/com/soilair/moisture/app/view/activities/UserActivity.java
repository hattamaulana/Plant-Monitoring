package com.soilair.moisture.app.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.soilair.moisture.app.R;
import com.soilair.moisture.app.network.database.SQLite;
import com.soilair.moisture.app.network.database.SQLiteRead;

public class UserActivity extends AppCompatActivity {
    private TextView NAMA;
    private TextView EMAIL;
    private TextView USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        this.setTitle(SQLite.SESSION_NAMA_USER);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        actionBar.setDisplayShowHomeEnabled(true);

        NAMA     = (TextView) findViewById(R.id.txt_pakn_nama);
        USERNAME = (TextView) findViewById(R.id.txt_pakn_username);
//        EMAIL    = (TextView) findViewById(R.id.txt_pakn_email);

        NAMA.setText(SQLite.SESSION_NAMA_USER);
        USERNAME.setText(SQLite.SESSION_USERNAME_USER);
        EMAIL.setText(SQLite.SESSION_EMAIL_USER);

        findViewById(R.id.btn_pkan_logout)
                .setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new SQLiteRead(getApplicationContext()).delete(SQLite.SessionUser.TABLE_NAME);
                Intent intent = new Intent(getApplicationContext(), NotLoggedActivity.class);
                intent.putExtra("action", "signIn");
                startActivity(intent);
            }
        });
    }
}
