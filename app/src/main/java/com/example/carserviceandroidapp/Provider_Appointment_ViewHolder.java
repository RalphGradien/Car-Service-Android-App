package com.example.carserviceandroidapp;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Provider_Appointment_ViewHolder extends RecyclerView.ViewHolder{
    TextView label,appointmentID, customerName,
            selectedServices, customerAddress, appointmentStatusHead,
            dropOffDateLabel, dropOffDate,
            pickUpDateLabel, pickUpDate;
    RelativeLayout relativeLayout;

    public Provider_Appointment_ViewHolder(@NonNull View itemView) {
        super(itemView);
        label= itemView.findViewById(R.id.label);
        appointmentID = itemView.findViewById(R.id.appointmentID);
        customerName= itemView.findViewById(R.id.customerName);
        selectedServices=itemView.findViewById(R.id.selectedServices);
        customerAddress=itemView.findViewById(R.id.customerAddress);
        appointmentStatusHead = itemView.findViewById(R.id.appointmentStatusHead);
        dropOffDateLabel = itemView.findViewById(R.id.dropOffDateLabel);
        dropOffDate = itemView.findViewById(R.id.dropOffDate);
        pickUpDateLabel = itemView.findViewById(R.id.pickUpDateLabel);
        pickUpDate = itemView.findViewById(R.id.pickUpDate);
        relativeLayout = itemView.findViewById(R.id.containerProviderAppointment);

    }
}
