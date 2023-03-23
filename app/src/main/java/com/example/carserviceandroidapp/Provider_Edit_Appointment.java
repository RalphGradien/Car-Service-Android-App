package com.example.carserviceandroidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Provider_Edit_Appointment extends AppCompatActivity {

    String v_customerName, v_customerContact, v_customerEmail, v_dropOffDateTime, v_dropOffLocation, v_pickUpDateTime,
            v_pickUpLocation, v_selectedServices, v_appointmentStatus;
    int v_appointmentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_edit_appointment);
        Intent intent = getIntent();
        TextView appointmentStatusHead = (TextView) findViewById(R.id.appointmentStatusHead);
        TextView customerName = (TextView) findViewById(R.id.customerName);
        TextView customerContact = (TextView) findViewById(R.id.contact);
        TextView customerEmail = (TextView) findViewById(R.id.email);
        TextView dropOffDateTime = (TextView) findViewById(R.id.dropOffDateTime);
        TextView dropOffLocation = (TextView) findViewById(R.id.dropOffLocation);
        TextView pickUpDateTime = (TextView) findViewById(R.id.pickUpDateTime);
        TextView pickUpLocation = (TextView) findViewById(R.id.pickUpLocation);
        TextView selectedServices = (TextView) findViewById(R.id.selectedServices);
        TextView appointmentStatusEdit = (TextView) findViewById(R.id.appointmentStatusEdit);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnRemind = findViewById(R.id.btnEmail);


//        String[] spEmailArr=new String[1];
//        String[] spNameArr = new String[1];
//        String[] dropOffDateTimeArr = new String[1];
//        String[] pickUpDateTimeArr = new String[1];

        if (intent != null) {
            v_appointmentID = Appointment.AppointmentID;
            v_appointmentStatus = intent.getStringExtra("AppointmentStatus");
            v_customerName = intent.getStringExtra("CustomerName");
            v_customerContact = intent.getStringExtra("CustomerContact");
            v_customerEmail = intent.getStringExtra("CustomerEmail");
            v_dropOffDateTime = intent.getStringExtra("DropOffDateTime");
            v_dropOffLocation = intent.getStringExtra("DropOffLocation");
            v_pickUpDateTime = intent.getStringExtra("PickupDateTime");
            v_pickUpLocation = intent.getStringExtra("PickUpLocation");
            v_selectedServices = intent.getStringExtra("SelectedService");

            //add to text view
            appointmentStatusHead.setText(v_appointmentStatus);
            customerName.setText(v_customerName);
            customerContact.setText("Contact: " + v_customerContact);
            customerEmail.setText(v_customerEmail);
            dropOffDateTime.setText(v_dropOffDateTime);
            dropOffLocation.setText(v_dropOffLocation);
            pickUpDateTime.setText(v_pickUpDateTime);
            pickUpLocation.setText(v_pickUpLocation);
            selectedServices.setText(v_selectedServices);
            appointmentStatusEdit.setText(v_appointmentStatus);

            appointmentStatusHead.setTextColor(Color.WHITE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(20);
            int chosenColor = Color.rgb(247, 201, 16);
            drawable.setColor(chosenColor);
            appointmentStatusHead.setBackgroundDrawable(drawable);
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                Intent intent = new Intent(Customer_EditAppointment.this, Customer_EditAppointment_Form.class);
//                intent.putExtra("AppId",getIntent().getIntExtra("AppId",0));
//                intent.putExtra("ServiceProviderName",getIntent().getStringExtra("ServiceProviderName"));
//                intent.putExtra("SPAddress",getIntent().getStringExtra("SPAddress"));
//                intent.putExtra("AppStatus",getIntent().getStringExtra("AppStatus"));
//                intent.putExtra("DropoffD",getIntent().getStringExtra("DropoffD"));
//                intent.putExtra("DropoffT",getIntent().getStringExtra("DropoffT"));
//                intent.putExtra("PickupD",getIntent().getStringExtra("PickupD"));
//                intent.putExtra("PickupT",getIntent().getStringExtra("PickupT"));
//                intent.putExtra("DropoffLoc",getIntent().getStringExtra("DropoffLoc"));
//                intent.putExtra("PickupLoc",getIntent().getStringExtra("PickupLoc"));
//                intent.putExtra("ServiceDet",getIntent().getStringExtra("ServiceDet"));
//                intent.putExtra("SPPhone", getIntent().getStringExtra("SPPhone"));
//                intent.putExtra("SPEmail", getIntent().getStringExtra("SPEmail"));
//                //place cell number here
//                //place email address
//                startActivity(intent);

            }
        });
