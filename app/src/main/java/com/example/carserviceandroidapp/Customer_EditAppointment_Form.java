package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Customer_EditAppointment_Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_appointment_form);

        TextView textView = (TextView) findViewById(R.id.textViewDropOffLoc);
        textView.isInEditMode();
        //textView.getText()
    }
}