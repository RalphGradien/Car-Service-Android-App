package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import javax.mail.Session;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.MessagingException;

import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;

import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.util.Properties;


public class ForgotPassword extends AppCompatActivity {
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        //EditText emailTxt = findViewById(R.id.email);

        Button sendLink = findViewById(R.id.sendLink);



        sendLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailEditText = findViewById(R.id.email);
                String recipient = emailEditText.getText().toString();
                sendEmail(recipient);
                //checks if username or password is empty and creates toast
                DBHelper dbHelper = new DBHelper(ForgotPassword.this);
                //Checks if login details are correct.
                // Returns CUSTOMER, SERVICE_PROVIDED, or NOT_FOUND on loginStatus[0]
                //Returns CustomerID or Service ID on loginStatus[1]
                //Returns password on loginStatus[2]
                String[] loginStatus = dbHelper.checkEmail(recipient);
                if (recipient.isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (loginStatus[0].equals("NOT_FOUND")) {
                    //login details not in Database, show error
                    Toast.makeText(getApplicationContext(), "Email does not exist", Toast.LENGTH_SHORT).show();
                } else if (loginStatus[0].equals("CUSTOMER")) {
                    //Stores ID to global variable
                    Toast.makeText(getApplicationContext(), "Email Sent!", Toast.LENGTH_LONG).show();
                    Customer.CustomerID = Integer.parseInt(loginStatus[1]);
                    password = loginStatus[2].toString();
                    sendEmail(recipient);
                    Log.d("CustomerID LOG:", String.valueOf(Customer.CustomerID));
                }else if (loginStatus[0].equals("SERVICE_PROVIDER")) {
                    //Stores ID to global variable
                    Toast.makeText(getApplicationContext(), "Email Sent!", Toast.LENGTH_LONG).show();
                    ServiceProvider.ServiceProviderID = Integer.parseInt(loginStatus[1]);
                    password = loginStatus[2].toString();
                    sendEmail(recipient);
                    Log.d("ServiceProviderID LOG:", String.valueOf(ServiceProvider.ServiceProviderID));
                    // login service provider successful, start app Service Provider Module
                }
            }

        });
    }
    private void sendEmail(String emailEditText) {
        try {
            String stringSenderEmail = "garkmobileapp@gmail.com";
            String stringReceiverEmail = String.valueOf(emailEditText);
            String stringPasswordSenderEmail = "fpaozvcdwjnosccy";

            String stringHost = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", stringHost);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                }
            });
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));
            mimeMessage.setSubject("Gark App Forgot Password");
            mimeMessage.setText("Greetings! " + ", \n\nYour password is: " + password +
                    " \n\n Cheers!\n");


            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}