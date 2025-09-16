package com.example.myapplication.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.Button;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private EditText myUsername;
    private EditText myPassword;
    private Button myButton;
    private TextView errorMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView textViewLinkRegister = findViewById(R.id.textViewLinkRegister);
        myUsername = findViewById(R.id.username);
        myPassword  = findViewById(R.id.password);
        myButton = findViewById(R.id.btnLogin);

        errorMessage = findViewById(R.id.errorMessage);
        textViewLinkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class); // Giả sử bạn có RegisterActivity
                startActivity(intent);
            }
        });
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

                }
            }
        });

    }
    private void displayError(String message){
        if(errorMessage != null){
            errorMessage.setText(message);
            errorMessage.setVisibility(View.VISIBLE);
        }
    }
}