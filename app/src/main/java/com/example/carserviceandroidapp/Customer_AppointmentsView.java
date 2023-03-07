package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Customer_AppointmentsView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_appointments_view);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewCustViewAppointments);
        List<CustomerApointmentItems> customerApointmentItemsList = new ArrayList<CustomerApointmentItems>();
        customerApointmentItemsList.add(new CustomerApointmentItems(12345,"2022-10-12","09:40PM", "","CarServiceProvider1","New Westminster","Brake Check", "Completed"));
        customerApointmentItemsList.add(new CustomerApointmentItems(12346,"2022-11-8","07:30AM",
                "","CarServiceProvider2","Abbotsford","Steering Wheel Check",
                "Ongoing"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomerAppointmentAdapter(getApplicationContext(),customerApointmentItemsList));
    }
}