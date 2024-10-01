package com.unipi.diodeir.healthygram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MealActivity extends AppCompatActivity {
    TextView textView71,textView72,textView73,textView78;
    ImageButton imageButton43,imageButton44,imageButton45,imageButton46;
    EditText editTextNumber2;
    RecyclerView recyclerView;
    ArrayList<Food> list;
    ArrayList<String> list2;
    Dialog dialog;
    FoodAdapter foodAdapter;
    DatabaseReference reference,reference1;
    FirebaseAuth auth;
    String id;
    String date;
    String food;
    Double cals, qty, sum = 0.0;
    Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        textView71 = findViewById(R.id.textView71);
        textView72 = findViewById(R.id.textView72);
        textView73 = findViewById(R.id.textView73);
        textView78 = findViewById(R.id.textView78);
        imageButton43 = findViewById(R.id.imageButton43);
        imageButton44 = findViewById(R.id.imageButton44);
        imageButton45 = findViewById(R.id.imageButton45);
        imageButton46 = findViewById(R.id.imageButton46);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        textView71.setText(getIntent().getStringExtra("Meal"));
        date = getIntent().getStringExtra("Date");
        recyclerView = findViewById(R.id.recyclerView);
        auth = FirebaseAuth.getInstance();
        id = auth.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(id).child("Nutrition").child(date).child(textView71.getText().toString());
        reference1 = FirebaseDatabase.getInstance().getReference("Food Database");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        foodAdapter = new FoodAdapter(this,list);
        recyclerView.setAdapter(foodAdapter);

        reference.child("Entries").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sum = 0.0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Food food3 = dataSnapshot.getValue(Food.class);
                    if(dataSnapshot.exists() && food3 != null){
                        list.add(food3);
                        sum = sum + food3.getCalories();
                        textView73.setText(String.valueOf(sum));
                    }
                }
                foodAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        RecyclerView.AdapterDataObserver dataObserver = new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                sum = 0.0;
                for (int i=0; i<list.size();i++) {
                    Food food3 = list.get(i);
                    sum = sum + food3.getCalories();
                }
                textView73.setText(String.valueOf(sum));
            }
        };
        assert adapter != null;
        adapter.registerAdapterDataObserver(dataObserver);
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    list2.add(dataSnapshot.getKey());
                }

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

    public void add (View view) {
        if (!editTextNumber2.getText().toString().isEmpty() && food != null) {
            reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    cals = Double.parseDouble(Objects.requireNonNull(snapshot.child(food).getValue()).toString());
                    Food food1 = snapshot.getValue(Food.class);
                    assert food1 != null;
                    food1.setQuantity(Double.parseDouble(editTextNumber2.getText().toString()));
                    qty = food1.getQuantity();
                    cals = cals * qty;
                    food1.setTitle(food);
                    food1.setCalories(cals);
                    if (list.isEmpty()) {
                        sum = 0.0;
                        sum = sum + cals;
                        textView73.setText(String.valueOf(sum));
                        list.add(food1);
                    }
                    else {
                        for (Food food2 : list) {
                            if (food2.getTitle().equals(food1.getTitle())){
                                flag = false;
                                break;
                            }
                            else {
                                flag = true;
                            }
                        }
                        if (flag) {
                            sum = sum + cals;
                            textView73.setText(String.valueOf(sum));
                            list.add(food1);
                        }
                        else {
                            Toast.makeText(MealActivity.this,food+" has already been chosen!",Toast.LENGTH_LONG).show();
                        }
                    }
                    foodAdapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        else {
            Toast.makeText(MealActivity.this,"Quantity and food fields must be filled in!",Toast.LENGTH_LONG).show();
        }
    }
    public void save(View view){
        int i = 0;
        reference.removeValue();
        sum = 0.0;
        for (Food food2 : list) {
            reference.child("Entries").child("entry"+i).child("Quantity").setValue(food2.getQuantity());
            reference.child("Entries").child("entry"+i).child("Title").setValue(food2.getTitle());
            reference.child("Entries").child("entry"+i).child("Calories").setValue(food2.getCalories());
            sum = sum + food2.getCalories();
            i++;
        }
        Toast.makeText(this,"Meals are saved!",Toast.LENGTH_LONG).show();
        if (sum > 0) {
            Objects.requireNonNull(reference.getParent()).child("Calories").child(textView71.getText().toString()).setValue(sum);
        }
        else {
            Objects.requireNonNull(reference.getParent()).child("Calories").child(textView71.getText().toString()).removeValue();
        }
        Intent intent = new Intent(this, FoodCalorieActivity.class);
        startActivity(intent);
    }
    @SuppressLint("NotifyDataSetChanged")
    public void clear(View view) {
        list.clear();
        foodAdapter.notifyDataSetChanged();
        sum = 0.0;
        textView73.setText(String.valueOf(sum));
    }
    public void menu(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_search_spinner);
        Objects.requireNonNull(dialog.getWindow()).setLayout(1000,1800);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText editText = dialog.findViewById(R.id.edit_text);
        ListView listView4 = dialog.findViewById(R.id.listView4);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list2);
        listView4.setAdapter(adapter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView78.setText(adapter.getItem(position));
                food = textView78.getText().toString();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}