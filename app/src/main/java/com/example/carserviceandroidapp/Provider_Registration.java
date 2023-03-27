package com.example.carserviceandroidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class Provider_Registration extends AppCompatActivity {
    MaterialCardView ProviderServices;
    boolean [] selectedServices;
    ArrayList<Integer> selectedLocation = new ArrayList<>();
    ArrayList<StringBuilder> selectedServiceList = new ArrayList<>();
    DBHelper dbHelper = new DBHelper(Provider_Registration.this);
    String [] serviceProvide = {"Full Brake Check","Tire Rotation","Battery Replacement","Air Filter Replacement",
        "Wheel Alignment", "Spark Plug Replacement", "Coolant Flush", "Transmission Service", "Fuel Injection Service",
        "Wheel Replacement", "Brake Check"};
//    String [] serviceID = {"1","2","3","4","5","6","7","8","9","10","11"};

    //variables to hold the input data
    String v_providerName, v_providerPassWord, v_email,  v_contact, v_address, v_city;

    boolean emailIsValidated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_registration);

        //EditText - Button
        EditText name = findViewById(R.id.ServiceProviderName);
        EditText passWord = findViewById(R.id.ServiceProviderPassword);
        EditText email = findViewById(R.id.ServiceProviderEmail);
        EditText contact = findViewById(R.id.ServiceProviderContact);
        EditText address = findViewById(R.id.ServiceProviderAddress);
        EditText city = findViewById(R.id.ServiceProviderCity);
        Button btnProviderRegister = findViewById(R.id.btnProviderRegister);

        //initial all views
        ProviderServices = findViewById(R.id.ProviderServices);
        selectedServices = new boolean[serviceProvide.length];
        //Checkboxes for services drop_down
        ProviderServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showServiceDialog();
            }
            private void showServiceDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Provider_Registration.this);
                builder.setCancelable(false);
                builder.setMultiChoiceItems(serviceProvide, selectedServices, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                            selectedLocation.add(which);
                        }
                        else selectedLocation.remove(which);
                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < selectedLocation.size(); i++) {
                            //create String builder
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(serviceProvide[selectedLocation.get(i)]);
                            selectedServiceList.add(stringBuilder);
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < selectedServices.length; i++) {
                            selectedServices[i] = false;
                            selectedServiceList.clear();
                        }
                    }
                });
                builder.show();
            }
        });

        //On click listener to hold input values
        btnProviderRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v_providerName = name.getText().toString();
                v_providerPassWord = passWord.getText().toString();
                v_email = email.getText().toString();
                v_contact = contact.getText().toString();
                v_address = address.getText().toString();
                v_city = city.getText().toString();

                emailIsValidated = validateEmail(v_email);
                //validate input
                if (v_providerName.isEmpty() || v_providerPassWord.isEmpty() || v_email.isEmpty() ||
                    v_contact.isEmpty() || v_address.isEmpty() || v_city.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please provide all the required fields!", Toast.LENGTH_SHORT).show();
                }
                else if(emailIsValidated==true){
                    Toast.makeText(Provider_Registration.this, "Email is already existing", Toast.LENGTH_LONG).show();
                }
                else {
                    //add service provider data
                    dbHelper.insertServiceProvider(
                            v_providerPassWord, v_providerName, v_address, v_city, null, null, v_email,
                            v_contact, "d");

                    //get service provider ID from service provider email
                    Integer serviceProviderID = null;
                    Cursor cursor = dbHelper.getServiceProviderID(v_email);
                    if (cursor.moveToFirst()) {
                        serviceProviderID = cursor.getInt(cursor.getColumnIndexOrThrow("ServiceProviderID"));
                    }

                    //insert service list to SERVICE_LIST table
                    String serviceListID = "";
                    Integer serviceDetailID;

                    for (int i = 0; i < selectedLocation.size(); i++) {
                        serviceDetailID = selectedLocation.get(i) + 1;
                        serviceListID = "SP_" + serviceProviderID + "_" + serviceDetailID ;
                        dbHelper.insertServiceList(
                                serviceListID, serviceProviderID, serviceDetailID
                        );
                    };
                    Toast.makeText(Provider_Registration.this, "Successful registration!", Toast.LENGTH_SHORT).show();
                };

                //remove all items in arrayList everytime
                selectedServiceList.clear();
            }
        });

        //Already have an account  - Login activity
        TextView logIn = findViewById(R.id.logInHere);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Provider_Registration.this, LogIn.class);
                startActivity(intent);
            }
        });
    }


    //Validate register email
    public boolean validateEmail(String email){

        boolean result=false;
        Cursor cursor = dbHelper.getServiceProviderInfo();
        try{
            if(cursor.getCount() > 0){
                cursor.moveToPosition(-1);
                while(cursor.moveToNext()){
                    if(cursor.getString(cursor.getColumnIndexOrThrow("email")).trim().equals(email)){
                        result = true;
                    }
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
