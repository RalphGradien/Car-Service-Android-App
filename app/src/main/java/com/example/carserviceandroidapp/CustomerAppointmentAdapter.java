package com.example.carserviceandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerAppointmentAdapter extends RecyclerView.Adapter<CustomerAppointmentViewHolder> {
    Context context;
    List<CustomerApointmentItems> customerApointmentItemsList;

    public CustomerAppointmentAdapter(Context context, List<CustomerApointmentItems> customerApointmentItemsList) {
        this.context = context;
        this.customerApointmentItemsList = customerApointmentItemsList;
    }

    @NonNull
    @Override
    public CustomerAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomerAppointmentViewHolder(LayoutInflater.from(context).inflate(R.layout.customer_appointmentsview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAppointmentViewHolder holder, int position) {
        holder.appointmentIDTextView.setText(customerApointmentItemsList.get(position).getAppointmentIDText());
        holder.appointmentDateView.setText(customerApointmentItemsList.get(position).getAppointmentDate());
        holder.appointmentTimeView.setText(customerApointmentItemsList.get(position).getAppointmentTime());
        holder.bookedServiceProviderNameView.setText(customerApointmentItemsList.get(position).getBookedServiceProviderName());
        holder.bookedServiceProviderAddressView.setText(customerApointmentItemsList.get(position).getBookedServiceProviderAddress());
        holder.serviceAvailedView.setText(customerApointmentItemsList.get(position).getServiceAvailed());
        holder.bookingStatusView.setText(customerApointmentItemsList.get(position).getBookingStatus());
    }

    @Override
    public int getItemCount() {
        return customerApointmentItemsList.size();
    }
}
