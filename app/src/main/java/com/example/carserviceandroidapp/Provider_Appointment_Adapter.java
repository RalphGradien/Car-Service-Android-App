package com.example.carserviceandroidapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Provider_Appointment_Adapter extends RecyclerView.Adapter<Provider_Appointment_ViewHolder> {
    Context context;
    List<Provider_Appointment_Class> providerAppointmentClass;
    private ProviderAppointmentInterface providerAppointmentInterface;

    public Provider_Appointment_Adapter(Context context, List<Provider_Appointment_Class> providerAppointmentClass, ProviderAppointmentInterface providerAppointmentInterface ) {
        this.context = context;
        this.providerAppointmentClass = providerAppointmentClass;
        this.providerAppointmentInterface = providerAppointmentInterface;
    }

    @NonNull
    @Override
    public Provider_Appointment_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Provider_Appointment_ViewHolder(LayoutInflater.from(context).inflate(R.layout.provider_appointmentview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Provider_Appointment_ViewHolder holder, int position) {
        holder.appointmentID.setText(String.valueOf(providerAppointmentClass.get(position).getAppointmentID()));
        holder.customerName.setText(providerAppointmentClass.get(position).getCustomerName());
        holder.selectedServices.setText(providerAppointmentClass.get(position).getSelectedService());
        holder.customerAddress.setText(providerAppointmentClass.get(position).getCustomerAddress());
        holder.appointmentStatusHead.setText(providerAppointmentClass.get(position).getAppointmentStatus());
        holder.dropOffDate.setText(providerAppointmentClass.get(position).getDropOffDateTime());
        holder.pickUpDate.setText(providerAppointmentClass.get(position).getPickUpDateTime());


        if (providerAppointmentClass.get(position).getAppointmentStatus().equals("Ongoing")) {
            int chosenColor = Color.rgb(247, 201, 16);
            holder.appointmentStatusHead.setTextColor(Color.WHITE);
            holder.dropOffDate.setTextColor(chosenColor);
            holder.pickUpDate.setTextColor(chosenColor);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(20);
            drawable.setColor(chosenColor);
            holder.appointmentStatusHead.setBackgroundDrawable(drawable);
        } else if (providerAppointmentClass.get(position).getAppointmentStatus().equals("Completed")) {
            int chosenColor = Color.rgb(101, 207, 114);
            holder.dropOffDate.setTextColor(chosenColor);
            holder.pickUpDate.setTextColor(chosenColor);
            holder.appointmentStatusHead.setTextColor(Color.WHITE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(20);
            drawable.setColor(chosenColor);
            holder.appointmentStatusHead.setBackgroundDrawable(drawable);
        } else if (providerAppointmentClass.get(position).getAppointmentStatus().equals("Cancelled")) {
            holder.appointmentStatusHead.setTextColor(Color.WHITE);
            holder.dropOffDate.setTextColor(Color.RED);
            holder.pickUpDate.setTextColor(Color.RED);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(20);
            drawable.setColor(Color.RED);
            holder.appointmentStatusHead.setBackgroundDrawable(drawable);

        }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                providerAppointmentInterface.onItemClick(providerAppointmentClass.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return providerAppointmentClass.size();
    }
}
