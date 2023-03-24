package com.example.carserviceandroidapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerMainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main_menu);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ServiceAccount()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                   Fragment selectedFragment=null;

                   switch(item.getItemId()) {
                       //CHANGE TO THE APPROPRIATE VIEW:
                       //REPLACE ServiceAccount();
                       //Customer Account
                       case R.id.account:
                           selectedFragment = new ServiceAccount();
                           break;
                       //Customer Search
                       case R.id.search:
                           selectedFragment = new CustomerFindServiceProviderLocation();
                           break;
                           //Customer Appointments
                       case R.id.appointment:
                           selectedFragment = new Customer_AppointmentsView();
                           break;
                           //Customer History
                       case R.id.history:
                           selectedFragment = new Customer_ServiceHistoryView();
                           break;
                   }

                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                   return true;
                }
            };
    
}