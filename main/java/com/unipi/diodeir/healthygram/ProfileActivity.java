package com.unipi.diodeir.healthygram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    TextView textView16,textView17,textView18,textView19,textView20,textView21,textView74,textView75;
    EditText editTextNumber4,editTextNumberDecimal7,editTextNumberDecimal8;
    ImageButton imageButton21,imageButton22,imageButton23,imageButton24,imageButton25,imageButton26,imageButton27,imageButton48;
    DatabaseReference reference;
    FirebaseAuth auth;
    String id;
    Integer flag,flag1,flag2,flag3,flag4;
    Button button6;
    double bmi,bmr,weight,height,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textView16 = findViewById(R.id.textView16);
        textView17 = findViewById(R.id.textView17);
        textView18 = findViewById(R.id.textView18);
        textView19 = findViewById(R.id.textView19);
        textView20 = findViewById(R.id.textView20);
        textView21 = findViewById(R.id.textView21);
        textView74 = findViewById(R.id.textView74);
        textView75 = findViewById(R.id.textView75);
        button6 = findViewById(R.id.button6);
        editTextNumber4 = findViewById(R.id.editTextNumber4);
        editTextNumberDecimal7 = findViewById(R.id.editTextNumberDecimal7);
        editTextNumberDecimal8 = findViewById(R.id.editTextNumberDecimal8);
        imageButton21 = findViewById(R.id.imageButton21);
        imageButton22 = findViewById(R.id.imageButton22);
        imageButton23 = findViewById(R.id.imageButton23);
        imageButton24 = findViewById(R.id.imageButton24);
        imageButton25 = findViewById(R.id.imageButton25);
        imageButton26 = findViewById(R.id.imageButton26);
        imageButton27 = findViewById(R.id.imageButton27);
        imageButton48 = findViewById(R.id.imageButton48);
        auth = FirebaseAuth.getInstance();
        id = auth.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(id).child("Personal Data");
        flag = flag1 = flag2 = flag3 = flag4 = 0;
        SpannableString content = new SpannableString("Content");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView21.setText(content);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                textView21.setText(Objects.requireNonNull(snapshot.child("Gender").getValue()).toString());
                editTextNumber4.setText(Objects.requireNonNull(snapshot.child("Age").getValue()).toString());
                editTextNumberDecimal7.setText(Objects.requireNonNull(snapshot.child("Weight").getValue()).toString());
                editTextNumberDecimal8.setText(Objects.requireNonNull(snapshot.child("Height").getValue()).toString());
                textView75.setText(Objects.requireNonNull(snapshot.child("Exercise").getValue()).toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                textView21.setText(error.getMessage());
                editTextNumber4.setText(error.getMessage());
                editTextNumberDecimal7.setText(error.getMessage());
                editTextNumberDecimal8.setText(error.getMessage());
                textView75.setText(error.getMessage());
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
    public void edit(View view) {
        if (imageButton24.isPressed()) {
            button6.setVisibility(View.VISIBLE);
            PopupMenu popup = new PopupMenu(this, view);
            popup.setOnMenuItemClickListener(this);
            popup.inflate(R.menu.gender);
            popup.show();
        }
        else if (imageButton25.isPressed()) {
            button6.setVisibility(View.VISIBLE);
            editTextNumber4.setEnabled(true);
        }
        else if (imageButton26.isPressed()) {
            button6.setVisibility(View.VISIBLE);
            editTextNumberDecimal7.setEnabled(true);
        }
        else if (imageButton27.isPressed()) {
            button6.setVisibility(View.VISIBLE);
            editTextNumberDecimal8.setEnabled(true);
        }
        else if (imageButton48.isPressed()) {
            button6.setVisibility(View.VISIBLE);
            PopupMenu popup = new PopupMenu(this, view);
            popup.setOnMenuItemClickListener(this);
            popup.inflate(R.menu.exercise);
            popup.show();
        }
    }
    public void done(View view) {
        if (!textView21.getText().toString().isEmpty()){
            reference.child("Gender").setValue(textView21.getText().toString());
            flag = 1;
        }
        else {
            Toast.makeText(this,"Gender must be filled in.",Toast.LENGTH_LONG).show();
        }
        if (!editTextNumber4.getText().toString().isEmpty() && Integer.parseInt(editTextNumber4.getText().toString())>0){
            reference.child("Age").setValue(Integer.parseInt(editTextNumber4.getText().toString()));
            flag1 = 1;
            editTextNumber4.setEnabled(false);
        }
        else {
            Toast.makeText(this,"Age must be filled in or invalid number.",Toast.LENGTH_LONG).show();
        }
        if (!editTextNumberDecimal7.getText().toString().isEmpty() && Double.parseDouble(editTextNumberDecimal7.getText().toString())>0){
            reference.child("Weight").setValue(Double.parseDouble(editTextNumberDecimal7.getText().toString()));
            flag2 = 1;
            editTextNumberDecimal7.setEnabled(false);
        }
        else {
            Toast.makeText(this,"Weight must be filled in or invalid number.",Toast.LENGTH_LONG).show();
        }
        if (!editTextNumberDecimal8.getText().toString().isEmpty() && Double.parseDouble(editTextNumberDecimal8.getText().toString())>0){
            reference.child("Height").setValue(Double.parseDouble(editTextNumberDecimal8.getText().toString()));
            flag3 = 1;
            editTextNumberDecimal8.setEnabled(false);
        }
        else {
            Toast.makeText(this,"Height must be filled in or invalid number.",Toast.LENGTH_LONG).show();
        }
        if (!textView75.getText().toString().isEmpty()) {
            reference.child("Exercise").setValue(textView75.getText().toString());
            flag4 = 1;
        }
        else {
            Toast.makeText(this,"Exercise must be filled in.",Toast.LENGTH_LONG).show();
        }
        if (flag == 1 && flag1 == 1 && flag2 == 1 && flag3 == 1 && flag4 == 1) {
            age = Double.parseDouble(editTextNumber4.getText().toString());
            weight = Double.parseDouble(editTextNumberDecimal7.getText().toString());
            height = Double.parseDouble(editTextNumberDecimal8.getText().toString());
            bmi = weight/(height*height);
            if (textView21.getText().toString().equals("Male")) {
                bmr = (10*weight)+(625*height)-(5*age)+5;
            }
            else {
                bmr = (10*weight)+(625*height)-(5*age)-161;
            }
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            decimalFormat.setMaximumFractionDigits(2);
            bmr = Double.parseDouble(decimalFormat.format(bmr));
            bmi = Double.parseDouble(decimalFormat.format(bmi));
            reference.child("BMR").setValue(bmr);
            if (Objects.equals(textView75.getText().toString(), "Sedentary: little or no exercise")) {
                bmr = bmr+bmr*(20f/100.0f);
            }
            else if (Objects.equals(textView75.getText().toString(), "Exercise 1–3 times/week")) {
                bmr = bmr+bmr*(37.5f/100.0f);
            }
            else if (Objects.equals(textView75.getText().toString(), "Exercise 4–5 times/week")) {
                bmr = bmr+bmr*(46.5f/100.0f);
            }
            else if (Objects.equals(textView75.getText().toString(), "Daily or intense exercise 3–4 times/week")) {
                bmr = bmr+bmr*(55f/100.0f);
            }
            else if (Objects.equals(textView75.getText().toString(), "Intense exercise 6–7 times/week")) {
                bmr = bmr+bmr*(72.5f/100.0f);
            }
            else if (Objects.equals(textView75.getText().toString(), "Intense exercise daily or physical job")) {
                bmr = bmr+bmr*(90f/100.0f);
            }
            bmr = Double.parseDouble(decimalFormat.format(bmr));
            reference.child("BMI").setValue(bmi);
            reference.child("BMR2").setValue(bmr);
            Toast.makeText(this,"Changes have been saved successfully!",Toast.LENGTH_LONG).show();
            button6.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            textView21.setText(item.getTitle());
        }
        else if (item.getItemId() == R.id.item2) {
            textView21.setText(item.getTitle());
        }
        else if (item.getItemId() == R.id.exercise1) {
            textView75.setText(item.getTitle());
        }
        else if (item.getItemId() == R.id.exercise2) {
            textView75.setText(item.getTitle());
        }
        else if (item.getItemId() == R.id.exercise3) {
            textView75.setText(item.getTitle());
        }
        else if (item.getItemId() == R.id.exercise4) {
            textView75.setText(item.getTitle());
        }
        else if (item.getItemId() == R.id.exercise5) {
            textView75.setText(item.getTitle());
        }
        else if (item.getItemId() == R.id.exercise6) {
            textView75.setText(item.getTitle());
        }
        return true;
    }
}