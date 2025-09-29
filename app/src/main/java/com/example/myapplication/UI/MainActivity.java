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

public class MainActivity extends AppCompatActivity {
    private EditText myUsername;
    private EditText myPassword;
    private Button myButton;
    private TextView errorMessage;
    private User user;
    private TextView registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView textViewLinkRegister = findViewById(R.id.textViewLinkRegister);
        myUsername = findViewById(R.id.username);
        myPassword  = findViewById(R.id.password);
        myButton = findViewById(R.id.btnLogin);
        registerButton = findViewById(R.id.textViewLinkRegister);

        errorMessage = findViewById(R.id.errorMessage);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = myUsername.getText().toString();
                String password = myPassword.getText().toString();
                if(username.isEmpty())
                {
                    displayError("Username is required");
                }
                else if(password.isEmpty()){
                    displayError("Password is required");
                }
                else{
                    loginWithAPI(username,password);
                }
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    private void displayError(String message){
        if(errorMessage != null){
            errorMessage.setText(message);
            errorMessage.setVisibility(View.VISIBLE);
        }
    }
    private void loginWithAPI(String username, String password){
        String url = "https://661r3b81-3000.asse.devtunnels.ms/api/login";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        HashMap<String,String> params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);

        JSONObject jsonBody = new JSONObject(params);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonBody,
                response -> {
                    try {
                        String name = response.getString("name");


                        if (!name.isEmpty()) {
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            intent.putExtra("fullname", name);
                            startActivity(intent);
                        } else {
                            displayError("Sai tài khoản hoặc mật khẩu");
                        }
                    } catch (Exception e) {
                        displayError("Lỗi parse JSON: " + e.getMessage());
                    }
                },
                error -> displayError("Lỗi kết nối: " + error.toString())
        );

        requestQueue.add(jsonObjectRequest);
    }

}