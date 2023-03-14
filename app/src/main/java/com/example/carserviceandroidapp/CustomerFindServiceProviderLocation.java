package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerFindServiceProviderLocation extends AppCompatActivity {
    ArrayList<String> location = new ArrayList<>(); String spinLoc;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_find_service_provider_location);


        DB = new DBHelper(this);
        displaydata();

        Spinner Spinner = findViewById(R.id.spinLocation);
        Button btnNextLocation = findViewById(R.id.btnNextLocation);

        btnNextLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerFindServiceProviderLocation.this,CustomerFindServiceProviderList.class);
                intent.putExtra("LOC",spinLoc);
                startActivity(intent);
                //  startActivity(new Intent(CustomerFindServiceProviderLocation.this,CustomerFindServiceProviderList.class));

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
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

    }

    private void displaydata()
    {
        Cursor cursor = DB.getServiceProviderData();

        if(cursor.getCount()==0)
        {
            Toast.makeText(CustomerFindServiceProviderLocation.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
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