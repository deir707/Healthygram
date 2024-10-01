package com.unipi.diodeir.healthygram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
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

public class AchievementsActivity extends AppCompatActivity {
    TextView textView14,textView83,textView84,textView85,textView87,textView88,textView89,textView91,textView92,textView93;
    ImageButton imageButton18,imageButton19,imageButton20,imageButton49,imageButton50,imageButton51,imageButton52,imageButton53,imageButton54,imageButton55,imageButton56,imageButton57;
    ImageView imageView8;
    StorageReference storageReference;
    DatabaseReference reference;
    FirebaseAuth auth;
    String id;
    Boolean flag1,flag2,flag3,flag4,flag5,flag6,flag7,flag8,flag9;
    Integer steps;
    Double cals_burn, train_time;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        textView14 = findViewById(R.id.textView14);
        textView83 = findViewById(R.id.textView83);
        textView84 = findViewById(R.id.textView84);
        textView85 = findViewById(R.id.textView85);
        textView87 = findViewById(R.id.textView87);
        textView88 = findViewById(R.id.textView88);
        textView89 = findViewById(R.id.textView89);
        textView91 = findViewById(R.id.textView91);
        textView92 = findViewById(R.id.textView92);
        textView93 = findViewById(R.id.textView93);
        imageView8 = findViewById(R.id.imageView8);
        imageButton18 = findViewById(R.id.imageButton18);
        imageButton19 = findViewById(R.id.imageButton19);
        imageButton20 = findViewById(R.id.imageButton20);
        imageButton49 = findViewById(R.id.imageButton49);
        imageButton50 = findViewById(R.id.imageButton50);
        imageButton51 = findViewById(R.id.imageButton51);
        imageButton52 = findViewById(R.id.imageButton52);
        imageButton53 = findViewById(R.id.imageButton53);
        imageButton54 = findViewById(R.id.imageButton54);
        imageButton55 = findViewById(R.id.imageButton55);
        imageButton56 = findViewById(R.id.imageButton56);
        imageButton57 = findViewById(R.id.imageButton57);
        storageReference = FirebaseStorage.getInstance().getReference();
        flag1 = flag2 = flag3 = flag4 = flag5 = flag6 = flag7 = flag8 = flag9 = false;
        steps = 0;
        train_time = 0.0;
        cals_burn = 0.0;
        auth = FirebaseAuth.getInstance();
        id = auth.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(id).child("Personal Data").child("Statistics");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.child("Steps").exists()) {
                        steps = steps + (Integer.parseInt(Objects.requireNonNull(dataSnapshot.child("Steps").getValue()).toString()));
                    }
                    if (dataSnapshot.child("Training Time").exists()) {
                        train_time = train_time + (Double.parseDouble(Objects.requireNonNull(dataSnapshot.child("Training Time").getValue()).toString()));
                    }
                    cals_burn = (steps * 0.04) + (360 * (train_time/60));
                    if (cals_burn >= 400) {
                        textView87.setTextColor(Color.parseColor("#CD7F32"));
                        textView87.setTypeface(null, Typeface.BOLD);
                        imageButton52.setImageResource(R.drawable.baseline_bronze_medal_24);
                        flag4 = true;
                    }
                    if (cals_burn >= 700) {
                        textView88.setTextColor(Color.parseColor("#7F7F7F"));
                        textView88.setTypeface(null, Typeface.BOLD);
                        imageButton53.setImageResource(R.drawable.baseline_silver_medal_24);
                        flag5 = true;
                    }
                    if (cals_burn >= 1000) {
                        textView89.setTextColor(Color.parseColor("#D9B700"));
                        textView89.setTypeface(null, Typeface.BOLD);
                        imageButton54.setImageResource(R.drawable.baseline_gold_medal_24);
                        flag6 = true;
                    }
                }
                if (steps >= 100000) {
                    textView83.setTextColor(Color.parseColor("#CD7F32"));
                    textView83.setTypeface(null, Typeface.BOLD);
                    imageButton49.setImageResource(R.drawable.baseline_bronze_medal_24);
                    flag1 = true;
                }
                if (steps >= 150000) {
                    textView84.setTextColor(Color.parseColor("#7F7F7F"));
                    textView84.setTypeface(null, Typeface.BOLD);
                    imageButton50.setImageResource(R.drawable.baseline_silver_medal_24);
                    flag2 = true;
                }
                if (steps >= 200000) {
                    textView85.setTextColor(Color.parseColor("#D9B700"));
                    textView85.setTypeface(null, Typeface.BOLD);
                    imageButton51.setImageResource(R.drawable.baseline_gold_medal_24);
                    flag3 = true;
                }
                if (train_time >= 9360) {
                    textView91.setTextColor(Color.parseColor("#CD7F32"));
                    textView91.setTypeface(null, Typeface.BOLD);
                    imageButton55.setImageResource(R.drawable.baseline_bronze_medal_24);
                    flag7 = true;
                }
                if (train_time >= 12480) {
                    textView92.setTextColor(Color.parseColor("#7F7F7F"));
                    textView92.setTypeface(null, Typeface.BOLD);
                    imageButton56.setImageResource(R.drawable.baseline_silver_medal_24);
                    flag8 = true;
                }
                if (train_time >= 15600) {
                    textView93.setTextColor(Color.parseColor("#D9B700"));
                    textView93.setTypeface(null, Typeface.BOLD);
                    imageButton57.setImageResource(R.drawable.baseline_gold_medal_24);
                    flag9 = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        StorageReference image1 = storageReference.child("achievements.png");
        try {
            File localFile = File.createTempFile("tempImage","jpg");
            image1.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageView8.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void bronze1 (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (flag1) {
            builder.setTitle("Bronze Medal");
            builder.setMessage("Congratulations! You have made 100.000 steps!");
        }
        else {
            builder.setTitle("Bronze Medal");
            builder.setMessage("You have to make 100.000 steps in order to take the medal.");
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void silver1 (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (flag2) {
            builder.setTitle("Silver Medal");
            builder.setMessage("Congratulations! You have made 150.000 steps!");
        }
        else {
            builder.setTitle("Silver Medal");
            builder.setMessage("You have to make 150.000 steps in order to take the medal.");
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void gold1 (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (flag3) {
            builder.setTitle("Gold Medal");
            builder.setMessage("Congratulations! You have made 200.000 steps!");
        }
        else {
            builder.setTitle("Gold Medal");
            builder.setMessage("You have to make 200.000 steps in order to take the medal.");
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void bronze2 (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (flag4) {
            builder.setTitle("Bronze Medal");
            builder.setMessage("Congratulations! You have burned 400 calories in one day!");
        }
        else {
            builder.setTitle("Bronze Medal");
            builder.setMessage("You have to burn 400 calories in one day in order to take the medal.");
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void silver2 (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (flag5) {
            builder.setTitle("Silver Medal");
            builder.setMessage("Congratulations! You have lost 700 calories in one day!");
        }
        else {
            builder.setTitle("Silver Medal");
            builder.setMessage("You have to burn 700 calories in one day in order to take the medal.");
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void gold2 (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (flag6) {
            builder.setTitle("Gold Medal");
            builder.setMessage("Congratulations! You have burned 1000 calories in one day!");
        }
        else {
            builder.setTitle("Gold Medal");
            builder.setMessage("You have to burn 1000 calories in one day in order to take the medal.");
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void bronze3 (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (flag7) {
            builder.setTitle("Bronze Medal");
            builder.setMessage("Congratulations! You have been training over than 156 hours!");
        }
        else {
            builder.setTitle("Bronze Medal");
            builder.setMessage("You have to train over than 156 hours in order to take the medal.");
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void silver3 (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (flag8) {
            builder.setTitle("Silver Medal");
            builder.setMessage("Congratulations! You have been training over than 208 hours!");
        }
        else {
            builder.setTitle("Silver Medal");
            builder.setMessage("You have to train over than 208 hours in order to take the medal.");
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void gold3 (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (flag9) {
            builder.setTitle("Gold Medal");
            builder.setMessage("Congratulations! You have been training over than 260 hours!");
        }
        else {
            builder.setTitle("Gold Medal");
            builder.setMessage("You have to train over than 260 hours in order to take the medal.");
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}