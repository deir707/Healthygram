package com.unipi.diodeir.healthygram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyViewHolder> {
    Context context;

    ArrayList<ListExercises> list;

    public ExerciseAdapter(Context context, ArrayList<ListExercises> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_exericses,parent,false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListExercises listExercises = list.get(position);
        holder.title.setText(listExercises.getTitle());
        holder.sets.setText(listExercises.getSets().toString());
        holder.repeats.setText(listExercises.getRepeats());
        Picasso.get().load(listExercises.getImage_url()).fit().centerCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, sets, repeats;
        ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Title);
            sets = itemView.findViewById(R.id.textView135);
            repeats = itemView.findViewById(R.id.textView136);
            imageView = itemView.findViewById(R.id.imageView17);
        }
    }
}
