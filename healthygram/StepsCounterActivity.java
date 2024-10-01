package com.unipi.diodeir.healthygram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class StepsCounterActivity extends AppCompatActivity {
    TextView textView108,textView113,textView115, textView121, textView122;
    ImageButton imageButton64,imageButton65,imageButton66;
    ImageView imageView14;
    DatabaseReference reference;
    FirebaseAuth auth;
    Dialog dialog;
    String id,date = "";
    double MagnitudePrevious = 0;
    Integer stepCount, total;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_counter);
        textView108 = findViewById(R.id.textView108);
        textView113 = findViewById(R.id.textView113);
        textView115 = findViewById(R.id.textView115);
        textView121 = findViewById(R.id.textView121);
        textView122 = findViewById(R.id.textView122);
        imageView14 = findViewById(R.id.imageView14);
        imageButton64 = findViewById(R.id.imageButton64);
        imageButton65 = findViewById(R.id.imageButton65);
        imageButton66 = findViewById(R.id.imageButton66);
        stepCount = -1;
        total = -1;
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
        textView122.setText(date);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        auth = FirebaseAuth.getInstance();
        id = auth.getUid();

        reference = FirebaseDatabase.getInstance().getReference("Users").child(id).child("Personal Data").child("Statistics");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.child("Steps").exists()) {
                        total = total + (Integer.parseInt(Objects.requireNonNull(dataSnapshot.child("Steps").getValue()).toString()));
                    }
                }
                if (snapshot.child(date).child("Steps").exists()) {
                    stepCount = Integer.parseInt(Objects.requireNonNull(snapshot.child(date).child("Steps").getValue()).toString());
                    textView115.setText(stepCount.toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        SensorEventListener stepDetector = new SensorEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event != null) {
                    float x_acceleration = event.values[0];
                    float y_acceleration = event.values[1];
                    float z_acceleration = event.values[2];
                    double Magnitude = Math.sqrt(x_acceleration*x_acceleration + y_acceleration*y_acceleration + z_acceleration*z_acceleration);
                    double MagnitudeDelta = Magnitude - MagnitudePrevious;
                    MagnitudePrevious = Magnitude;
                    if (MagnitudeDelta > 6) {
                        stepCount++;
                        total++;
                        if (total == 100000) {
                            medal_achievement();
                        }
                        else if (total == 150000) {
                            medal_achievement();
                        }
                        else if (total == 200000) {
                            medal_achievement();
                        }
                    }
                    textView115.setText(stepCount.toString());
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);
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

    @Override
    protected void onPause() {
        super.onPause();
        reference.child(date).child("Steps").setValue(stepCount);
    }

    @Override
    protected void onStop() {
        super.onStop();
        reference.child(date).child("Steps").setValue(stepCount);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(date).child("Steps").exists()) {
                    stepCount = Integer.parseInt(Objects.requireNonNull(snapshot.child(date).child("Steps").getValue()).toString());
                    textView115.setText(stepCount.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "If you press back again, you will exit and the step counter will stop", Toast.LENGTH_LONG).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @SuppressLint("SetTextI18n")
    public void medal_achievement() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.medal_achievement);
        Objects.requireNonNull(dialog.getWindow()).setLayout(1200,1000);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        TextView textview = dialog.findViewById(R.id.textView123);
        ImageView imageView = dialog.findViewById(R.id.imageView15);
        if (total == 100000) {
            textview.setText("Congratulations! You have made 100.000 steps!");
            imageView.setImageResource(R.drawable.baseline_bronze_medal_24);
        }
        else if (total == 150000) {
            textview.setText("Congratulations! You have made 150.000 steps!");
            imageView.setImageResource(R.drawable.baseline_silver_medal_24);
        }
        else if (total == 200000) {
            textview.setText("Congratulations! You have made 200.000 steps!");
            imageView.setImageResource(R.drawable.baseline_gold_medal_24);
        }
        dialog.show();
    }
}