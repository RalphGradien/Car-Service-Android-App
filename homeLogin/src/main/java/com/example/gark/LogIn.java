package com.example.gark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        EditText username = findViewById(R.id.usernameTxt);
        EditText password = findViewById(R.id.passwordTxt);

        Button login = findViewById(R.id.loginBtn2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();
                DBHelper dbHelper = new DBHelper(LogIn.this);
                boolean loginSuccessful = dbHelper.checkLogin(enteredUsername, enteredPassword);
                if (loginSuccessful) {
                    // login successful, do something
                    Intent intent = new Intent(LogIn.this, home.class);
                    startActivity(intent);
                } else {
                    // login failed, show error message
                    Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        TextView regServiceTxt = findViewById(R.id.regService);
        regServiceTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, registerService.class);
                startActivity(intent);
            }
        });

        TextView regCustomerTxt = findViewById(R.id.regCustomer);
        regCustomerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, registerCustomer.class);
                startActivity(intent);
            }
        });
    }
}