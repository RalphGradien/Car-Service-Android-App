package com.example.carserviceandroidapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
        toKen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogIn.class);
                startActivity(intent);
            }
        });

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

               // DB.insertServiceProvider("password1","Motospot Workshop","Abbosford","Surrey","BC","V4N1E3","motospot@gmail.com");
               DB.insertServiceProvider("password2","AutoPro","Main street","Vancouver","BC","V6A 1C7","autoproservice@gmail.com","604-555-1234","b");
               DB.insertAppointment(1, 1,"03-13-2023", "Surrey", "03-16-2023", "03-17-2023", "Surrey", "03-15-2023", "", "Fluid Replacement", "Completed");
                DB.insertAppointment(2, 1,"04-13-2023", "Burnaby", "04-16-2023", "04-17-2023", "Coquitlam", "04-15-2023", "", "Oil Change", "Completed");
                DB.insertAppointment(3, 1,"05-13-2023", "New Westminster", "05-16-2023", "05-17-2023", "Richmond", "04-15-2023", "", "Oil Change", "Cancelled");
                                DB.insertServiceProvider("password3","Jiffy Lube","Granville street","Vancouver","BC","V6H 3K4","jiffylube@outlook.com","604-555-6789","c");
                DB.insertServiceProvider("password4","Speedy Auto Service","King street","Toronto","ON","M5V 1K4","speedyservice@hotmail.com","416-555-4321","d");
                DB.insertServiceProvider("password5","Mr. Lube","Yonge street","Toronto","ON","M4N 3M7","mrlubetoronto@gmail.com","416-555-9876","e");
                DB.insertServiceProvider("password6","Precision Tune Auto Care","Portage avenue","Winnipeg","MB","R3G 0W4","precisiontunewinnipeg@yahoo.com","204-555-3456","f");
                DB.insertServiceProvider("password8","Mister Transmission","Regina avenue","Saskatoon","SK","S7N 1B5","mistertranssaskatoon@hotmail.com","306-555-7890","g");
                DB.insertServiceProvider("password9","Oil Changers","Queen street","Toronto","ON","M4C 1G5","oilchangersontario@gmail.com","416-555-2468","h");
                DB.insertServiceProvider("password10","Great Canadian Oil Change","Albert street","Regina","SK","S4R 2N3","greatcanadianoilchange@yahoo.ca","306-555-1357","i");
                DB.insertServiceProvider("password11","Meineke Car Care","St. James street","Surrey","MB","R3G 3J6","meinekewinnipeg@outlook.com","204-555-8642","j");

                DB.insertServiceDetail("Full Brake Check","It will include flushing out the old brake fluid and replacing it with new fluid");
                DB.insertServiceDetail("Tire Rotation","It will include swapping the position of the tires to ensure even wear and tear");
                DB.insertServiceDetail("Battery Replacement","It will include removing the old battery and installing a new one");
                DB.insertServiceDetail("Air Filter Replacement","It will include removing the old air filter and installing a new one");
                DB.insertServiceDetail("Wheel Alignment","It will include adjusting the angles of the wheels to ensure they are parallel and perpendicular to the ground");
                DB.insertServiceDetail("Spark Plug Replacement","It will include removing the old spark plugs and installing new ones");
                DB.insertServiceDetail("Coolant Flush","It will include flushing out the old coolant and replacing it with new coolant");
                DB.insertServiceDetail("Transmission Service","It will include draining and refilling the transmission fluid and replacing the filter");
                DB.insertServiceDetail("Fuel Injection Service","It will include cleaning the fuel injectors to improve fuel efficiency and engine performance");

                DB.insertServiceList("SP_1_2",1,2);DB.insertServiceList("SP_1_3",1,3);DB.insertServiceList("SP_1_4",1,4);DB.insertServiceList("SP_1_5",1,5);
                DB.insertServiceList("SP_2_1", 2, 1); DB.insertServiceList("SP_2_2", 2, 2); DB.insertServiceList("SP_2_6", 2, 6); DB.insertServiceList("SP_2_7", 2, 7);
                DB.insertServiceList("SP_3_3", 3, 3);
                DB.insertServiceList("SP_4_5", 4, 5);
                DB.insertServiceList("SP_5_8", 5, 8);
                DB.insertServiceList("SP_6_4", 6, 4);
                DB.insertServiceList("SP_8_9", 8, 9);
                DB.insertServiceList("SP_9_6", 9, 6);
                DB.insertServiceList("SP_10_9", 10, 9);
                Toast.makeText(MainActivity.this, "Insert Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onClickToGilbert(View v){
        Intent intent = new Intent(MainActivity.this, Customer_Registration.class);
        startActivity(intent);
    }


    public void toArifin(View v){
        Intent intent = new Intent(MainActivity.this, CustomerEditProfile.class);
        startActivity(intent);
    }



}