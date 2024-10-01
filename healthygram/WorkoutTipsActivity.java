package com.unipi.diodeir.healthygram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class WorkoutTipsActivity extends AppCompatActivity {
    TextView textView109;
    ImageView imageView16;
    ImageButton imageButton67,imageButton68,imageButton69;
    String[] tips = {"01. Set realistic goals","02. Consult a professional","03. Start slow","04. Focus on proper form","05. Warm up and cool down","06. Stay always hydrated","07. Listen to your body",
            "08. Mix it up"};
    ListView listView6;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_tips);
        textView109 = findViewById(R.id.textView109);
        imageView16 = findViewById(R.id.imageView16);
        imageButton67 = findViewById(R.id.imageButton67);
        imageButton68 = findViewById(R.id.imageButton68);
        imageButton69 = findViewById(R.id.imageButton69);
        listView6 = findViewById(R.id.listView6);
        storageReference = FirebaseStorage.getInstance().getReference();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.tips, R.id.textView25, tips);
        listView6.setAdapter(arrayAdapter);

        StorageReference image1 = storageReference.child("fitness.png");
        try {
            File localFile = File.createTempFile("tempImage","jpg");
            image1.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageView16.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        listView6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ItemTipsActivity.class);
                intent.putExtra("Title",tips[position]);
                startActivity(intent);
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