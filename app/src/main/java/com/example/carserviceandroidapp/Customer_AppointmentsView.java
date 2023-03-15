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
        //List<CustomerApointmentItems> customerApointmentItemsList = new ArrayList<CustomerApointmentItems>();
        List<CustomerApointmentItems> customerApointmentItems = new ArrayList<>();
        List<CustomerApointmentItems> customerAppointmentsOngoing = new ArrayList<>();
        customerApointmentItems.add(new CustomerApointmentItems(20, "CarServiceProvider1",
                "Brake Fluid Check","Burnaby","Ongoing","2022-10-22",
                "09:40AM","","","Infront of Metrotown",""));

        customerApointmentItems.add(new CustomerApointmentItems(21, "CarServiceProvider2",
                "Wheel Replacement","New Westminster","Ongoing","2023-03-14",
                "01:30PM","2023-03-20","09:20AM","Douglas College","Langara College"));


        customerApointmentItems.add(new CustomerApointmentItems(17, "CarServiceProvider3",
                "Mirror Replacement","New Westminster","Completed","2022-11-01",
                "01:30PM","2022-11-03","08:30AM","At Customer's Address","At SP's Shop"));

        for(CustomerApointmentItems ongoingItem : customerApointmentItems){
            if(ongoingItem.histbookingStatus.equals("Ongoing")){
                customerAppointmentsOngoing.add(ongoingItem);
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomerAppointmentAdapter(getApplicationContext(),customerAppointmentsOngoing, this));
    }

    @Override
    public void onItemClick(CustomerApointmentItems customerApointmentItems) {
        Intent intent = new Intent(Customer_AppointmentsView.this, Customer_EditAppointment.class);
        intent.putExtra("ServiceProviderName",customerApointmentItems.histbookedServiceProviderName);
        intent.putExtra("SPAddress",customerApointmentItems.histbookedServiceProviderAddress);
        intent.putExtra("AppStatus",customerApointmentItems.histbookingStatus);
        intent.putExtra("DropoffD",customerApointmentItems.histdropoffAppointmentDate);
        intent.putExtra("DropoffT",customerApointmentItems.histcustomDropOffTime);
        intent.putExtra("PickupD",customerApointmentItems.histpickupAppointmentDate);
        intent.putExtra("PickupT",customerApointmentItems.histcustomPickupTime);
        intent.putExtra("DropoffLoc",customerApointmentItems.histcustomDropOffLoc);
        intent.putExtra("PickupLoc",customerApointmentItems.histcustomPickupLoc);
        intent.putExtra("ServiceDet",customerApointmentItems.histserviceAvailed);
        //place cell number here
        //place email address
        startActivity(intent);
    }
}