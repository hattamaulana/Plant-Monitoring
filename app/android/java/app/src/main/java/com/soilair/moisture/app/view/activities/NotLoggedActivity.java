package com.soilair.moisture.app.view.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.soilair.moisture.app.R;
import com.soilair.moisture.app.view.fragments.AfterInstallFragment;
import com.soilair.moisture.app.view.fragments.LoginFragment;

public class NotLoggedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.primaryDark));

        setContentView(R.layout.activity_not_logged_in);

        Intent intent = getIntent();
        String mesage = intent.getStringExtra("action");
        // mesage = "signin";

        if (mesage.equals("signUp"))
            setFragments(new AfterInstallFragment());
        else if (mesage.equals("signIn"))
            setFragments(new LoginFragment());
        else if (mesage.equals("error"))
            Log.i("Splash_Screen", "Intent Mengirim kan data Error");
    }

    private void setFragments (Fragment fragments) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fr_nt_loggin, fragments).commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0 &&
            getSupportFragmentManager().getBackStackEntryCount() < 2) {
            getSupportFragmentManager().popBackStack();
        } else {
            new AlertDialog.Builder(this).setMessage("Apa anda yakin ingin Keluar")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("Kembali", null)
                    .show();
        }
    }
}
