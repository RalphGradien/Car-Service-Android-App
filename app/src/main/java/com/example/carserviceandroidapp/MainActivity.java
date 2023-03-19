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

                //Insert Customer Data
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

                //Insert Service Provider Data
                DB.insertServiceProvider("password1","Motospot Workshop","Abbosford","Surrey","BC","V43G67","davinci8gilbert@yahoo.com","5874235","a");
                DB.insertServiceProvider("password2","AutoPro","Main street","Vancouver","BC","V6A 1C7","autoproservice@gmail.com","604-555-1234","b");
                DB.insertServiceProvider("password3","Jiffy Lube","Granville street","Vancouver","BC","V6H 3K4","jiffylube@outlook.com","604-555-6789","c");
                DB.insertServiceProvider("password4","Speedy Auto Service","King street","Toronto","ON","M5V 1K4","speedyservice@hotmail.com","416-555-4321","d");
                DB.insertServiceProvider("password5","Mr. Lube","Yonge street","Toronto","ON","M4N 3M7","davinci8gilbert@yahoo.com","416-555-9876","e");
                DB.insertServiceProvider("password6","Precision Tune Auto Care","Portage avenue","Winnipeg","MB","R3G 0W4","precisiontunewinnipeg@yahoo.com","204-555-3456","f");
                DB.insertServiceProvider("password8","Mister Transmission","Regina avenue","Saskatoon","SK","S7N 1B5","mistertranssaskatoon@hotmail.com","306-555-7890","g");
                DB.insertServiceProvider("password9","Oil Changers","Queen street","Toronto","ON","M4C 1G5","oilchangersontario@gmail.com","416-555-2468","h");
                DB.insertServiceProvider("password10","Great Canadian Oil Change","Albert street","Regina","SK","S4R 2N3","greatcanadianoilchange@yahoo.ca","306-555-1357","i");
                DB.insertServiceProvider("password11","Meineke Car Care","St. James street","Surrey","MB","R3G 3J6","meinekewinnipeg@outlook.com","204-555-8642","j");

                //Insert Service Detail Data
                DB.insertServiceDetail("Full Brake Check","It will include flushing out the old brake fluid and replacing it with new fluid");
                DB.insertServiceDetail("Tire Rotation","It will include swapping the position of the tires to ensure even wear and tear");
                DB.insertServiceDetail("Battery Replacement","It will include removing the old battery and installing a new one");
                DB.insertServiceDetail("Air Filter Replacement","It will include removing the old air filter and installing a new one");
                DB.insertServiceDetail("Wheel Alignment","It will include adjusting the angles of the wheels to ensure they are parallel and perpendicular to the ground");
                DB.insertServiceDetail("Spark Plug Replacement","It will include removing the old spark plugs and installing new ones");
                DB.insertServiceDetail("Coolant Flush","It will include flushing out the old coolant and replacing it with new coolant");
                DB.insertServiceDetail("Transmission Service","It will include draining and refilling the transmission fluid and replacing the filter");
                DB.insertServiceDetail("Fuel Injection Service","It will include cleaning the fuel injectors to improve fuel efficiency and engine performance");
                DB.insertServiceDetail("Wheel Replacement","Change of overall wheels");
                DB.insertServiceDetail("Brake Check","Change of overall wheels");

                //Insert Service List Data
                DB.insertServiceList("SP_1_2",1,1);DB.insertServiceList("SP_1_3",1,3);DB.insertServiceList("SP_1_4",1,4);DB.insertServiceList("SP_1_5",1,5);DB.insertServiceList("SP_1_6",1,6);
                DB.insertServiceList("SP_2_1", 2, 1);DB.insertServiceList("SP_2_2", 2, 2); DB.insertServiceList("SP_2_6", 2, 6); DB.insertServiceList("SP_2_7", 2, 7);
                DB.insertServiceList("SP_3_3", 3, 3);DB.insertServiceList("SP_3_1", 3, 1); DB.insertServiceList("SP_3_2", 3, 2);
                DB.insertServiceList("SP_4_5", 4, 5);DB.insertServiceList("SP_4_1", 4, 1); DB.insertServiceList("SP_4_2", 4, 2); DB.insertServiceList("SP_4_3", 4, 3);
                DB.insertServiceList("SP_5_8", 5, 8);DB.insertServiceList("SP_5_7", 5, 7);DB.insertServiceList("SP_5_11", 5, 11);
                DB.insertServiceList("SP_6_4", 6, 4);DB.insertServiceList("SP_6_10", 6, 10);DB.insertServiceList("SP_6_11", 6, 11);
                DB.insertServiceList("SP_7_1", 7, 1);DB.insertServiceList("SP_7_2", 7, 2);DB.insertServiceList("SP_7_3", 7, 3);
                DB.insertServiceList("SP_8_9", 8, 9);DB.insertServiceList("SP_8_8", 8, 8);DB.insertServiceList("SP_8_7", 8, 7);
                DB.insertServiceList("SP_9_6", 9, 6); DB.insertServiceList("SP_9_5", 9, 5); DB.insertServiceList("SP_9_4", 9, 4); DB.insertServiceList("SP_9_3", 9, 3);
                DB.insertServiceList("SP_10_9", 10, 9); DB.insertServiceList("SP_10_8", 10, 8); DB.insertServiceList("SP_10_7", 10, 7);

                //Insert Appointment Data
                DB.insertAppointment(1, 1,"03/13/2023 11:00 AM", "Surrey", "03/21/2023", "03-17-2023 04:00 PM", "123 Main St", "03/15/2023", "", "Drop Off", "Completed");
                DB.insertAppointment(1, 1,"03/15/2023 10:30 AM", "Vancouver", "03/22/2023", "03-19-2023 02:00 PM", "456 Oak St", "03/16/2023", "", "Pick Up", "Completed");
                DB.insertAppointment(2, 1,"03/16/2023 11:00 AM", "Burnaby", "03/23/2023", "03-18-2023 01:00 PM", "789 Maple St", "03/17/2023", "", "Drop Off", "Completed");
                DB.insertAppointment(3, 1,"03/18/2023 12:30 PM", "Vancouver", "03/25/2023", "03-20-2023 10:00 AM", "321 Pine St", "03/19/2023", "", "Pick Up", "Completed");
                DB.insertAppointment(4, 1,"03/20/2023 09:00 AM", "Surrey", "03/27/2023", "03-22-2023 11:00 AM", "654 Elm St", "03/21/2023", "", "Drop Off", "Cancelled");
                DB.insertAppointment(5, 1,"03/22/2023 01:00 PM", "Vancouver", "03/29/2023", "03-24-2023 03:00 PM", "987 Cedar St", "03/23/2023", "", "Pick Up", "Completed");
                DB.insertAppointment(6, 1,"03/23/2023 02:30 PM", "Burnaby", "03/30/2023", "03-26-2023 12:00 PM", "321 Oak St", "03/24/2023", "", "Drop Off", "Cancelled");
                DB.insertAppointment(7, 1,"03/25/2023 10:00 AM", "Vancouver", "04/01/2023", "03-28-2023 09:00 AM", "654 Pine St", "03/26/2023", "", "Pick Up", "Completed");
                DB.insertAppointment(8, 1,"03/27/2023 12:00 PM", "Surrey", "04/03/2023", "03-29-2023 11:00 AM", "987 Elm St", "03/28/2023", "", "Drop Off", "Cancelled");
                DB.insertAppointment(9, 1,"03/29/2023 03:30 PM", "Vancouver", "04/05/2023", "03-31-2023 02:00 PM", "321 Cedar St", "03/30/2023", "", "Pick Up", "Completed");
                DB.insertAppointment(10, 1,"03/31/2023 11:30 AM", "Burnaby", "04/07/2023", "04-02-2023 01:00 PM", "654 Oak St", "04/01/2023", "", "Drop Off", "Completed");
                DB.insertAppointment(1, 5,"", "", "04/22/2023", "04-17-2023 01:00 PM", "456 Oak Ave", "04/15/2023", "", "Pick Up", "Ongoing");
                DB.insertAppointment(1, 8,"05/13/2023 02:30 PM", "789 Elm St", "05/19/2023", "05/19/2023 11:00 AM", "789 Elm St", "04/15/2023", "04/20/2023", "Drop Off", "Completed");
                DB.insertAppointment(1, 3,"05/13/2023 02:30 PM","Granville street, Vancouver, BC, V6H 3K4" , "", "05/17/2023 11:00 AM", "Granville street, Vancouver, BC, V6H 3K4", "04/15/2023", "04/17/2023", "Drop Off", "Completed");
                DB.insertAppointment(5, 6,"05/19/2023 02:30 PM", "654 Birch Ln", "05/19/2023", "05/17/2023 11:00 AM", "654 Birch Ln", "04/15/2023", "", "Drop Off", "Completed");
                DB.insertAppointment(6, 7, "", "Kelowna", "", "2023/03/11 11:30 AM", "Nanaimo", "2023/03/06", "", "Drop Off", "Ongoing");
                DB.insertAppointment(7, 9, "", "Kelowna", "", "2023/03/11 11:30 AM", "Nanaimo", "2023/03/06", "", "Drop Off", "Ongoing");
                DB.insertAppointment(8, 2, "2023/04/02 02:00 PM", "Victoria", "2023/04/02", "2023/04/01 01:30 PM", "Abbotsford", "2023/03/27", "", "Pick Up", "Completed");
                DB.insertAppointment(9, 4, "", "", "", "2023/01/21 01:30 PM", "789 Sycamore Rd", "2023/01/18", "", "Drop Off", "Ongoing");
                DB.insertAppointment(10, 1, "", "", "", "2023/02/11 01:30 PM", "321 Magnolia Dr", "2023/02/06", "2023/02/06", "Drop Off", "Cancelled");

                //Insert Appointment Detail Data
                DB.insertAppointmentDetail(1,"SP_1_2");
                DB.insertAppointmentDetail(2,"SP_5_8");
                DB.insertAppointmentDetail(3,"SP_8_9");
                DB.insertAppointmentDetail(4,"SP_3_1");
                DB.insertAppointmentDetail(5,"SP_6_10");
                DB.insertAppointmentDetail(6,"SP_7_3");
                DB.insertAppointmentDetail(7,"SP_9_5");
                DB.insertAppointmentDetail(8,"SP_2_1");
                DB.insertAppointmentDetail(9,"SP_4_5");
                DB.insertAppointmentDetail(10,"SP_1_6");
                DB.insertAppointmentDetail(11,"SP_1_2");
                DB.insertAppointmentDetail(12,"SP_5_8");
                DB.insertAppointmentDetail(13,"SP_8_9");
                DB.insertAppointmentDetail(14,"SP_3_1");
                DB.insertAppointmentDetail(15,"SP_6_10");
                DB.insertAppointmentDetail(16,"SP_7_3");
                DB.insertAppointmentDetail(17,"SP_9_5");
                DB.insertAppointmentDetail(18,"SP_2_1");
                DB.insertAppointmentDetail(19,"SP_4_5");
                DB.insertAppointmentDetail(20,"SP_1_6");
                DB.insertAppointmentDetail(21,"SP_1_2");
                DB.insertAppointmentDetail(22,"SP_5_8");
                DB.insertAppointmentDetail(23,"SP_8_9");
                DB.insertAppointmentDetail(24,"SP_3_1");
                DB.insertAppointmentDetail(25,"SP_6_10");
                DB.insertAppointmentDetail(26,"SP_7_3");
                DB.insertAppointmentDetail(27,"SP_9_5");
                DB.insertAppointmentDetail(28,"SP_2_1");
                DB.insertAppointmentDetail(29,"SP_4_5");
                DB.insertAppointmentDetail(30,"SP_1_6");
                DB.insertAppointmentDetail(31,"SP_9_5");
                DB.insertAppointmentDetail(32,"SP_2_1");
                DB.insertAppointmentDetail(33,"SP_4_5");
                DB.insertAppointmentDetail(34,"SP_1_6");
                DB.insertAppointmentDetail(35,"SP_1_2");
                DB.insertAppointmentDetail(36,"SP_5_8");
                DB.insertAppointmentDetail(37,"SP_8_9");
                DB.insertAppointmentDetail(38,"SP_3_1");
                DB.insertAppointmentDetail(39,"SP_6_10");
                DB.insertAppointmentDetail(40,"SP_7_3");




                Toast.makeText(MainActivity.this, "Insert Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onClickToGilbert(View v){
        Intent intent = new Intent(MainActivity.this, Customer_Registration.class);
        startActivity(intent);
    }


    public void toArifin(View v){
        Intent intent = new Intent(MainActivity.this, CustomerFindServiceProviderLocation.class);
        startActivity(intent);
    }



}