package com.example.carserviceandroidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
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

public class Customer_EditAppointment extends AppCompatActivity {
    private static final String TAG = "RemindEmail";
    DBHelper dbh = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_appointment);

        Intent intent = getIntent();
        int appID=0;

        String appointmentStatus="";
        String[] spEmailArr=new String[1];
        String spEmail = "";

        String[] spNameArr = new String[1];
        String serviceProviderName="";

        String[] dropOffDateTimeArr = new String[1];
        String dropoffDate="";

        String[] pickUpDateTimeArr = new String[1];
        String pickupD = "";


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
        TextView textViewAppontType = (TextView) findViewById(R.id.tvAppointType);

        if(intent != null){
            appID = intent.getIntExtra("AppId",0);
            serviceProviderName = intent.getStringExtra("ServiceProviderName");
            String serviceProviderAddress = intent.getStringExtra("SPAddress");
            appointmentStatus = intent.getStringExtra("AppStatus");
            dropoffDate = intent.getStringExtra("DropoffD");
            String dropoffT = intent.getStringExtra("DropoffT");
            pickupD = intent.getStringExtra("PickupD");
            String pickupT = intent.getStringExtra("PickupT");
            String dropoffLoc = intent.getStringExtra("DropoffLoc");
            String pickupLoc = intent.getStringExtra("PickupLoc");
            String serviceDetails = intent.getStringExtra("ServiceDet");
            String spPhone = intent.getStringExtra("SPPhone");
            spEmail = intent.getStringExtra("SPEmail");
            String appType = intent.getStringExtra("AppType");

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
            textViewAppontType.setText(appType);

            textViewAppStatus.setTextColor(Color.WHITE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(20);
            int chosenColor = Color.rgb(247, 201, 16);
            drawable.setColor(chosenColor);
            textViewAppStatus.setBackgroundDrawable(drawable);

    }

        Button btnUpdateApp = (Button) findViewById(R.id.buttonUpdateFirst);
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
                intent.putExtra("SPEmail", getIntent().getStringExtra("SPEmail"));
                intent.putExtra("AppType",getIntent().getStringExtra("AppType"));
                //place cell number here
                //place email address
                startActivity(intent);

            }
        });

        int[] appIDArr= new int[1];
        appIDArr[0]= appID;
        String[]status = {appointmentStatus};
        spEmailArr[0] = spEmail;
        spNameArr[0]= serviceProviderName;
        dropOffDateTimeArr[0]= dropoffDate;
        pickUpDateTimeArr[0]= pickupD;

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
                                       new Thread(new Runnable() {
                                           @Override
                                           public void run() {
                                               String stringReceiverEmail = spEmailArr[0];
                                               String host = "smtp.mail.yahoo.com";
                                               String port = "587";
                                               String username = "thienphuocufo@yahoo.com.vn";
                                               String password = "wnvqewwhprkhwrqd";

                                               Properties props = new Properties();
                                               props.put("mail.smtp.host", host);
                                               props.put("mail.smtp.port", port);
                                               props.put("mail.smtp.auth", "true");
                                               props.put("mail.smtp.starttls.enable", "true");

                                               Session session = Session.getInstance(props,
                                                       new Authenticator() {
                                                           protected PasswordAuthentication getPasswordAuthentication() {
                                                               return new PasswordAuthentication(username, password);
                                                           }
                                                       });
                                               try {
                                                   Message message = new MimeMessage(session);
                                                   message.setFrom(new InternetAddress(username));
                                                   message.setRecipients(Message.RecipientType.TO,
                                                           InternetAddress.parse(stringReceiverEmail));
                                                   message.setSubject("APPOINTMENT CANCELLATION MESSAGE");


                                                   message.setText("Hello "+spNameArr[0]+", \n\nThis is to inform that Appointment "+appIDArr[0]+" set on "+dropOffDateTimeArr[0]+
                                                               " has been cancelled by the Customer.  Check you GARK account for details. \n\n Cheers!\nGARK");

                                                   Transport.send(message);
                                                   Log.i(TAG, "Email sent successfully");
                                               } catch (MessagingException e) {
                                                   Log.e(TAG, "Email sending failed: " + e.getMessage());
                                               }
                                           }
                                       }).start();


                                       Toast.makeText(Customer_EditAppointment.this, "Record Updated", Toast.LENGTH_SHORT).show();
                                       startActivity(new Intent(Customer_EditAppointment.this,PlainActivity.class));
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
        remindApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String stringReceiverEmail = spEmailArr[0];
                        String host = "smtp.mail.yahoo.com";
                        String port = "587";
                        String username = "thienphuocufo@yahoo.com.vn";
                        String password = "wnvqewwhprkhwrqd";

                        Properties props = new Properties();
                        props.put("mail.smtp.host", host);
                        props.put("mail.smtp.port", port);
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.starttls.enable", "true");

                        Session session = Session.getInstance(props,
                                new javax.mail.Authenticator() {
                                    protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(username, password);
                                    }
                                });
                        try {
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(username));
                            message.setRecipients(Message.RecipientType.TO,
                                    InternetAddress.parse(stringReceiverEmail));
                            message.setSubject("Appointment Reminder");

                            if(textViewPickupDT.getText().toString().replace(" ","").isEmpty()){
                                message.setText("Hello "+spNameArr[0]+", \n\nThis a reminder of our drop-off appointment on "+dropOffDateTimeArr[0]+
                                ". \n\n Cheers!\nCustomer");
                            }else{
                                message.setText("Hello "+spNameArr[0]+", \n\nThis a reminder of our pickup appointment on "+pickUpDateTimeArr[0]+
                                ". \n\n Cheers!\nGARK");
                                 }

                            Transport.send(message);
                            Log.i(TAG, "Email sent successfully");
                        } catch (MessagingException e) {
                            Log.e(TAG, "Email sending failed: " + e.getMessage());
                        }
                    }
                }).start();



                //                Toast.makeText(Customer_EditAppointment.this, "Email Reminder Sent", Toast.LENGTH_LONG).show();
//                try {
//                    String stringSenderEmail = "thienphuocufo@yahoo.com.vn";
//                    String stringReceiverEmail = spEmailArr[0];
//                    String stringPasswordSenderEmail = "wnvqewwhprkhwrqd";
//
//                    String stringHost = "smtp.mail.yahoo.com";
//
//                   // Properties properties = System.getProperties();
//                    Properties properties = new Properties();
//
//                    properties.put("mail.smtp.host", stringHost);
//                    properties.put("mail.smtp.port", "587");
//                    properties.put("mail.smtp.starttls.enable", "true");
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
//                    if(textViewPickupDT.getText().toString().replace(" ","").isEmpty()){
//                        mimeMessage.setText("Hello "+spNameArr[0]+", \n\nThis a reminder of our drop-off appointment on "+dropOffDateTimeArr[0]+
//                                ". \n\n Cheers!\nCustomer");
//                    }else{
//                        mimeMessage.setText("Hello "+spNameArr[0]+", \n\nThis a reminder of our pickup appointment on "+pickUpDateTimeArr[0]+
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


            }
        });

    }

}