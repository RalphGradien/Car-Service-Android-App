package com.example.carserviceandroidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Customer_Registration extends AppCompatActivity {

    DBHelper dbh = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        EditText etName = (EditText) findViewById(R.id.ProviderName);
        EditText etPW = (EditText) findViewById(R.id.ProviderPassWord);
        EditText etConfirmPW = (EditText) findViewById(R.id.ProviderConfirmPassword);
        EditText etEmail = (EditText) findViewById(R.id.ProviderEmail);
        EditText etAddress = (EditText) findViewById(R.id.ProviderAddress);
        EditText etContact = (EditText) findViewById(R.id.ProviderContact);

        Button buttonRegister = (Button) findViewById(R.id.btnProviderRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Customer_Registration.this, Customer_AppointmentsView.class);
//                startActivity(intent);

                String cName = etName.getText().toString();
                String cPW = etPW.getText().toString();
                String cEmail = etEmail.getText().toString();
                String cNumber = etContact.getText().toString();
                String cAddress = etAddress.getText().toString();

                Customer_Account customer_account=new Customer_Account(cName,cPW, cEmail, cNumber, cAddress);


                if(etName.getText().toString().isEmpty() || etPW.getText().toString().isEmpty() ||
                        etConfirmPW.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty() ||
                        etAddress.getText().toString().isEmpty() || etContact.getText().toString().isEmpty()){

                    Toast.makeText(Customer_Registration.this, "Some fields are empty.", Toast.LENGTH_LONG).show();

                }else{
                    dbh.insertuserdata(customer_account.getcName(), customer_account.getcPassword(),
                            customer_account.getcEmail(), customer_account.getcMobile(),
                            customer_account.getcAddress());

                    AlertDialog.Builder builder = new AlertDialog.Builder(Customer_Registration.this,R.style.MyDialogStyle);
                    builder.setTitle("Action Successful")
                            .setMessage("The action was completed successfully.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                    startActivity(new Intent(Customer_Registration.this, MainActivity.class));

                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }


    //temporary. to replace accordingly later
    public void onClickToAppointmentsView(View v){
       Intent intent = new Intent(Customer_Registration.this, Customer_AppointmentsView.class);
       startActivity(intent);
    }

    public void onClickToHistoryView(View v){
        Intent intent = new Intent(Customer_Registration.this, Customer_ServiceHistoryView.class);
        startActivity(intent);
    }

}