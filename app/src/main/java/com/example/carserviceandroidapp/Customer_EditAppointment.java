package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Customer_EditAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_appointment);

        Intent intent = getIntent();
        if(intent != null){
            int appID = intent.getIntExtra("AppId",0);
            String serviceProviderName = intent.getStringExtra("ServiceProviderName");
            String serviceProviderAddress = intent.getStringExtra("SPAddress");
            String appointmentStatus = intent.getStringExtra("AppStatus");
            String dropoffDate = intent.getStringExtra("DropoffD");
            String dropoffT = intent.getStringExtra("DropoffT");
            String pickupD = intent.getStringExtra("PickupD");
            String pickupT = intent.getStringExtra("PickupT");
            String dropoffLoc = intent.getStringExtra("DropoffLoc");
            String pickupLoc = intent.getStringExtra("PickupLoc");
            String serviceDetails = intent.getStringExtra("ServiceDet");
            String spPhone = intent.getStringExtra("SPPhone");

            TextView textViewSPName = (TextView) findViewById(R.id.textViewSPNameDisplay);
            TextView textViewSPAddress = (TextView) findViewById(R.id.textViewSPAddress);
            TextView textViewSPPhone = (TextView)findViewById(R.id.textViewSPCellDisplay);
            TextView textViewAppStatus = (TextView) findViewById(R.id.textViewStatus);
            TextView textViewdropOffDT = (TextView) findViewById(R.id.tvDropOffDT);
            TextView textViewdropOffLoc = (TextView)findViewById(R.id.textViewDropOffLoc);
            TextView textViewPickupDT = (TextView) findViewById(R.id.textViewPickupTime);
            TextView textViewPickupLoc = (TextView)findViewById(R.id.tvPickupLocation);
            TextView textViewServiceDetails = (TextView)findViewById(R.id.textViewServiceDetails);
            TextView textViewAppStatusDown = (TextView)findViewById(R.id.editTextAppointmentStatus);

            textViewSPName.setText(serviceProviderName);
            textViewSPAddress.setText(serviceProviderAddress);
            textViewSPPhone.setText("Contact: "+spPhone);
            textViewAppStatus.setText(appointmentStatus);
            textViewdropOffDT.setText(dropoffDate + "  "+ dropoffT);
            textViewdropOffLoc.setText(dropoffLoc);
            textViewPickupDT.setText(pickupD+"  "+ pickupT);
            textViewPickupLoc.setText(pickupLoc);
            textViewServiceDetails.setText(serviceDetails);
            textViewAppStatusDown.setText(appointmentStatus);

            textViewAppStatus.setTextColor(Color.WHITE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(20);
            int chosenColor = Color.rgb(247, 201, 16);
            drawable.setColor(chosenColor);
            textViewAppStatus.setBackgroundDrawable(drawable);

    }

        Button btnUpdateApp = (Button) findViewById(R.id.buttonUpdate);
        btnUpdateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Customer_EditAppointment.this, Customer_EditAppointment_Form.class);
                intent.putExtra("AppId",getIntent().getIntExtra("ServiceProviderName",0));
                intent.putExtra("ServiceProviderName",getIntent().getStringExtra("ServiceProviderName"));
                intent.putExtra("SPAddress",getIntent().getStringExtra("SPAddress"));
                intent.putExtra("AppStatus",getIntent().getStringExtra("AppStatus"));
                intent.putExtra("DropoffD",getIntent().getStringExtra("DropoffD"));
                intent.putExtra("DropoffT",getIntent().getStringExtra("DropoffT"));
                intent.putExtra("PickupD",getIntent().getStringExtra("PickupD"));
                intent.putExtra("PickupT",getIntent().getStringExtra("PickupT"));
                intent.putExtra("DropoffLoc",getIntent().getStringExtra("DropoffLoc"));
                intent.putExtra("PickupLoc",getIntent().getStringExtra("PickupLoc"));
                intent.putExtra("ServiceDet",getIntent().getStringExtra("ServiceDet"));
                intent.putExtra("SPPhone", getIntent().getStringExtra("SPPhone"));
                //place cell number here
                //place email address
                startActivity(intent);


            }
        });
    }

}