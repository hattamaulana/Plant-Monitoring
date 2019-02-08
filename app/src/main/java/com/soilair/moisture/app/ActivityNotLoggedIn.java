package com.soilair.moisture.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.soilair.moisture.app.fragments.ScreenStartNow;
import com.soilair.moisture.app.fragments.signin.SignIn;

public class ActivityNotLoggedIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_not_logged_in);

        Intent intent = getIntent();
        String mesage = intent.getStringExtra("action");
        // mesage = "signin";

        if (mesage.equals("launch_application"))
            startActivity(new Intent(getApplicationContext(), ActivityMain.class));
        else if (mesage.equals("signUp"))
            setFragments(new ScreenStartNow());
        else if (mesage.equals("signIn"))
            setFragments(new SignIn());
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
