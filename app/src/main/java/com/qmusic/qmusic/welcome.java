package com.qmusic.qmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class welcome extends AppCompatActivity {
CoordinatorLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        layout=findViewById(R.id.activity_welcome_layout);
        if (isOnline(this)) {
            startIntent();
        } else {


        }


    }

    private void startIntent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(welcome.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        //conecting
        if (info != null && info.isConnected())
            return true;
            //not conecting
        else
            return false;
    }
}