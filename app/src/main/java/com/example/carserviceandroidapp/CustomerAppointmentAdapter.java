package com.example.carserviceandroidapp;

import android.content.Context;
import android.content.Intent;
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
          holder.appointmentIDTextView.setText(String.valueOf((customerApointmentItemsList.get(position).getAppointmentIDText())));
          holder.appointmentDateView.setText(customerApointmentItemsList.get(position).getAppointmentDate());
          holder.appointmentTimeView.setText(customerApointmentItemsList.get(position).getAppointmentTime());
          holder.bookedServiceProviderNameView.setText(customerApointmentItemsList.get(position).getBookedServiceProviderName());
          holder.bookedServiceProviderAddressView.setText(customerApointmentItemsList.get(position).getBookedServiceProviderAddress());
          holder.serviceAvailedView.setText(customerApointmentItemsList.get(position).getServiceAvailed());
          holder.bookingStatusView.setText(customerApointmentItemsList.get(position).getBookingStatus());
          if(customerApointmentItemsList.get(position).getBookingStatus().equals("Ongoing")){
              holder.bookingStatusView.setTextColor(Color.WHITE);
              GradientDrawable drawable = new GradientDrawable();
              drawable.setShape(GradientDrawable.RECTANGLE);
              drawable.setCornerRadius(20);
              int chosenColor = Color.rgb(247,201,16);
              drawable.setColor(chosenColor);
              holder.bookingStatusView.setBackgroundDrawable(drawable);
          }
          else if(customerApointmentItemsList.get(position).getBookingStatus().equals("Completed")){
              holder.bookingStatusView.setTextColor(Color.WHITE);
              GradientDrawable drawable = new GradientDrawable();
              drawable.setShape(GradientDrawable.RECTANGLE);
              drawable.setCornerRadius(20);
              drawable.setColor(Color.RED);
              holder.bookingStatusView.setBackgroundDrawable(drawable);
          }

          else if(customerApointmentItemsList.get(position).getBookingStatus().equals("Ready for Pickup")){
              holder.bookingStatusView.setTextColor(Color.WHITE);
              GradientDrawable drawable = new GradientDrawable();
              drawable.setShape(GradientDrawable.RECTANGLE);
              drawable.setCornerRadius(20);
              int chosenColor = Color.rgb(101,207,114);
              drawable.setColor(chosenColor);
              holder.bookingStatusView.setBackgroundDrawable(drawable);

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
