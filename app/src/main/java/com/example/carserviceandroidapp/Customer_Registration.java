package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Customer_Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
    }

    //temporary. to replace accordingly later
    public void onClickToAppointmentsView(View v){
       Intent intent = new Intent(Customer_Registration.this, Customer_AppointmentsView.class);
       startActivity(intent);
    }

}