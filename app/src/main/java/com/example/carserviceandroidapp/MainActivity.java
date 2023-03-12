package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DBHelper(this);
        Button btnDummyData = findViewById(R.id.btnDummyData);

        btnDummyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.insertuserdata("John Hancock", "password1", "john123@gmail.com", "9876543210", "123 Main St");
                DB.insertuserdata("Alice Claire", "password2", "alice456@yahoo.com", "1234567890", "456 Oak Ave");
                DB.insertuserdata("Bob Walden", "password3", "bob789@hotmail.com", "5555555555", "789 Elm St");
                DB.insertuserdata("Emily Hirch", "password4", "emily567@outlook.com", "1112223333", "321 Maple St");
                DB.insertuserdata("David Pleat", "password5", "david999@aol.com", "7777777777", "654 Birch Ln");
                DB.insertuserdata("Oliver Pascal", "password6", "oliver246@gmail.com", "3334445555", "987 Pine Rd");
                DB.insertuserdata("Grace Simpson", "password7", "grace135@yahoo.com", "8888888888", "123 Cedar Ave");
                DB.insertuserdata("Alex Grady", "password8", "alex789@outlook.com", "9999999999", "456 Spruce St");
                DB.insertuserdata("Sophia Estevez", "password9", "sophia567@hotmail.com", "4445556666", "789 Sycamore Rd");
                DB.insertuserdata("Ethan Moriaty", "password10", "ethan123@gmail.com", "2223334444", "321 Magnolia Dr");

                DB.insertServiceProvider("password1","Motospot Workshop","Abbosford","Surrey","BC","V43G67","motospot123@gmail.com","5874235");
                DB.insertServiceProvider("password2","AutoPro","Main street","Vancouver","BC","V6A 1C7","autoproservice@gmail.com","604-555-1234");
                DB.insertServiceProvider("password3","Jiffy Lube","Granville street","Vancouver","BC","V6H 3K4","jiffylube@outlook.com","604-555-6789");
                DB.insertServiceProvider("password4","Speedy Auto Service","King street","Toronto","ON","M5V 1K4","speedyservice@hotmail.com","416-555-4321");
                DB.insertServiceProvider("password5","Mr. Lube","Yonge street","Toronto","ON","M4N 3M7","mrlubetoronto@gmail.com","416-555-9876");
                DB.insertServiceProvider("password6","Precision Tune Auto Care","Portage avenue","Winnipeg","MB","R3G 0W4","precisiontunewinnipeg@yahoo.com","204-555-3456");
                DB.insertServiceProvider("password8","Mister Transmission","Regina avenue","Saskatoon","SK","S7N 1B5","mistertranssaskatoon@hotmail.com","306-555-7890");
                DB.insertServiceProvider("password9","Oil Changers","Queen street","Toronto","ON","M4C 1G5","oilchangersontario@gmail.com","416-555-2468");
                DB.insertServiceProvider("password10","Great Canadian Oil Change","Albert street","Regina","SK","S4R 2N3","greatcanadianoilchange@yahoo.ca","306-555-1357");
                DB.insertServiceProvider("password11","Meineke Car Care","St. James street","Winnipeg","MB","R3G 3J6","meinekewinnipeg@outlook.com","204-555-8642");
                Toast.makeText(MainActivity.this, "Insert Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onClickToGilbert(View v){
        Intent intent = new Intent(MainActivity.this, Customer_Registration.class);
        startActivity(intent);
    }


    public void onClickToArifin(View v){
       // Intent intent = new Intent(MainActivity.this, Customer_Registration.class);
      //  startActivity(intent);
    }
}