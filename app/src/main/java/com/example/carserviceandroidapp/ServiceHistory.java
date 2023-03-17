package com.example.carserviceandroidapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ServiceHistory extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_service_history, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.historyRecyclerView);
        List<ServiceHistoryItems> historyItems = new ArrayList<>();

        int serviceProviderID = ServiceProvider.ServiceProviderID;
//        int serviceProviderID = 1;
        // retrieve appointments data for serviceProviderID
        DBHelper dbHelper = new DBHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT * FROM APPOINTMENT WHERE ServiceProviderID=?", new String[]{String.valueOf(serviceProviderID)})) {
            // loop through cursor and add values to historyItems
            while (cursor.moveToNext()) {
                int customerId = cursor.getInt(cursor.getColumnIndexOrThrow("Userid"));
                try (Cursor customerCursor = db.rawQuery("SELECT * FROM CUSTOMER WHERE Userid=?", new String[]{String.valueOf(customerId)})) {
                    if (customerCursor.moveToFirst()) {
                        String customerName = customerCursor.getString(customerCursor.getColumnIndexOrThrow("name"));
                        String customerNumber = customerCursor.getString(customerCursor.getColumnIndexOrThrow("mobile"));
                        String customerEmail = customerCursor.getString(customerCursor.getColumnIndexOrThrow("email"));
                        String pickUpDateTime = cursor.getString(cursor.getColumnIndexOrThrow("PickUpDateTime"));
                        String pickUpLocation = cursor.getString(cursor.getColumnIndexOrThrow("PickUpLocation"));
                        String pickUpReadyDate = cursor.getString(cursor.getColumnIndexOrThrow("PickUpReadyDate"));
                        String dropOffTimeDate = cursor.getString(cursor.getColumnIndexOrThrow("DropOffTimeDate"));
                        String dropOffLocation = cursor.getString(cursor.getColumnIndexOrThrow("DropOffLocation"));
                        String bookingDate = cursor.getString(cursor.getColumnIndexOrThrow("BookingDate"));
                        String cancelledDate = cursor.getString(cursor.getColumnIndexOrThrow("CancelledDate"));
                        String appointmentType = cursor.getString(cursor.getColumnIndexOrThrow("AppointmentType"));
                        String appointmentStatus = cursor.getString(cursor.getColumnIndexOrThrow("AppointmentStatus"));

                        if (appointmentStatus.equals("Completed")) {
                            historyItems.add(new ServiceHistoryItems(customerName, customerNumber, customerEmail, pickUpDateTime, pickUpReadyDate, dropOffTimeDate, appointmentStatus, appointmentType));
                        } else {
                            historyItems.add(new ServiceHistoryItems(customerName, customerNumber, customerEmail, appointmentStatus, appointmentType, dropOffTimeDate));
                        }



                    }
                }
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ServiceHistoryAdapter(getActivity(), historyItems));
        return view;

    }
}