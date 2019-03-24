package com.android.dyslexia;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Identify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);
        TextView textView = findViewById(R.id.greet);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/AvenirNextLTPro-Bold.otf");
        Typeface face2 = Typeface.createFromAsset(getAssets(), "fonts/AvenirNextLTPro-Regular.otf");
        textView.setTypeface(face);
        Button button = findViewById(R.id.button);
        button.setTypeface(face);
        TextView textView1 = findViewById(R.id.content);
        textView1.setTypeface(face2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),IdentifyTest.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
