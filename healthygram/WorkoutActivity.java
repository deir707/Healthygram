package com.unipi.diodeir.healthygram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class WorkoutActivity extends AppCompatActivity {
    TextView textView13;
    ImageButton imageButton15,imageButton16,imageButton17;
    ListView listView5;
    String[] menuList = {"Step Counter","Interval Timer","Exercises","Helpful tips"};
    int[] itemImages = {R.drawable.baseline_directions_run_24, R.drawable.baseline_timer_24, R.drawable.baseline_fitness_center_24, R.drawable.baseline_emoji_objects_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        textView13 = findViewById(R.id.textView13);
        imageButton15 = findViewById(R.id.imageButton15);
        imageButton16 = findViewById(R.id.imageButton16);
        imageButton17 = findViewById(R.id.imageButton17);
        listView5 = findViewById(R.id.listView5);

        MenuAdapter menuAdapter = new MenuAdapter(getApplicationContext(),menuList,itemImages);
        listView5.setAdapter(menuAdapter);
        listView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    step_counter();
                }
                else if (position == 1) {
                    timer();
                }
                else if (position == 2) {
                    exercises();
                }
                else if (position == 3) {
                    tips1();
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
    public void step_counter () {
        Intent intent = new Intent(this, StepsCounterActivity.class);
        startActivity(intent);
    }
    public void timer () {
        Intent intent = new Intent(this, IntervalTimerActivity.class);
        startActivity(intent);
    }
    public void exercises () {
        Intent intent = new Intent(this, MuscleGroupsActivity.class);
        startActivity(intent);
    }
    public void tips1 () {
        Intent intent = new Intent(this, WorkoutTipsActivity.class);
        startActivity(intent);
    }
}