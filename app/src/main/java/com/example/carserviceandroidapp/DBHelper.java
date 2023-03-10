package com.example.carserviceandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                "email TEXT, mobile TEXT, address TEXT)");

        // Create the Appointment table
        DB.execSQL("CREATE TABLE IF NOT EXISTS APPOINTMENT (AppointmentID INTEGER PRIMARY KEY AUTOINCREMENT, Userid INTEGER, ServiceProviderID INTEGER, " +
                "PickUpDateTime DATE, PickUpLocation TEXT, PickUpReadyDate DATE, " +
                "DropOffTimeDate DATE, BookingDate DATE, CancelledDate DATE, AppointmentType TEXT, " +
                "AppointmentStatus TEXT, FOREIGN KEY(Userid) REFERENCES CUSTOMER(Userid), " +
                "FOREIGN KEY(ServiceProviderID) REFERENCES SERVICE_PROVIDER(ServiceProviderID))");

        // Create the Service Provider table
        DB.execSQL("CREATE TABLE IF NOT EXISTS SERVICE_PROVIDER (ServiceProviderID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "serviceProviderPassword TEXT, serviceProviderFullName TEXT, street TEXT, city TEXT, province TEXT, " +
                "postalCode TEXT, phone TEXT, email TEXT)");

        // Create the Appointment Detail table
        DB.execSQL("CREATE TABLE IF NOT EXISTS APPOINTMENT_DETAIL(AppointmentDetailID INTEGER PRIMARY KEY AUTOINCREMENT,AppointmentID INTEGER, ServiceListID INTEGER, " +
                "FOREIGN KEY(AppointmentID) REFERENCES APPOINTMENT(AppointmentID), FOREIGN KEY(ServiceListID) REFERENCES SERVICE_LIST(ServiceListID) )");

        // Create the Service List table
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
                                         String province, String postalCode, String email, String phone )
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

    public Cursor getServiceProviderData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select distinct city from SERVICE_PROVIDER", null);
        return cursor;
    }

}
