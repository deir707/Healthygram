package com.unipi.diodeir.healthygram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExercisesActivity extends AppCompatActivity {

    TextView textView130, textView131;
    ImageButton imageButton83, imageButton84, imageButton85;
    RecyclerView recyclerView2;
    DatabaseReference reference;
    ExerciseAdapter exerciseAdapter;
    ArrayList<ListExercises> list;
    String muscle_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        textView130 = findViewById(R.id.textView130);
        textView131 = findViewById(R.id.textView131);
        imageButton83 = findViewById(R.id.imageButton83);
        imageButton84 = findViewById(R.id.imageButton84);
        imageButton85 = findViewById(R.id.imageButton85);
        recyclerView2 = findViewById(R.id.recyclerView2);
        reference = FirebaseDatabase.getInstance().getReference().child("Exercises");
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        exerciseAdapter = new ExerciseAdapter(this,list);
        recyclerView2.setAdapter(exerciseAdapter);
        muscle_group = getIntent().getStringExtra("Muscle group");
        textView131.setText(muscle_group);

        reference.child(muscle_group).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ListExercises listExercises = dataSnapshot.getValue(ListExercises.class);
                    list.add(listExercises);
                }
                exerciseAdapter.notifyDataSetChanged();
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