package com.example.carserviceandroidapp;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class CustomerScheduleDropOff extends AppCompatActivity {
    DBHelper DB; int spID,userID;String spName,sdID,userName,custEmail, fullLoc, custLoc; int aptID;
    ArrayList<String>spDetails = new ArrayList<>();
    ArrayList<String>location = new ArrayList<>();
    String pickupDateTime="",pickupLocation="",pickupReadyDate="", DropoffTimeDate="", DropoffLocation="",
            BookingDate="",CancelledDate="", appointmentType="", AppointmentStatus="", ServiceList, ServiceDetail;

    private static final String TAG = "RemindEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_schedule_drop_off);
        DB = new DBHelper(this);
        TextView txtServiceProviderName = findViewById(R.id.txtCSDO_ServiceProviderName);
        TextView txtServiceProviderLocation = findViewById(R.id.txtCSDO_ServiceProviderLocation);

        Spinner SpinServiceDetail = findViewById(R.id.spinCSDO_ServiceDetail);
       // Spinner SpinServiceLocation = findViewById(R.id.spinCSDO_Location);
        Spinner SpinDate = findViewById(R.id.spinCSDO_Date);
        Spinner SpinHours = findViewById(R.id.spinCSDO_Time);

        Button btnCSDOCancel = findViewById(R.id.btnCSDOCancel);
        Button btnCSDOConfirm = findViewById(R.id.btnCSDOConfirm);
        btnCSDOConfirm.setBackgroundColor(Color.GREEN);
        btnCSDOCancel.setBackgroundColor(Color.RED);


        RadioGroup radioGroup = findViewById(R.id.radio_group);
        EditText otherText = findViewById(R.id.other_text);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_button3) {
                    // Show the EditText when Radio Button 3 is selected
                    otherText.setVisibility(View.VISIBLE);
                } else {
                    // Hide the EditText for all other RadioButtons
                    otherText.setVisibility(View.GONE);
                }
            }
        });



        RadioButton radioButton1 = findViewById(R.id.radio_button1);
// Set an OnClickListener on Radio Button 1
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Select Radio Button 1 when its text is clicked
                radioButton1.setChecked(true);
                DropoffLocation = fullLoc;
                appointmentType = "Drop Off";
            }
        });

        RadioButton radioButton2 = findViewById(R.id.radio_button2);
// Set an OnClickListener on Radio Button 2
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Select Radio Button 2 when its text is clicked
                radioButton2.setChecked(true);
                DropoffLocation = custLoc;
                appointmentType = "Pick Up";
            }
        });

        RadioButton radioButton3 = findViewById(R.id.radio_button3);
