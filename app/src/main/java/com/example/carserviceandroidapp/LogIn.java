package com.example.carserviceandroidapp;

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
        setContentView(com.example.gark.R.layout.activity_log_in);
        EditText username = findViewById(com.example.gark.R.id.email);
        EditText password = findViewById(com.example.gark.R.id.passwordTxt);


        Button login = findViewById(com.example.gark.R.id.sendLink);
        //LOGIN BUTTON
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String enteredUsername = username.getText().toString();
//                String enteredPassword = password.getText().toString();
//
//                // checks if username or password is empty and creates toast
//                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Please enter both email and password", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                Intent intent = new Intent(LogIn.this, MainActivity.class);
                startActivity(intent);
//                DBHelper dbHelper = new DBHelper(LogIn.this);
//                boolean loginSuccessful = dbHelper.checkLogin(enteredUsername, enteredPassword);
//                if (loginSuccessful) {
//                    // login successful, start app
//
//                } else {
//                    // login failed, show error message
//                    Toast.makeText(getApplicationContext(), "Incorrect email or password", Toast.LENGTH_SHORT).show();
//                }
            }
        });
        //REGISTER AS SERVICE PROVIDER
        TextView regServiceTxt = findViewById(com.example.gark.R.id.regService);
        regServiceTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Provider_Registration.class);
                startActivity(intent);
            }
        });
        //REGISTER AS CUSTOMER
        TextView regCustomerTxt = findViewById(com.example.gark.R.id.regCustomer);
        regCustomerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Customer_Registration.class);
                startActivity(intent);
            }
        });
        //Forgot Password
        TextView forgotPw = findViewById(com.example.gark.R.id.forgotPassword);
        forgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }
}