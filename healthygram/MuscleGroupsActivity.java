package com.unipi.diodeir.healthygram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MuscleGroupsActivity extends AppCompatActivity {
    TextView textView94, textView106, textView125, textView126, textView127, textView128, textView129;
    ImageButton imageButton58,imageButton59,imageButton60, imageButton73, imageButton74, imageButton75, imageButton76, imageButton77, imageButton82;
    StorageReference storageReference;
    String muscle_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_groups);
        textView94 = findViewById(R.id.textView94);
        textView106 = findViewById(R.id.textView106);
        textView125 = findViewById(R.id.textView125);
        textView126 = findViewById(R.id.textView126);
        textView127 = findViewById(R.id.textView127);
        textView128= findViewById(R.id.textView128);
        textView129 = findViewById(R.id.textView129);
        imageButton58 = findViewById(R.id.imageButton58);
        imageButton59 = findViewById(R.id.imageButton59);
        imageButton60 = findViewById(R.id.imageButton60);
        imageButton73 = findViewById(R.id.imageButton73);
        imageButton74 = findViewById(R.id.imageButton74);
        imageButton75 = findViewById(R.id.imageButton75);
        imageButton76 = findViewById(R.id.imageButton76);
        imageButton77 = findViewById(R.id.imageButton77);
        imageButton82 = findViewById(R.id.imageButton82);
        storageReference = FirebaseStorage.getInstance().getReference().child("Workout");

        StorageReference image1 = storageReference.child("Chest").child("chest.png");
        try {
            File localFile = File.createTempFile("tempImage","jpg");
            image1.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageButton73.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageReference image2 = storageReference.child("Back").child("back.png");
        try {
            File localFile = File.createTempFile("tempImage","jpg");
            image2.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageButton74.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageReference image3 = storageReference.child("Shoulders").child("shoulders.png");
        try {
            File localFile = File.createTempFile("tempImage","jpg");
            image3.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageButton75.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageReference image4 = storageReference.child("Arms").child("arms.png");
        try {
            File localFile = File.createTempFile("tempImage","jpg");
            image4.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageButton76.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageReference image5 = storageReference.child("Legs").child("Legs.png");
        try {
            File localFile = File.createTempFile("tempImage","jpg");
            image5.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageButton77.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageReference image6 = storageReference.child("Abs").child("abs.png");
        try {
            File localFile = File.createTempFile("tempImage","jpg");
            image6.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageButton82.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
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
    public void exercise (View view) {
        if (imageButton73.isPressed()) {
            muscle_group = textView94.getText().toString();
        }
        else if (imageButton74.isPressed()) {
            muscle_group = textView125.getText().toString();
        }
        else if (imageButton75.isPressed()) {
            muscle_group = textView126.getText().toString();
        }
        else if (imageButton76.isPressed()) {
            muscle_group = textView127.getText().toString();
        }
        else if (imageButton77.isPressed()) {
            muscle_group = textView128.getText().toString();
        }
        else if (imageButton82.isPressed()) {
            muscle_group = textView129.getText().toString();
        }
        Intent intent = new Intent(this, ExercisesActivity.class);
        intent.putExtra("Muscle group",muscle_group);
        startActivity(intent);
    }
}