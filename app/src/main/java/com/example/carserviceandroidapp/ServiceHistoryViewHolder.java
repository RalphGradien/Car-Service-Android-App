package com.example.carserviceandroidapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServiceHistoryViewHolder extends RecyclerView.ViewHolder {

   TextView nameView, numberView, emailView,
           appointmentTypeView,appointmentStatusView, deliveryTypeView, idNumberView,
           completedDateView, pickupDateView, dropOffDateView, completed, pickup;

    public ServiceHistoryViewHolder(@NonNull View itemView) {

        super(itemView);
        idNumberView = itemView.findViewById(R.id.iD_Number);
        nameView = itemView.findViewById(R.id.name);
        numberView = itemView.findViewById(R.id.number);
        emailView = itemView.findViewById(R.id.email);
        appointmentTypeView = itemView.findViewById(R.id.appointmentType);
        deliveryTypeView = itemView.findViewById(R.id.deliveryType);
        appointmentStatusView = itemView.findViewById(R.id.appointmentStatusHead);
        completedDateView = itemView.findViewById(R.id.completedDate);
        pickupDateView = itemView.findViewById(R.id.pickupDate);
        dropOffDateView = itemView.findViewById(R.id.dropOffDate);
        completed = itemView.findViewById(R.id.completed);
        pickup = itemView.findViewById(R.id.pickUpReady);





    }


}
