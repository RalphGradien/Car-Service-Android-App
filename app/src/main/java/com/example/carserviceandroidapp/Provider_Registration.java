package com.example.carserviceandroidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
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
    String [] serviceProvide = {"Select 1","Select 2","Select 3","Select 4"};

    //variables to hold the input data
    String v_providerName, v_providerPassWord, v_email,  v_contact, v_address, v_city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_registration);

        //EditText - Button
        EditText name = findViewById(R.id.ProviderName);
        EditText passWord = findViewById(R.id.ProviderPassWord);
        EditText email = findViewById(R.id.ProviderEmail);
        EditText contact = findViewById(R.id.ProviderContact);
        EditText address = findViewById(R.id.ProviderAddress);
        EditText city = findViewById(R.id.ProviderCity);
        Button btnProviderRegister = findViewById(R.id.bthCustomerRegister);

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

                //validate input
                if (v_providerName.isEmpty() || v_providerPassWord.isEmpty() || v_email.isEmpty() ||
                    v_contact.isEmpty() || v_address.isEmpty() || v_city.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please provide all the required fields!", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper dbHelper = new DBHelper(Provider_Registration.this);
                    Toast.makeText(Provider_Registration.this, "Successful registration!", Toast.LENGTH_SHORT).show();

                    //add service provider data
                    dbHelper.insertServiceProvider(
                            v_providerPassWord, v_providerName, v_address, v_city, null, null, v_email,
                            v_contact, null);

                    //insert service list
                    dbHelper.getServiceList();
                };




//                    public Boolean insertServiceProvider(String password, String fullName, String street, String city,
//                            String province, String postalCode, String email, String phone, String imageName )
//                    {
//                        SQLiteDatabase db = this.getWritableDatabase();
//                        ContentValues values = new ContentValues();
//                        values.put("serviceProviderPassword", password);
//                        values.put("serviceProviderFullName", fullName);
//                        values.put("street", street);
//                        values.put("city",city);
//                        values.put("province",province);
//                        values.put("postalCode",postalCode);
//                        values.put("email", email);
//                        values.put("phone", phone);
//                        values.put("imageName", imageName);
//                };
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
}
