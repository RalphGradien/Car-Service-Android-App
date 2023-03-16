package com.example.carserviceandroidapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServiceHistoryViewHolder extends RecyclerView.ViewHolder {

   TextView nameView, numberView, emailView,
           appointmentTypeView,appointmentStatusView,
           completedDateView, pickupDateView, dropOffDateView;

    public ServiceHistoryViewHolder(@NonNull View itemView) {

        super(itemView);
        nameView = itemView.findViewById(R.id.name);
        numberView = itemView.findViewById(R.id.number);
        emailView = itemView.findViewById(R.id.email);
        appointmentTypeView = itemView.findViewById(R.id.appointmentType);
        appointmentStatusView = itemView.findViewById(R.id.appointmentStatus);
        completedDateView = itemView.findViewById(R.id.completedDate);
        pickupDateView = itemView.findViewById(R.id.pickupDate);
        dropOffDateView = itemView.findViewById(R.id.dropOffDate);



    }


}