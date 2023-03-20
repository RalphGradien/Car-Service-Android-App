package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Provider_EditProfile extends AppCompatActivity {
    DBHelper dbHelper;
    Integer spID;
    String editName, editPassword, editEmail, editContact, editAddress, editCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_edit_profile);


    }
}