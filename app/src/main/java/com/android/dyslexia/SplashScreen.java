package com.android.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    private  FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (mAuth.getCurrentUser() != null) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                }
                else {
                    startActivity(new Intent(getApplicationContext(), LoginSign.class));
                    finish();
                }
            }
        }, 1500);
    }
}
