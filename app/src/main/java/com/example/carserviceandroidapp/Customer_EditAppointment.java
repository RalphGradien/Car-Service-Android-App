package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Customer_EditAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_appointment);

        Intent intent = getIntent();
        if(intent != null){
            String serviceProviderName = intent.getStringExtra("ServiceProviderName");
            TextView textViewSPName = (TextView) findViewById(R.id.textViewServiceProviderName);
            textViewSPName.setText(serviceProviderName);
        }
    }
}