// Set an OnClickListener on Radio Button 3
        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Select Radio Button 3 when its text is clicked
                radioButton3.setChecked(true);
                appointmentType = "Pick Up";

            }
        });

        otherText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton3.setChecked(true);
            }
        });

        Intent intent = getIntent();
        if(intent!=null)
        {
            spID = Integer.parseInt(intent.getStringExtra("SPID").toString());
        }

        userID = Customer.CustomerID;
        displayData();
        displaydata2();
        displayLocation();
        displayLocation2();
        txtServiceProviderName.setText(spName);
        txtServiceProviderLocation.setText(fullLoc);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spDetails);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinServiceDetail.setAdapter(adapter);


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, location);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // SpinServiceLocation.setAdapter(adapter2);


        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy EEEE", Locale.getDefault());
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        String CurrDate = dateFormat.format(currentDate);
        String[] days = new String[7];

        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DATE, 1);
            Date nextDate = calendar.getTime();
            days[i] = dateFormat.format(nextDate);
        }

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        SpinDate.setAdapter(adapter3);

        // Get the selected day from the day spinner
        String selectedDay = SpinDate.getSelectedItem().toString();

        // Create an array to hold the working hours based on the selected day
        String[] hours;
        if (selectedDay.equals("Sunday")) {
            hours = new String[]{"11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "01:00 PM", "01:30 PM", "02:00 PM", "02:30 PM", "03:00 PM"};
        } else {
            hours = new String[]{"10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "01:00 PM", "01:30 PM", "02:00 PM", "02:30 PM", "03:00 PM", "03:30 PM", "04:00 PM"};
        }
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hours);
        SpinHours.setAdapter(adapter4);
        String selectedHour = SpinHours.getSelectedItem().toString();

        btnCSDOCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerScheduleDropOff.this, CustomerMainMenu.class);
                startActivity(intent);


            }
        });


        btnCSDOConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //method of confirmation of booking an appointment

                if(!radioButton1.isChecked()&& !radioButton2.isChecked()&& !radioButton3.isChecked())
                {
                    Toast.makeText(CustomerScheduleDropOff.this,"Please fill in the location",Toast.LENGTH_SHORT).show();
                }
                else if(radioButton3.isChecked() && TextUtils.isEmpty(otherText.getText().toString()))
                {
                 Toast.makeText(CustomerScheduleDropOff.this,"Please fill in the location",Toast.LENGTH_SHORT).show();

                }
                else {
                 showDialog();
                }
            }


            private void showDialog()
            {
                final AlertDialog.Builder alert = new AlertDialog.Builder(CustomerScheduleDropOff.this);
                View mView = getLayoutInflater().inflate(R.layout.confirm_customer_appointment_dialog, null);
                alert.setView(mView);
                final AlertDialog alertDialog = alert.create();
                alertDialog.setCancelable(false);
                ServiceDetail = SpinServiceDetail.getSelectedItem().toString();
                displayData3();
                DropoffTimeDate = SpinDate.getSelectedItem().toString() + " " + SpinHours.getSelectedItem().toString() ;
                ServiceList = "SP_" + spID + "_" + sdID ;
                 if(radioButton3.isChecked()) DropoffLocation = otherText.getText().toString();

//                if(SpinServiceLocation.getSelectedItem().toString().equals("Drop Off")) { DropoffLocation = fullLoc;}
//                else {DropoffLocation = custLoc;}
//                appointmentType = SpinServiceLocation.getSelectedItem().toString();
                BookingDate = CurrDate;AppointmentStatus = "Ongoing";
                DB.insertAppointment(userID,spID,pickupDateTime,pickupLocation,pickupReadyDate,DropoffTimeDate
                        ,DropoffLocation,BookingDate,CancelledDate,appointmentType,AppointmentStatus);
                getAptID();
                DB.insertAppointmentDetail(aptID,ServiceList);
                sendEmail();

                mView.findViewById(R.id.okBTN3).setOnClickListener(v -> {

                    Intent intent = new Intent(CustomerScheduleDropOff.this, PlainActivity.class);
                    startActivity(intent);
                    alertDialog.dismiss();
                });
                alertDialog.show();
            }

            //method for sending email booking confirmation
            private  void sendEmail()
            {
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
                            displaydata4();
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(username));
                            message.setRecipients(Message.RecipientType.TO,
                                    InternetAddress.parse(custEmail));
                            message.setSubject("Subject: Booking Appointment Confirmation");
                            message.setText("Hello, " + userName + "\n\nThis is a confirmation email regarding the appointment you booked at our Service Provider. Here are the details" +
                                    "\n\nService Booked   :   " + ServiceDetail +
                                    "\nBooking Time  :   " + BookingDate +
                                    "\nAppointment type  :   " + appointmentType +
                                    "\nDate and Time  :  " + DropoffTimeDate  +
                                    "\nLocation  :   " + DropoffLocation +
                                    "\n\nIf you need any further information, please contact us by phone, we will be gladly at your service." +
                                    "\n\nThank you!" +
                                    "\n\n"+ spName

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




    }

    //method to get the latest appointment ID on the appointment table
    private void getAptID()
    {
        Cursor cursor = DB.getAppointmentID();

        if(cursor.getCount()==0)
        {
            Toast.makeText(CustomerScheduleDropOff.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                aptID = Integer.parseInt(cursor.getString(0));
            }
        }


    }

    //method to get the list of service providers
    private void displayData()
    {
        Cursor cursor = DB.getServiceProviderList();

        if(cursor.getCount()==0)
        {
            Toast.makeText(CustomerScheduleDropOff.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                if(Integer.parseInt(cursor.getString(0).toString()) == spID)
                {
                    spName = cursor.getString(2);
                }
            }
        }

    }

    //method to get the service details from the particular service provider
    private void displaydata2()
    {
        Cursor cursor = DB.getServiceDetails();

        if(cursor.getCount()==0)
        {
            Toast.makeText(CustomerScheduleDropOff.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                if(Integer.parseInt(cursor.getString(2).toString()) == spID)
                {
                    spDetails.add(cursor.getString(0));
                }

            }
        }

    }

    //method to get the customer location
    private void displayLocation()
    {
        Cursor cursor = DB.getDropOffLocationCust();
        if(cursor.getCount()==0)
        {
            Toast.makeText(CustomerScheduleDropOff.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                if(Integer.parseInt(cursor.getString(0).toString()) == userID)
                {
                    location.add(cursor.getString(1));
                    custLoc = cursor.getString(1);
                }

            }
        }

    }

    //method to get the service provider location
    private void displayLocation2()
    {
        Cursor cursor = DB.getDropOffLocationSP();
        if(cursor.getCount()==0)
        {
            Toast.makeText(CustomerScheduleDropOff.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                if(Integer.parseInt(cursor.getString(0).toString()) == spID)
                {
                    fullLoc = cursor.getString(1) + ", " + cursor.getString(2) +", "
                            +cursor.getString(3) +", " + cursor.getString(4) ;
                    location.add(fullLoc);
                }

            }
        }

    }

//method to get the service ID from the service details
    public void displayData3()
    {
        Cursor cursor = DB.getServiceDetails();

        if(cursor.getCount()==0)
        {
            Toast.makeText(CustomerScheduleDropOff.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                if(ServiceDetail.equals(cursor.getString(0)))
                {
                    sdID = cursor.getString(3);
//                    spName = cursor.getString(2);
                }
            }
        }

    }

    //method to get the customer name
    private void displaydata4()
    {
        Cursor cursor = DB.getCustomerData();

        if(cursor.getCount()==0)
        {
            Toast.makeText(CustomerScheduleDropOff.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                if(Integer.parseInt( cursor.getString(0))==userID)
                {
                    userName = cursor.getString(1);
                    custEmail = cursor.getString(3);
                }
            }
        }

    }


}