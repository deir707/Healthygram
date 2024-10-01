package com.unipi.diodeir.healthygram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
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

public class FoodCalorieActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    TextView textView23,textView66,textView67,textView68,textView69,textView70;
    ImageButton imageButton31,imageButton32,imageButton33;
    CalendarView calendarView;
    DatabaseReference reference1,reference2;
    FirebaseAuth auth;
    String id,date = "";
    Double sum = 0.0;
    double bmr;
    private static final DecimalFormat df = new DecimalFormat("0.0");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_calorie);
        textView23 = findViewById(R.id.textView23);
        textView66 = findViewById(R.id.textView66);
        textView67 = findViewById(R.id.textView67);
        textView68 = findViewById(R.id.textView68);
        textView69 = findViewById(R.id.textView69);
        textView70 = findViewById(R.id.textView70);
        imageButton31 = findViewById(R.id.imageButton31);
        imageButton32 = findViewById(R.id.imageButton32);
        imageButton33 = findViewById(R.id.imageButton33);
        calendarView = findViewById(R.id.calendarView);
        auth = FirebaseAuth.getInstance();
        id = auth.getUid();
        reference2 = FirebaseDatabase.getInstance().getReference("Users").child(id).child("Personal Data").child("BMR2");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                sum = 0.0;
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

                reference1 = FirebaseDatabase.getInstance().getReference("Users").child(id).child("Nutrition").child(date).child("Calories");
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            sum = sum + Double.parseDouble(Objects.requireNonNull(dataSnapshot.getValue()).toString());
                        }

                        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                bmr = Double.parseDouble(Objects.requireNonNull(snapshot.getValue()).toString());
                                textView67.setText(String.valueOf(sum));
                                if (bmr >= sum) {
                                    textView69.setTextColor(Color.GREEN);
                                    textView69.setText("No extra exercise needed");
                                }
                                else {
                                    textView69.setTextColor(Color.RED);
                                    textView69.setText(df.format(sum - bmr));
                                }
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
    public void popup(View view) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.meals);
        popupMenu.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (date != null) {
            Intent intent = new Intent(this, MealActivity.class);
            intent.putExtra("Meal",item.getTitle());
            intent.putExtra("Date",date);
            startActivity(intent);
            return true;
        }
        else {
            Toast.makeText(this,"A day must be chosen first!",Toast.LENGTH_LONG).show();
            return false;
        }
    }
}