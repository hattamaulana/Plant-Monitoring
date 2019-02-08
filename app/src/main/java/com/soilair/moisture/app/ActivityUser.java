package com.soilair.moisture.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.soilair.moisture.app.data.database.DataBase;
import com.soilair.moisture.app.data.database.SQLiteRead;

public class ActivityUser extends AppCompatActivity {
    private TextView NAMA;
    private TextView EMAIL;
    private TextView USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        this.setTitle(DataBase.SESSION_NAMA_USER);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        actionBar.setDisplayShowHomeEnabled(true);

        NAMA     = (TextView) findViewById(R.id.txt_pakn_nama);
        USERNAME = (TextView) findViewById(R.id.txt_pakn_username);
        EMAIL    = (TextView) findViewById(R.id.txt_pakn_email);

        NAMA.setText(DataBase.SESSION_NAMA_USER);
        USERNAME.setText(DataBase.SESSION_USERNAME_USER);
        EMAIL.setText(DataBase.SESSION_EMAIL_USER);

        findViewById(R.id.btn_pkan_logout)
                .setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SQLiteRead(getApplicationContext()).delete(DataBase.SessionUser.TABLE_NAME);
                Intent intent = new Intent(getApplicationContext(), ActivityNotLoggedIn.class);
                intent.putExtra("action", "signIn");
                startActivity(intent);
            }
        });
    }
}
