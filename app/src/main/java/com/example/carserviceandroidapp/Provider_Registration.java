package com.example.carserviceandroidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class Provider_Registration extends AppCompatActivity {
    MaterialCardView ProviderServices;
    boolean [] selectedServices;
    ArrayList<Integer> selectedLocation = new ArrayList<>();
    ArrayList<StringBuilder> selectedServiceList = new ArrayList<>();
    String [] serviceProvide = {"Select 1","Select 2","Select 3","Select 4"};

    //variables to hold the input data
    String v_userName, v_providerPassWord, v_confirmPassWord, v_email, v_providerName, v_address;
    String v_city, v_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_registration);

        //EditText - Button
        EditText name = findViewById(R.id.ProviderName);
        EditText passWord = findViewById(R.id.ProviderPassWord);
        EditText confirmPassWord = findViewById(R.id.ProviderConfirmPassword);
        EditText email = findViewById(R.id.ProviderEmail);
        EditText providerName = findViewById(R.id.ProviderName);
        EditText address = findViewById(R.id.ProviderAddress);
        EditText contact = findViewById(R.id.ProviderContact);
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
            String str = "";
            String str2 = "";
            //            TextView outPut = findViewById(R.id.outPut);
            @Override
            public void onClick(View v) {
                v_userName = name.getText().toString();
                v_providerPassWord = passWord.getText().toString();
                v_confirmPassWord = confirmPassWord.getText().toString();
                v_email = email.getText().toString();
                v_address = address.getText().toString();
                v_contact = contact.getText().toString();

                //check array - delete later
                for (int i = 0; i < selectedServiceList.size(); i++) {
                    str2 += selectedServiceList.get(i) + " ";
                }
                //remove all items in arrayList everutimes
                selectedServiceList.clear();
                str = v_userName + " " + v_providerPassWord + " " + v_confirmPassWord
                        + " " + v_email + " " + v_userName + " " + v_address
                        + " " + v_city + " " + v_contact + str2;
//                outPut.setText(str);

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
}

//add some thing

