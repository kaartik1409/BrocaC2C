package com.android.dyslexia;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;

public class IdentifyTest extends AppCompatActivity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_test);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue-Bold.otf");
        Typeface face2 = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue-Regular.otf");
        TextView textView = findViewById(R.id.greet);
        TextView textView2 = findViewById(R.id.levvel);
        TextView textView3 = findViewById(R.id.hint);
        textView.setTypeface(face);
        TextView textView1 = findViewById(R.id.a);
        textView2.setTypeface(face);
        textView1.setTypeface(face);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        textView3.setTypeface(face2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Quiz.class);
                startActivity(intent);
            }
        });
        ImageButton button = findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ImageButton speak = findViewById(R.id.speak);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.spellapple);
                mp.start();
            }
        });
    }
}
