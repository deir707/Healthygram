package com.unipi.diodeir.healthygram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ItemTipsActivity extends AppCompatActivity {
    TextView textView63,textView64;
    ImageButton imageButton40,imageButton41,imageButton42;
    ImageView imageView7;
    StorageReference storageReference;
    DatabaseReference reference;
    String title;
    Integer tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_tips);
        textView63 = findViewById(R.id.textView63);
        textView64 = findViewById(R.id.textView64);
        imageButton40 = findViewById(R.id.imageButton40);
        imageButton41 = findViewById(R.id.imageButton41);
        imageButton42 = findViewById(R.id.imageButton42);
        imageView7 = findViewById(R.id.imageView7);
        storageReference = FirebaseStorage.getInstance().getReference();
        title = getIntent().getStringExtra("Title");
        textView63.setText(title);
        title = textView63.getText().toString().substring(4);
        reference = FirebaseDatabase.getInstance().getReference();
        tip = 0;

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (title.equals("Limit sugary drinks")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Eat nuts and seeds")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Avoid ultra-processed foods")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Don’t fear coffee")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Eat fatty fish")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Get enough sleep")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Feed your gut bacteria")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Stay hydrated")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Don’t eat heavily charred meats")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Avoid bright lights before sleep")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Take vitamin D if you’re deficient")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Eat plenty of fruits and vegetables")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Eat adequate protein")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Get moving")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Don’t smoke or use drugs, and only drink in moderation")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Use extra virgin olive oil")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Minimize your sugar intake")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Limit refined carbs")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Lift weights")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Avoid artificial trans fats")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Use plenty of herbs and spices")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Nurture your social relationships")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Occasionally track your food intake")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Get rid of excess belly fat")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Avoid restrictive diets")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Eat whole eggs")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Meditate")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Healthy Tips").child(title).getValue()).toString());
                    tip = 1;
                }
                else if (title.equals("Consult a professional")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Gym Tips").child(title).getValue()).toString());
                    tip = 2;
                }
                else if (title.equals("Focus on proper form")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Gym Tips").child(title).getValue()).toString());
                    tip = 2;
                }
                else if (title.equals("Listen to your body")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Gym Tips").child(title).getValue()).toString());
                    tip = 2;
                }
                else if (title.equals("Mix it up")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Gym Tips").child(title).getValue()).toString());
                    tip = 2;
                }
                else if (title.equals("Set realistic goals")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Gym Tips").child(title).getValue()).toString());
                    tip = 2;
                }
                else if (title.equals("Start slow")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Gym Tips").child(title).getValue()).toString());
                    tip = 2;
                }
                else if (title.equals("Stay always hydrated")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Gym Tips").child(title).getValue()).toString());
                    tip = 2;
                }
                else if (title.equals("Warm up and cool down")){
                    textView64.setText(Objects.requireNonNull(snapshot.child("Gym Tips").child(title).getValue()).toString());
                    tip = 2;
                }
                textView64.setMovementMethod(new ScrollingMovementMethod());
                if (tip == 1) {
                    StorageReference image1 = storageReference.child("healthy_tips.png");
                    try {
                        File localFile = File.createTempFile("tempImage","jpg");
                        image1.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                imageView7.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (tip == 2) {
                    StorageReference image1 = storageReference.child("coach.png");
                    try {
                        File localFile = File.createTempFile("tempImage","jpg");
                        image1.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                imageView7.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                textView64.setText(error.getMessage());
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