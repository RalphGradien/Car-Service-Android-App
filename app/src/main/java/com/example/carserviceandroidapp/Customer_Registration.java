package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Customer_Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        EditText etName = (EditText) findViewById(R.id.editTextName);
        EditText etPW = (EditText) findViewById(R.id.editTextPWord);
        EditText etConfirmPW = (EditText) findViewById(R.id.editTextCPassword);
        EditText etEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText etAddress = (EditText) findViewById(R.id.editTextAddress);
        EditText etContact = (EditText) findViewById(R.id.editTextContact);

        Button buttonRegister = (Button) findViewById(R.id.buttonCRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //temporary. to replace accordingly later
    public void onClickToAppointmentsView(View v){
       Intent intent = new Intent(Customer_Registration.this, Customer_AppointmentsView.class);
       startActivity(intent);
    }

}