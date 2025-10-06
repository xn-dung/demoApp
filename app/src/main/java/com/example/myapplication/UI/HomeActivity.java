package com.example.myapplication.UI;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.Button;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.model.User;
import com.example.myapplication.R;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.*;
public class HomeActivity extends AppCompatActivity{
    private TextView errorMessage;
    private TextView fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home);
        Intent intent = getIntent();
        String fullname = intent.getStringExtra("fullname");

        fullName = findViewById(R.id.textFullName);
        fullName.setText(fullname);





    }

}
