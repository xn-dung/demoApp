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
public class UserProfileActivity extends AppCompatActivity{

    private Button btnLogout;
    private TextView userFullname;
    private TextView userEmail;
    private TextView textSettings;
    private User user;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        btnLogout = findViewById(R.id.btnLogout);
        userFullname = findViewById(R.id.userFullName);
        userEmail = findViewById(R.id.userEmail);
        textSettings = findViewById(R.id.textSettings);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User");
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        String fullname = user.getFullname();
        String email = user.getEmail();
        userFullname.setText(fullname);
        userEmail.setText(email);
        textSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("User",user);
                startActivity(intent);
            }
        });


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if(itemId == R.id.menuHome){
                    Intent intent = new Intent(UserProfileActivity.this, HomeActivity.class);
                    intent.putExtra("User",user);
                    startActivity(intent);
                    return true;

                }
                else if(itemId == R.id.menuProfile){
                    return true;
                }
//                else if(itemId == R.id.menuSearch){
//                    Intent intent = new Intent(UserProfileActivity.this, SearchFoodActivity.class);
//                    intent.putExtra("User",user);
//                    startActivity(intent);
//                    return true;
//                }
                return false;
            }
        });
    }

}
