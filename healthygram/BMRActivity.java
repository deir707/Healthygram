package com.unipi.diodeir.healthygram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class BMRActivity extends AppCompatActivity {
    TextView textView24,textView44,textView45,textView46,textView47,textView48,textView49,textView50,textView51,textView52,textView53,textView54,textView55,textView56,textView57,textView58,textView59,textView60,textView61,textView65;
    ImageButton imageButton34,imageButton35,imageButton36;
    TableLayout table2;
    DatabaseReference reference;
    FirebaseAuth auth;
    String id,exercise;
    double bmr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmractivity);
        textView24 = findViewById(R.id.textView24);
        textView44 = findViewById(R.id.textView44);
        textView45 = findViewById(R.id.textView45);
        textView46 = findViewById(R.id.textView46);
        textView47 = findViewById(R.id.textView47);
        textView48 = findViewById(R.id.textView48);
        textView49 = findViewById(R.id.textView49);
        textView50 = findViewById(R.id.textView50);
        textView51 = findViewById(R.id.textView51);
        textView52 = findViewById(R.id.textView52);
        textView53 = findViewById(R.id.textView53);
        textView54 = findViewById(R.id.textView54);
        textView55 = findViewById(R.id.textView55);
        textView56 = findViewById(R.id.textView56);
        textView57 = findViewById(R.id.textView57);
        textView58 = findViewById(R.id.textView58);
        textView59 = findViewById(R.id.textView59);
        textView60 = findViewById(R.id.textView60);
        textView61 = findViewById(R.id.textView61);
        textView65 = findViewById(R.id.textView65);
        imageButton34 = findViewById(R.id.imageButton34);
        imageButton35 = findViewById(R.id.imageButton35);
        imageButton36 = findViewById(R.id.imageButton36);
        table2 = findViewById(R.id.table2);
        auth = FirebaseAuth.getInstance();
        id = auth.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(id).child("Personal Data");

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                exercise = Objects.requireNonNull(snapshot.child("Exercise").getValue()).toString();
                bmr = Double.parseDouble(Objects.requireNonNull(snapshot.child("BMR").getValue()).toString());
                textView48.setText(Integer.toString((int) (bmr+bmr*(20f/100.0f))));
                textView50.setText(Integer.toString((int) (bmr+bmr*(37.5f/100.0f))));
                textView52.setText(Integer.toString((int) (bmr+bmr*(46.5f/100.0f))));
                textView54.setText(Integer.toString((int) (bmr+bmr*(55f/100.0f))));
                textView56.setText(Integer.toString((int) (bmr+bmr*(72.5f/100.0f))));
                textView58.setText(Integer.toString((int) (bmr+bmr*(90f/100.0f))));
                textView60.setText(Integer.toString((int) bmr));
                if (Objects.equals(exercise, textView47.getText().toString())) {
                    textView47.setTextColor(Color.BLUE);
                    textView47.setPaintFlags(textView47.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    textView48.setTextColor(Color.BLUE);
                    textView48.setPaintFlags(textView48.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
                else if (Objects.equals(exercise, textView49.getText().toString())) {
                    textView49.setTextColor(Color.BLUE);
                    textView49.setPaintFlags(textView49.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    textView50.setTextColor(Color.BLUE);
                    textView50.setPaintFlags(textView50.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
                else if (Objects.equals(exercise, textView51.getText().toString())) {
                    textView51.setTextColor(Color.BLUE);
                    textView51.setPaintFlags(textView51.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    textView52.setTextColor(Color.BLUE);
                    textView52.setPaintFlags(textView52.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
                else if (Objects.equals(exercise, textView53.getText().toString())) {
                    textView53.setTextColor(Color.BLUE);
                    textView53.setPaintFlags(textView53.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    textView54.setTextColor(Color.BLUE);
                    textView54.setPaintFlags(textView54.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
                else if (Objects.equals(exercise, textView55.getText().toString())) {
                    textView55.setTextColor(Color.BLUE);
                    textView55.setPaintFlags(textView55.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    textView56.setTextColor(Color.BLUE);
                    textView56.setPaintFlags(textView56.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
                else if (Objects.equals(exercise, textView57.getText().toString())) {
                    textView57.setTextColor(Color.BLUE);
                    textView57.setPaintFlags(textView57.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    textView58.setTextColor(Color.BLUE);
                    textView58.setPaintFlags(textView58.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void home (View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
    public void personal (View view) {
        Intent intent = new Intent(this, PersonalActivity.class);
        startActivity(intent);
    }
    public void statistics (View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
}