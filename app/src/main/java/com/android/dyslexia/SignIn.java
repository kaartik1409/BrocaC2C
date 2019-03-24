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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    private EditText textEmail;
    private EditText password;
    private FirebaseAuth mAuth;
    private Button button;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        progressDialog = new ProgressDialog(this);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        mAuth = FirebaseAuth.getInstance();
        textEmail = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        TextView textView = findViewById(R.id.greet);
        TextView textView1 = findViewById(R.id.ema);
        TextView textView2 = findViewById(R.id.pas);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue-Bold.otf");
        textView.setTypeface(face);
        textView1.setTypeface(face);
        textView2.setTypeface(face);
        button.setTypeface(face);

    }

    private void registerUser() {

        final String email = textEmail.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please Enter A Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Just A Moment");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                        // ...
                    }

                    private void updateUI(FirebaseUser user) {
                        Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(startIntent);
                        finish();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            registerUser();

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
}
