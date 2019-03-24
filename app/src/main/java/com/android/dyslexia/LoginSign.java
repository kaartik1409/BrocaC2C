package com.android.dyslexia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class LoginSign extends AppCompatActivity {

    private ViewPager viewPager;
    int currentPage = 0;
    int NUM_PAGES = 3;
    Timer timer;
    final long DELAY_MS = 2500;
    final long PERIOD_MS = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign);

        TextView textView = findViewById(R.id.desc);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue-Regular.otf");
        textView.setTypeface(face);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        @SuppressLint("CutPasteId") Button signin = findViewById(R.id.signin);
        @SuppressLint("CutPasteId") Button getstarted = findViewById(R.id.getstarted);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GetStarted.class);
                startActivity(intent);
            }
        });
        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignIn.class);
                startActivity(intent);
            }
        });
        Integer[] imageId = {R.drawable.one, R.drawable.two, R.drawable.three};
        String[] imagesName = {"image1","image2","image3"};
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        PagerAdapter adapter = new CustomAdapter(LoginSign.this,imageId,imagesName);
        viewPager.setAdapter(adapter);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if(currentPage == NUM_PAGES){
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

}
