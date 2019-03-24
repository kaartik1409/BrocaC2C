package com.android.dyslexia;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ABCTest extends AppCompatActivity implements DrawingImageView.DrawFinishListener {

    private TextView textView4;
    private TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abctest);
        textView4 = findViewById(R.id.done);
        LinearLayout llMain = (LinearLayout) findViewById(R.id.llMain);
        int drawable = R.drawable.aname;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawable);
        DrawingImageView image = new DrawingImageView(this, bitmap);
        image.setImageBitmap(bitmap);
        llMain.addView(image);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue-Bold.otf");
        Typeface face2 = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue-Regular.otf");
        TextView textView = findViewById(R.id.greet);
        textView2 = findViewById(R.id.levvel);
        textView4.setTypeface(face);
        TextView textView3 = findViewById(R.id.hint);
        textView3.setTypeface(face2);
        textView.setTypeface(face);
        TextView textView1 = findViewById(R.id.a);
        textView.setTypeface(face);
        textView2.setTypeface(face);
        textView1.setTypeface(face);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageButton button = findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onDrawFinish() {
        overridePendingTransition(R.anim.slide_up, R.anim.fadeout);
       textView4.setVisibility(View.VISIBLE);
       textView2.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onDrawStop() {
       Toast.makeText(getApplicationContext(),"You are doing great!",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDrawStart() {

    }
}
