package com.example.carserviceandroidapp;



import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.Manifest;

import androidx.core.app.ActivityCompat;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ServiceHistory extends Fragment {

   // private List<ServiceHistoryItems> filteredItems;

    //E/SQLiteLog: (1) no such column: A.ServiceDetailID in "SELECT C.name, C.mobile, C.email, A.PickUpDateTime, A.PickUpReadyDate, A.DropOffTimeDate, A.AppointmentStatus, SD.ServiceName FROM APPOINTMENT A INNER JOIN CUSTOMER C ON A
    //D/AndroidRuntime: Shutting down VM
    //E/AndroidRuntime: FATAL EXCEPTION: main
    private static final String QUERY_COMPLETED_APPOINTMENTS =
            "SELECT A.AppointmentID, C.name, C.mobile, C.email, A.AppointmentStatus, A.PickUpDateTime, A.PickUpReadyDate,A.DropOffTimeDate, A.ServiceProviderID,  A.AppointmentType, SD.ServiceName " +
                    "FROM APPOINTMENT A " +
                    "INNER JOIN CUSTOMER C ON A.Userid = C.Userid " +
                    "INNER JOIN APPOINTMENT_DETAIL AD ON A.AppointmentID = AD.AppointmentID " +
                    "INNER JOIN SERVICE_LIST SL ON AD.ServiceListID = SL.ServiceListID " +
                    "INNER JOIN SERVICE_DETAIL SD ON SL.ServiceDetailID = SD.ServiceDetailID " +
                    "WHERE A.ServiceProviderID = ? AND AppointmentStatus = 'Completed'";

    private static final String QUERY_CANCELLED_APPOINTMENTS =
            "SELECT A.AppointmentID, C.name, C.mobile, C.email, A.AppointmentStatus, A.PickUpDateTime, A.PickUpReadyDate,A.DropOffTimeDate, A.ServiceProviderID, A.AppointmentType,SD.ServiceName " +
                    "FROM APPOINTMENT A " +
                    "INNER JOIN CUSTOMER C ON A.Userid = C.Userid " +
                    "INNER JOIN APPOINTMENT_DETAIL AD ON A.AppointmentID = AD.AppointmentID " +
                    "INNER JOIN SERVICE_LIST SL ON AD.ServiceListID = SL.ServiceListID " +
                    "INNER JOIN SERVICE_DETAIL SD ON SL.ServiceDetailID = SD.ServiceDetailID " +
                    "WHERE A.ServiceProviderID = ? AND AppointmentStatus = 'Cancelled'";
    //private static final String FILE_NAME = "ServiceReport_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".pdf";
    private static final String FILE_NAME = "ServiceReport_";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_service_history, container, false);
        Context context = getContext().getApplicationContext();
        List<ServiceHistoryItems> historyItems = new ArrayList<>();
        Spinner filterSpinner = view.findViewById(R.id.filterSpinner);
        RecyclerView recyclerView = view.findViewById(R.id.historyRecyclerView);

        Button generateReportButton = view.findViewById(R.id.generateReportButton);
        ActivityCompat.requestPermissions(getActivity(), new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        generateReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ServiceHistoryItems> filteredItems = new ArrayList<>();
// Filter the data based on the selected spinner option
                String selectedOption = filterSpinner.getSelectedItem().toString();
                if (selectedOption.equals("Completed")) {
                    for (ServiceHistoryItems item : historyItems) {
                        if (item.getServiceAppointmentStatus().equals("Completed")) {
                            filteredItems.add(item);
                        }
                    }
                } else if (selectedOption.equals("Cancelled")) {
                    for (ServiceHistoryItems item : historyItems) {
                        if (item.getServiceAppointmentStatus().equals("Cancelled")) {
                            filteredItems.add(item);
                        }
                    }
                } else {
                    filteredItems.addAll(historyItems);
                }
                // Create a PDF report based on the filtered data
                generatePDF(filteredItems, recyclerView);
            }});

            filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                List<ServiceHistoryItems> filteredItems = new ArrayList<>();
                if (selectedOption.equals("Completed")) {
                    for (ServiceHistoryItems item : historyItems) {
                        if (item.getServiceAppointmentStatus().equals("Completed")) {
                            filteredItems.add(item);
                        }
                    }
                } else if (selectedOption.equals("Cancelled")) {
                    for (ServiceHistoryItems item : historyItems) {
                        if (item.getServiceAppointmentStatus().equals("Cancelled")) {
                            filteredItems.add(item);
                        }
                    }
                } else {
                    filteredItems.addAll(historyItems);
                }
                recyclerView.setAdapter(new ServiceHistoryAdapter(getActivity(), filteredItems));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        int serviceProviderID = ServiceProvider.ServiceProviderID;

        DBHelper dbHelper = new DBHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try (Cursor cursor = db.rawQuery(QUERY_COMPLETED_APPOINTMENTS, new String[]{String.valueOf(serviceProviderID)})) {
            while (cursor.moveToNext()) {
                String appointmentID = cursor.getString(cursor.getColumnIndexOrThrow("AppointmentID"));
                String customerName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String customerNumber = cursor.getString(cursor.getColumnIndexOrThrow("mobile"));
                String customerEmail = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String pickUpDateTime = cursor.getString(cursor.getColumnIndexOrThrow("PickUpDateTime"));
                String pickUpReadyDate = cursor.getString(cursor.getColumnIndexOrThrow("PickUpReadyDate"));
                String dropOffTimeDate = cursor.getString(cursor.getColumnIndexOrThrow("DropOffTimeDate"));
                String appointmentStatus = cursor.getString(cursor.getColumnIndexOrThrow("AppointmentStatus"));
                String appointmentType = cursor.getString(cursor.getColumnIndexOrThrow("AppointmentType"));
                String serviceName = cursor.getString(cursor.getColumnIndexOrThrow("ServiceName"));

                historyItems.add(new ServiceHistoryItems(appointmentID, customerName, customerNumber, customerEmail, pickUpDateTime,
                        pickUpReadyDate, dropOffTimeDate, appointmentStatus, serviceName, appointmentType));
            }
        }

        try (Cursor cursor = db.rawQuery(QUERY_CANCELLED_APPOINTMENTS, new String[]{String.valueOf(serviceProviderID)})) {
            while (cursor.moveToNext()) {
                String appointmentID = cursor.getString(cursor.getColumnIndexOrThrow("AppointmentID"));
                String customerName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String customerNumber = cursor.getString(cursor.getColumnIndexOrThrow("mobile"));
                String customerEmail = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String dropOffTimeDate = cursor.getString(cursor.getColumnIndexOrThrow("DropOffTimeDate"));
                String appointmentStatus = cursor.getString(cursor.getColumnIndexOrThrow("AppointmentStatus"));
                String appointmentType = cursor.getString(cursor.getColumnIndexOrThrow("AppointmentType"));
                String serviceName = cursor.getString(cursor.getColumnIndexOrThrow("ServiceName"));

                historyItems.add(new ServiceHistoryItems(appointmentID, customerName, customerNumber, customerEmail, appointmentStatus,
                        serviceName , dropOffTimeDate, appointmentType));
            }
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ServiceHistoryAdapter(getActivity(), historyItems));
        return view;
    }
    private void generatePDF(List<ServiceHistoryItems> filteredItems, RecyclerView recyclerView) {
        // Define the name of the PDF file
        String pdfFileName = "Service_Report-" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".pdf";

        // Get a directory where the PDF will be saved
        File pdfFileDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/" + pdfFileName);

        try {
            // Create a new document object
            PdfDocument document = new PdfDocument();

            // Create a page description
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(recyclerView.getWidth(), recyclerView.getMeasuredHeight()+2000, 1).create();

            // Start a new page
            PdfDocument.Page page = document.startPage(pageInfo);
            recyclerView.measure(
                    View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            // Get a canvas to draw on the page
            Canvas canvas = page.getCanvas();

            // Create a paint object for text
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(96);

            // Draw the "Service Provider Report" text


            // Load the logo drawable
            Drawable logoDrawable = getResources().getDrawable(R.drawable.verticallogo);

            // Convert the drawable to a bitmap
            Bitmap logoBitmap = ((BitmapDrawable) logoDrawable).getBitmap();

            // Define the desired width and height of the logo
            int desiredWidth = 100;
            int desiredHeight = 100;

            // Create a scaled bitmap of the logo
            Bitmap scaledLogoBitmap = Bitmap.createScaledBitmap(logoBitmap, desiredWidth, desiredHeight, true);

            // Resize the logo drawable to the calculated width and height

            canvas.drawText("Service Provider Report", 0, 100, paint);


            // Draw the logo on the canvas


            // Create a bitmap from the RecyclerView
            Bitmap recyclerViewBitmap = Bitmap.createBitmap(recyclerView.getWidth(), recyclerView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas recyclerViewCanvas = new Canvas(recyclerViewBitmap);
            recyclerView.draw(recyclerViewCanvas);

            canvas.drawBitmap(recyclerViewBitmap, 0, 80 + desiredHeight, null);

            // Finish the page and save the document to the file
            document.finishPage(page);
            FileOutputStream outputStream = new FileOutputStream(pdfFileDirectory);
            document.writeTo(outputStream);
            document.close();
            // Display a success message
            Toast.makeText(getContext(), "PDF created successfully", Toast.LENGTH_LONG).show();

            // Open the PDF in a PDF viewer app
            Uri contentUri = FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".fileprovider", pdfFileDirectory);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(contentUri, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } catch (IOException e) {
            e.printStackTrace();
            // Display an error message
            Toast.makeText(getContext(), "Error creating PDF", Toast.LENGTH_LONG).show();
        }

    }



}
