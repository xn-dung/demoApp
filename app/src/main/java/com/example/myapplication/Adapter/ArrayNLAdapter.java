package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.model.BaiDang;
import com.example.myapplication.model.NguyenLieu;

import java.util.ArrayList;
import java.util.List;

public class ArrayNLAdapter extends ArrayAdapter<NguyenLieu> {
    Activity context;
    int idLayout;
    BaiDang baiDang;
    List<NguyenLieu> nlList;

    public ArrayNLAdapter(Activity context, int idLayout, List<NguyenLieu> nlList) {
        super(context, idLayout, nlList);
        this.context = context;
        this.idLayout = idLayout;
        this.nlList = nlList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(idLayout, null);
        NguyenLieu nguyenLieu = nlList.get(position);
        TextView txtTenNguyenLieu = convertView.findViewById(R.id.txtTenNguyenLieu);
//        TextView txtDinhLuong = convertView.findViewById(R.id.txtDinhLuong);
        txtTenNguyenLieu.setText(nguyenLieu.getTen());
//        txtDinhLuong.setText(nguyenLieu.getDinhLuong());
        return convertView;
    }
}