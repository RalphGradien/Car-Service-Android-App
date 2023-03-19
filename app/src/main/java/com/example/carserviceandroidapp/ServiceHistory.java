package com.example.carserviceandroidapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ServiceHistory extends Fragment {
    //E/SQLiteLog: (1) no such column: A.ServiceDetailID in "SELECT C.name, C.mobile, C.email, A.PickUpDateTime, A.PickUpReadyDate, A.DropOffTimeDate, A.AppointmentStatus, SD.ServiceName FROM APPOINTMENT A INNER JOIN CUSTOMER C ON A
    //D/AndroidRuntime: Shutting down VM
    //E/AndroidRuntime: FATAL EXCEPTION: main
    private static final String QUERY_COMPLETED_APPOINTMENTS =
            "SELECT C.name, C.mobile, C.email, A.AppointmentStatus, A.PickUpDateTime, A.PickUpReadyDate,A.DropOffTimeDate, A.ServiceProviderID, SD.ServiceName " +
                    "FROM APPOINTMENT A " +
                    "INNER JOIN CUSTOMER C ON A.Userid = C.Userid " +
                    "INNER JOIN APPOINTMENT_DETAIL AD ON A.AppointmentID = AD.AppointmentID " +
                    "INNER JOIN SERVICE_LIST SL ON AD.ServiceListID = SL.ServiceListID " +
                    "INNER JOIN SERVICE_DETAIL SD ON SL.ServiceDetailID = SD.ServiceDetailID " +
                    "WHERE A.ServiceProviderID = ? AND AppointmentStatus = 'Completed'";

    private static final String QUERY_CANCELLED_APPOINTMENTS =
            "SELECT C.name, C.mobile, C.email, A.AppointmentStatus, A.PickUpDateTime, A.PickUpReadyDate,A.DropOffTimeDate, A.ServiceProviderID, SD.ServiceName " +
                    "FROM APPOINTMENT A " +
                    "INNER JOIN CUSTOMER C ON A.Userid = C.Userid " +
                    "INNER JOIN APPOINTMENT_DETAIL AD ON A.AppointmentID = AD.AppointmentID " +
                    "INNER JOIN SERVICE_LIST SL ON AD.ServiceListID = SL.ServiceListID " +
                    "INNER JOIN SERVICE_DETAIL SD ON SL.ServiceDetailID = SD.ServiceDetailID " +
                    "WHERE A.ServiceProviderID = ? AND AppointmentStatus = 'Cancelled'";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_service_history, container, false);
        List<ServiceHistoryItems> historyItems = new ArrayList<>();
        Spinner filterSpinner = view.findViewById(R.id.filterSpinner);
        RecyclerView recyclerView = view.findViewById(R.id.historyRecyclerView);
        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                List<ServiceHistoryItems> filteredItems = new ArrayList<>();
                if (selectedOption.equals("Completed")) {
                    for (ServiceHistoryItems item : historyItems) {
                        if (item.getServiceAppointmentStatus().equals("Completed")) {
                            filteredItems.add(item);
                        }
                    }
                } else if (selectedOption.equals("Cancelled")) {
                    for (ServiceHistoryItems item : historyItems) {
                        if (item.getServiceAppointmentStatus().equals("Cancelled")) {
                            filteredItems.add(item);
                        }
                    }
                } else {
                    filteredItems.addAll(historyItems);
                }
                recyclerView.setAdapter(new ServiceHistoryAdapter(getActivity(), filteredItems));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });





//        int serviceProviderID = ServiceProvider.ServiceProviderID;
        int serviceProviderID = 1;

        DBHelper dbHelper = new DBHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try (Cursor cursor = db.rawQuery(QUERY_COMPLETED_APPOINTMENTS, new String[]{String.valueOf(serviceProviderID)})) {
            while (cursor.moveToNext()) {
                String customerName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String customerNumber = cursor.getString(cursor.getColumnIndexOrThrow("mobile"));
                String customerEmail = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String pickUpDateTime = cursor.getString(cursor.getColumnIndexOrThrow("PickUpDateTime"));
                String pickUpReadyDate = cursor.getString(cursor.getColumnIndexOrThrow("PickUpReadyDate"));
                String dropOffTimeDate = cursor.getString(cursor.getColumnIndexOrThrow("DropOffTimeDate"));
                String appointmentStatus = cursor.getString(cursor.getColumnIndexOrThrow("AppointmentStatus"));
                String serviceName = cursor.getString(cursor.getColumnIndexOrThrow("ServiceName"));

                historyItems.add(new ServiceHistoryItems(customerName, customerNumber, customerEmail, pickUpDateTime, pickUpReadyDate, dropOffTimeDate, appointmentStatus, serviceName));
            }
        }

        try (Cursor cursor = db.rawQuery(QUERY_CANCELLED_APPOINTMENTS, new String[]{String.valueOf(serviceProviderID)})) {
            while (cursor.moveToNext()) {
                String customerName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String customerNumber = cursor.getString(cursor.getColumnIndexOrThrow("mobile"));
                String customerEmail = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String dropOffTimeDate = cursor.getString(cursor.getColumnIndexOrThrow("DropOffTimeDate"));
                String appointmentStatus = cursor.getString(cursor.getColumnIndexOrThrow("AppointmentStatus"));
                String serviceName = cursor.getString(cursor.getColumnIndexOrThrow("ServiceName"));

                historyItems.add(new ServiceHistoryItems(customerName, customerNumber, customerEmail, appointmentStatus, serviceName , dropOffTimeDate));
            }
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ServiceHistoryAdapter(getActivity(), historyItems));
        return view;
    }

}
