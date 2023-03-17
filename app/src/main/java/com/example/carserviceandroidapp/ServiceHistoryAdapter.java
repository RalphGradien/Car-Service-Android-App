package com.example.carserviceandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServiceHistoryAdapter extends RecyclerView.Adapter<ServiceHistoryViewHolder> {

    Context context;

    List<ServiceHistoryItems> items;

    public ServiceHistoryAdapter(Context context, List<ServiceHistoryItems> items) {
        this.context = context;
        this.items = items;
    }

    public ServiceHistoryAdapter(Context applicationContext) {
    }

    @NonNull
    @Override
    public ServiceHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ServiceHistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.history_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceHistoryViewHolder holder, int position) {
        ServiceHistoryItems currentItem = items.get(position);
        holder.nameView.setText(currentItem.getCustomerName());
        holder.numberView.setText(currentItem.getCustomerNumber());
        holder.emailView.setText(currentItem.getCustomerEmail());
        holder.appointmentTypeView.setText(currentItem.getServiceAppointmentType());

        String appointmentStatus = currentItem.getServiceAppointmentStatus();
        holder.appointmentStatusView.setText(appointmentStatus);

        if (appointmentStatus.equals("Completed")) {
            holder.completedDateView.setText(currentItem.getServiceCompletedDate());

            holder.appointmentStatusView.setBackgroundResource(R.drawable.green_rounded_rectangle);
        } else if (appointmentStatus.equals("Cancelled")) {
            holder.pickupDateView.setVisibility(View.GONE);
            holder.completedDateView.setVisibility(View.GONE);
            holder.dropOffDateView.setText(currentItem.getServiceDropOffDate());
            holder.appointmentStatusView.setBackgroundResource(R.drawable.red_rounded_rectangle);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
