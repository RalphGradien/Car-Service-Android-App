package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Customer_AppointmentsView extends AppCompatActivity implements CustomerAppointmentsViewSelectInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_appointments_view);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewCustViewAppointments);
        List<CustomerApointmentItems> customerApointmentItemsList = new ArrayList<CustomerApointmentItems>();
        customerApointmentItemsList.add(new CustomerApointmentItems(12345,"2022-10-12","09:40PM",
                "","CarServiceProvider1","New Westminster","Brake Check", "Completed"));
        customerApointmentItemsList.add(new CustomerApointmentItems(12346,"2022-11-8","07:30AM",
                "","CarServiceProvider2","Abbotsford","Steering Wheel Check",
                "Ongoing"));

        customerApointmentItemsList.add(new CustomerApointmentItems(12347,"2022-10-8","10:30AM",
                "","CarServiceProvider3","Burnaby","Oil Refill",
                "Ready for Pickup"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomerAppointmentAdapter(getApplicationContext(),customerApointmentItemsList, this));
    }

    @Override
    public void onItemClick(CustomerApointmentItems customerApointmentItems) {
        Intent intent = new Intent(Customer_AppointmentsView.this, Customer_EditAppointment.class);
        intent.putExtra("ServiceProviderName",customerApointmentItems.bookedServiceProviderName);
        intent.putExtra("SPAddress",customerApointmentItems.bookedServiceProviderAddress);
        intent.putExtra("AppStatus",customerApointmentItems.bookingStatus);
        //place cell number here
        //place email address
        startActivity(intent);
    }
}