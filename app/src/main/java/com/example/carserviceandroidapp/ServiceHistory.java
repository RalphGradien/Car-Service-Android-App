package com.example.carserviceandroidapp;

import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ServiceHistory extends Fragment {

    @Nullable
    //@Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_service_history, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.historyRecyclerView);
        List<ServiceHistoryItems> items = new ArrayList<ServiceHistoryItems>();
        items.add(new ServiceHistoryItems("John Wick", "778123456","johnwick@gmail.com","Fluid Replacement", "Completed", "2013-12-15", "2013-12-12", "2013-12-10"));
        items.add(new ServiceHistoryItems("Naruto Uzumaki", "123456789","naruto@gmail.com","Brake Check", "Completed", "2013-12-15", "2013-12-12", "2013-12-10"));
        items.add(new ServiceHistoryItems("Justin Bieber", "778069420","bieberfever@gmail.com","Oil Change", "", "", "", "2013-12-10"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ServiceHistoryAdapter(getActivity(), items));
        return view;

    }
}
