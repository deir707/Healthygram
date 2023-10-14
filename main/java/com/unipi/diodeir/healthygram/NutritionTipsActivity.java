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

public class NutritionTipsActivity extends AppCompatActivity {
    TextView textView62;
    ImageView imageView5;
    ImageButton imageButton37,imageButton38,imageButton39;
    String[] tips = {"01. Limit sugary drinks","02. Eat nuts and seeds","03. Avoid ultra-processed foods","04. Don’t fear coffee","05. Eat fatty fish","06. Get enough sleep","07. Feed your gut bacteria",
    "08. Stay hydrated", "09. Don’t eat heavily charred meats","10. Avoid bright lights before sleep","11. Take vitamin D if you’re deficient","12. Eat plenty of fruits and vegetables",
    "13. Eat adequate protein","14. Get moving","15. Don’t smoke or use drugs, and only drink in moderation","16. Use extra virgin olive oil","17. Minimize your sugar intake",
    "18. Limit refined carbs","19. Lift weights","20. Avoid artificial trans fats","21. Use plenty of herbs and spices","22. Nurture your social relationships","23. Occasionally track your food intake",
    "24. Get rid of excess belly fat","25. Avoid restrictive diets","26. Eat whole eggs","27. Meditate"};
    ListView listView3;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_tips);
        textView62 = findViewById(R.id.textView62);
        imageView5 = findViewById(R.id.imageView5);
        imageButton37 = findViewById(R.id.imageButton37);
        imageButton38 = findViewById(R.id.imageButton38);
        imageButton39 = findViewById(R.id.imageButton39);
        listView3 = findViewById(R.id.listView3);
        storageReference = FirebaseStorage.getInstance().getReference();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.tips, R.id.textView25, tips);
        listView3.setAdapter(arrayAdapter);

        StorageReference image1 = storageReference.child("food_pyramid.png");
        try {
            File localFile = File.createTempFile("tempImage","jpg");
            image1.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageView5.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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