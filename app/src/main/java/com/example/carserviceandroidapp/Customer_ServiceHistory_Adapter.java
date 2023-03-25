package com.example.carserviceandroidapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Customer_ServiceHistory_Adapter extends RecyclerView.Adapter<Customer_ServiceHistory_ViewHolder> {
    Context context;
    List<Customer_ServiceHistory_Items>customer_serviceHistory_items;

    public Customer_ServiceHistory_Adapter(Context context, List<Customer_ServiceHistory_Items> customer_serviceHistory_items) {
        this.context = context;
        this.customer_serviceHistory_items = customer_serviceHistory_items;
    }

    @NonNull
    @Override
    public Customer_ServiceHistory_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Customer_ServiceHistory_ViewHolder((LayoutInflater.from(context).inflate(R.layout.customerhistory_item_view, parent,false)));
    }

    @Override
    public void onBindViewHolder(@NonNull Customer_ServiceHistory_ViewHolder holder, int position) {
        holder.tvhistappointmentIDInt.setText(String.valueOf(customer_serviceHistory_items.get(position).getHistappointmentIDInt()));
        holder.tvhistbookedServiceProviderName.setText(customer_serviceHistory_items.get(position).getHistbookedServiceProviderName());
        holder.tvhistserviceAvailed.setText(customer_serviceHistory_items.get(position).getHistserviceAvailed());
        holder.tvhistbookedServiceProviderAddress.setText(customer_serviceHistory_items.get(position).getHistbookedServiceProviderAddress());
        holder.tvhistbookingStatus.setText(customer_serviceHistory_items.get(position).getHistbookingStatus());
        holder.tvhistdropoffAppointmentDate.setText(customer_serviceHistory_items.get(position).getHistdropoffAppointmentDate());
        //holder.tvhistcustomDropOffTime.setText(customer_serviceHistory_items.get(position).getHistcustomDropOffTime());
        holder.tvhistpickupAppointmentDate.setText(customer_serviceHistory_items.get(position).getHistpickupAppointmentDate());
       // holder.tvhistcustomPickupTime.setText(customer_serviceHistory_items.get(position).getHistcustomPickupTime());
        holder.tvhistcustomAppointmentType.setText(customer_serviceHistory_items.get(position).getHistcustomAppType());

        if (customer_serviceHistory_items.get(position).getHistbookingStatus().equals("Ongoing")) {
            int chosenColor = Color.rgb(247, 201, 16);
            holder.tvhistbookingStatus.setTextColor(Color.WHITE);
            holder.tvhistdropoffAppointmentDate.setTextColor(chosenColor);
            holder.tvhistpickupAppointmentDate.setTextColor(chosenColor);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(20);
            drawable.setColor(chosenColor);
            holder.tvhistbookingStatus.setBackgroundDrawable(drawable);
        } else if (customer_serviceHistory_items.get(position).getHistbookingStatus().equals("Cancelled")) {

            holder.tvhistbookingStatus.setTextColor(Color.WHITE);
            holder.tvhistdropoffAppointmentDate.setTextColor(Color.RED);
            holder.tvhistpickupAppointmentDate.setTextColor(Color.RED);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(20);
            drawable.setColor(Color.RED);
            holder.tvhistbookingStatus.setBackgroundDrawable(drawable);
        } else if (customer_serviceHistory_items.get(position).getHistbookingStatus().equals("Completed")) {
            int chosenColor = Color.rgb(101, 207, 114);
            holder.tvhistdropoffAppointmentDate.setTextColor(chosenColor);
            holder.tvhistpickupAppointmentDate.setTextColor(chosenColor);
            holder.tvhistbookingStatus.setTextColor(Color.WHITE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(20);
            drawable.setColor(chosenColor);
            holder.tvhistbookingStatus.setBackgroundDrawable(drawable);

        }
    }

    @Override
    public int getItemCount() {
        return customer_serviceHistory_items.size();
    }
}
