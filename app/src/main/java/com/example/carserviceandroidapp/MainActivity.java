package com.example.carserviceandroidapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    DBHelper DB;
    Button toKen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DBHelper(this);
        Button btnDummyData = findViewById(R.id.btnDummyData);
        toKen = findViewById(R.id.toKenneth);
        Button generatePDF = findViewById(R.id.createPDFbtn);

        generatePDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePDF();
            }
        });

        toKen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogIn.class);
                startActivity(intent);
            }
        });
    }
    public void onClickToGilbert(View v){
        Intent intent = new Intent(MainActivity.this, Customer_Registration.class);
        startActivity(intent);
    }
    private void generatePDF() {
        DB.insertuserdata("Kenneth", "password1", "kenleano@gmail.com", "9876543210", "123 Main St");

    }
}