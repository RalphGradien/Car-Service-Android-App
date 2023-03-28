package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Provider_EditProfile extends AppCompatActivity {
    DBHelper dbHelper;
    Integer spID;
    String editName, editPassword, editEmail, editContact, editAddress, editCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_edit_profile);

        dbHelper = new DBHelper(this);
        TextView name = findViewById(R.id.editProviderName);
        EditText  password = findViewById(R.id.editProviderPassword);
        TextView email = findViewById(R.id.editProviderEmail);
        EditText contact = findViewById(R.id.editProviderContact);
        EditText address = findViewById(R.id.editProviderAddress);
        EditText city = findViewById(R.id.editProviderCity);
        Button buttonSaveChanges = findViewById(R.id.btnSaveChanges);
        Button buttonDeleteChanges = findViewById(R.id.btnDeleteAccount);

        spID= ServiceProvider.ServiceProviderID;
        displayService();
        name.setText(editName);
        email.setText(editEmail);
        password.setText(editPassword);
        city.setText(editCity);
        contact.setText(editContact);
        address.setText(editAddress);

        buttonDeleteChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Provider_EditProfile.this);
                builder.setTitle("Warning");
                builder.setMessage("Do your really want to delete your account?");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Action to take when "Confirm" is clicked
                        Boolean deletedata=  dbHelper.deleteServiceProvider(spID);

                        if (deletedata == true) {
                            Toast.makeText(Provider_EditProfile.this, "Deleted successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Provider_EditProfile.this, "Failed to delete", Toast.LENGTH_LONG).show();
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
                Boolean checkupdatedata = dbHelper.updateServiceProviderProfile(spID, password.getText().toString(), name.getText().toString(),
                        address.getText().toString(), city.getText().toString(), contact.getText().toString(), email.getText().toString());
                if (checkupdatedata == true) {
                    Toast.makeText(Provider_EditProfile.this, "Updated Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Provider_EditProfile.this, "Check input fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void displayService()
    {
        Cursor cursor = dbHelper.getServiceProviderDataAll();

        if(cursor.getCount()==0)
        {
            Toast.makeText(Provider_EditProfile.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                if(Integer.parseInt( cursor.getString(0)) == spID)
                {
                    editName = cursor.getString(2);
                    editPassword = cursor.getString(1);
                    editEmail = cursor.getString(8);
                    editContact = cursor.getString(7);
                    editAddress = cursor.getString(3);
                    editCity = cursor.getString(4);
                }
            }
        }

    }
}