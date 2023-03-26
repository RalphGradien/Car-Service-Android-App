package com.example.carserviceandroidapp;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Provider_Appointment extends Fragment implements ProviderAppointmentInterface {

    int serviceDetailID;
    int appointmentID, customerID;
    String appointmentStatus, dropOffDateTime, dropOffLocation, pickUpDateTime, pickUpLocation, serviceType;
    String customerName, customerAddress, customerContact, customerEmail, selectedService, serviceListID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DBHelper dbh = new DBHelper(getActivity());
        View view = inflater.inflate(R.layout.activity_provider_appointment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewProviderAppointment);

        List<Provider_Appointment_Class> providerAppointmentClass = new ArrayList<>();
        List<Provider_Appointment_Class> providerAppointmentsOngoing = new ArrayList<>();

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
                                    customerName = cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("name"));
                                    customerAddress = cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("address"));
                                    customerContact = cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("mobile"));
                                    customerEmail = cursorCustomer.getString(cursorCustomer.getColumnIndexOrThrow("email"));
                                }
                            }
                        }

//                            spAddress = cusAddress;
                        appointmentStatus = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("AppointmentStatus"));
                        dropOffDateTime = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("DropOffTimeDate"));
                        dropOffLocation = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("DropOffLocation"));
                        pickUpDateTime = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("PickUpDateTime"));
                        pickUpLocation = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("PickUpLocation"));
                        serviceType = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("AppointmentType"));
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
                                    selectedService = cursorServiceDetail.getString(cursorServiceDetail.getColumnIndexOrThrow("ServiceName"));
                                }
                            }
                        }
                        providerAppointmentClass.add(new Provider_Appointment_Class(appointmentID, customerName, customerContact, customerEmail, selectedService, customerAddress,
                                appointmentStatus, dropOffDateTime, pickUpDateTime, pickUpLocation, dropOffLocation, serviceType));
                    }
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        for (Provider_Appointment_Class ongoingItem : providerAppointmentClass) {
            if (ongoingItem.appointmentStatus.equals("Ongoing")) {
                providerAppointmentsOngoing.add(ongoingItem);
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new Provider_Appointment_Adapter(getActivity(), providerAppointmentsOngoing, this));
        return view;
    }
    @Override
    public void onItemClick(Provider_Appointment_Class providerAppointmentClass) {
        Intent intent = new Intent(getActivity(), Provider_Edit_Appointment.class);
        intent.putExtra("AppointmentID", providerAppointmentClass.appointmentID);
        intent.putExtra("CustomerName", providerAppointmentClass.customerName);
        intent.putExtra("CustomerAddress", providerAppointmentClass.customerAddress);
        intent.putExtra("AppointmentStatus", providerAppointmentClass.appointmentStatus);
        intent.putExtra("DropOffDateTime", providerAppointmentClass.dropOffDateTime);
        intent.putExtra("PickupDateTime", providerAppointmentClass.pickUpDateTime);
        intent.putExtra("DropOffLocation", providerAppointmentClass.dropOffLocation);
        intent.putExtra("PickUpLocation", providerAppointmentClass.pickUpLocation);
        intent.putExtra("SelectedService", providerAppointmentClass.selectedService);
        intent.putExtra("CustomerContact", providerAppointmentClass.customerContact);
        intent.putExtra("CustomerEmail", providerAppointmentClass.customerEmail);
        intent.putExtra("AppointmentType", providerAppointmentClass.appointmentType);
        startActivity(intent);
    }
}