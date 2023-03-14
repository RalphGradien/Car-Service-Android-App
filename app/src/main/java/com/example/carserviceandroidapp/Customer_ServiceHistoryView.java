package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Customer_ServiceHistoryView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service_history_view);

        RecyclerView recyclerView = findViewById(R.id.customer_history_recyclerview);

        List<Customer_ServiceHistory_Items> customer_serviceHistory_itemsList = new ArrayList<Customer_ServiceHistory_Items>();
        customer_serviceHistory_itemsList.add(new Customer_ServiceHistory_Items(23456,"ServiceProvider 1",
                "Brake Check","Burnaby","Completed","2023-01-11",
                "11:30AM","2023-01-14","01:20PM"));

        customer_serviceHistory_itemsList.add(new Customer_ServiceHistory_Items(23490,"ServiceProvider 30",
                "Front Wheel Replacement","New Westminster","Completed","2023-02-20",
                "12:30PM","2023-02-22","01:40PM"));

        customer_serviceHistory_itemsList.add(new Customer_ServiceHistory_Items(23500,"ServiceProvider 40",
                "Steering Wheel Check","Metrotown Area","Ongoing","2023-03-11",
                "4:30PM","",""));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Customer_ServiceHistory_Adapter(getApplicationContext(),customer_serviceHistory_itemsList));
    }

}