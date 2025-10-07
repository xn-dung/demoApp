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
import android.widget.Toast;

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

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = myUsername.getText().toString();
                String password = myPassword.getText().toString();
                if(username.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Username is required", Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
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

    private void loginWithAPI(String username, String password){
        String url = "https://mobilenodejs.onrender.com/api/login";
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
                        JSONObject user = response.getJSONObject("user");
                        String id = user.getString("id");
                        String name = user.getString("name");
                        String address = user.getString("address");
                        String email = user.getString("email");
                        String phone = user.getString("phone");
                        String userName = user.getString("username");
                        String passWord = user.getString("password");
                        User logginUser= new User(userName,passWord,name,address,email,phone);


                        if (!logginUser.getFullname().isEmpty()) {
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            intent.putExtra("User",logginUser);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this,"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this,"Lỗi parse JSON: " + e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(MainActivity.this,"Lỗi kết nối: " + error.toString(),Toast.LENGTH_SHORT).show()
        );

        requestQueue.add(jsonObjectRequest);
    }

}