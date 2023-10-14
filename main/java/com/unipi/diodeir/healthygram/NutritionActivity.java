package com.unipi.diodeir.healthygram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;



public class NutritionActivity extends AppCompatActivity {
    TextView textView11;
    ImageButton imageButton9,imageButton10,imageButton11;
    ListView listView2;
    String[] menuList = {"BMI Calculator","BMR Calculator","Food Calorie","Helpful tips"};
    int[] itemImages = {R.drawable.bmi, R.drawable.bmr, R.drawable.food_calories, R.drawable.baseline_emoji_objects_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        textView11 = findViewById(R.id.textView11);
        imageButton9 = findViewById(R.id.imageButton9);
        imageButton10 = findViewById(R.id.imageButton10);
        imageButton11 = findViewById(R.id.imageButton11);
        listView2 = findViewById(R.id.listView2);

        MenuAdapter menuAdapter = new MenuAdapter(getApplicationContext(),menuList,itemImages);
        listView2.setAdapter(menuAdapter);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    bmi();
                }
                else if (position == 1) {
                    bmr();
                }
                else if (position == 2) {
                    food_calorie();
                }
                else if (position == 3) {
                    tips();
                }
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
    void bmi() {
        Intent intent = new Intent(this, BMIActivity.class);
        startActivity(intent);
    }
    void bmr() {
        Intent intent = new Intent(this, BMRActivity.class);
        startActivity(intent);
    }
    void food_calorie() {
        Intent intent = new Intent(this, FoodCalorieActivity.class);
        startActivity(intent);
    }
    void tips() {
        Intent intent = new Intent(this, NutritionTipsActivity.class);
        startActivity(intent);
    }
}