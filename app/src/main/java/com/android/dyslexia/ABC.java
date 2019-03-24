package com.android.dyslexia;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;

public class ABC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/AvenirNextLTPro-Bold.otf");
        Typeface face2 = Typeface.createFromAsset(getAssets(), "fonts/AvenirNextLTPro-Regular.otf");
        Button button = findViewById(R.id.button);
        button.setTypeface(face);
        TextView textView1 = findViewById(R.id.content);
        textView1.setTypeface(face2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ABCTest.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
