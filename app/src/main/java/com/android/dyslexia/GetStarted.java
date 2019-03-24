package com.android.dyslexia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GetStarted extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private Button button;
    private TextView textView3;
    private TextView textView5;
    private TextView textView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        firebaseAuth = FirebaseAuth.getInstance();
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        databaseReference = FirebaseDatabase.getInstance("https://broca-77e50.firebaseio.com/").getReference("Users");
        progressDialog = new ProgressDialog(this);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/AvenirNextLTPro-Regular.otf");
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue-Bold.otf");
        Typeface face2 = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue-Regular.otf");
        TextView textView4 = findViewById(R.id.ema);
        TextView textView = findViewById(R.id.log);
        textView5 = findViewById(R.id.email);
        TextView textView6 = findViewById(R.id.pas);
        textView7 = findViewById(R.id.pass);
        TextView textView2 = findViewById(R.id.nam);
        TextView textView11 = findViewById(R.id.signin);
        textView3 = findViewById(R.id.name);
        textView3.setTypeface(custom_font);
        textView5.setTypeface(custom_font);
        textView7.setTypeface(custom_font);
        button.setTypeface(face);
        textView2.setTypeface(face);
        textView4.setTypeface(face);
        textView6.setTypeface(face);
        textView.setTypeface(face);
        textView11.setTypeface(face2);

    }
    private void register() {
        final String name = textView3.getText().toString().trim();
        final String email = textView5.getText().toString().trim();
        final String password = textView7.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Just A Moment");
        progressDialog.show();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    user();
                                    Intent startIntent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(startIntent);

                                } else {

                                    Toast.makeText(getApplicationContext(), "Either The Email Address Already Exists Or Password Is Weak", Toast.LENGTH_SHORT).show();
                                    progressDialog.cancel();
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                }
                            }


                            private void user() {
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                assert user != null;
                                String id = user.getUid();
                                Users users = new Users(name, email, password,id);
                                databaseReference.child(user.getUid()).setValue(users);
                            }
                        }
                );
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            register();
        }
    }
}
