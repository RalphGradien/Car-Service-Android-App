package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Customer_EditAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_appointment);

        Intent intent = getIntent();
        if(intent != null){
            String serviceProviderName = intent.getStringExtra("ServiceProviderName");
            String serviceProviderAddress = intent.getStringExtra("SPAddress");
            TextView textViewSPName = (TextView) findViewById(R.id.textViewSPNameDisplay);
            TextView textViewSPAddress = (TextView) findViewById(R.id.textViewSPAddress);

            textViewSPName.setText(serviceProviderName);
            textViewSPAddress.setText(serviceProviderAddress);
        }
    }
}