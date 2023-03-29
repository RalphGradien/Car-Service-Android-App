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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
            v_pickUpLocation, v_selectedServices, v_appointmentStatus, v_serviceTypeBook, v_serviceTypeDone;
    int v_appointmentID = 0;
    EditText pickUpDateTime, pickUpLocation;
    private static final String TAG = "RemindEmail";
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_edit_appointment);
        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        TextView appointmentStatusHead = (TextView) findViewById(R.id.appointmentStatusHead);
        TextView customerName = (TextView) findViewById(R.id.customerName);
        TextView customerContact = (TextView) findViewById(R.id.contact);
        TextView customerEmail = (TextView) findViewById(R.id.email);
        TextView dropOffDateTime = (TextView) findViewById(R.id.dropOffDateTime);
        TextView dropOffLocation = (TextView) findViewById(R.id.dropOffLocation);
        TextView serviceTypeBook = (TextView) findViewById(R.id.serviceTypeBook);
        TextView serviceTypeDone = (TextView) findViewById(R.id.serviceTypeDone);
        pickUpDateTime = findViewById(R.id.pickUpDateTime);
        pickUpLocation = findViewById(R.id.pickUpLocation);
        TextView selectedServices = (TextView) findViewById(R.id.selectedServices);
        TextView appointmentStatusEdit = findViewById(R.id.appointmentStatusEdit);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnRemind = findViewById(R.id.btnEmail);

        if (intent != null) {
            v_appointmentID = intent.getIntExtra("AppointmentID", 0);
            v_appointmentStatus = intent.getStringExtra("AppointmentStatus");
            v_customerName = intent.getStringExtra("CustomerName");
            v_customerContact = intent.getStringExtra("CustomerContact");
            v_customerEmail = intent.getStringExtra("CustomerEmail");
            v_dropOffDateTime = intent.getStringExtra("DropOffDateTime");
            v_dropOffLocation = intent.getStringExtra("DropOffLocation");
            v_pickUpDateTime = intent.getStringExtra("PickupDateTime");
            v_pickUpLocation = intent.getStringExtra("PickUpLocation");
            v_selectedServices = intent.getStringExtra("SelectedService");
            v_serviceTypeBook = intent.getStringExtra("AppointmentType");
            v_serviceTypeDone = intent.getStringExtra("AppointmentType");


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
            serviceTypeBook.setText(v_serviceTypeBook);
            serviceTypeDone.setText(v_serviceTypeDone);


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
                                    "Service booked              : " + v_selectedServices + "\n" +
                                    "Service booked schedule: " + v_dropOffDateTime + "\n" +
                                    "Service booked location  : " + v_dropOffLocation + "\n" +
                                    "Service type booked       : " + v_serviceTypeBook + "\n" +
                                    "Service done schedule    : " + v_pickUpDateTime + "\n" +
                                    "Drop-off/Pick-up location: " + v_pickUpLocation + "\n" +
                                    "After serviced                : " + v_serviceTypeDone + "\n\n" +
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
                Toast.makeText(Provider_Edit_Appointment.this, "Email sent", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        //set radio button for Appointment Status change
        appointmentStatusEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the RadioGroup
                RadioGroup radioGroup = new RadioGroup(Provider_Edit_Appointment.this);
                radioGroup.setOrientation(RadioGroup.VERTICAL);
                // Create the RadioButtons and add them to the RadioGroup
                RadioButton rdOngoing = new RadioButton(Provider_Edit_Appointment.this);
                rdOngoing.setText("Ongoing");
                radioGroup.addView(rdOngoing);

                RadioButton rdCompleted = new RadioButton(Provider_Edit_Appointment.this);
                rdCompleted.setText("Completed");
                radioGroup.addView(rdCompleted);

                // Show the RadioGroup in an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Provider_Edit_Appointment.this);
                builder.setView(radioGroup);
                builder.setTitle("Select Appointment Status");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Get the selected RadioButton
                        int selectedStatus = radioGroup.getCheckedRadioButtonId();
                        RadioButton selectedRadioButton = (RadioButton) radioGroup.findViewById(selectedStatus);
                        String selectedOption = selectedRadioButton.getText().toString();
                        appointmentStatusHead.setText(selectedOption);
                        appointmentStatusEdit.setText(selectedOption);

                        //set color for the status
                        GradientDrawable drawable = new GradientDrawable();
                        if (selectedOption.equals("Ongoing")) {
                            drawable.setColor(Color.rgb(247, 201, 16));
                        } else if (selectedOption.equals("Completed")) {
                            drawable.setColor(Color.GREEN);
                        }

                    }
                });
                builder.setNegativeButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkupdatedata = dbHelper.updateServiceProviderAppointment(v_appointmentID, pickUpDateTime.getText().toString(), pickUpLocation.getText().toString(),
                        appointmentStatusEdit.getText().toString());
                if (checkupdatedata == true) {
                    Toast.makeText(Provider_Edit_Appointment.this, "Updated Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Provider_Edit_Appointment.this, "Update failed", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });


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
                                    boolean isUpdated = dbHelper.cancelAppointment(appIdCurrent);
                                    if (isUpdated) {
                                        Toast.makeText(Provider_Edit_Appointment.this, "Successfully Cancelled", Toast.LENGTH_SHORT).show();
                                        finish();
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
                    Toast.makeText(Provider_Edit_Appointment.this, "Service in progress, you cannot cancel the appointment", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

