package com.example.carserviceandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
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
       holder.nameView.setText(items.get(position).getServicename());
       holder.numberView.setText(items.get(position).getServicenumber());
       holder.emailView.setText(items.get(position).getServiceemail());
       holder.appointmentTypeView.setText(items.get(position).getServiceappointmentType());
       holder.appointmentStatusView.setText(items.get(position).getServiceappointmentStatus());
       holder.completedDateView.setText(items.get(position).getServicecompletedDate());
       holder.pickupDateView.setText(items.get(position).getServicepickupDate());
       holder.dropOffDateView.setText(items.get(position).getServicedropOffDate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
