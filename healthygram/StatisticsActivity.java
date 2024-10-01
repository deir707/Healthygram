package com.unipi.diodeir.healthygram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class StatisticsActivity extends AppCompatActivity {
    TextView textView12;
    ImageButton imageButton12,imageButton13,imageButton14;
    CalendarView calendarView2;
    DatabaseReference reference,reference2;
    FirebaseAuth auth;
    String id,date = "", date_before = "";
    Double cal, weight, cals_burn, bmr, train_time, walk_time, run_time, bicycle_time;
    Integer steps;
    Dialog dialog;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd");
    DecimalFormat df = new DecimalFormat("#.#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        textView12 = findViewById(R.id.textView12);
        imageButton12 = findViewById(R.id.imageButton12);
        imageButton13 = findViewById(R.id.imageButton13);
        imageButton14 = findViewById(R.id.imageButton14);
        calendarView2 = findViewById(R.id.calendarView2);
        auth = FirebaseAuth.getInstance();
        id = auth.getUid();

        calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                cal = 0.0;
                weight = 0.0;
                steps = 0;
                cals_burn = 0.0;
                train_time = 0.0;
                bmr = 0.0;
                walk_time = 0.0;
                run_time = 0.0;
                bicycle_time = 0.0;
                if (month <= 9 && dayOfMonth <= 9) {
                    date = year+" "+ "0"+(month+1)+" "+"0"+dayOfMonth;
                }
                else if (month <= 9) {
                    date = year+" "+ "0"+(month+1)+" "+dayOfMonth;
                }
                else if (dayOfMonth <= 9) {
                    date = year+" "+(month+1)+" "+"0"+dayOfMonth;
                }
                else {
                    date = year+" "+(month+1)+" "+dayOfMonth;
                }
                LocalDate localDate = LocalDate.parse(date,dtf);
                localDate = localDate.minusDays(1);
                date_before = dtf.format(localDate);
                reference = FirebaseDatabase.getInstance().getReference("Users").child(id).child("Nutrition").child(date).child("Calories");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            cal = cal + Double.parseDouble(Objects.requireNonNull(dataSnapshot.getValue()).toString());
                        }
                        reference2 = FirebaseDatabase.getInstance().getReference("Users").child(id).child("Personal Data");
                        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.child("Statistics").child(date_before).child("Weight").exists()) {
                                    weight = Double.valueOf(Objects.requireNonNull(snapshot.child("Statistics").child(date_before).child("Weight").getValue()).toString());
                                }
                                else {
                                    weight = Double.valueOf(Objects.requireNonNull(snapshot.child("Weight").getValue()).toString());
                                }
                                if (snapshot.child("Statistics").child(date).child("Steps").exists()) {
                                    steps = Integer.valueOf(Objects.requireNonNull(snapshot.child("Statistics").child(date).child("Steps").getValue()).toString());
                                }
                                if (snapshot.child("Statistics").child(date).child("Training Time").exists()) {
                                    train_time = Double.valueOf(Objects.requireNonNull(snapshot.child("Statistics").child(date).child("Training Time").getValue()).toString());
                                }
                                cals_burn = (steps * 0.04) + (360 * (train_time/60));
                                cals_burn = Double.valueOf(df.format(cals_burn));
                                bmr = Double.valueOf(Objects.requireNonNull(snapshot.child("BMR2").getValue()).toString());
                                weight = weight * 7716.17; // calories conversion
                                weight = weight - cals_burn + cal -bmr;
                                weight = weight / 7716.17;
                                weight = Double.valueOf(df.format(weight));
                                walk_time = Double.valueOf(df.format((cal * 200) / (5 * 3.5 * weight)));
                                run_time = Double.valueOf(df.format((cal * 200) / (11 * 3.5 * weight)));
                                bicycle_time = Double.valueOf(df.format((cal * 200) / (7 * 3.5 * weight)));
                                if (cal > 0) {
                                    reference2.child("Statistics").child(date).child("Weight").setValue(weight);
                                }
                                statistics_details();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
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
    @SuppressLint("SetTextI18n")
    public void statistics_details() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.statistics_details);
        Objects.requireNonNull(dialog.getWindow()).setLayout(1300,1800);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView textview = dialog.findViewById(R.id.textView97);
        TextView textview1 = dialog.findViewById(R.id.textView99);
        TextView textview2 = dialog.findViewById(R.id.textView101);
        TextView textview3 = dialog.findViewById(R.id.textView103);
        TextView textview4 = dialog.findViewById(R.id.textView105);
        TextView textview5 = dialog.findViewById(R.id.textView139);
        TextView textview6 = dialog.findViewById(R.id.textView141);
        TextView textview7 = dialog.findViewById(R.id.textView143);
        textview.setText(weight.toString());
        textview1.setText(steps.toString());
        textview2.setText(train_time.toString());
        textview3.setText(cals_burn.toString());
        textview4.setText(cal.toString());
        textview5.setText(walk_time.toString()+ " " + "min");
        textview6.setText(run_time.toString()+ " " + "min");
        textview7.setText(bicycle_time.toString()+ " " + "min");
        dialog.show();
    }
}