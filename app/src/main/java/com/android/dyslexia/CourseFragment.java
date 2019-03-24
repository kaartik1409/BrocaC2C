package com.android.dyslexia;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;

public class CourseFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coursefragment, container, false);
        TextView greet = view.findViewById(R.id.greet);
        Typeface face = Typeface.createFromAsset((Objects.requireNonNull(getActivity())).getAssets(), "fonts/AvenirNextLTPro-Bold.otf");
        greet.setTypeface(face);
        ImageButton button = view.findViewById(R.id.card1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ABC.class);
                startActivity(intent);
            }
        });
        return view;

    }
}
