package com.unipi.diodeir.healthygram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.Objects;


public class PersonalizeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    TextView textView2,textView3,textView4,textView5,textView6,textView77;
    RadioButton radioButton,radioButton2;
    EditText editTextNumber, editTextNumberDecimal,editTextNumberDecimal2;
    DatabaseReference reference;
    FirebaseAuth auth;
    Integer flag,flag1,flag2,flag3,flag4;
    String id;
    double bmi,bmr,weight,height,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalize);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView77 = findViewById(R.id.textView77);
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
        editTextNumberDecimal2 = findViewById(R.id.editTextNumberDecimal2);
        auth = FirebaseAuth.getInstance();
        id = auth.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(id).child("Personal Data");
        flag = flag1 = flag2 = flag3 = flag4 = 0;
    }
    public void radiobutton1(View view){
        if (radioButton.isChecked()){
            radioButton.setChecked(true);
            radioButton2.setChecked(false);
        }
        else {
            radioButton2.setChecked(true);
        }
    }
    public void radiobutton2(View view){
        if (radioButton2.isChecked()){
            radioButton2.setChecked(true);
            radioButton.setChecked(false);
        }
        else {
            radioButton.setChecked(true);
        }
    }
    public void popup2(View view) {
        if (textView77.isPressed()) {
            PopupMenu popup = new PopupMenu(this, view);
            popup.setOnMenuItemClickListener(this);
            popup.inflate(R.menu.exercise);
            popup.show();
        }
    }
    public void register2(View view) {
        if (radioButton.isChecked()){
            reference.child("Gender").setValue(radioButton.getText().toString());
            flag = 1;
        }
        else if (radioButton2.isChecked()){
            reference.child("Gender").setValue(radioButton2.getText().toString());
            flag = 1;
        }
        else {
            Toast.makeText(this,"Gender must be filled in.",Toast.LENGTH_LONG).show();
        }
        if (!editTextNumber.getText().toString().isEmpty() && Integer.parseInt(editTextNumber.getText().toString())>0){
            reference.child("Age").setValue(Integer.parseInt(editTextNumber.getText().toString()));
            flag1 = 1;
        }
        else {
            Toast.makeText(this,"Age must be filled in or invalid number.",Toast.LENGTH_LONG).show();
        }
        if (!editTextNumberDecimal.getText().toString().isEmpty() && Double.parseDouble(editTextNumberDecimal.getText().toString())>0){
            reference.child("Weight").setValue(Double.parseDouble(editTextNumberDecimal.getText().toString()));
            flag2 = 1;
        }
        else {
            Toast.makeText(this,"Weight must be filled in or invalid number.",Toast.LENGTH_LONG).show();
        }
        if (!editTextNumberDecimal2.getText().toString().isEmpty() && Double.parseDouble(editTextNumberDecimal2.getText().toString())>0){
            reference.child("Height").setValue(Double.parseDouble(editTextNumberDecimal2.getText().toString()));
            flag3 = 1;
        }
        else {
            Toast.makeText(this,"Height must be filled in or invalid number.",Toast.LENGTH_LONG).show();
        }
        if (!textView77.getText().toString().isEmpty()) {
            reference.child("Exercise").setValue(textView77.getText().toString());
            flag4 = 1;
        }
        else {
            Toast.makeText(this,"Exercise must be filled in.",Toast.LENGTH_LONG).show();
        }
        if (flag == 1 && flag1 == 1 && flag2 == 1 && flag3 == 1 && flag4 == 1) {
            age = Double.parseDouble(editTextNumber.getText().toString());
            weight = Double.parseDouble(editTextNumberDecimal.getText().toString());
            height = Double.parseDouble(editTextNumberDecimal2.getText().toString());
            bmi = weight/(height*height);
            if (radioButton.getText().toString().equals("Male")) {
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
            if (Objects.equals(textView77.getText().toString(), "Sedentary: little or no exercise")) {
                bmr = bmr+bmr*(20f/100.0f);
            }
            else if (Objects.equals(textView77.getText().toString(), "Exercise 1–3 times/week")) {
                bmr = bmr+bmr*(37.5f/100.0f);
            }
            else if (Objects.equals(textView77.getText().toString(), "Exercise 4–5 times/week")) {
                bmr = bmr+bmr*(46.5f/100.0f);
            }
            else if (Objects.equals(textView77.getText().toString(), "Daily or intense exercise 3–4 times/week")) {
                bmr = bmr+bmr*(55f/100.0f);
            }
            else if (Objects.equals(textView77.getText().toString(), "Intense exercise 6–7 times/week")) {
                bmr = bmr+bmr*(72.5f/100.0f);
            }
            else if (Objects.equals(textView77.getText().toString(), "Intense exercise daily or physical job")) {
                bmr = bmr+bmr*(90f/100.0f);
            }
            bmr = Double.parseDouble(decimalFormat.format(bmr));
            reference.child("BMI").setValue(bmi);
            reference.child("BMR2").setValue(bmr);
            Toast.makeText(this,"You have successfully registered!",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.exercise1) {
            textView77.setText(item.getTitle());
        }
        else if (item.getItemId() == R.id.exercise2) {
            textView77.setText(item.getTitle());
        }
        else if (item.getItemId() == R.id.exercise3) {
            textView77.setText(item.getTitle());
        }
        else if (item.getItemId() == R.id.exercise4) {
            textView77.setText(item.getTitle());
        }
        else if (item.getItemId() == R.id.exercise5) {
            textView77.setText(item.getTitle());
        }
        else if (item.getItemId() == R.id.exercise6) {
            textView77.setText(item.getTitle());
        }
        return true;
    }
}