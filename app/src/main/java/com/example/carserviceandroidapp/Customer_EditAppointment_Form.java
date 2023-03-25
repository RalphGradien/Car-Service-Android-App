package com.example.carserviceandroidapp;

//import static android.content.ContentValues.TAG;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

public class Customer_EditAppointment_Form extends AppCompatActivity {
    DBHelper dbh = new DBHelper(this);
    private static final String TAG = "RemindEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_appointment_form);

        TextView tvStatusForm = (TextView) findViewById(R.id.textViewStatusForm);
        TextView tvSPNameDispForm = (TextView) findViewById(R.id.textViewSPNameDisplayForm);
        TextView tvSPAddressForm = (TextView) findViewById(R.id.textViewSPAddressForm);
        TextView tvSPCellDispForm = (TextView) findViewById(R.id.textViewSPCellDisplayForm);
        EditText etDODateForm = (EditText) findViewById(R.id.tvDropOffDateForm);
        EditText etDOTimeForm = (EditText)findViewById(R.id.etDropOffTimeForm);
        EditText etDOLocForm = (EditText) findViewById(R.id.textViewDropOffLocForm);
        EditText etPUDateForm = (EditText) findViewById(R.id.textViewPickupDateForm);
        EditText etPUTimeForm = (EditText)findViewById(R.id.etPickUpTimeForm);
        TextView tvAppStatusDownForm = (TextView) findViewById(R.id.editTextAppointmentStatusForm);
        EditText etPULocForm = (EditText) findViewById(R.id.tvPickupLocationForm);
        TextView tvAppType = (TextView) findViewById(R.id.tvAppointTypeForm);
//
        int[] appIdArr = new int[1];
        int appId=0;
//
        String[] spEmailArr = new String[1];
        String spEmail="";
//
        String[] spNameArr = new String[1];
        String serviceProviderName="";

        Intent intent = getIntent();
        if(intent != null) {

            appId = intent.getIntExtra("AppId",0);
            serviceProviderName = intent.getStringExtra("ServiceProviderName");
            String serviceProviderAddress = intent.getStringExtra("SPAddress");
            String appointmentStatus = intent.getStringExtra("AppStatus");
            String appType = intent.getStringExtra("AppType");
            String[] dropoffDateTime = intent.getStringExtra("DropoffD").split(" ");
            String dropOffDate = dropoffDateTime[0];
            String droffOffTime = dropoffDateTime[1]+" "+dropoffDateTime[2];
            String dropoffT = intent.getStringExtra("DropoffT");
            String pickupDateTimeString = intent.getStringExtra("PickupD");
            String[] pickupDateTime = new String[3];
            String pickupDate="";
            String pickupTime="";

            if(pickupDateTimeString!=null && !pickupDateTimeString.isEmpty() ){
                pickupDateTime = intent.getStringExtra("PickupD").split(" ");
                pickupDate = pickupDateTime[0];
                pickupTime = pickupDateTime[1]+" "+pickupDateTime[2] ;
            }else{
                pickupDate = "";
                pickupTime="";
            }

            String pickupT = intent.getStringExtra("PickupT");
            String dropoffLoc = intent.getStringExtra("DropoffLoc");
            String pickupLoc = intent.getStringExtra("PickupLoc");
            String serviceDetails = intent.getStringExtra("ServiceDet");
            String spPhone = intent.getStringExtra("SPPhone");
            spEmail = intent.getStringExtra("SPEmail");



            tvStatusForm.setTextColor(Color.WHITE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(20);
            int chosenColor=0;
            if(appointmentStatus.equals("Ongoing")){
                chosenColor = Color.rgb(247, 201, 16);
            }else if(appointmentStatus.equals("Completed")){
                chosenColor = Color.RED;
            }

            drawable.setColor(chosenColor);
            tvStatusForm.setBackgroundDrawable(drawable);
            tvStatusForm.setText(appointmentStatus);
            tvSPNameDispForm.setText(serviceProviderName);
            tvSPAddressForm.setText(serviceProviderAddress);
            tvSPCellDispForm.setText(spPhone);
            etDODateForm.setText(dropOffDate);
            etDOTimeForm.setText(droffOffTime);
            tvAppType.setText(appType);

            etDOLocForm.setText(dropoffLoc);
            String newDOLoc = "";

            etPUDateForm.setText(pickupDate);
            etPUTimeForm.setText(pickupTime);
            String[] newDODateTime = new String[2];

            if(TextUtils.isEmpty(etPUDateForm.getText().toString())){
                etDODateForm.setEnabled(true);
                etDOTimeForm.setEnabled(true);
                etDOLocForm.setEnabled(true);
                etDODateForm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar selectedDate = Calendar.getInstance();
                        // Create a new DatePickerDialog
                        DatePickerDialog datePickerDialog = new DatePickerDialog(Customer_EditAppointment_Form.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the year, month, and day fields of the selectedDate Calendar object
                                selectedDate.set(Calendar.YEAR, year);
                                selectedDate.set(Calendar.MONTH, month);
                                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                // Do something with the selected date
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                                String selectedDateAsString = dateFormat.format(selectedDate.getTime());
                                etDODateForm.setText(selectedDateAsString);
                                newDODateTime[0] =  selectedDateAsString;

                            }
                        }, selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DAY_OF_MONTH));

