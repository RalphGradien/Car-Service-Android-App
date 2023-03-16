package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Customer_ServiceHistoryView extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_customer_service_history_view, container, false);

        //setContentView(R.layout.activity_customer_service_history_view);

        RecyclerView recyclerView = view.findViewById(R.id.customer_history_recyclerview);

        List<Customer_ServiceHistory_Items> customer_serviceHistory_itemsList = new ArrayList<Customer_ServiceHistory_Items>();
        customer_serviceHistory_itemsList.add(new Customer_ServiceHistory_Items(23456,"ServiceProvider 1",
                "Brake Check","Burnaby","Completed","2023-01-11",
                "11:30AM","2023-01-14","01:20PM"));

        customer_serviceHistory_itemsList.add(new Customer_ServiceHistory_Items(23490,"ServiceProvider 30",
                "Front Wheel Replacement","New Westminster","Completed","2023-02-20",
                "12:30PM","2023-02-22","01:40PM"));

        customer_serviceHistory_itemsList.add(new Customer_ServiceHistory_Items(23500,"ServiceProvider 40",
                "Steering Wheel Check","Metrotown Area","Ongoing","2023-03-11",
                "4:30PM","",""));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new Customer_ServiceHistory_Adapter(requireContext(),customer_serviceHistory_itemsList));
        return view;
    }

}