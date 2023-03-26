package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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

        String[] appTypeArr = new String[1];
        String appType = "";


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
            appType = intent.getStringExtra("AppType");

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
        appTypeArr[0] = appType;

        Button btnCancelApp = (Button) findViewById(R.id.buttonCancel);
        btnCancelApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showDialogCancelApp();
            }

            private void showDialogCancelApp(){
                if(textViewPickupDT.getText().toString().replace(" ","").isEmpty()){

                    final android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(Customer_EditAppointment.this);
                    View mView = getLayoutInflater().inflate(R.layout.customer_cancelappointment, null);
                    alert.setView(mView);

                    final AlertDialog alertDialog = alert.create();
                    alertDialog.setCancelable(false);

                    mView.findViewById(R.id.noButton).setOnClickListener(v -> {
                        alertDialog.dismiss();
                    });

                    mView.findViewById(R.id.yesButton).setOnClickListener(v -> {
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

                               Toast.makeText(Customer_EditAppointment.this, "Appointment Cancelled", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(Customer_EditAppointment.this,PlainActivity.class));
                           }else{
                               Toast.makeText(Customer_EditAppointment.this, "Unsuccessful Cancellation", Toast.LENGTH_SHORT).show();
                           }
                        alertDialog.dismiss();

                    });
                    alertDialog.show();

                }
                else{
                    Toast.makeText(Customer_EditAppointment.this, "You can only update this appointment but not cancel.", Toast.LENGTH_SHORT).show();
                }

            }

        });

        Button remindApp = (Button) findViewById(R.id.butnRemind);
        remindApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(Customer_EditAppointment.this);
                View mView = getLayoutInflater().inflate(R.layout.customer_appointment_reminder_dialogue, null);
                alert.setView(mView);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCancelable(false);

                mView.findViewById(R.id.okBTNRemind).setOnClickListener(view -> {

                    alertDialog.dismiss();
                });

                alertDialog.show();

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
                            message.setSubject("Appointment Reminder");

                            if(textViewPickupDT.getText().toString().replace(" ","").isEmpty()){
                                message.setText("Hello "+spNameArr[0]+", \n\nThis a reminder of the "+appTypeArr[0] +" appointment set on "+dropOffDateTimeArr[0]+
                                " under Appointment ID "+appIDArr[0]+". "+"Check your GARK account for details. \n\n Cheers!\nGARK");
                            }else{
                                message.setText("Hello "+spNameArr[0]+", \n\nThis a reminder of our after-servicing pickup appointment on "+pickUpDateTimeArr[0]+
                                " under Appointment ID "+appIDArr[0]+". "+"Check your GARK account for details. \n\n Cheers!\nGARK");
                                 }

                            Transport.send(message);
                            Log.i(TAG, "Email sent successfully");
                        } catch (MessagingException e) {
                            Log.e(TAG, "Email sending failed: " + e.getMessage());
                        }
                    }
                }).start();

            }
        });

    }

}