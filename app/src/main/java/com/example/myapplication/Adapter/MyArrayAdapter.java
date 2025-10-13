package com.example.myapplication.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.BaiDang;

public class MyArrayAdapter extends ArrayAdapter<BaiDang> {
    Activity context;
    int IdLayout;
    ArrayList<BaiDang> mylist;

    public MyArrayAdapter(Activity context, int idLayout, ArrayList<BaiDang> mylist) {
        super(context, idLayout, mylist);
        this.context = context;
        IdLayout = idLayout;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myFlater = context.getLayoutInflater();
        convertView = myFlater.inflate(IdLayout, null);
        BaiDang myBaiDang = mylist.get(position);
        ImageView imgItem = convertView.findViewById(R.id.imageFood);
        Glide.with(this.getContext())
                .load(myBaiDang.getImage())
                .placeholder(R.drawable.logo_app)
                .error(R.drawable.ic_launcher_background)
                .into(imgItem);
        TextView nameItem = convertView.findViewById(R.id.textFoodName);
        nameItem.setText(myBaiDang.getTenMon());
        return convertView;
    }
}