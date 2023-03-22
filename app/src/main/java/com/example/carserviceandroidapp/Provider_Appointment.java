package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Provider_Appointment extends Fragment implements CustomerAppointmentsViewSelectInterface {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            DBHelper dbh = new DBHelper(getActivity());
            View view = inflater.inflate(R.layout.activity_provider_appointment, container, false);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerViewProviderAppointment);

            List<CustomerApointmentItems> customerApointmentItems = new ArrayList<>();
            List<CustomerApointmentItems> customerAppointmentsOngoing = new ArrayList<>();


            int spID = ServiceProvider.ServiceProviderID;
            Cursor cursorAppointment = dbh.getAppointment();
            Cursor cursorCustomer = dbh.getCustomerInfo();
            Cursor cursorAppDetail = dbh.getAppointmentDetail();
            Cursor cursorServiceList = dbh.getServiceList();
            Cursor cursorServiceDetail = dbh.getServiceDetails();


            String cusName = "";
            String cusAddress = "";
            String cusContact = "";
            String cusEmail = "";
            String selectedServices = "";
            String serviceListID = "";
            int serviceDetailID = 0;
            int appID = 0;
            int cusID = -1;
            try {

                if (cursorAppointment.getCount() > 0) {

                    while (cursorAppointment.moveToNext()) {

                        if (cursorAppointment.getInt(cursorAppointment.getColumnIndexOrThrow("ServiceProviderID")) == spID) {
                            appID = cursorAppointment.getInt(cursorAppointment.getColumnIndexOrThrow("AppointmentID"));
                            cusID = cursorAppointment.getInt(cursorAppointment.getColumnIndexOrThrow("Userid"));

                            if (cursorCustomer.getCount() > 0) {
                                cursorCustomer.moveToPosition(-1);
                                while (cursorCustomer.moveToNext()) {
                                    if (cursorCustomer.getInt(cursorCustomer.getColumnIndexOrThrow("Userid")) == cusID) {
                                        cusName = cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("name"));
                                        cusAddress = cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("address"));


                                        cusContact = cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("mobile"));
                                        cusEmail = cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("address"));
                                    }
                                }
                            }

                            String spAddress = cusAddress;
                            String appStatus = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("AppointmentStatus"));
                            String dropOffDT = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("DropOffTimeDate"));
                            String dropOffLoc = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("DropOffLocation"));
                            String pickupDT = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("PickUpDateTime"));
                            String pickupLoc = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("PickUpLocation"));
//
                            if (cursorAppDetail.getCount() > 0) {
                                cursorAppDetail.moveToPosition(-1);
                                while (cursorAppDetail.moveToNext()) {
                                    if (cursorAppDetail.getInt(cursorAppDetail.getColumnIndexOrThrow("AppointmentID")) == appID) {
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
                                        selectedServices = cursorServiceDetail.getString(cursorServiceDetail.getColumnIndexOrThrow("ServiceName"));
                                    }
                                }
                            }
                            customerApointmentItems.add(new CustomerApointmentItems(appID, cusName, selectedServices, cusAddress, appStatus, dropOffDT, "", pickupDT, "", dropOffLoc, pickupLoc, cusContact, cusEmail));

                        }
                    }
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }

            for (CustomerApointmentItems ongoingItem : customerApointmentItems) {
                if (ongoingItem.histbookingStatus.equals("Ongoing") ) {
                    customerAppointmentsOngoing.add(ongoingItem);
                }
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(new CustomerAppointmentAdapter(getActivity(), customerAppointmentsOngoing, this));
            return view;

        }

        @Override
        public void onItemClick(CustomerApointmentItems customerApointmentItems) {

            Intent intent = new Intent(getActivity(), Provider_Appointment.class);
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
            intent.putExtra("cusContact",customerApointmentItems.histSPPhone);
            intent.putExtra("cusEmail",customerApointmentItems.histSPEmail);
            //place cell number here
            //place email address
            startActivity(intent);
        }
    }