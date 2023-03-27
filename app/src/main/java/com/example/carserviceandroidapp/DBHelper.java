package com.example.carserviceandroidapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "Userdata.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase DB) {
        // This method is only called if the database does not exist
        // You can create the database schema here if needed

        // Create the Customer table
        DB.execSQL("create table IF NOT EXISTS CUSTOMER(Userid INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, password TEXT, " +
                "email TEXT, mobile TEXT , address TEXT)");

        // Create the Appointment table
        DB.execSQL("CREATE TABLE IF NOT EXISTS APPOINTMENT (AppointmentID INTEGER PRIMARY KEY AUTOINCREMENT, Userid INTEGER, ServiceProviderID INTEGER, " +
                "PickUpDateTime DATE, PickUpLocation TEXT , PickUpReadyDate DATE, " +
                "DropOffTimeDate DATE, DropOffLocation TEXT, BookingDate DATE, CancelledDate DATE , AppointmentType TEXT, " +
                "AppointmentStatus TEXT, FOREIGN KEY(Userid) REFERENCES CUSTOMER(Userid), " +
                "FOREIGN KEY(ServiceProviderID) REFERENCES SERVICE_PROVIDER(ServiceProviderID))");

        // Create the Service Provider table
        DB.execSQL("CREATE TABLE IF NOT EXISTS SERVICE_PROVIDER (ServiceProviderID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "serviceProviderPassword TEXT, serviceProviderFullName TEXT, street TEXT, city TEXT, province TEXT, " +
                "postalCode TEXT, phone TEXT, email TEXT, imageName TEXT)");

        // Create the Appointment Detail table
        DB.execSQL("CREATE TABLE IF NOT EXISTS APPOINTMENT_DETAIL(AppointmentDetailID INTEGER PRIMARY KEY AUTOINCREMENT,AppointmentID INTEGER, ServiceListID TEXT, " +
                "FOREIGN KEY(AppointmentID) REFERENCES APPOINTMENT(AppointmentID), FOREIGN KEY(ServiceListID) REFERENCES SERVICE_LIST(ServiceListID) )");

        // Create the Service List provided by each Service Provider table
        DB.execSQL("CREATE TABLE IF NOT EXISTS SERVICE_LIST(ServiceListID TEXT, ServiceProviderID INTEGER, " +
                "ServiceDetailID INTEGER, FOREIGN KEY(ServiceProviderID) REFERENCES SERVICE_PROVIDER(ServiceProviderID), " +
                "FOREIGN KEY(ServiceDetailID) REFERENCES SERVICE_DETAIL(ServiceDetailID) )");


        // Create the Service Detail table
        DB.execSQL("CREATE TABLE IF NOT EXISTS SERVICE_DETAIL(ServiceDetailID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ServiceName TEXT, ServiceInformation TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Customer");
    }

    public Boolean insertServiceList(String serviceListID, int serviceProviderID, int serviceDetailID)
    {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put("ServiceListID", serviceListID);
        Values.put("ServiceProviderID", serviceProviderID);
        Values.put("ServiceDetailID", serviceDetailID);

        long result = DB.insert("SERVICE_LIST", null, Values);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public Boolean insertAppointmentDetail(int AppointmentID, String ServiceListID)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put("AppointmentID",AppointmentID);
        Values.put("ServiceListID",ServiceListID);
        long result = DB.insert("APPOINTMENT_DETAIL", null, Values);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }


    }

    public Boolean insertServiceDetail(String serviceName, String serviceInformation)
    {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put("ServiceName", serviceName);
        Values.put("ServiceInformation", serviceInformation);

        long result = DB.insert("SERVICE_DETAIL", null, Values);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public Boolean insertuserdata(String name, String password, String email, String mobile, String address)
    {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("mobile", mobile);
        contentValues.put("address", address);

        long result = DB.insert("CUSTOMER", null, contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public Boolean insertServiceProvider(String password, String fullName, String street, String city,
                                         String province, String postalCode, String email, String phone, String imageName )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("serviceProviderPassword", password);
        values.put("serviceProviderFullName", fullName);
        values.put("street", street);
        values.put("city",city);
        values.put("province",province);
        values.put("postalCode",postalCode);
        values.put("email", email);
        values.put("phone", phone);
        values.put("imageName", imageName);



        long result = db.insert("SERVICE_PROVIDER", null, values);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean insertAppointment(int userID, int serviceProviderID, String pickupDateTime, String pickupLocation,
                                     String pickupReadyDate, String DropoffTimeDate, String DropoffLocation,
                                     String BookingDate,String CancelledDate, String appointmentType, String AppointmentStatus)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Userid",userID);
        values.put("PickUpDateTime",pickupDateTime);
        values.put("ServiceProviderID",serviceProviderID);
        values.put("PickUpLocation",pickupLocation);
        values.put("PickUpReadyDate",pickupReadyDate);
        values.put("DropOffTimeDate",DropoffTimeDate);
        values.put("DropOffLocation",DropoffLocation);
        values.put("BookingDate", BookingDate);
        values.put("CancelledDate",CancelledDate);
        values.put("AppointmentType",appointmentType);
        values.put("AppointmentStatus",AppointmentStatus);

        long result = db.insert("APPOINTMENT", null, values);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public Boolean updateData(String name, int userID, String password, String email, String mobile, String address)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // get a reference to the database
        // define the new values for the record
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("password", password);
        values.put("email", email);
        values.put("mobile", mobile);
        values.put("address", address);

        // define the selection criteria
        String selection = "userID = ?";
        String[] selectionArgs = { Integer.toString(userID) };
        int count = db.update("CUSTOMER", values, selection, selectionArgs);

        // check if the record was updated successfully
        if (count > 0) {
            return true;

            // record updated successfully
        } else {
            return false;
            // failed to update record
        }


    }

    public Boolean cancelApt(int userID, String aptStatus, String cancelDate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        // get a reference to the database
        // define the new values for the record
        ContentValues values = new ContentValues();
        values.put("AppointmentStatus", aptStatus);
        values.put("CancelledDate", cancelDate);


        // define the selection criteria
        String selection = "userID = ? AND AppointmentStatus != 'Cancelled'";
        String[] selectionArgs = { Integer.toString(userID) };
        int count = db.update("APPOINTMENT", values, selection, selectionArgs);

        // check if the record was updated successfully
        if (count > 0) {
            return true;

            // record updated successfully
        } else {
            return false;
            // failed to update record
        }
    }

    public Boolean deleteData(int userID)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        // Define the WHERE clause (i.e., the condition that must be met for the record to be deleted)
        String selection = "userID = ?";
        String[] selectionArgs = { Integer.toString(userID) }; // Replace with the actual ID of the record to delete

        // Perform the deletion
        int deletedRows = DB.delete("CUSTOMER", selection, selectionArgs);

        // Check if the deletion was successful
        if (deletedRows > 0) {
            return true;
            // Record was deleted successfully
        } else {
            return false;
            // Record was not found or could not be deleted
        }

    }

    public Cursor getCustomerData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from CUSTOMER", null);
        return cursor;
    }
    //Get Service Provider Data
    public Cursor getServiceProviderInfo()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from SERVICE_PROVIDER", null);
        return cursor;
    }
    public Cursor getAppointmentID()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT last_insert_rowid()", null);

        return cursor;
    }
    public Cursor getServiceProviderData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select distinct city from SERVICE_PROVIDER", null);
        return cursor;
    }

    public Cursor getServiceProviderList()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from SERVICE_PROVIDER", null);
        return cursor;
    }

    public Cursor getServiceDetails()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT SD.ServiceName, SD.ServiceInformation, SL.ServiceProviderID, SD.ServiceDetailID \n" +
                "FROM SERVICE_DETAIL SD\n" +
                "INNER JOIN SERVICE_LIST SL ON SD.ServiceDetailID = SL.ServiceDetailID\n" +
                "INNER JOIN SERVICE_PROVIDER SP ON SL.ServiceProviderID = SP.ServiceProviderID", null);
        return cursor;
    }

    public Cursor getAppointment()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from APPOINTMENT", null);
        return cursor;

    }
    public Cursor getDropOffLocationCust()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select Userid,address from CUSTOMER", null);
        return cursor;
    }

    public Cursor getDropOffLocationSP()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select ServiceProviderID, street, city, province, postalCode from SERVICE_PROVIDER", null);
        return cursor;
    }

    public Cursor getServiceList()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from SERVICE_LIST", null);
        return cursor;
    }

    public String checkLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor customerCursor = db.rawQuery("SELECT * FROM CUSTOMER WHERE email=? AND password=?", new String[]{email, password});
        Cursor providerCursor = db.rawQuery("SELECT * FROM SERVICE_PROVIDER WHERE Email=? AND ServiceProviderPassword=?", new String[]{email, password});

        if (customerCursor.getCount() > 0) {
            return "CUSTOMER";
        } else if (providerCursor.getCount() > 0) {
            return "SERVICE_PROVIDER";
        } else {
            return "NOT_FOUND";
        }
    }
    public String[] checkLoginID(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor customerCursor = db.rawQuery("SELECT * FROM CUSTOMER WHERE email=? AND password=?", new String[]{email, password});
        Cursor providerCursor = db.rawQuery("SELECT * FROM SERVICE_PROVIDER WHERE Email=? AND ServiceProviderPassword=?", new String[]{email, password});

        if (customerCursor.getCount() > 0) {
            customerCursor.moveToFirst();
            @SuppressLint("Range") int customerId = customerCursor.getInt(customerCursor.getColumnIndex("Userid"));
            return new String[]{"CUSTOMER", String.valueOf(customerId)};
        } else if (providerCursor.getCount() > 0) {
            providerCursor.moveToFirst();
            @SuppressLint("Range") int serviceProviderId = providerCursor.getInt(providerCursor.getColumnIndex("ServiceProviderID"));
            return new String[]{"SERVICE_PROVIDER", String.valueOf(serviceProviderId)};
        } else {
            return new String[]{"NOT_FOUND"};
        }
    }

    public String[] checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor customerCursor = db.rawQuery("SELECT * FROM CUSTOMER WHERE email=?", new String[]{email});
        Cursor providerCursor = db.rawQuery("SELECT * FROM SERVICE_PROVIDER WHERE Email=?", new String[]{email});

        if (customerCursor.getCount() > 0) {
            customerCursor.moveToFirst();
            @SuppressLint("Range") int customerId = customerCursor.getInt(customerCursor.getColumnIndex("Userid"));
            @SuppressLint("Range") String password = customerCursor.getString(customerCursor.getColumnIndex("password"));
            return new String[]{"CUSTOMER", String.valueOf(customerId), password};
        } else if (providerCursor.getCount() > 0) {
            providerCursor.moveToFirst();
            @SuppressLint("Range") int serviceProviderId = providerCursor.getInt(providerCursor.getColumnIndex("ServiceProviderID"));
            @SuppressLint("Range") String password = customerCursor.getString(customerCursor.getColumnIndex("serviceProviderPassword"));
            return new String[]{"SERVICE_PROVIDER", String.valueOf(serviceProviderId), password};
        } else {
            return new String[]{"NOT_FOUND"};
        }
    }

    public Cursor getAppointmentDetail()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from APPOINTMENT_DETAIL",null);
        return cursor;
    }

    public Cursor getServiceProviderDataAll()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from SERVICE_PROVIDER", null);
        return cursor;
    }

    public Boolean cancelAppointment(int appID)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the new values for the record
        ContentValues values = new ContentValues();
        values.put("AppointmentStatus", "Cancelled");

        // Define the selection criteria
        String selection = "AppointmentID = ?";
        String[] selectionArgs = { Integer.toString(appID) };

        // Update the record and get the number of rows affected
        int rowsAffected = db.update("APPOINTMENT", values, selection, selectionArgs);

        // Check if any rows were affected by the update
        if (rowsAffected > 0) {
            // Record updated successfully
            return true;
        } else {
            // Failed to update record
            return false;
        }
    }

    public Boolean updateAppointment(int appID,String doDate, String doLoc,
                                     String puDate,  String puLoc, String newStatus)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the new values for the record
        ContentValues values = new ContentValues();
        values.put("DropOffTimeDate", doDate);
        values.put("DropOffLocation", doLoc);
        values.put("PickUpDateTime", puDate);
        values.put("PickUpLocation", puLoc);
        values.put("AppointmentStatus", newStatus);

        // Define the selection criteria
        String selection = "AppointmentID = ?";
        String[] selectionArgs = { Integer.toString(appID) };

        // Update the record and get the number of rows affected
        int rowsAffected = db.update("APPOINTMENT", values, selection, selectionArgs);

        // Check if any rows were affected by the update
        if (rowsAffected > 0) {
            // Record updated successfully
            return true;
        } else {
            // Failed to update record
            return false;
        }
    }
    public Cursor getServiceProviderName(int ID) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT serviceProviderFullName FROM SERVICE_PROVIDER WHERE ServiceProviderID = ?", new String[] { String.valueOf(ID) });
        return cursor;
    }

    public Cursor getServiceProviderID(String p_email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT serviceProviderID FROM SERVICE_PROVIDER WHERE email = ?", new String[] { String.valueOf(p_email) });
        return cursor;
    }

    //update Service provider profile
    public Boolean updateServiceProviderProfile(Integer spID, String password, String name, String address, String city, String contact, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("serviceProviderPassword", password);
        values.put("serviceProviderFullName", name);
        values.put("street", address);
        values.put("city", city);
        values.put("phone", contact);
        values.put("email", email);

        // define the selection criteria
        String selection = "ServiceProviderID = ?";
        String[] selectionArgs = { Integer.toString(spID) };
        int count = db.update("SERVICE_PROVIDER", values, selection, selectionArgs);

        // check if the record was updated successfully
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
    //
    //update Customer Appointment with Service Provider
    public Boolean updateServiceProviderAppointment(Integer appointmentID, String pickUpDateTime,
          String pickUpLocation, String appointmentStatus)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PickUpDateTime", pickUpDateTime);
        values.put("PickUpLocation", pickUpLocation);
        values.put("AppointmentStatus", appointmentStatus);

        // define the selection criteria
        String selection = "AppointmentID = ?";
        String[] selectionArgs = { Integer.toString(appointmentID) };
        int count = db.update("APPOINTMENT", values, selection, selectionArgs);

        // check if the record was updated successfully
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    //Get customer info
    public Cursor getCustomerInfo()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from CUSTOMER", null);
        return cursor;
    }

    public boolean deleteServiceProvider(int ID) {
        SQLiteDatabase DB = this.getWritableDatabase();
        int rowsDeleted = DB.delete("SERVICE_PROVIDER", "ServiceProviderID = ?", new String[] { String.valueOf(ID) });
        return rowsDeleted > 0;
    }

    public Cursor getCustomerName(int cID) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT name FROM CUSTOMER WHERE Userid = ?", new String[] { String.valueOf(cID) });
        return cursor;
    }

    public Cursor getServiceProviderImage(int ID) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT imageName FROM SERVICE_PROVIDER WHERE ServiceProviderID = ?", new String[] { String.valueOf(ID) });
        return cursor;
    }

    //check the exist of the data in database
    public boolean isDatabaseEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        String count = "SELECT count(*) FROM CUSTOMER";
        Cursor cursor = db.rawQuery(count, null);
        cursor.moveToFirst();
        int rowCount = cursor.getInt(0);
        cursor.close();
        return rowCount == 0;
    }

}





