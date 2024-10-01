package com.unipi.diodeir.healthygram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class PersonalActivity extends AppCompatActivity {
    TextView textView10;
    ImageButton imageButton6,imageButton7,imageButton8;
    ListView listView;
    DatabaseReference reference;
    FirebaseAuth auth;
    String id;
    String[] personalList = {"My achievements","My profile","Logout","Delete account"};
    int[] itemImages = {R.drawable.baseline_emoji_events_24, R.drawable.baseline_person_24, R.drawable.baseline_logout_24, R.drawable.baseline_delete_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        textView10 = findViewById(R.id.textView10);
        imageButton6 = findViewById(R.id.imageButton6);
        imageButton7 = findViewById(R.id.imageButton7);
        imageButton8 = findViewById(R.id.imageButton8);
        listView = findViewById(R.id.listView);
        auth = FirebaseAuth.getInstance();
        id = auth.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(id);
        MenuAdapter menuAdapter = new MenuAdapter(getApplicationContext(),personalList,itemImages);
        listView.setAdapter(menuAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    myAchievements();
                }
                else if (position == 1) {
                    myProfile();
                }
                else if (position == 2) {
                    logout();
                }
                else if (position == 3) {
                    delete();
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
    void myAchievements() {
        Intent intent = new Intent(this, AchievementsActivity.class);
        startActivity(intent);
    }
    void myProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
    void logout() {
        auth.signOut();
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
    void delete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Account deletion");
        builder.setMessage("You are about to delete your account permanently. Are you sure?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reference.removeValue();
                        Objects.requireNonNull(auth.getCurrentUser()).delete();
                        logout();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}