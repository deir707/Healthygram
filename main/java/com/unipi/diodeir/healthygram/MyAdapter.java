package com.unipi.diodeir.healthygram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
    Context context;
    String[] menuList;
    int[] itemImages;
    LayoutInflater inflater;

    public MyAdapter (Context ctx, String [] list, int [] images) {
        this.context = ctx;
        this.menuList = list;
        this.itemImages = images;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return menuList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.menu, null);
        TextView txtView = convertView.findViewById(R.id.textView25);
        ImageView img = convertView.findViewById(R.id.imageView6);
        txtView.setText(menuList[position]);
        img.setImageResource(itemImages[position]);
        return convertView;
    }
}
