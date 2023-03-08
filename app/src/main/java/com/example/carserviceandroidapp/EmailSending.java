package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class EmailSending extends AppCompatActivity {

    private Button sendEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sending);

        sendEmailButton = findViewById(R.id.buttonSendEmail);
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }



    private void sendEmail() {
        String recipientEmail = "davinci8gilbert@yahoo.com";
        String subject = "Reminder";
        String messageBody = "This is a reminder Email";

        // Set up the mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Set up the user credentials
        String username = "gilbertgule82@gmail.com";
        char password = "s";

        // Authenticate the user
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        // Create a mail session with the user credentials
        Session session = Session.getInstance(props, auth);

        // Create a message object
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(messageBody);

            // Send the message
            Transport.send(message);
            Toast.makeText(this, "Email sent", Toast.LENGTH_SHORT).show();
        } catch (AddressException e) {
            e.printStackTrace();
            Toast.makeText(this, "Invalid recipient email address", Toast.LENGTH_SHORT).show();
        } catch (MessagingException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to send email", Toast.LENGTH_SHORT).show();
        }


    }
}