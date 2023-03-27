package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        EditText username = findViewById(R.id.email);
        EditText password = findViewById(R.id.passwordTxt);


        Button login = findViewById(R.id.sendLink);
        //LOGIN BUTTON
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();


                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    // show error if either field is empty
                    Toast.makeText(getApplicationContext(), "Username or password is empty", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper dbHelper = new DBHelper(LogIn.this);

                    //Checks if login details are correct.
                    // Returns CUSTOMER, SERVICE_PROVIDED, or NOT_FOUND on loginStatus[0]
                    //Returns CustomerID or Service ID on loginStatus[1]
                    String[] loginStatus = dbHelper.checkLoginID(enteredUsername, enteredPassword);

                    if (loginStatus[0].equals("CUSTOMER")) {


                        //Stores ID to global variable
                        Customer.CustomerID=Integer.parseInt(loginStatus[1]);
                        Log.d("CustomerID LOG:", String.valueOf(Customer.CustomerID));
                        // login customer successful, start app Customer Module
                        Intent intent = new Intent(LogIn.this, CustomerMainMenu.class);
                        startActivity(intent);
                    } else if (loginStatus[0].equals("SERVICE_PROVIDER")) {


                        //Stores ID to global variable
                        ServiceProvider.ServiceProviderID=Integer.parseInt(loginStatus[1]);

                        Log.d("ServiceProviderID LOG:", String.valueOf(ServiceProvider.ServiceProviderID));
                        // login service provider successful, start app Service Provider Module
                        Intent intent = new Intent(LogIn.this, ServiceMainMenu.class);
                        startActivity(intent);
                    }
                    else if (loginStatus[0].equals("NOT_FOUND")) {
                        //login details not in Database, show error
                        Toast.makeText(getApplicationContext(), "Incorrect email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //REGISTER AS SERVICE PROVIDER
        TextView regServiceTxt = findViewById(R.id.regService);
        regServiceTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Provider_Registration.class);
                startActivity(intent);
            }
        });
        //REGISTER AS CUSTOMER
        TextView regCustomerTxt = findViewById(R.id.regCustomer);
        regCustomerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Customer_Registration.class);
                startActivity(intent);
            }
        });
        //Forgot Password
        TextView forgotPw = findViewById(R.id.forgotPassword);
        forgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }
}