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
    private CustomerAppointmentsViewSelectInterface selectInterface;

    public ServiceHistoryAdapter(Context context, List<ServiceHistoryItems> items) {
        this.context = context;
        this.items = items;
    }

//    public ServiceHistoryAdapter(Context applicationContext) {}

    @NonNull
    @Override
    public ServiceHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ServiceHistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.serivce_history_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceHistoryViewHolder holder, int position) {
        ServiceHistoryItems currentItem = items.get(position);
        holder.nameView.setText(currentItem.getCustomerName());
        holder.numberView.setText(currentItem.getCustomerNumber());
        holder.emailView.setText(currentItem.getCustomerEmail());
        holder.appointmentTypeView.setText(currentItem.getServiceDetails());
        holder.deliveryTypeView.setText(currentItem.getServiceAppointmentType());
        holder.idNumberView.setText(currentItem.getServiceAppointmentID());
        String appointmentStatus = currentItem.getServiceAppointmentStatus();
        holder.appointmentStatusView.setText(appointmentStatus);
        //Shows all parameters when "completed"
        if (appointmentStatus.equals("Completed")) {
            holder.completedDateView.setText(currentItem.getServiceCompletedDate());
            holder.pickupDateView.setText(currentItem.getServicePickupDate());
            holder.dropOffDateView.setText(currentItem.getServiceDropOffDate());
            holder.appointmentStatusView.setBackgroundResource(R.drawable.green_rounded_rectangle);
        }
        //Adjusts styling if cancelled
        else if (appointmentStatus.equals("Cancelled")) {
            holder.pickupDateView.setVisibility(View.GONE);
            holder.completedDateView.setVisibility(View.GONE);
            holder.completed.setVisibility(View.GONE);
            holder.pickup.setVisibility(View.GONE);
            holder.dropOffDateView.setText(currentItem.getServiceDropOffDate());
            holder.dropOffDateView.setTextColor(context.getResources().getColor(R.color.red));
            holder.appointmentStatusView.setBackgroundResource(R.drawable.red_rounded_rectangle);
        }
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}
