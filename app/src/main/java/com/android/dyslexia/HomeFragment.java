package com.android.dyslexia;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class HomeFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container, false);
        TextView greet = view.findViewById(R.id.greet);
        final TextView greet3 = view.findViewById(R.id.greet2);
        TextView greet2 = view.findViewById(R.id.immersive);
        TextView reccomended = view.findViewById(R.id.rec);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            assert firebaseUser != null;
            final String uid = firebaseUser.getUid();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://broca-77e50.firebaseio.com/").getReference("Users").child(uid);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String acctname = dataSnapshot.child("username").getValue(String.class);
                    greet3.setText(acctname);
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("error", databaseError.getMessage());
                }
            });
            Typeface face = Typeface.createFromAsset((Objects.requireNonNull(getActivity())).getAssets(), "fonts/AvenirNextLTPro-Bold.otf");
            greet.setTypeface(face);
            greet2.setTypeface(face);
            greet3.setTypeface(face);
            reccomended.setTypeface(face);
            ImageButton imageButton1 = view.findViewById(R.id.card2);
            ImageButton imageButton = view.findViewById(R.id.card1);
            ImageButton imageButton2 = view.findViewById(R.id.card4);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ABC.class);
                    startActivity(intent);
                }
            });
            imageButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Identify.class);
                    startActivity(intent);
                }
            });
            imageButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Speak.class);
                    startActivity(intent);

                }
            });

        }
        return view;
    }
}
