package com.example.myapplication.UI;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

public class RegisterActivity extends AppCompatActivity {
    private TextView errorMessage;
    private EditText username;
    private EditText password;
    private EditText fullname;
    private EditText address;
    private EditText email;
    private EditText tel;
    private EditText confirmPassword;
    private Button btnRegister;
    private Button btnBack;

    private boolean isUserExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register);
        username = findViewById(R.id.editRegisterUsername);
        password = findViewById(R.id.editRegisterPassword);
        fullname = findViewById(R.id.editRegisterName);
        address = findViewById(R.id.editRegisterAddress);
        email = findViewById(R.id.editRegisterEmail);
        tel = findViewById(R.id.editRegisterPhone);
        btnRegister = findViewById(R.id.button2);
        errorMessage = findViewById(R.id.errorMessage);
        confirmPassword = findViewById(R.id.editConfirmPassword);
        btnBack = findViewById(R.id.buttonBack);

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String userName = s.toString().trim();
                findUser(userName);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = username.getText().toString();
                String passWord = password.getText().toString();
                String fullName = fullname.getText().toString();
                String Address = address.getText().toString();
                String Email = email.getText().toString();
                String Tel = tel.getText().toString();
                String confirmpassword = confirmPassword.getText().toString();
                if(userName.isEmpty()){
                    displayError("Username is required");
                }
                else if(isUserExit){
                    displayError("Username đã tồn tại");
                }
                else if(passWord.isEmpty()){
                    displayError("Password is required");
                }
                else if(!passWord.equals(confirmpassword)){
                    displayError("Password does not match");
                }
                else if(fullName.isEmpty()){
                    displayError("Fullname is required");
                }
                else if(Address.isEmpty()){
                    displayError("Address is required");
                }
                else if(Email.isEmpty()){
                    displayError("Email is required");
                }
                else if(Tel.isEmpty()){
                    displayError("Tel is required");
                }
                else{
                    User user = new User(userName,passWord,fullName,Address,Email,Tel);
                    RegisterWithAPI(user);
                }


            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
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
    private void RegisterWithAPI(User user){
        String url = "https://661r3b81-3000.asse.devtunnels.ms/api/register";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        HashMap<String,String> params = new HashMap<>();
        params.put("username",user.getUsername());
        params.put("password",user.getPassword());
        params.put("name",user.getFullname());
        params.put("address",user.getAddress());
        params.put("email",user.getEmail());
        params.put("phone",user.getTel());

        JSONObject jsonBody = new JSONObject(params);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonBody,
                response -> {
                    try {
                        String name = response.getString("name");
                        if (!name.isEmpty()) {
                            displayError("Đăng ký thành công");
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            displayError("Lỗi thông tin đăng kí");
                        }
                    } catch (Exception e) {
                        displayError("Lỗi parse JSON: " + e.getMessage());
                    }
                },
                error -> displayError("Lỗi kết nối: " + error.toString())
        );

        requestQueue.add(jsonObjectRequest);
    }
    private void findUser(String username){
        String url = "https://661r3b81-3000.asse.devtunnels.ms/api/user";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        HashMap<String,String> params = new HashMap<>();
        params.put("username",username);
        JSONObject jsonBody = new JSONObject(params);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonBody,
                response -> {
                    try {
                        String name = response.getString("name");
                        if (!name.isEmpty()) {
                            isUserExit = true;
                        }
                    } catch (Exception e) {
                        displayError("Lỗi parse JSON: " + e.getMessage());
                        isUserExit = false;
                    }
                },
                error -> {
                    displayError("Lỗi kết nối: " + error.toString());
                    isUserExit = false;
                }

        );
        requestQueue.add(jsonObjectRequest);
    }

}