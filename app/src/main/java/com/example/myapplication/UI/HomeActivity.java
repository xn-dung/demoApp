package com.example.myapplication.UI;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.Button;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.model.User;
import com.example.myapplication.R;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONObject;

import java.util.*;
public class HomeActivity extends AppCompatActivity{
    private TextView errorMessage;
    private TextView fullName;
    private User user;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User");
        String fullname = user.getFullname();

        fullName = findViewById(R.id.textFullName);
        fullName.setText(fullname);

        bottomNavigationView = findViewById(R.id.bottomNavView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if(itemId == R.id.menuHome){
                    return true;
                }
                else if(itemId == R.id.menuProfile){
                    Intent intent = new Intent(HomeActivity.this, UserProfileActivity.class);
                    intent.putExtra("User", user);
                    startActivity(intent);
                    return true;
                }
                else if(itemId == R.id.menuSearch){
                    Intent intent = new Intent(HomeActivity.this, SearchFoodActivity.class);
                    intent.putExtra("User", user);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });




    }

}
