package com.example.carserviceandroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerEditProfile extends AppCompatActivity {
    DBHelper DB;
    String username,password,confirm_password,email,mobile,address;
    int userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_profile);
        DB = new DBHelper(this);
        TextView txtCustName = findViewById(R.id.txtCustName);
        EditText editTxtUserName = findViewById(R.id.editTxtUserName);
        EditText editTxtEmail = findViewById(R.id.editTextEmail);
        EditText editTxtPassword = findViewById(R.id.editTextPassword);
        EditText editTxtConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        EditText editTxtMobile = findViewById(R.id.editTextMobile);
        EditText editTxtAddress = findViewById(R.id.editTxtAddress);
        Button buttonSaveChanges = findViewById(R.id.btnSaveCust);
        Button buttonDeleteChanges = findViewById(R.id.btnDeleteCust);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        userID = sharedPref.getInt("key1",0);
        displaydata();
        txtCustName.setText(username);
        editTxtUserName.setText(username);
        editTxtEmail.setText(email);
        editTxtPassword.setText(password);
        editTxtConfirmPassword.setText(password);
        editTxtMobile.setText(mobile);
        editTxtAddress.setText(address);

        buttonDeleteChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerEditProfile.this);
                builder.setTitle("Title of the dialog box");
                builder.setMessage("Are you sure you want to delete this account?");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Action to take when "Confirm" is clicked
                      Boolean deletedata=  DB.deleteData(userID);

                        if (deletedata == true) {
                            Toast.makeText(CustomerEditProfile.this, "Deleted successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(CustomerEditProfile.this, "Failed to delete", Toast.LENGTH_LONG).show();
                        }

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Action to take when "Cancel" is clicked
                    }
                });
                builder.show();
            }
        });
        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkupdatedata = DB.updateData(editTxtUserName.getText().toString(), userID, editTxtPassword.getText().toString(), editTxtEmail.getText().toString(), editTxtMobile.getText().toString(), editTxtAddress.getText().toString());
                if (checkupdatedata == true) {
                    Toast.makeText(CustomerEditProfile.this, "Updated Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CustomerEditProfile.this, "Failed to Updated", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void displaydata()
    {
        Cursor cursor = DB.getCustomerData();

        if(cursor.getCount()==0)
        {
            Toast.makeText(CustomerEditProfile.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
            if(Integer.parseInt( cursor.getString(0))==userID)
                {
                    username = cursor.getString(1);
                    password = cursor.getString(2);
                    email = cursor.getString(3);
                    mobile = cursor.getString(4);
                    address = cursor.getString(5);
                }
            }
        }

    }
}