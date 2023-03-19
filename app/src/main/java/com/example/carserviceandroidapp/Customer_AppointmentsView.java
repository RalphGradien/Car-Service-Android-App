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

        List<CustomerApointmentItems> customerApointmentItems = new ArrayList<>();
        List<CustomerApointmentItems> customerAppointmentsOngoing = new ArrayList<>();


        int userId=1;
        Cursor cursorAppointment = dbh.getAppointment();
        Cursor cursorServiceProvider = dbh.getServiceProviderDataAll();
        Cursor cursorAppDetail = dbh.getAppointmentDetail();
        Cursor cursorServiceList = dbh.getServiceList();
        Cursor cursorServiceDetail = dbh.getServiceDetails();


        String spName="";
        String spStreet = "";
        String spCity = "";
        String spProvince = "";
        String spPostal = "";
        String spPhone="";
        String spEmail="";
        String serviceAvailed = "";
        String serviceListID = "";
        int serviceDetailID = 0;
        int appID = 0;
        int appSPID=-1;
        try {

            if (cursorAppointment.getCount() > 0) {

                while (cursorAppointment.moveToNext()) {

                    if (cursorAppointment.getInt(cursorAppointment.getColumnIndexOrThrow("Userid")) == userId) {
                        appID = cursorAppointment.getInt(cursorAppointment.getColumnIndexOrThrow("AppointmentID"));
                        appSPID = cursorAppointment.getInt(cursorAppointment.getColumnIndexOrThrow("ServiceProviderID"));

                        if (cursorServiceProvider.getCount() > 0) {
                            cursorServiceProvider.moveToPosition(-1);
                            while (cursorServiceProvider.moveToNext()) {
                                if (cursorServiceProvider.getInt(cursorServiceProvider.getColumnIndexOrThrow("ServiceProviderID")) == appSPID) {
                                    spName = cursorServiceProvider.getString(cursorServiceProvider.getColumnIndexOrThrow("serviceProviderFullName"));
                                    spStreet = cursorServiceProvider.getString(cursorServiceProvider.getColumnIndexOrThrow("street"));
                                    spCity = cursorServiceProvider.getString(cursorServiceProvider.getColumnIndexOrThrow("city"));
                                    spProvince = cursorServiceProvider.getString(cursorServiceProvider.getColumnIndexOrThrow("province"));
                                    spPostal = cursorServiceProvider.getString(cursorServiceProvider.getColumnIndexOrThrow("postalCode"));
                                    spPhone = cursorServiceProvider.getString(cursorServiceProvider.getColumnIndexOrThrow("phone"));
                                    spEmail = cursorServiceProvider.getString(cursorServiceProvider.getColumnIndexOrThrow("email"));
                                }
                            }
                        }

                        String spAddress = spStreet + ", " + spCity + ", " + spProvince + " " + spPostal;
                        String appStatus = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("AppointmentStatus"));
                        String dropOffDT = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("DropOffTimeDate"));
                        String dropOffLoc = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("DropOffLocation"));
                        String pickupDT = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("PickUpDateTime"));
                        String pickupLoc = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("PickUpLocation"));
//
                        if (cursorAppDetail.getCount() > 0) {
                            cursorAppDetail.moveToPosition(-1);
                            while (cursorAppDetail.moveToNext()) {
                                if (cursorAppDetail.getInt(cursorAppDetail.getColumnIndexOrThrow("AppointmentID"))== appID) {
                                    serviceListID = cursorAppDetail.getString(cursorAppDetail.getColumnIndexOrThrow("ServiceListID"));
                                }
                            }
                        }
                        if (cursorServiceList.getCount() > 0) {
                            cursorServiceList.moveToPosition(-1);
                            while (cursorServiceList.moveToNext()) {
                                if (cursorServiceList.getString(cursorServiceList.getColumnIndexOrThrow("ServiceListID")).equals(serviceListID)) {
                                    serviceDetailID = cursorServiceList.getInt(cursorServiceList.getColumnIndexOrThrow("ServiceDetailID"));
                                }
                            }
                        }
                        if (cursorServiceDetail.getCount() > 0) {
                            cursorServiceDetail.moveToPosition(-1);
                            while (cursorServiceDetail.moveToNext()) {
                                if (cursorServiceDetail.getInt(cursorServiceDetail.getColumnIndexOrThrow("ServiceDetailID")) == serviceDetailID) {
                                    serviceAvailed = cursorServiceDetail.getString(cursorServiceDetail.getColumnIndexOrThrow("ServiceName"));
                                }
                            }
                        }
                        customerApointmentItems.add(new CustomerApointmentItems(appID, spName, serviceAvailed, spAddress, appStatus, dropOffDT,"", pickupDT, "", dropOffLoc, pickupLoc,spPhone,spEmail));

                    }
                }
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }

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
        intent.putExtra("AppId",customerApointmentItems.histappointmentIDInt);
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
        intent.putExtra("SPPhone",customerApointmentItems.histSPPhone);
        intent.putExtra("SPEmail",customerApointmentItems.histSPEmail);
        //place cell number here
        //place email address
        startActivity(intent);
    }


}