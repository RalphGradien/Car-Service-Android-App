package com.example.carserviceandroidapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerAppointmentAdapter extends RecyclerView.Adapter<CustomerAppointmentViewHolder> {
    Context context;
    List<CustomerApointmentItems> customerApointmentItemsList;
    private CustomerAppointmentsViewSelectInterface selectInterface;

    public CustomerAppointmentAdapter(Context context, List<CustomerApointmentItems> customerApointmentItemsList, CustomerAppointmentsViewSelectInterface selectInterface ) {
        this.context = context;
        this.customerApointmentItemsList = customerApointmentItemsList;
        this.selectInterface = selectInterface;
    }

    @NonNull
    @Override
    public CustomerAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomerAppointmentViewHolder(LayoutInflater.from(context).inflate(R.layout.customer_appointmentsview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAppointmentViewHolder holder, int position) {
            holder.tvhistappointmentIDInt.setText(String.valueOf(customerApointmentItemsList.get(position).getHistappointmentIDInt()));
            holder.tvhistbookedServiceProviderName.setText(customerApointmentItemsList.get(position).getHistbookedServiceProviderName());
            holder.tvhistserviceAvailed.setText(customerApointmentItemsList.get(position).getHistserviceAvailed());
            holder.tvhistbookedServiceProviderAddress.setText(customerApointmentItemsList.get(position).getHistbookedServiceProviderAddress());
            holder.tvhistbookingStatus.setText(customerApointmentItemsList.get(position).getHistbookingStatus());
            holder.tvhistdropoffAppointmentDate.setText(customerApointmentItemsList.get(position).getHistdropoffAppointmentDate());
            //holder.tvhistcustomDropOffTime.setText(customerApointmentItemsList.get(position).getHistcustomDropOffTime());
            holder.tvhistpickupAppointmentDate.setText(customerApointmentItemsList.get(position).getHistpickupAppointmentDate());
            //holder.tvhistcustomPickupTime.setText(customerApointmentItemsList.get(position).getHistcustomPickupTime());
            holder.tvhistAppointmentType.setText(customerApointmentItemsList.get(position).getHistAppointType());


            if (customerApointmentItemsList.get(position).getHistbookingStatus().equals("Ongoing")) {
                int chosenColor = Color.rgb(247, 201, 16);
                holder.tvhistbookingStatus.setTextColor(Color.WHITE);
                holder.tvhistdropoffAppointmentDate.setTextColor(chosenColor);
                holder.tvhistpickupAppointmentDate.setTextColor(chosenColor);
                GradientDrawable drawable = new GradientDrawable();
                drawable.setShape(GradientDrawable.RECTANGLE);
                drawable.setCornerRadius(20);
                drawable.setColor(chosenColor);
                holder.tvhistbookingStatus.setBackgroundDrawable(drawable);
            } else if (customerApointmentItemsList.get(position).getHistbookingStatus().equals("Completed")) {
                int chosenColor = Color.rgb(101, 207, 114);
                holder.tvhistdropoffAppointmentDate.setTextColor(chosenColor);
                holder.tvhistpickupAppointmentDate.setTextColor(chosenColor);
                holder.tvhistbookingStatus.setTextColor(Color.WHITE);
                GradientDrawable drawable = new GradientDrawable();
                drawable.setShape(GradientDrawable.RECTANGLE);
                drawable.setCornerRadius(20);
                drawable.setColor(chosenColor);
                holder.tvhistbookingStatus.setBackgroundDrawable(drawable);
            } else if (customerApointmentItemsList.get(position).getHistbookingStatus().equals("Cancelled")) {
                holder.tvhistbookingStatus.setTextColor(Color.WHITE);
                holder.tvhistdropoffAppointmentDate.setTextColor(Color.RED);
                holder.tvhistpickupAppointmentDate.setTextColor(Color.RED);
                GradientDrawable drawable = new GradientDrawable();
                drawable.setShape(GradientDrawable.RECTANGLE);
                drawable.setCornerRadius(20);
                drawable.setColor(Color.RED);
                holder.tvhistbookingStatus.setBackgroundDrawable(drawable);

            }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectInterface.onItemClick(customerApointmentItemsList.get(holder.getAdapterPosition()));
            }
        });

        }


    @Override
    public int getItemCount() {
        return customerApointmentItemsList.size();
    }
}
