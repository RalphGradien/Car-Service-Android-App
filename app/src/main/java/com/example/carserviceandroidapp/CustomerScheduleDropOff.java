package com.example.carserviceandroidapp;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    DBHelper DB; int spID,userID;String spName,sdID,userName, fullLoc; int aptID;
    ArrayList<String>spDetails = new ArrayList<>();
    ArrayList<String>location = new ArrayList<>();
    String pickupDateTime="",pickupLocation="",pickupReadyDate="", DropoffTimeDate="", DropoffLocation="",
            BookingDate="",CancelledDate="", appointmentType="", AppointmentStatus="", ServiceList, ServiceDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_schedule_drop_off);
        DB = new DBHelper(this);
        TextView txtServiceProviderName = findViewById(R.id.txtCSDO_ServiceProviderName);
        TextView txtServiceProviderLocation = findViewById(R.id.txtCSDO_ServiceProviderLocation);

        Spinner SpinServiceDetail = findViewById(R.id.spinCSDO_ServiceDetail);
        Spinner SpinServiceLocation = findViewById(R.id.spinCSDO_Location);
        Spinner SpinDate = findViewById(R.id.spinCSDO_Date);
        Spinner SpinHours = findViewById(R.id.spinCSDO_Time);

        Button btnCSDOCancel = findViewById(R.id.btnCSDOCancel);
        Button btnCSDOConfirm = findViewById(R.id.btnCSDOConfirm);
        btnCSDOConfirm.setBackgroundColor(Color.GREEN);
        btnCSDOCancel.setBackgroundColor(Color.RED);


        Intent intent = getIntent();
        if(intent!=null)
        {
            spID = Integer.parseInt(intent.getStringExtra("SPID").toString());
        }
        //SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        userID = Customer.CustomerID;
        //userID = 3;
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
        SpinServiceLocation.setAdapter(adapter2);

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






        btnCSDOConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             showDialog();
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
                DropoffLocation = SpinServiceLocation.getSelectedItem().toString();
                BookingDate = CurrDate; appointmentType= "Drop Off";AppointmentStatus = "Ongoing";
                DB.insertAppointment(userID,spID,pickupDateTime,pickupLocation,pickupReadyDate,DropoffTimeDate
                        ,DropoffLocation,BookingDate,CancelledDate,appointmentType,AppointmentStatus);
                getAptID();
                DB.insertAppointmentDetail(aptID,ServiceList);
                Toast.makeText(CustomerScheduleDropOff.this,"Successfuly Book an Appointment!!",Toast.LENGTH_SHORT).show();
                try {
                    String stringSenderEmail = "garkmobileapp@gmail.com";
                    String stringReceiverEmail = "arifinw@gmail.com";
                    String stringPasswordSenderEmail = "fpaozvcdwjnosccy";

                    String stringHost = "smtp.gmail.com";

                    Properties properties = System.getProperties();

                    properties.put("mail.smtp.host", stringHost);
                    properties.put("mail.smtp.port", "465");
                    properties.put("mail.smtp.ssl.enable", "true");
                    properties.put("mail.smtp.auth", "true");

                    javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                        }
                    });

                    MimeMessage mimeMessage = new MimeMessage(session);
                    mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));

                    mimeMessage.setSubject("Subject: Booking Appointment Details");
                    mimeMessage.setText("Hello, " + userName + "\n\nThis is a confirmation email regarding the appointment you booked at our Service Provider. Here are the details" +
                            "\n\nService Booked   :   " + ServiceDetail +
                            "\nBooking Time  :   " + BookingDate +
                            "\nDrop-Off Date and Time  :  " + DropoffTimeDate  +
                            "\nDrop-Off Location  :   " + DropoffLocation +
                            "\n\nIf you need any further information, please contact us by phone, we will be gladly at your service." +
                            "\n\nThank you!" +
                            "\n\n"+ spName

                    );

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Transport.send(mimeMessage);
                            } catch (MessagingException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();

                } catch (AddressException e) {
                    e.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                mView.findViewById(R.id.okBTN3).setOnClickListener(v -> {
                    // Toast.makeText(this, "Clicked OK BTN", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CustomerScheduleDropOff.this, MainActivity.class);
                    startActivity(intent);
                    alertDialog.dismiss();
                });
                alertDialog.show();
            }

        });
//        // Define the arrays of working hours for each day
//        String[] sundayHours = {"11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "01:00 PM", "01:30 PM", "02:00 PM", "02:30 PM", "03:00 PM"};
//        String[] otherDaysHours = {"10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "01:00 PM", "01:30 PM", "02:00 PM", "02:30 PM", "03:00 PM", "03:30 PM", "04:00 PM"};
//
//// Create an ArrayAdapter for the hour spinner
//        ArrayAdapter<String> hourAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, otherDaysHours);
//        SpinHours.setAdapter(hourAdapter);
//// Set an OnItemSelectedListener for the day spinner
//        SpinDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                // Get the selected day from the day spinner
//                String selectedDay = parent.getSelectedItem().toString();
//
//                // Update the adapter for the hour spinner based on the selected day
//                if (selectedDay.equals("Sunday")) {
//                    hourAdapter.clear();
//                    hourAdapter.addAll(sundayHours);
//                    hourAdapter.notifyDataSetChanged();
//                } else {
//                    hourAdapter.clear();
//                    hourAdapter.addAll(otherDaysHours);
//                    hourAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // Do nothing
//            }
//        });



    }

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
                }

            }
        }

    }

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

                }
            }
        }

    }


}