package com.example.carserviceandroidapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerAppointmentViewHolder extends RecyclerView.ViewHolder{
    TextView appointmentIDTextView;
    TextView appointmentDateView;
    TextView appointmentTimeView;
    TextView bookedServiceProviderNameView;
    TextView bookedServiceProviderAddressView;
    TextView serviceAvailedView;
    TextView bookingStatusView;

    public CustomerAppointmentViewHolder(@NonNull View itemView) {
        super(itemView);
        appointmentIDTextView = itemView.findViewById(R.id.appointmentIDInt);
        appointmentDateView = itemView.findViewById(R.id.customAppointmentDate);
        appointmentTimeView = itemView.findViewById(R.id.customAppointmentTime);
        bookedServiceProviderNameView = itemView.findViewById(R.id.bookedServiceProviderName);
        bookedServiceProviderAddressView = itemView.findViewById(R.id.bookedServiceProviderAddress);
        serviceAvailedView = itemView.findViewById(R.id.serviceAvailed);
        bookingStatusView = itemView.findViewById(R.id.bookingStatus);
    }
}
