package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

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

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;


public class ForgotPassword extends AppCompatActivity {
    String userPassword = "";

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

                //checks if username or password is empty and creates toast
                DBHelper dbHelper = new DBHelper(ForgotPassword.this);
                //Checks if login details are correct.
                // Returns CUSTOMER, SERVICE_PROVIDED, or NOT_FOUND on loginStatus[0]
                //Returns CustomerID or Service ID on loginStatus[1]
                //Returns password on loginStatus[2]
                String[] loginStatus = dbHelper.checkEmail(recipient);
                if (recipient.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (loginStatus[0].equals("NOT_FOUND")) {
                    Toast.makeText(getApplicationContext(), "Email does not exist", Toast.LENGTH_SHORT).show();
                    return;
                } else if (loginStatus[0].equals("CUSTOMER")) {
                    Toast.makeText(getApplicationContext(), "Email Sent!", Toast.LENGTH_LONG).show();
                    Customer.CustomerID = Integer.parseInt(loginStatus[1]);
                    userPassword = loginStatus[2].toString();
                    sendEmail(recipient);
                    Log.d("CustomerID LOG:", String.valueOf(Customer.CustomerID));
                } else if (loginStatus[0].equals("SERVICE_PROVIDER")) {
                    Toast.makeText(getApplicationContext(), "Email Sent!", Toast.LENGTH_LONG).show();
                    ServiceProvider.ServiceProviderID = Integer.parseInt(loginStatus[1]);
                    userPassword = loginStatus[2].toString();
                    sendEmail(recipient);
                    Log.d("ServiceProviderID LOG:", String.valueOf(ServiceProvider.ServiceProviderID));
                }
            }
        });

    }
    private static final String TAG = "ForgotPassword";
    private void sendEmail(String emailEditText) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String stringReceiverEmail = String.valueOf(emailEditText);
                String host = "smtp.mail.yahoo.com";
                String port = "587";
                String username = "thienphuocufo@yahoo.com.vn";
                String password = "wnvqewwhprkhwrqd";

                Properties props = new Properties();
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", port);
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");

                Session session = Session.getInstance(props,
                        new Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }
                        });
                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(stringReceiverEmail));
                    message.setSubject("Gark App Forgot Password");

                    message.setText("Greetings! " + "\n\nIt looks like you forgot something. Your password is: " + userPassword +
                            " \n\n Cheers!\n");

                    Transport.send(message);
                    Log.i(TAG, "Email sent successfully");
                    userPassword="";
                } catch (MessagingException e) {
                    Log.e(TAG, "Email sending failed: " + e.getMessage());
                }
            }
        }).start();
    }

}