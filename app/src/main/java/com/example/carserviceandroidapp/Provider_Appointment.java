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
    String cusName = "";
    String cusAddress = "";
    String cusContact = "";
    String cusEmail = "";
    String selectedServices = "";
    String serviceListID = "";
    int serviceDetailID = 0;
    int appointmentID;
    int customerID;
    String spAddress, appStatus, dropOffDateTime, dropOffLocation, pickUpDateTime, pickUpLocation;

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

        try {

            if (cursorAppointment.getCount() > 0) {

                while (cursorAppointment.moveToNext()) {

                    if (cursorAppointment.getInt(cursorAppointment.getColumnIndexOrThrow("ServiceProviderID")) == spID) {
                        appointmentID = cursorAppointment.getInt(cursorAppointment.getColumnIndexOrThrow("AppointmentID"));
                        customerID = cursorAppointment.getInt(cursorAppointment.getColumnIndexOrThrow("Userid"));

                        if (cursorCustomer.getCount() > 0) {
                            cursorCustomer.moveToPosition(-1);
                            while (cursorCustomer.moveToNext()) {
                                if (cursorCustomer.getInt(cursorCustomer.getColumnIndexOrThrow("Userid")) == customerID) {
                                    cusName = cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("name"));
                                    cusAddress = cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("address"));
                                    cusContact = cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("mobile"));
                                    cusEmail = cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("email"));
                                }
                            }
                        }

//                            spAddress = cusAddress;
                        appStatus = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("AppointmentStatus"));
                        dropOffDateTime = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("DropOffTimeDate"));
                        dropOffLocation = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("DropOffLocation"));
                        pickUpDateTime = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("PickUpDateTime"));
                        pickUpLocation = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("PickUpLocation"));
//
                        if (cursorAppDetail.getCount() > 0) {
                            cursorAppDetail.moveToPosition(-1);
                            while (cursorAppDetail.moveToNext()) {
                                if (cursorAppDetail.getInt(cursorAppDetail.getColumnIndexOrThrow("AppointmentID")) == appointmentID) {
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
                        customerApointmentItems.add(new CustomerApointmentItems(appointmentID, cusName, selectedServices, cusAddress, appStatus, dropOffDateTime,
                                "", pickUpDateTime, "", dropOffLocation, pickUpLocation, cusContact, cusEmail));

                    }
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        for (CustomerApointmentItems ongoingItem : customerApointmentItems) {
            if (ongoingItem.histbookingStatus.equals("Ongoing")) {
                customerAppointmentsOngoing.add(ongoingItem);
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new CustomerAppointmentAdapter(getActivity(), customerAppointmentsOngoing, this));
        return view;
    }

    @Override
    public void onItemClick(CustomerApointmentItems customerApointmentItems) {
        Intent intent = new Intent(getActivity(), Provider_Edit_Appointment.class);
        intent.putExtra("AppointmentID", appointmentID);
        intent.putExtra("CustomerName", cusName);
        intent.putExtra("CustomerAddress", cusAddress);
        intent.putExtra("AppointmentStatus", appStatus);
        intent.putExtra("DropOffDateTime", dropOffDateTime);
        intent.putExtra("PickupDateTime", pickUpDateTime);
        intent.putExtra("DropOffLocation", dropOffLocation);
        intent.putExtra("PickUpLocation", pickUpLocation);
        intent.putExtra("SelectedService", selectedServices);
        intent.putExtra("CustomerContact", cusContact);
        intent.putExtra("CustomerEmail", cusEmail);
        startActivity(intent);
    }
}