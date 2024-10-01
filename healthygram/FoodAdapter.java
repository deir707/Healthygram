package com.unipi.diodeir.healthygram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {
    Context context;

    ArrayList<Food> list;

    public FoodAdapter(Context context, ArrayList<Food> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.food_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Food food1 = list.get(position);
        holder.Quantity.setText(String.valueOf(food1.getQuantity()));
        holder.Title.setText(food1.getTitle());
        holder.Calories.setText(String.valueOf(food1.getCalories()));
        holder.imageButton47.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Quantity,Title,Calories;
        ImageButton imageButton47;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Quantity = itemView.findViewById(R.id.Quantity);
            Title = itemView.findViewById(R.id.Title);
            Calories = itemView.findViewById(R.id.Calories);
            imageButton47 = itemView.findViewById(R.id.imageButton47);
        }
    }
}
