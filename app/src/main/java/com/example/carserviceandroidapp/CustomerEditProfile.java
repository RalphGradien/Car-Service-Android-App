package com.example.carserviceandroidapp;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerEditProfile extends AppCompatActivity {
    DBHelper DB;
    String username,password,confirm_password,email,mobile,address;
    int userID;

    EditText etName;
    EditText etPW;
    EditText etConfirmPW;
    EditText etEmail;
    EditText etAddress;
    EditText etContact;

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
        Button buttonSaveChanges = findViewById(R.id.btnSaveChanges);
        Button buttonDeleteChanges = findViewById(R.id.btnDeleteCust);

        etName = editTxtUserName;
        etPW = editTxtPassword;
        etConfirmPW = editTxtConfirmPassword;
        etEmail = editTxtEmail;
        etAddress = editTxtAddress;
        etContact = editTxtMobile;

      //  userID= Customer.CustomerID;
        userID= 2;

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
                ShowDialogBox();
            }


            private void ShowDialogBox (){
                final AlertDialog.Builder alert = new AlertDialog.Builder(CustomerEditProfile.this);
                View mView = getLayoutInflater().inflate(R.layout.confirmation_dialog, null);
                alert.setView(mView);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCancelable(false);

                mView.findViewById(R.id.chancelBTN).setOnClickListener(v -> {
                    alertDialog.dismiss();
                });

                mView.findViewById(R.id.okBTN).setOnClickListener(v -> {
                    Boolean deletedata=  DB.deleteData(userID);
                        if (deletedata == true) {
                            showDelDialog();
                           Toast.makeText(CustomerEditProfile.this, "Deleted successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(CustomerEditProfile.this, "Failed to delete", Toast.LENGTH_LONG).show();


                        }
                       alertDialog.dismiss();



                });
                alertDialog.show();
            }

            private void showDelDialog()
            {
                final AlertDialog.Builder alert = new AlertDialog.Builder(CustomerEditProfile.this);
                View mView = getLayoutInflater().inflate(R.layout.confirm_delete_dialog, null);
                alert.setView(mView);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCancelable(false);



                mView.findViewById(R.id.okBTN).setOnClickListener(v -> {
                   // Toast.makeText(this, "Clicked OK BTN", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CustomerEditProfile.this, MainActivity.class);
                    startActivity(intent);
                    alertDialog.dismiss();
                });

                alertDialog.show();

            }


        });
        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cName = editTxtUserName.getText().toString();
                String cPW = editTxtPassword.getText().toString();
                String confPW = editTxtConfirmPassword.getText().toString();
                String cEmail = editTxtEmail.getText().toString();
                String cAddress = editTxtAddress.getText().toString();
                String cNumber = editTxtMobile.getText().toString();

                boolean emailIsValidated = validateEmail(cEmail);
                boolean passwordCheck= comparedPassword(cPW, confPW);
                boolean checkInputs = validateInfo(cName,cPW,confPW,cEmail,cAddress,cNumber);
                if (checkInputs == true && emailIsValidated==false && passwordCheck==true  ) {
                    showDialogBox2();
                } else {
                    if (emailIsValidated == true) {
                        Toast.makeText(CustomerEditProfile.this, "Email is already existing", Toast.LENGTH_LONG).show();
                    } else if (passwordCheck == false) {
                        Toast.makeText(CustomerEditProfile.this, "Password Did Not Match", Toast.LENGTH_LONG).show();
                    } else if (emailIsValidated == false && passwordCheck == false) {
                        Toast.makeText(CustomerEditProfile.this, "Email is already registered and password is incorrect.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(CustomerEditProfile.this, "Some fields do not meet the needed parameters.", Toast.LENGTH_LONG).show();
                    }
                }
            }


            private void showDialogBox2()
            {
                final AlertDialog.Builder alert = new AlertDialog.Builder(CustomerEditProfile.this);
                View mView = getLayoutInflater().inflate(R.layout.confirm_customer_update_dialog, null);
                alert.setView(mView);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCancelable(false);
                Boolean checkupdatedata = DB.updateData(editTxtUserName.getText().toString(), userID, editTxtPassword.getText().toString(), editTxtEmail.getText().toString(), editTxtMobile.getText().toString(), editTxtAddress.getText().toString());
                if (checkupdatedata == true) {
                    Toast.makeText(CustomerEditProfile.this, "Updated Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CustomerEditProfile.this, "Failed to Updated", Toast.LENGTH_LONG).show();
                }


                mView.findViewById(R.id.okBTN).setOnClickListener(v -> {
                    // Toast.makeText(this, "Clicked OK BTN", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CustomerEditProfile.this, MainActivity.class);
                    startActivity(intent);
                    alertDialog.dismiss();
                });

                alertDialog.show();


            }



        });
    }

    private Boolean validateInfo(String cName, String pWord, String confPW,String cEmail, String cAddress, String cContact ){

        if(cName.length()==0){
            etName.requestFocus();
            etName.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else if(!cName.matches("(?=.*[a-zA-Z])" +".{4,}")){   //any letter,  at least 4
            etName.requestFocus();
            etName.setError("ENTER AT LEAST 4 CHARACTERS");
            return false;
        }
        else if(pWord.length()==0){
            etPW.requestFocus();
            etPW.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else if(confPW.length()==0){
            etConfirmPW.requestFocus();
            etConfirmPW.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else if(cEmail.length()==0){
            etEmail.requestFocus();
            etEmail.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else if(cAddress.length()==0){
            etAddress.requestFocus();
            etAddress.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else if(cContact.length()==0){
            etContact.requestFocus();
            etContact.setError("FIELD CANNOT BE EMPTY");
            return false;
        }

        else if(!cEmail.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            etEmail.requestFocus();
            etEmail.setError("ENTER VALID EMAIL");
            return false;
        }

        else if(pWord.length()==0){
            etPW.requestFocus();
            etPW.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else{
            return true;
        }
    }

    private boolean validateEmail(String email){

        boolean result=false;
        Cursor cursor = DB.getCustomerData();
        try{
            if(cursor.getCount() > 0){
                //  cursor.moveToPosition(-1);
                while(cursor.moveToNext()){
                    if(cursor.getString(cursor.getColumnIndexOrThrow("email")).equals(email)){
                        result = true;
                    }else{
                        result =   false;
                    }
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public boolean comparedPassword(String cPW, String confPW){
        boolean passwordOk=false;
        if(cPW.equals(confPW)){
            return true;
        }else{
            return false;
        }
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