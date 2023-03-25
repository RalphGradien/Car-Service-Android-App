package com.example.carserviceandroidapp;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerAppointmentViewHolder extends RecyclerView.ViewHolder{
    TextView tvhistappointmentIDLabel,tvhistappointmentIDInt, tvhistbookedServiceProviderName,
            tvhistserviceAvailed, tvhistbookedServiceProviderAddress, tvhistbookingStatus,
            tvhistdropofflabel, tvhistdropoffAppointmentDate,
            tvhistpickuplabel, tvhistpickupAppointmentDate, tvhistAppointmentType;


    RelativeLayout relativeLayout;

    public CustomerAppointmentViewHolder(@NonNull View itemView) {
        super(itemView);
        tvhistappointmentIDLabel= itemView.findViewById(R.id.histappointmentIDLabel);
        tvhistappointmentIDInt = itemView.findViewById(R.id.histappointmentIDInt);
        tvhistbookedServiceProviderName= itemView.findViewById(R.id.histbookedServiceProviderName);
        tvhistserviceAvailed=itemView.findViewById(R.id.histserviceAvailed);
        tvhistbookedServiceProviderAddress=itemView.findViewById(R.id.histbookedServiceProviderAddress);
        tvhistbookingStatus = itemView.findViewById(R.id.histbookingStatus);
        tvhistdropofflabel = itemView.findViewById(R.id.histdropofflabel);
        tvhistdropoffAppointmentDate = itemView.findViewById(R.id.histdropoffAppointmentDate);
        tvhistpickuplabel = itemView.findViewById(R.id.histpickuplabel);
        tvhistpickupAppointmentDate = itemView.findViewById(R.id.histpickupAppointmentDate);
        tvhistAppointmentType = itemView.findViewById(R.id.histserviceAppointType);

        relativeLayout = itemView.findViewById(R.id.container_custappointment_item);


    }
}
