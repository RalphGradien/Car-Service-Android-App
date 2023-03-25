package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PlainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plain);

       Customer_AppointmentsView customer_appointmentsView = new Customer_AppointmentsView();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.plainLayout,customer_appointmentsView);
        transaction.commit();

        Button homeButton = (Button) findViewById(R.id.buttonHomePlain);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlainActivity.this,CustomerMainMenu.class));
            }
        });

    }
}