//
//        int[] appIDArr = new int[1];
//        appIDArr[0] = appID;
//        String[] status = {appointmentStatus};
//
//        Button btnCancelApp = (Button) findViewById(R.id.buttonCancel);
//        btnCancelApp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (textViewPickupDT.getText().toString().replace(" ", "").isEmpty()) {
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(Customer_EditAppointment.this, R.style.MyDialogStyle);
//                    builder.setTitle("Cancel An Appointment")
//                            .setMessage("Are you sure you want to cancel this appointment?")
//                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    // Get the ID of the row to update
//                                    int appIdCurrent = appIDArr[0];
//                                    boolean isUpdated = dbh.cancelAppointment(appIdCurrent);
//                                    if (isUpdated) {
//                                        Toast.makeText(Customer_EditAppointment.this, "Record Updated", Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(Customer_EditAppointment.this, PlainActivity.class));
//                                    } else {
//                                        Toast.makeText(Customer_EditAppointment.this, "Not Updated", Toast.LENGTH_SHORT).show();
//                                    }
//
//                                    // User clicked the Yes button
//                                    // Do something here, such as cancel the appointment
//                                    dialog.dismiss();
//                                }
//                            })
//                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    // User clicked the No button
//                                    // Do nothing, simply close the dialog box
//                                    dialog.dismiss();
//                                }
//                            });
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//                } else {
//                    Toast.makeText(Customer_EditAppointment.this, "You cannot cancel this Appointment", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });
//
//        spEmailArr[0] = spEmail;
//        spNameArr[0] = serviceProviderName;
//        dropOffDateTimeArr[0] = dropoffDate;
//        pickUpDateTimeArr[0] = pickupD;
//
//        Button remindApp = (Button) findViewById(R.id.butnRemind);
//        remindApp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(Customer_EditAppointment.this, "Email Reminder Sent", Toast.LENGTH_LONG).show();
//                try {
//                    String stringSenderEmail = "garkmobileapp@gmail.com";
//                    String stringReceiverEmail = spEmailArr[0];
//                    String stringPasswordSenderEmail = "fpaozvcdwjnosccy";
//
//                    String stringHost = "smtp.gmail.com";
//
//                    Properties properties = System.getProperties();
//
//                    properties.put("mail.smtp.host", stringHost);
//                    properties.put("mail.smtp.port", "465");
//                    properties.put("mail.smtp.ssl.enable", "true");
//                    properties.put("mail.smtp.auth", "true");
//
//                    javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
//                        @Override
//                        protected PasswordAuthentication getPasswordAuthentication() {
//                            return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
//                        }
//                    });
//                    MimeMessage mimeMessage = new MimeMessage(session);
//                    mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));
//
//                    mimeMessage.setSubject("Subject: Android App email");
//                    if (textViewPickupDT.getText().toString().replace(" ", "").isEmpty()) {
//                        mimeMessage.setText("Hello " + spNameArr[0] + ", \n\nThis a reminder of our drop-off appointment on " + dropOffDateTimeArr[0] +
//                                ". \n\n Cheers!\nCustomer");
//                    } else {
//                        mimeMessage.setText("Hello " + spNameArr[0] + ", \n\nThis a reminder of our pickup appointment on " + pickUpDateTimeArr[0] +
//                                ". \n\n Cheers!\nProgrammer World");
//                    }
//
//
//                    Thread thread = new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Transport.send(mimeMessage);
//                            } catch (MessagingException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                    thread.start();
//
//                } catch (AddressException e) {
//                    e.printStackTrace();
//                } catch (MessagingException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }
}