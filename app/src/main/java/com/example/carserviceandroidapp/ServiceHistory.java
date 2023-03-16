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
    //@Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_service_history, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.historyRecyclerView);
        List<ServiceHistoryItems> historyItems = new ArrayList<ServiceHistoryItems>();

        int serviceProviderID = 69;
        int userID = 1;
        // retrieve appointments data for serviceProviderID
        DBHelper dbHelper = new DBHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM APPOINTMENTS WHERE ServiceProviderID=?", new String[]{String.valueOf(serviceProviderID)});
        Cursor customerCursor = db.rawQuery("SELECT * FROM CUSTOMER WHERE Userid=?", new String[]{String.valueOf(userID)});
        // loop through cursor and add values to historyItems
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String customerName = customerCursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String customerNumber = customerCursor.getString(cursor.getColumnIndex("mobile"));
            @SuppressLint("Range") String customerEmail = customerCursor.getString(cursor.getColumnIndex("email"));
            @SuppressLint("Range") String pickUpDateTime = cursor.getString(cursor.getColumnIndex("PickUpDateTime"));
            @SuppressLint("Range") String pickUpLocation = cursor.getString(cursor.getColumnIndex("PickUpLocation"));
            @SuppressLint("Range") String pickUpReadyDate = cursor.getString(cursor.getColumnIndex("PickUpReadyDate"));
            @SuppressLint("Range") String dropOffTimeDate = cursor.getString(cursor.getColumnIndex("DropOffTimeDate"));
            @SuppressLint("Range") String dropOffLocation = cursor.getString(cursor.getColumnIndex("DropOffLocation"));
            @SuppressLint("Range") String bookingDate = cursor.getString(cursor.getColumnIndex("BookingDate"));
            @SuppressLint("Range") String cancelledDate = cursor.getString(cursor.getColumnIndex("CancelledDate"));
            @SuppressLint("Range") String appointmentType = cursor.getString(cursor.getColumnIndex("AppointmentType"));
            @SuppressLint("Range") String appointmentStatus = cursor.getString(cursor.getColumnIndex("AppointmentStatus"));
//
//
            historyItems.add(new ServiceHistoryItems(customerName,customerNumber,customerEmail, pickUpDateTime, pickUpReadyDate, dropOffTimeDate, appointmentStatus, appointmentType));
        }
        cursor.close();
        dbHelper.close();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ServiceHistoryAdapter(getActivity(), historyItems));
        return view;

    }
}
