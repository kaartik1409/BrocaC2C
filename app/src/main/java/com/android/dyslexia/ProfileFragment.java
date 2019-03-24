package com.android.dyslexia;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.VIBRATOR_SERVICE;

public class ProfileFragment extends Fragment{
    private FirebaseAuth firebaseAuth;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profilefragment, container, false);
        Typeface face = Typeface.createFromAsset((Objects.requireNonNull(getActivity())).getAssets(), "fonts/AvenirNextLTPro-Bold.otf");
        Typeface face2 = Typeface.createFromAsset((Objects.requireNonNull(getActivity())).getAssets(), "fonts/AvenirNextLTPro-Regular.otf");
        TextView greet = view.findViewById(R.id.greet);
        firebaseAuth = FirebaseAuth.getInstance();
        TextView progressrepor = view.findViewById(R.id.progress);
        ImageButton button2 = view.findViewById(R.id.log);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder;
                builder = new android.app.AlertDialog.Builder(getContext());
                builder.setTitle("Are You Sure You Want To Log Out?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                firebaseAuth.signOut();
                                Intent startIntent = new Intent(getContext(), LoginSign.class);
                                startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(startIntent);

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();

        }
        });
        TextView repor = view.findViewById(R.id.course);
        progressrepor.setTypeface(face2);
        repor.setTypeface(face2);
        final TextView name = view.findViewById(R.id.name);
        name.setTypeface(face);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        assert firebaseUser != null;
        final String uid = firebaseUser.getUid();
        TextView textView = view.findViewById(R.id.edit);
        textView.setTypeface(face2);
        name.setTypeface(face2);
        greet.setTypeface(face);
        ImageButton button = view.findViewById(R.id.card1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ABC.class);
                startActivity(intent);
            }
        });
        if (firebaseAuth.getCurrentUser() != null) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://broca-77e50.firebaseio.com/").getReference("Users").child(uid);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String acctname = dataSnapshot.child("username").getValue(String.class);
                    name.setText(acctname);
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("error", databaseError.getMessage());
                }
            });

        }
        return view;
    }


}
