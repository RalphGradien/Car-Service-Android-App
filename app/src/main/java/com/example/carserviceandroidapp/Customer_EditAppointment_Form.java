package com.example.carserviceandroidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class Customer_EditAppointment_Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_appointment_form);

        Intent intent = getIntent();
        if(intent != null) {
            String serviceProviderName = intent.getStringExtra("ServiceProviderName");
            String serviceProviderAddress = intent.getStringExtra("SPAddress");
            String appointmentStatus = intent.getStringExtra("AppStatus");
            String dropoffDateTime = intent.getStringExtra("DropoffD");
            String dropoffT = intent.getStringExtra("DropoffT");
            String pickupDateTime = intent.getStringExtra("PickupD");
            String pickupT = intent.getStringExtra("PickupT");
            String dropoffLoc = intent.getStringExtra("DropoffLoc");
            String pickupLoc = intent.getStringExtra("PickupLoc");
            String serviceDetails = intent.getStringExtra("ServiceDet");
            String spPhone = intent.getStringExtra("SPPhone");

            TextView tvStatusForm = (TextView) findViewById(R.id.textViewStatusForm);
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

            TextView tvSPNameDispForm = (TextView) findViewById(R.id.textViewSPNameDisplayForm);
            tvSPNameDispForm.setText(serviceProviderName);
            TextView tvSPAddressForm = (TextView) findViewById(R.id.textViewSPAddressForm);
            tvSPAddressForm.setText(serviceProviderAddress);
            TextView tvSPCellDispForm = (TextView) findViewById(R.id.textViewSPCellDisplayForm);
            tvSPCellDispForm.setText(spPhone);
            EditText tvDODTForm = (EditText) findViewById(R.id.tvDropOffDTForm);
            tvDODTForm.setText(dropoffDateTime);

            EditText tvDOLocForm = (EditText) findViewById(R.id.textViewDropOffLocForm);
            tvDOLocForm.setText(dropoffLoc);
            String newDOLoc = "";

            EditText tvPUDTForm = (EditText) findViewById(R.id.textViewPickupTimeForm);
            tvPUDTForm.setText(pickupDateTime);
            Button btnSetNewDffTime = (Button) findViewById(R.id.btnNewDOTime);

            if(TextUtils.isEmpty(tvPUDTForm.getText().toString())){
                String[] dateTimeArray = new String[2];
                tvDODTForm.setEnabled(true);
                tvDODTForm.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        btnSetNewDffTime.setVisibility(View.VISIBLE);
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
                                tvDODTForm.setText(selectedDateAsString);
                                dateTimeArray[0] =  selectedDateAsString;

                            }
                        }, selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DAY_OF_MONTH));

                        // Show the DatePickerDialog
                        datePickerDialog.show();
                    }

                });

                Calendar selectedTime = Calendar.getInstance();
                // Set an OnClickListener to show the TimePickerDialog when the TextView is clicked
                btnSetNewDffTime.setOnClickListener(new View.OnClickListener() {
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
                                 String dateOutput= DateFormat.getTimeInstance().format(selectedTime.getTime());

                                 dateTimeArray[1] = dateOutput;
                                String newDODateTime = dateTimeArray[0] + " "+dateTimeArray[1];
                                tvDODTForm.setText(newDODateTime);
                                btnSetNewDffTime.setVisibility(View.INVISIBLE);

                            }
                        }, selectedTime.get(Calendar.HOUR_OF_DAY), selectedTime.get(Calendar.MINUTE), true);

                        // Show the TimePickerDialog
                        timePickerDialog.show();
                    }

                });
                newDOLoc= tvDOLocForm.getText().toString();

            } else{

                tvDODTForm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Customer_EditAppointment_Form.this, "You are not allowed to Edit this field", Toast.LENGTH_LONG).show();
                    }
                });
                tvDODTForm.setEnabled(false);
                tvDOLocForm.setEnabled(false);
            }

            EditText tvPULocForm = (EditText) findViewById(R.id.tvPickupLocationForm);
            tvPULocForm.setText(pickupLoc);
            String newPULoc;
            Button bntNewPUTime = (Button) findViewById(R.id.btnNewPUTime);

            if(tvPUDTForm.getText().toString().isEmpty()){
                tvPUDTForm.setText("NOT READY FOR PICKUP");
                tvPUDTForm.setEnabled(false);
                tvPULocForm.setText("NOT READY FOR PICKUP");
                tvPULocForm.setEnabled(false);
            }else {
                String[] dateTimeArray = new String[2];
                tvPUDTForm.setEnabled(true);
                tvPUDTForm.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        bntNewPUTime.setVisibility(View.VISIBLE);
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
                                tvPUDTForm.setText(selectedDateAsString);
                                dateTimeArray[0] = selectedDateAsString;

                            }
                        }, selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DAY_OF_MONTH));

                        // Show the DatePickerDialog
                        datePickerDialog.show();
                    }

                });

                Calendar selectedTime = Calendar.getInstance();
                // Set an OnClickListener to show the TimePickerDialog when the TextView is clicked
                bntNewPUTime.setOnClickListener(new View.OnClickListener() {
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
                                String dateOutput = DateFormat.getTimeInstance().format(selectedTime.getTime());

                                dateTimeArray[1] = dateOutput;
                                String newDODateTime = dateTimeArray[0] + " " + dateTimeArray[1];
                                tvPUDTForm.setText(newDODateTime);
                                bntNewPUTime.setVisibility(View.INVISIBLE);

                            }
                        }, selectedTime.get(Calendar.HOUR_OF_DAY), selectedTime.get(Calendar.MINUTE), true);

                        // Show the TimePickerDialog
                        timePickerDialog.show();
                    }

                });
                newPULoc = tvPULocForm.getText().toString();
            }

            TextView tvServDetailsForm = (TextView) findViewById(R.id.textViewServiceDetailsForm);
            tvServDetailsForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Customer_EditAppointment_Form.this, "Book another appointment if you want to change a Service", Toast.LENGTH_LONG).show();
                }
            });

            tvServDetailsForm.setText(serviceDetails);
            TextView tvAppStatusDownForm = (TextView) findViewById(R.id.editTextAppointmentStatusForm);
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
                                drawable.setColor( Color.RED);
                            }

                        }
                    });
                    builder.setNegativeButton("Cancel", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });




        }
    }
}