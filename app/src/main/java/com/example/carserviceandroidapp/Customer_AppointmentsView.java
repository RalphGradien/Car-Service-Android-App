package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Customer_AppointmentsView extends AppCompatActivity implements CustomerAppointmentsViewSelectInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_appointments_view);

        DBHelper dbh = new DBHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerviewCustViewAppointments);
        //List<CustomerApointmentItems> customerApointmentItemsList = new ArrayList<CustomerApointmentItems>();
        List<CustomerApointmentItems> customerApointmentItems = new ArrayList<>();
        List<CustomerApointmentItems> customerAppointmentsOngoing = new ArrayList<>();

        int userId=2345;
        Cursor cursorAppointment = dbh.getAppointment();
        Cursor cursorServiceProvider = dbh.getServiceProviderData();
        Cursor cursorAppDetail = dbh.getAppointmentDetail();
        Cursor cursorServiceList = dbh.getServiceList();
        Cursor cursorServiceDetail = dbh.getServiceDetails();


        if(cursorAppointment.getCount()>0){
            while(cursorAppointment.moveToNext()){
                if(cursorAppointment.getInt(2)==userId){
                    int appID = cursorAppointment.getInt(1);
                    int appSPID = cursorAppointment.getInt(3);
                    String spName = "";
                    String spStreet = "";
                    String spCity = "";
                    String spProvince = "";
                    String spPostal = "";
                    String spAddress = spStreet+", "+spCity+", "+spProvince+" "+spPostal;

                    if(cursorServiceProvider.getCount()>0){
                        while(cursorServiceProvider.moveToNext()){
                            if(cursorServiceProvider.getInt(1)== appSPID){
                                spName = cursorServiceProvider.getString(3);
                                spStreet = cursorServiceProvider.getString(4);
                                spCity = cursorServiceProvider.getString(5);
                                spProvince = cursorServiceProvider.getString(6);
                                spPostal=cursorServiceProvider.getString(7);
                            }
                        }
                        }

                    String appStatus = cursorAppointment.getString(12);
                    String dropOffDT = cursorAppointment.getString(7);
                    String dropOffLoc = cursorAppointment.getString(8);
                    String pickupDT = cursorAppointment.getString(4);
                    String pickupLoc = cursorAppointment.getString(5);
                    String serviceAvailed = "";
                    String serviceListID="";
                    int serviceDetailID=0;
                    if (cursorAppDetail.getCount()>0){
                        while(cursorAppDetail.moveToNext()) {
                            if (cursorAppDetail.getInt(1) == appID) {
                                serviceListID = cursorAppDetail.getString(2);
                            }
                        }
                    }
                    if(cursorServiceList.getCount()>0) {
                        while (cursorServiceList.moveToNext()) {
                            if (cursorServiceList.getString(0) == serviceListID) {
                                serviceDetailID = cursorServiceList.getInt(2);
                            }
                        }
                    }
                    if(cursorServiceDetail.getCount()>0) {
                        while (cursorServiceDetail.moveToNext()) {
                            if (cursorServiceDetail.getString(0) == serviceListID) {
                                serviceAvailed = cursorServiceDetail.getString(1);
                            }
                        }
                    }

                    customerApointmentItems.add(new CustomerApointmentItems(appID, spName, serviceAvailed, spAddress, appStatus,dropOffDT,
                            "",pickupDT, "",dropOffLoc,pickupLoc));
                }
            }
        }






//        customerApointmentItems.add(new CustomerApointmentItems(20, "CarServiceProvider1",
//                "Brake Fluid Check","Burnaby","Ongoing","2022-10-22",
//                "09:40AM","","","Infront of Metrotown",""));
//
//        customerApointmentItems.add(new CustomerApointmentItems(21, "CarServiceProvider2",
//                "Wheel Replacement","New Westminster","Ongoing","2023-03-14",
//                "01:30PM","2023-03-20","09:20AM","Douglas College","Langara College"));
//
//
//        customerApointmentItems.add(new CustomerApointmentItems(17, "CarServiceProvider3",
//                "Mirror Replacement","New Westminster","Completed","2022-11-01",
//                "01:30PM","2022-11-03","08:30AM","At Customer's Address","At SP's Shop"));

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