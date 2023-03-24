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
import android.widget.EditText;
import android.widget.RadioGroup;
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
    EditText pickUpDateTime, pickUpLocation, appointmentStatusEdit;
//    RadioGroup appointmentStatusEdit;
    private static final String TAG = "RemindEmail";
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
        pickUpDateTime = findViewById(R.id.pickUpDateTime);
        pickUpLocation = findViewById(R.id.pickUpLocation);
        TextView selectedServices = (TextView) findViewById(R.id.selectedServices);
        TextView appointmentStatusEdit = (TextView) findViewById(R.id.appointmentStatusEdit);
//        appointmentStatusEdit = findViewById(R.id.appointmentStatusEdit);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnRemind = findViewById(R.id.btnEmail);

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
//            appointmentStatusEdit.check(v_appointmentStatus.equals("Ongoing") ? R.id.ongoingRadioButton : R.id.completedRadioButton);

            appointmentStatusHead.setTextColor(Color.WHITE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(20);
            int chosenColor = Color.rgb(247, 201, 16);
            drawable.setColor(chosenColor);
            appointmentStatusHead.setBackgroundDrawable(drawable);
        }

        //Remind by email
        btnRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
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
                                    InternetAddress.parse("ralphgradien01@gmail.com"));
                            message.setSubject("Car Service Remind");
                            message.setText("Dear " + v_customerName + ",\n\n" +
                            "This is a reminder email about the service you booked at our company. \n\n" +
                            "Service booked:            " + v_selectedServices + "\n" +
                            "Drop-off date and time: " + v_dropOffDateTime + "\n" +
                            "Drop-off location:          " + v_dropOffLocation + "\n" +
                            "Pick-up date and time:  " + v_pickUpDateTime + "\n" +
                            "Pick up location:            " + v_pickUpLocation + "\n\n" +
                            "If you need any further information, please contact us by phone or email.\n\n" +
                            "Thank you!"
                            );

                            Transport.send(message);
                            Log.i(TAG, "Email sent successfully");
                        } catch (MessagingException e) {
                            Log.e(TAG, "Email sending failed: " + e.getMessage());
                        }
                    }
                }).start();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Provider_Edit_Appointment.this, Provider_Update_Appointment.class);
                intent.putExtra("AppointmentID",getIntent().getIntExtra("AppointmentID",0));
                intent.putExtra("CustomerName",getIntent().getStringExtra("CustomerName"));
                intent.putExtra("CustomerAddress",getIntent().getStringExtra("CustomerAddress"));
                intent.putExtra("AppointmentStatus",getIntent().getStringExtra("AppointmentStatus"));
                intent.putExtra("DropOffDateTime",getIntent().getStringExtra("DropOffDateTime"));
                intent.putExtra("PickupDateTime",getIntent().getStringExtra("PickupDateTime"));
                intent.putExtra("DropOffLocation",getIntent().getStringExtra("DropOffLocation"));
                intent.putExtra("PickUpLocation",getIntent().getStringExtra("PickUpLocation"));
                intent.putExtra("SelectedService",getIntent().getStringExtra("SelectedService"));
                intent.putExtra("CustomerContact", getIntent().getStringExtra("CustomerContact"));
                intent.putExtra("CustomerEmail", getIntent().getStringExtra("CustomerEmail"));
                startActivity(intent);
            }
        });

        DBHelper dbh = new DBHelper(this);
        int[] appIDArr = new int[1];
        appIDArr[0] = v_appointmentID;
        String[] status = {v_appointmentStatus};
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pickUpDateTime.getText().toString().replace(" ", "").isEmpty()) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Provider_Edit_Appointment.this, R.style.MyDialogStyle);
                    builder.setTitle("Cancel the appointment")
                            .setMessage("Are you sure you want to cancel this appointment?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Get the ID of the row to update
                                    int appIdCurrent = appIDArr[0];
                                    boolean isUpdated = dbh.cancelAppointment(appIdCurrent);
                                    if (isUpdated) {
                                        Toast.makeText(Provider_Edit_Appointment.this, "Successfully Cancelled", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Provider_Edit_Appointment.this, PlainActivity.class));
                                    } else {
                                        Toast.makeText(Provider_Edit_Appointment.this, "Failure to Cancel", Toast.LENGTH_SHORT).show();
                                    }
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Toast.makeText(Provider_Edit_Appointment.this, "You cannot cancel this Appointment", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}