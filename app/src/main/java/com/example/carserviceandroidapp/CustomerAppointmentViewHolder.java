package com.example.carserviceandroidapp;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerAppointmentViewHolder extends RecyclerView.ViewHolder{
    TextView appointmentIDLabelView;
    TextView appointmentIDTextView;
    TextView appointmentDateView;
    TextView appointmentTimeView;
    TextView bookedServiceProviderNameView;
    TextView bookedServiceProviderAddressView;
    TextView spacerView;
    TextView serviceAvailedView;
    TextView bookingStatusView;

    RelativeLayout relativeLayout;

    public CustomerAppointmentViewHolder(@NonNull View itemView) {
        super(itemView);
        appointmentIDLabelView = itemView.findViewById(R.id.appointmentIDLabel);
        appointmentIDTextView = itemView.findViewById(R.id.appointmentIDInt);
        appointmentDateView = itemView.findViewById(R.id.customAppointmentDate);
        appointmentTimeView = itemView.findViewById(R.id.customAppointmentTime);
        bookedServiceProviderNameView = itemView.findViewById(R.id.bookedServiceProviderName);
        bookedServiceProviderAddressView = itemView.findViewById(R.id.bookedServiceProviderAddress);
        spacerView = itemView.findViewById(R.id.spacer);
        serviceAvailedView = itemView.findViewById(R.id.serviceAvailed);
        bookingStatusView = itemView.findViewById(R.id.bookingStatus);

        relativeLayout = itemView.findViewById(R.id.container_custappointment_item);


    }
}
