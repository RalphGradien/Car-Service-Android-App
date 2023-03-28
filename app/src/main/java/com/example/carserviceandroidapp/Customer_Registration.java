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
import android.widget.Toast;

public class Customer_Registration extends AppCompatActivity {

    DBHelper dbh = new DBHelper(this);

    EditText etName;
    EditText etPW;
    EditText etConfirmPW;
    EditText etEmail;
    EditText etAddress;
    EditText etContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        etName = (EditText) findViewById(R.id.ProviderName);
        etPW = (EditText) findViewById(R.id.ProviderPassWord);
        etConfirmPW = (EditText) findViewById(R.id.ProviderConfirmPassword);
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etAddress = (EditText) findViewById(R.id.ProviderAddress);
        etContact = (EditText) findViewById(R.id.ProviderContact);

        Button buttonRegister = (Button) findViewById(R.id.bthCustomerRegister);

            buttonRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String cName = etName.getText().toString();
                    String cPW = etPW.getText().toString();
                    String confPW = etConfirmPW.getText().toString();
                    String cEmail = etEmail.getText().toString();
                    String cNumber = etContact.getText().toString();
                    String cAddress = etAddress.getText().toString();
                    boolean emailIsValidated = validateEmail(cEmail);
                    boolean passwordCheck= comparedPassword(cPW, confPW);
                    boolean checkInputs = validateInfo(cName,cPW,confPW,cEmail,cAddress,cNumber);
                    if (checkInputs == true && emailIsValidated==false && passwordCheck==true  ) {
                        Customer_Account customer_account = new Customer_Account(cName, cPW, cEmail, cNumber, cAddress);
                        dbh.insertuserdata(customer_account.getcName(), customer_account.getcPassword(),
                                customer_account.getcEmail(), customer_account.getcMobile(),
                                customer_account.getcAddress());

                        final android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(Customer_Registration.this);
                        View mView = getLayoutInflater().inflate(R.layout.customer_registration_dialogue, null);
                        alert.setView(mView);

                        final AlertDialog alertDialog = alert.create();
                        alertDialog.setCancelable(false);

                        mView.findViewById(R.id.okBTNReg).setOnClickListener(view -> {
                            // Toast.makeText(this, "Clicked OK BTN", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Customer_Registration.this, LogIn.class);
                            startActivity(intent);
                            alertDialog.dismiss();
                        });

                        alertDialog.show();

                    } else {
                        if(emailIsValidated==true){
                            Toast.makeText(Customer_Registration.this, "Email is already existing", Toast.LENGTH_LONG).show();
                        }else if(passwordCheck==false){
                            Toast.makeText(Customer_Registration.this, "Password Did Not Match", Toast.LENGTH_LONG).show();
                        }else if(emailIsValidated==false&&passwordCheck==false){
                            Toast.makeText(Customer_Registration.this, "Email is already registered and password is incorrect.", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Customer_Registration.this, "Some fields do not meet the needed parameters.", Toast.LENGTH_LONG).show();
                        }

                        }
                }
            });
    }

    private boolean validateEmail(String email){

        boolean result=false;
        Cursor cursor = dbh.getCustomerData();
        try{
            if(cursor.getCount() > 0){
                cursor.moveToPosition(-1);
                while(cursor.moveToNext()){
                    if(cursor.getString(cursor.getColumnIndexOrThrow("email")).trim().equals(email)){
                        result = true;
                    }
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
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

    public boolean comparedPassword(String cPW, String confPW){
        boolean passwordOk=false;
        if(cPW.equals(confPW)){
            return true;
        }else{
            return false;
        }
    }

    //temporary. to replace accordingly later
    public void onClickToAppointmentsView(View v){
       Intent intent = new Intent(Customer_Registration.this, LogIn.class);
       startActivity(intent);
    }

//    public void onClickToHistoryView(View v){
//        Intent intent = new Intent(Customer_Registration.this, Customer_ServiceHistoryView.class);
//        startActivity(intent);
//    }



}