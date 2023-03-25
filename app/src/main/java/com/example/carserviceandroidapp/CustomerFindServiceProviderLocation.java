package com.example.carserviceandroidapp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerFindServiceProviderLocation extends Fragment {
    ArrayList<String> location = new ArrayList<>(); String spinLoc;
    DBHelper DB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_customer_find_service_provider_location, container, false);
        Context context = getContext().getApplicationContext();


        DB = new DBHelper(getActivity());
        displaydata();

        //spinner to choose the location
        Spinner Spinner = view.findViewById(R.id.spinLocation);
        Button btnNextLocation = view.findViewById(R.id.btnNextLocation);

        btnNextLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CustomerFindServiceProviderList.class);
                intent.putExtra("LOC",spinLoc);
                startActivity(intent);

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, location);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner.setAdapter(adapter);

        Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLocation = parent.getItemAtPosition(position).toString();
                spinLoc = selectedLocation;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinLoc = location.get(0);
            }
        });

        return view;
    }


    //method to display the list location of the providers
    private void displaydata()
    {
        Cursor cursor = DB.getServiceProviderData();

        if(cursor.getCount()==0)
        {
            Toast.makeText(getActivity(),"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {

                location.add(cursor.getString(0));


            }
        }

    }
}