                        // Show the DatePickerDialog
                        datePickerDialog.show();
                    }

                });

                Calendar selectedTime = Calendar.getInstance();
                // Set an OnClickListener to show the TimePickerDialog when the TextView is clicked
                etDOTimeForm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Create a new TimePickerDialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(Customer_EditAppointment_Form.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Set the hour and minute fields of the selectedTime Calendar object
                                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                selectedTime.set(Calendar.MINUTE, minute);

                                // Do something with the selected time
                                String timeOutput = String.format("%02d:%02d %s", (hourOfDay == 0 || hourOfDay == 12) ? 12 : hourOfDay % 12, minute, (hourOfDay < 12) ? "AM" : "PM");
                                 newDODateTime[1] = timeOutput;
                                etDOTimeForm.setText(timeOutput);

                            }
                        }, selectedTime.get(Calendar.HOUR_OF_DAY), selectedTime.get(Calendar.MINUTE), true);

                        // Show the TimePickerDialog
                        timePickerDialog.show();
                    }

                });
                newDOLoc= etDOLocForm.getText().toString();

            } else{

                etDODateForm.setEnabled(false);
                etDOTimeForm.setEnabled(false);
                etDOLocForm.setEnabled(false);

            }

            String newDODateTimeComb = newDODateTime[0]+ newDODateTime[1];

            etPULocForm.setText(pickupLoc);
            String newPULoc;

            String[] newPUDateTimeArray = new String[2];

            if(etPUDateForm.getText().toString().isEmpty()){
                etPUDateForm.setHint("");
                etPUDateForm.setEnabled(false);
                etPUTimeForm.setHint("");
                etPUTimeForm.setEnabled(false);
                etPULocForm.setHint("");
                etPULocForm.setEnabled(false);
            }else {
                etPUDateForm.setEnabled(true);
                etPUTimeForm.setEnabled(true);
                //etPULocForm.setEnabled(true);

                etPUDateForm.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Calendar selectedDate = Calendar.getInstance();

                        // Create a new DatePickerDialog
                        DatePickerDialog datePickerDialog = new DatePickerDialog(Customer_EditAppointment_Form.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the year, month, and day fields of the selectedDate Calendar object
                                selectedDate.set(Calendar.YEAR, year);
                                selectedDate.set(Calendar.MONTH, month);
                                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                // Do something with the selected date
                                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                                String selectedDateAsString = dateFormat.format(selectedDate.getTime());
                                etPUDateForm.setText(selectedDateAsString);
                                newPUDateTimeArray[0] = selectedDateAsString;

                            }
                        }, selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DAY_OF_MONTH));

                        // Show the DatePickerDialog
                        datePickerDialog.show();
                    }

                });

                Calendar selectedTime = Calendar.getInstance();
                // Set an OnClickListener to show the TimePickerDialog when the TextView is clicked
                etPUTimeForm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Create a new TimePickerDialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(Customer_EditAppointment_Form.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Set the hour and minute fields of the selectedTime Calendar object
                                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                selectedTime.set(Calendar.MINUTE, minute);

                                // Do something with the selected time
                                String dateOutput = String.format("%02d:%02d %s", (hourOfDay == 0 || hourOfDay == 12) ? 12 : hourOfDay % 12, minute, (hourOfDay < 12) ? "AM" : "PM");
                                newPUDateTimeArray[1] = dateOutput;
                                etPUTimeForm.setText(dateOutput);

                            }
                        }, selectedTime.get(Calendar.HOUR_OF_DAY), selectedTime.get(Calendar.MINUTE), true);

                        // Show the TimePickerDialog
                        timePickerDialog.show();
                    }

                });
                newPULoc = etPULocForm.getText().toString();
            }

            TextView tvServDetailsForm = (TextView) findViewById(R.id.textViewServiceDetailsForm);
            tvServDetailsForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Customer_EditAppointment_Form.this, "Book another appointment if you want to change a Service", Toast.LENGTH_LONG).show();
                }
            });

            tvServDetailsForm.setText(serviceDetails);

            tvAppStatusDownForm.setText(appointmentStatus);
            tvAppStatusDownForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create the RadioGroup
                    RadioGroup radioGroup = new RadioGroup(Customer_EditAppointment_Form.this);
                    radioGroup.setOrientation(RadioGroup.VERTICAL);

                    // Create the RadioButtons and add them to the RadioGroup
                    RadioButton radioButton1 = new RadioButton(Customer_EditAppointment_Form.this);
                    radioButton1.setText("Ongoing");
                    radioGroup.addView(radioButton1);

                    RadioButton radioButton2 = new RadioButton(Customer_EditAppointment_Form.this);
                    radioButton2.setText("Completed");
                    radioGroup.addView(radioButton2);


                    // Show the RadioGroup in an AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(Customer_EditAppointment_Form.this);
                    builder.setView(radioGroup);
                    builder.setTitle("Select an option");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Get the selected RadioButton and do something with its text
                            int selectedId = radioGroup.getCheckedRadioButtonId();
                            RadioButton selectedRadioButton = (RadioButton) radioGroup.findViewById(selectedId);
                            String selectedOption = selectedRadioButton.getText().toString();
                            tvAppStatusDownForm.setText(selectedOption);
                            tvStatusForm.setText(selectedOption);

                            if(selectedOption.equals("Ongoing")){
                                drawable.setColor( Color.rgb(247, 201, 16));
                            }else if(selectedOption.equals("Completed")){
                                drawable.setColor( Color.rgb(101, 207, 114));
                            }

                        }
                    });
                    builder.setNegativeButton("Cancel", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

        }

        appIdArr[0]=appId;
        spEmailArr[0] = spEmail;
        spNameArr[0]= serviceProviderName;
        Button buttonUpdate = (Button) findViewById(R.id.buttonUpdateSecond);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String doDate;
                String doTime;
                String doLoc;
                String puDate;
                String puTime;
                String puLoc;
                String puDateTime="";
                String doDateTime="";

                String statusDown = tvAppStatusDownForm.getText().toString();
                if(!etDODateForm.getText().toString().isEmpty()){
                    doDate = etDODateForm.getText().toString();
                }else{
                    doDate="";
                }
                if(!etDOTimeForm.getText().toString().isEmpty()){
                    doTime = etDOTimeForm.getText().toString();
                }else{
                    doTime="";
                }
                if(!etDOLocForm.getText().toString().isEmpty()){
                    doLoc = etDOLocForm.getText().toString();
                }else{
                    doLoc="";
                }
                if(!etPUDateForm.getText().toString().isEmpty()){
                    puDate  = etPUDateForm.getText().toString();
                }else{
                    puDate="";
                }
                if(!etPUTimeForm.getText().toString().isEmpty()){
                    puTime = etPUTimeForm.getText().toString();
                    puDateTime = puDate+" "+puTime;
                }else{
                    puTime ="";
                    puDateTime = "";

                }
                if(!etPULocForm.getText().toString().isEmpty()){
                    puLoc = etPULocForm.getText().toString();
                }else{
                    puLoc ="";
                }

                doDateTime = doDate+" "+doTime;


                boolean isUpdated = dbh.updateAppointment(appIdArr[0],doDateTime,doLoc,puDateTime,puLoc,statusDown);

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
                                message.setSubject("Appointment Update");
                                message.setText("Hello "+spNameArr[0]+", \n\nThis is to inform that there are changes to Appointment ID: "+appIdArr[0]+". Check GARK to view the changes"+
                                        ". \n\n Cheers!\nGARK");


                                Transport.send(message);
                                Log.i(TAG, "Email sent successfully");
                            } catch (MessagingException e) {
                                Log.e(TAG, "Email sending failed: " + e.getMessage());
                            }
                        }
                    }).start();


                    Toast.makeText(Customer_EditAppointment_Form.this, "Record Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Customer_EditAppointment_Form.this, PlainActivity.class));

                }else{
                    Toast.makeText(Customer_EditAppointment_Form.this, "Record Not Updated", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }

}