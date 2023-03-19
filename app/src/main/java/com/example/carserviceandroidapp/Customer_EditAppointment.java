package com.example.carserviceandroidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Customer_EditAppointment extends AppCompatActivity {

    DBHelper dbh = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_appointment);

        Intent intent = getIntent();
        int appID=0;

        String appointmentStatus="";

        TextView textViewSPName = (TextView) findViewById(R.id.textViewSPNameDisplay);
        TextView textViewSPAddress = (TextView) findViewById(R.id.textViewSPAddress);
        TextView textViewSPPhone = (TextView)findViewById(R.id.textViewSPCellDisplay);
        TextView textViewAppStatus = (TextView) findViewById(R.id.textViewStatus);
        TextView textViewdropOffDT = (TextView) findViewById(R.id.tvDropOffDT);
        TextView textViewdropOffLoc = (TextView)findViewById(R.id.textViewDropOffLoc);
        TextView textViewPickupDT = (TextView) findViewById(R.id.textViewPickupTime);
        TextView textViewPickupLoc = (TextView)findViewById(R.id.tvPickupLocation);
        TextView textViewServiceDetails = (TextView)findViewById(R.id.textViewServiceDetails);
        TextView textViewAppStatusDown = (TextView)findViewById(R.id.editTextAppointmentStatus);

        if(intent != null){
            appID = intent.getIntExtra("AppId",0);
            String serviceProviderName = intent.getStringExtra("ServiceProviderName");
            String serviceProviderAddress = intent.getStringExtra("SPAddress");
            appointmentStatus = intent.getStringExtra("AppStatus");
            String dropoffDate = intent.getStringExtra("DropoffD");
            String dropoffT = intent.getStringExtra("DropoffT");
            String pickupD = intent.getStringExtra("PickupD");
            String pickupT = intent.getStringExtra("PickupT");
            String dropoffLoc = intent.getStringExtra("DropoffLoc");
            String pickupLoc = intent.getStringExtra("PickupLoc");
            String serviceDetails = intent.getStringExtra("ServiceDet");
            String spPhone = intent.getStringExtra("SPPhone");



            textViewSPName.setText(serviceProviderName);
            textViewSPAddress.setText(serviceProviderAddress);
            textViewSPPhone.setText("Contact: "+spPhone);
            textViewAppStatus.setText(appointmentStatus);
            textViewdropOffDT.setText(dropoffDate + "  "+ dropoffT);
            textViewdropOffLoc.setText(dropoffLoc);
            textViewPickupDT.setText(pickupD+"  "+ pickupT);
            textViewPickupLoc.setText(pickupLoc);
            textViewServiceDetails.setText(serviceDetails);
            textViewAppStatusDown.setText(appointmentStatus);

            textViewAppStatus.setTextColor(Color.WHITE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(20);
            int chosenColor = Color.rgb(247, 201, 16);
            drawable.setColor(chosenColor);
            textViewAppStatus.setBackgroundDrawable(drawable);

    }

        Button btnUpdateApp = (Button) findViewById(R.id.buttonUpdate);
        btnUpdateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Customer_EditAppointment.this, Customer_EditAppointment_Form.class);
                intent.putExtra("AppId",getIntent().getIntExtra("AppId",0));
                intent.putExtra("ServiceProviderName",getIntent().getStringExtra("ServiceProviderName"));
                intent.putExtra("SPAddress",getIntent().getStringExtra("SPAddress"));
                intent.putExtra("AppStatus",getIntent().getStringExtra("AppStatus"));
                intent.putExtra("DropoffD",getIntent().getStringExtra("DropoffD"));
                intent.putExtra("DropoffT",getIntent().getStringExtra("DropoffT"));
                intent.putExtra("PickupD",getIntent().getStringExtra("PickupD"));
                intent.putExtra("PickupT",getIntent().getStringExtra("PickupT"));
                intent.putExtra("DropoffLoc",getIntent().getStringExtra("DropoffLoc"));
                intent.putExtra("PickupLoc",getIntent().getStringExtra("PickupLoc"));
                intent.putExtra("ServiceDet",getIntent().getStringExtra("ServiceDet"));
                intent.putExtra("SPPhone", getIntent().getStringExtra("SPPhone"));
                //place cell number here
                //place email address
                startActivity(intent);

            }
        });

        int[] appIDArr= new int[1];
        appIDArr[0]= appID;
        String[]status = {appointmentStatus};
        Button btnCancelApp = (Button) findViewById(R.id.buttonCancel);
        btnCancelApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textViewPickupDT.getText().toString().replace(" ","").isEmpty()){

                    AlertDialog.Builder builder = new AlertDialog.Builder(Customer_EditAppointment.this, R.style.MyDialogStyle);
                    builder.setTitle("Cancel An Appointment")
                            .setMessage("Are you sure you want to cancel this appointment?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Get the ID of the row to update
                                   int appIdCurrent = appIDArr[0];
                                   boolean isUpdated= dbh.cancelAppointment(appIdCurrent);
                                   if(isUpdated){
                                       Toast.makeText(Customer_EditAppointment.this, "Record Updated", Toast.LENGTH_SHORT).show();
                                       startActivity(new Intent(Customer_EditAppointment.this,Customer_AppointmentsView.class));
                                   }else{
                                       Toast.makeText(Customer_EditAppointment.this, "Not Updated", Toast.LENGTH_SHORT).show();
                                   }

                                    // User clicked the Yes button
                                    // Do something here, such as cancel the appointment
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User clicked the No button
                                    // Do nothing, simply close the dialog box
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else {
                    Toast.makeText(Customer_EditAppointment.this, "You cannot cancel this Appointment", Toast.LENGTH_LONG).show();
                }

            }
        });


        Button remindApp = (Button) findViewById(R.id.butnRemind);

    }

}