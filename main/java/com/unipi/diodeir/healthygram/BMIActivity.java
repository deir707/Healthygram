package com.unipi.diodeir.healthygram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

import com.ekn.gruzer.gaugelibrary.HalfGauge;
import com.ekn.gruzer.gaugelibrary.Range;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class BMIActivity extends AppCompatActivity {
    TextView textView22,textView26,textView27,textView28,textView29,textView30,textView31,textView32,textView33,textView34,textView35,textView36,textView37,textView38,textView39,textView40,textView41,textView42,textView43;
    ImageButton imageButton28,imageButton29,imageButton30;
    HalfGauge halfGauge;
    TableLayout table1;
    Range range1,range2,range3,range4,range5,range6,range7,range8;
    DatabaseReference reference;
    FirebaseAuth auth;
    String id;
    double bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);
        textView22 = findViewById(R.id.textView22);
        textView26 = findViewById(R.id.textView26);
        textView27 = findViewById(R.id.textView27);
        textView28 = findViewById(R.id.textView28);
        textView29 = findViewById(R.id.textView29);
        textView30 = findViewById(R.id.textView30);
        textView31 = findViewById(R.id.textView31);
        textView32 = findViewById(R.id.textView32);
        textView33 = findViewById(R.id.textView33);
        textView34 = findViewById(R.id.textView34);
        textView35 = findViewById(R.id.textView35);
        textView36 = findViewById(R.id.textView36);
        textView37 = findViewById(R.id.textView37);
        textView38 = findViewById(R.id.textView38);
        textView39 = findViewById(R.id.textView39);
        textView40 = findViewById(R.id.textView40);
        textView41 = findViewById(R.id.textView41);
        textView42 = findViewById(R.id.textView42);
        textView43 = findViewById(R.id.textView43);
        imageButton28 = findViewById(R.id.imageButton28);
        imageButton29 = findViewById(R.id.imageButton29);
        imageButton30 = findViewById(R.id.imageButton30);
        halfGauge = findViewById(R.id.halfGauge);
        table1 = findViewById(R.id.table1);
        range1 = new Range();
        range2 = new Range();
        range3 = new Range();
        range4 = new Range();
        range5 = new Range();
        range6 = new Range();
        range7 = new Range();
        range8 = new Range();
        auth = FirebaseAuth.getInstance();
        id = auth.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(id).child("Personal Data");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bmi = Double.parseDouble(Objects.requireNonNull(snapshot.child("BMI").getValue()).toString());
                halfGauge.setValue(bmi);

                if (bmi<16) {
                    textView28.setTextColor(Color.BLUE);
                    textView28.setPaintFlags(textView28.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    textView29.setTextColor(Color.BLUE);
                    textView29.setPaintFlags(textView29.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
                else if (bmi>16 && bmi<=17) {
                    textView30.setTextColor(Color.BLUE);
                    textView30.setPaintFlags(textView30.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    textView31.setTextColor(Color.BLUE);
                    textView31.setPaintFlags(textView31.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
                else if (bmi>17 && bmi<=18.5) {
                    textView32.setTextColor(Color.BLUE);
                    textView32.setPaintFlags(textView32.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    textView33.setTextColor(Color.BLUE);
                    textView33.setPaintFlags(textView33.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
                else if (bmi>18.5 && bmi<=25) {
                    textView34.setTextColor(Color.BLUE);
                    textView34.setPaintFlags(textView34.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    textView35.setTextColor(Color.BLUE);
                    textView35.setPaintFlags(textView35.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
                else if (bmi>25 && bmi<=30) {
                    textView36.setTextColor(Color.BLUE);
                    textView36.setPaintFlags(textView36.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    textView37.setTextColor(Color.BLUE);
                    textView37.setPaintFlags(textView37.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
                else if (bmi>30 && bmi<=35) {
                    textView38.setTextColor(Color.BLUE);
                    textView38.setPaintFlags(textView38.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    textView39.setTextColor(Color.BLUE);
                    textView39.setPaintFlags(textView39.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
                else if (bmi>35 && bmi<=40) {
                    textView40.setTextColor(Color.BLUE);
                    textView40.setPaintFlags(textView40.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    textView41.setTextColor(Color.BLUE);
                    textView41.setPaintFlags(textView41.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
                else {
                    textView42.setTextColor(Color.BLUE);
                    textView42.setPaintFlags(textView42.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    textView43.setTextColor(Color.BLUE);
                    textView43.setPaintFlags(textView43.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        add_gauge();
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
    public void add_gauge() {
        range1.setFrom(0);
        range1.setTo(15.9);
        range2.setFrom(16);
        range2.setTo(17);
        range3.setFrom(17.1);
        range3.setTo(18.5);
        range4.setFrom(18.6);
        range4.setTo(25);
        range5.setFrom(25.1);
        range5.setTo(30);
        range6.setFrom(30.1);
        range6.setTo(35);
        range7.setFrom(35.1);
        range7.setTo(40);
        range8.setFrom(40.1);
        range8.setTo(50);
        range1.setColor(Color.RED);
        range2.setColor(Color.MAGENTA);
        range3.setColor(Color.YELLOW);
        range4.setColor(Color.GREEN);
        range5.setColor(Color.YELLOW);
        range6.setColor(Color.MAGENTA);
        range7.setColor(Color.RED);
        range8.setColor(Color.BLACK);
        halfGauge.setMinValue(0);
        halfGauge.setMaxValue(50);
        halfGauge.addRange(range1);
        halfGauge.addRange(range2);
        halfGauge.addRange(range3);
        halfGauge.addRange(range4);
        halfGauge.addRange(range5);
        halfGauge.addRange(range6);
        halfGauge.addRange(range7);
        halfGauge.addRange(range8);
    }
}