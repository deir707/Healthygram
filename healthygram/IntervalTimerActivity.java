package com.unipi.diodeir.healthygram;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IntervalTimerActivity extends AppCompatActivity {
    TextView textView107;
    ImageButton imageButton61,imageButton62,imageButton63,imageButton70,imageButton71,imageButton78,imageButton79,imageButton80,imageButton81;
    EditText editTextNumber3,editTextNumber5,editTextNumber6,editTextNumber7,editTextNumber8;
    Integer sets,min1,min2,sec1,sec2;
    Calendar calendar = Calendar.getInstance();
    @SuppressLint("SimpleDateFormat") Format f1 = new SimpleDateFormat("mm");
    @SuppressLint("SimpleDateFormat") Format f2 = new SimpleDateFormat("ss");

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval_timer);
        textView107 = findViewById(R.id.textView107);
        imageButton61 = findViewById(R.id.imageButton61);
        imageButton62 = findViewById(R.id.imageButton62);
        imageButton63 = findViewById(R.id.imageButton63);
        imageButton70 = findViewById(R.id.imageButton70);
        imageButton71 = findViewById(R.id.imageButton71);
        imageButton78 = findViewById(R.id.imageButton78);
        imageButton79 = findViewById(R.id.imageButton79);
        imageButton80 = findViewById(R.id.imageButton80);
        imageButton81 = findViewById(R.id.imageButton81);
        editTextNumber3 = findViewById(R.id.editTextNumber3);
        editTextNumber5 = findViewById(R.id.editTextNumber5);
        editTextNumber6 = findViewById(R.id.editTextNumber6);
        editTextNumber7 = findViewById(R.id.editTextNumber7);
        editTextNumber8 = findViewById(R.id.editTextNumber8);
        sets = 1;
        min1 = min2 = sec1 = sec2 = 0;
        calendar.set(Calendar.SECOND,1);
        calendar.set(Calendar.MINUTE,0);
        editTextNumber3.setText(sets.toString());
        editTextNumber5.setText(f1.format(calendar.getTime()));
        editTextNumber6.setText(f2.format(calendar.getTime()));
        editTextNumber7.setText(f1.format(calendar.getTime()));
        editTextNumber8.setText(f1.format(calendar.getTime()));
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
    public void minus (View view) {
        sets = Integer.valueOf(editTextNumber3.getText().toString());
        if (!(sets <=1)) {
            sets--;
            editTextNumber3.setText(sets.toString());
        }
    }

    @SuppressLint("SetTextI18n")
    public void plus (View view) {
        sets = Integer.valueOf(editTextNumber3.getText().toString());
        sets++;
        editTextNumber3.setText(sets.toString());
    }
    @SuppressLint("SetTextI18n")
    public void minus2 (View view) {
        min1 = Integer.valueOf(editTextNumber5.getText().toString());
        sec1 = Integer.valueOf(editTextNumber6.getText().toString());
        sec1--;
        calendar.set(Calendar.SECOND,sec1);
        calendar.set(Calendar.MINUTE,min1);
        editTextNumber5.setText(f1.format(calendar.getTime()));
        editTextNumber6.setText(f2.format(calendar.getTime()));
    }
    @SuppressLint("SetTextI18n")
    public void plus2 (View view) {
        min1 = Integer.valueOf(editTextNumber5.getText().toString());
        sec1 = Integer.valueOf(editTextNumber6.getText().toString());
        sec1++;
        calendar.set(Calendar.SECOND,sec1);
        calendar.set(Calendar.MINUTE,min1);
        editTextNumber5.setText(f1.format(calendar.getTime()));
        editTextNumber6.setText(f2.format(calendar.getTime()));
    }
    @SuppressLint("SetTextI18n")
    public void minus3 (View view) {
        min2 = Integer.valueOf(editTextNumber7.getText().toString());
        sec2 = Integer.valueOf(editTextNumber8.getText().toString());
        sec2--;
        calendar.set(Calendar.SECOND,sec2);
        calendar.set(Calendar.MINUTE,min2);
        editTextNumber7.setText(f1.format(calendar.getTime()));
        editTextNumber8.setText(f2.format(calendar.getTime()));
    }
    @SuppressLint("SetTextI18n")
    public void plus3 (View view) {
        min2 = Integer.valueOf(editTextNumber7.getText().toString());
        sec2 = Integer.valueOf(editTextNumber8.getText().toString());
        sec2++;
        calendar.set(Calendar.SECOND,sec2);
        calendar.set(Calendar.MINUTE,min2);
        editTextNumber7.setText(f1.format(calendar.getTime()));
        editTextNumber8.setText(f2.format(calendar.getTime()));
    }
    public void start (View view) {
        if (Integer.parseInt(editTextNumber5.getText().toString()) == 0 && Integer.parseInt(editTextNumber6.getText().toString()) == 0) {
            Toast.makeText(this,"You have to rearrange workout time.",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, StartTimerActivity.class);
            sets = Integer.valueOf(editTextNumber3.getText().toString());
            min1 = Integer.valueOf(editTextNumber5.getText().toString());
            sec1 = Integer.valueOf(editTextNumber6.getText().toString());
            min2 = Integer.valueOf(editTextNumber7.getText().toString());
            sec2 = Integer.valueOf(editTextNumber8.getText().toString());
            intent.putExtra("sets",sets);
            intent.putExtra("workmin",min1);
            intent.putExtra("worksec",sec1);
            intent.putExtra("restmin",min2);
            intent.putExtra("restsec",sec2);
            startActivity(intent);
        }
    }
}