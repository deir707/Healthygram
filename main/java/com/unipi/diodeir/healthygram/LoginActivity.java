package com.unipi.diodeir.healthygram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    ImageView imageView2;
    StorageReference storageReference;
    EditText usernameField, passwordField;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageView2 = findViewById(R.id.imageView2);
        storageReference = FirebaseStorage.getInstance().getReference();
        usernameField = findViewById(R.id.editTextTextEmailAddress);
        passwordField = findViewById(R.id.editTextTextPassword);
        auth = FirebaseAuth.getInstance();

        StorageReference image1 = storageReference.child("keep_going.png");
        try {
            File localFile = File.createTempFile("tempImage","jpg");
            image1.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageView2.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void main (View view) {
        if (usernameField.getText().toString().isEmpty() || passwordField.getText().toString().isEmpty()) {
            Toast.makeText(this,"Username and password are required!",Toast.LENGTH_LONG).show();
        }
        else {
            auth.signInWithEmailAndPassword(usernameField.getText().toString(), passwordField.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        success();
                    }
                    else {
                        showMessage(Objects.requireNonNull(task.getException()).getLocalizedMessage());
                    }
                }
            });
        }
    }
    void showMessage(String message) {
        new AlertDialog.Builder(this).setTitle("Error").setMessage(message).setCancelable(true).show();
    }
    void success() {
        Toast.makeText(this, "Successful login!",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,MainMenuActivity.class);
        startActivity(intent);
    }
}