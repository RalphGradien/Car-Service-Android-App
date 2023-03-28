package com.example.carserviceandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class home extends AppCompatActivity {
    Button login, about;
    DBHelper DB = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        about = findViewById(R.id.aboutBtn);
        login = findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DB.isDatabaseEmpty()) {
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
                    DB.insertServiceProvider("password1", "Motospot Workshop", "Abbosford", "Surrey", "BC", "V43G67", "davinci8gilbert@yahoo.com", "5874235", "a");
                    DB.insertServiceProvider("password2", "AutoPro", "Main street", "Vancouver", "BC", "V6A 1C7", "autoproservice@gmail.com", "604-555-1234", "b");
                    DB.insertServiceProvider("password3", "Jiffy Lube", "Granville street", "Vancouver", "BC", "V6H 3K4", "jiffylube@outlook.com", "604-555-6789", "c");
                    DB.insertServiceProvider("password4", "Speedy Auto Service", "King street", "Toronto", "ON", "M5V 1K4", "speedyservice@gmail.com", "416-555-4321", "d");
                    DB.insertServiceProvider("password5", "Mr. Lube", "Yonge street", "Toronto", "ON", "M4N 3M7", "davinci8gilbert@yahoo.com", "416-555-9876", "e");
                    DB.insertServiceProvider("password6", "Precision Tune Auto Care", "Portage avenue", "Winnipeg", "MB", "R3G 0W4", "precisiontunewinnipeg@yahoo.com", "204-555-3456", "f");
                    DB.insertServiceProvider("password8", "Mister Transmission", "Regina avenue", "Saskatoon", "SK", "S7N 1B5", "mistertranssaskatoon@hotmail.com", "306-555-7890", "g");
                    DB.insertServiceProvider("password9", "Oil Changers", "Queen street", "Toronto", "ON", "M4C 1G5", "oilchangersontario@gmail.com", "416-555-2468", "h");
                    DB.insertServiceProvider("password10", "Great Canadian Oil Change", "Albert street", "Toronto", "ON", "S4R 2N3", "greatcanadianoilchange@yahoo.ca", "306-555-1357", "i");
                    DB.insertServiceProvider("password11", "Meineke Car Care", "St. James street", "Surrey", "MB", "R3G 3J6", "meinekewinnipeg@outlook.com", "204-555-8642", "j");

                    //Insert Service Detail Data
                    DB.insertServiceDetail("Full Brake Check", "It will include flushing out the old brake fluid and replacing it with new fluid");
                    DB.insertServiceDetail("Tire Rotation", "It will include swapping the position of the tires to ensure even wear and tear");
                    DB.insertServiceDetail("Battery Replacement", "It will include removing the old battery and installing a new one");
                    DB.insertServiceDetail("Air Filter Replacement", "It will include removing the old air filter and installing a new one");
                    DB.insertServiceDetail("Wheel Alignment", "It will include adjusting the angles of the wheels to ensure they are parallel and perpendicular to the ground");
                    DB.insertServiceDetail("Spark Plug Replacement", "It will include removing the old spark plugs and installing new ones");
                    DB.insertServiceDetail("Coolant Flush", "It will include flushing out the old coolant and replacing it with new coolant");
                    DB.insertServiceDetail("Transmission Service", "It will include draining and refilling the transmission fluid and replacing the filter");
                    DB.insertServiceDetail("Fuel Injection Service", "It will include cleaning the fuel injectors to improve fuel efficiency and engine performance");
                    DB.insertServiceDetail("Wheel Replacement", "Change of overall wheels");
                    DB.insertServiceDetail("Brake Check", "Change of overall wheels");

                    //Insert Service List Data
                    DB.insertServiceList("SP_1_2", 1, 2);
                    DB.insertServiceList("SP_1_3", 1, 3);
                    DB.insertServiceList("SP_1_4", 1, 4);
                    DB.insertServiceList("SP_1_5", 1, 5);
                    DB.insertServiceList("SP_1_6", 1, 6);

                    DB.insertServiceList("SP_2_1", 2, 1);
                    DB.insertServiceList("SP_2_2", 2, 2);
                    DB.insertServiceList("SP_2_6", 2, 6);
                    DB.insertServiceList("SP_2_7", 2, 7);

                    DB.insertServiceList("SP_3_3", 3, 3);
                    DB.insertServiceList("SP_3_1", 3, 1);
                    DB.insertServiceList("SP_3_2", 3, 2);

                    DB.insertServiceList("SP_4_5", 4, 5);
                    DB.insertServiceList("SP_4_1", 4, 1);
                    DB.insertServiceList("SP_4_2", 4, 2);
                    DB.insertServiceList("SP_4_3", 4, 3);

                    DB.insertServiceList("SP_4_6", 4, 6);
                    DB.insertServiceList("SP_4_7", 4, 7);
                    DB.insertServiceList("SP_4_8", 4, 8);
                    DB.insertServiceList("SP_4_9", 4, 9);


                    DB.insertServiceList("SP_5_8", 5, 8);
                    DB.insertServiceList("SP_5_7", 5, 7);
                    DB.insertServiceList("SP_5_11", 5, 11);

                    DB.insertServiceList("SP_6_4", 6, 4);
                    DB.insertServiceList("SP_6_10", 6, 10);
                    DB.insertServiceList("SP_6_11", 6, 11);

                    DB.insertServiceList("SP_7_1", 7, 1);
                    DB.insertServiceList("SP_7_2", 7, 2);
                    DB.insertServiceList("SP_7_3", 7, 3);

                    DB.insertServiceList("SP_8_9", 8, 9);
                    DB.insertServiceList("SP_8_8", 8, 8);
                    DB.insertServiceList("SP_8_7", 8, 7);

                    DB.insertServiceList("SP_9_6", 9, 6);
                    DB.insertServiceList("SP_9_5", 9, 5);
                    DB.insertServiceList("SP_9_4", 9, 4);
                    DB.insertServiceList("SP_9_3", 9, 3);

                    DB.insertServiceList("SP_10_9", 10, 9);
                    DB.insertServiceList("SP_10_8", 10, 8);
                    DB.insertServiceList("SP_10_7", 10, 7);

                    //Insert Appointment Data
                    DB.insertAppointment(1, 1, "", "", "", "03/17/2023 01:00 PM",
                            "123 Main St", "03/15/2023", "", "Pick Up", "Ongoing");

                    DB.insertAppointment(1, 2, "03/20/2023 02:30 PM", "Abbosford, Surrey, BC, V43G67 ", "03/19/2023", "03/16/2023 11:00 AM",
                            "123 Main St", "03/15/2023", "", "Drop Off", "Completed");
                    DB.insertAppointment(5, 2, "03/20/2023 02:30 PM", "Abbosford, Surrey, BC, V43G67 ", "03/19/2023", "03/16/2023 11:00 AM",
                            "123 Main St", "03/15/2023", "", "Drop Off", "Cancelled");

                    DB.insertAppointment(1, 4, "01/13/2023 03:30 PM", "Granville street, Vancouver, BC, V6H 3K4", "01/11/2023", "01/10/2023 11:00 AM",
                            "Granville street, Vancouver, BC, V6H 3K4", "01/06/2023", "", "Drop Off", "Completed");

                    DB.insertAppointment(1, 4, "02/20/2023 02:30 PM", "Granville street, Vancouver, BC, V6H 3K4", "02/18/2023", "02/17/2023 11:00 AM",
                            "Granville street, Vancouver, BC, V6H 3K4", "02/15/2023", "", "Pick Up", "Completed");

                    DB.insertAppointment(1, 5, "", "", "", "01/17/2023 11:00 AM",
                            "123 Main St", "01/15/2023", "01/18/2023", "Drop Off", "Cancelled");

                    DB.insertAppointment(2, 1, "", "", "", "03-17-2023 01:00 PM",
                            "456 Oak Ave88", "03/11/2023", "", "Pick Up", "Ongoing");

                    DB.insertAppointment(2, 2, "01/20/2023 02:30 PM", "Abbosford, Surrey, BC, V43G67 ", "01/19/2023", "01/16/2023 11:00 AM",
                            "456 Oak Ave88", "01/15/2023", "", "Drop Off", "Completed");

                    DB.insertAppointment(2, 4, "02/19/2023 03:30 PM", "Granville street, Vancouver, BC, V6H 3K4", "02/15/2023", "02/10/2023 11:00 AM",
                            "Granville street, Vancouver, BC, V6H 3K4", "02/05/2023", "", "Drop Off", "Completed");

                    DB.insertAppointment(1, 4, "03/25/2023 02:30 PM", "Granville street, Vancouver, BC, V6H 3K4", "03/19/2023", "03/17/2023 11:00 AM",
                            "Granville street, Vancouver, BC, V6H 3K4", "03/15/2023", "", "Pick Up", "Ongoing");

                    DB.insertAppointment(2, 5, "", "", "", "01/17/2023 11:00 AM",
                            "123 Main St", "01/15/2023", "01/20/2023", "Drop Off", "Cancelled");

                    DB.insertAppointment(3, 1, "", "", "", "03-17-2023 01:00 PM",
                            "789 Elm St", "03/11/2023", "", "Pick Up", "Ongoing");

                    DB.insertAppointment(3, 2, "01/20/2023 02:30 PM", "Abbosford, Surrey, BC, V43G67 ", "01/19/2023", "01/16/2023 11:00 AM",
                            "789 Elm St", "01/15/2023", "", "Drop Off", "Completed");

                    DB.insertAppointment(3, 4, "02/17/2023 03:30 PM", "Granville street, Vancouver, BC, V6H 3K4", "02/15/2023", "02/05/2023 11:00 AM",
                            "Granville street, Vancouver, BC, V6H 3K4", "02/02/2023", "", "Drop Off", "Completed");

                    DB.insertAppointment(3, 4, "03/03/2023 01:30 PM", "Granville street, Vancouver, BC, V6H 3K4", "03/07/2023", "03/11/2023 11:00 AM",
                            "Granville street, Vancouver, BC, V6H 3K4", "03/08/2023", "", "Pick Up", "Ongoing");
                    DB.insertAppointment(5, 4, "03/10/2023 02:30 PM", "Hornby street, Vancouver, BC, 3E4 4S3", "03/13/2023", "03/15/2023 11:30 AM",
                            "Hornby street, Vancouver, BC, 3E4 4S3", "03/08/2023", "", "Pick Up", "Ongoing");
                    DB.insertAppointment(6, 4, "03/15/2023 03:30 PM", "W Pender street, Vancouver, BC, 2H3 3K4", "03/20/2023", "03/22/2023 03:00 PM",
                            "W Pender street, Vancouver, BC, 2H3 3K4", "03/08/2023", "", "Pick Up", "Ongoing");
                    DB.insertAppointment(7, 4, "03/20/2023 04:30 PM", "152st street, Vancouver, BC, J6H 3K4", "03/25/2023", "03/27/2023 04:00 PM",
                            "152st street, Vancouver, BC, J6H 3K4", "03/08/2023", "", "Pick Up", "Ongoing");
                    DB.insertAppointment(8, 4, "03/25/2023 05:30 PM", "King George Avenue, Vancouver, BC, K6V 3K4", "03/27/2023", "03/30/2023 05:00 PM",
                            "King George Avenue, Vancouver, BC, K6V 3K4", "03/08/2023", "", "Pick Up", "Ongoing");
                    DB.insertAppointment(9, 4, "03/30/2023 06:30 PM", "Granville street, Vancouver, BC, S6H 3K6", "04/05/2023", "03/08/2023 06:00 PM",
                            "Granville street, Vancouver, BC, S6H 3K6", "03/08/2023", "", "Pick Up", "Ongoing");
                    DB.insertAppointment(10, 4, "03/25/2023 05:30 PM", "King George Avenue, Vancouver, BC, K6V 3K4", "03/27/2023", "03/30/2023 05:00 PM",
                            "King George Avenue, Vancouver, BC, K6V 3K4", "03/08/2023", "", "Pick Up", "Cancelled");
                    DB.insertAppointment(3, 4, "03/30/2023 06:30 PM", "Granville street, Vancouver, BC, S6H 3K6", "04/05/2023", "03/08/2023 06:00 PM",
                            "Granville street, Vancouver, BC, S6H 3K6", "03/08/2023", "", "Pick Up", "Cancelled");





                    DB.insertAppointment(3, 5, "", "", "", "01/17/2023 11:00 AM",
                            "789 Elm St", "01/14/2023", "01/22/2023", "Drop Off", "Cancelled");

                    //Insert Appointment Detail Data
                    DB.insertAppointmentDetail(1, "SP_1_2");
                    DB.insertAppointmentDetail(2, "SP_2_1");
                    DB.insertAppointmentDetail(3, "SP_4_1");
                    DB.insertAppointmentDetail(4, "SP_4_3");
                    DB.insertAppointmentDetail(5, "SP_5_7");

                    DB.insertAppointmentDetail(6, "SP_1_3");
                    DB.insertAppointmentDetail(7, "SP_2_6");
                    DB.insertAppointmentDetail(8, "SP_4_2");
                    DB.insertAppointmentDetail(9, "SP_4_3");
                    DB.insertAppointmentDetail(10, "SP_5_8");

                    DB.insertAppointmentDetail(11, "SP_1_5");
                    DB.insertAppointmentDetail(12, "SP_2_7");
                    DB.insertAppointmentDetail(13, "SP_4_3");
                    DB.insertAppointmentDetail(14, "SP_4_5");
                    DB.insertAppointmentDetail(15, "SP_5_11");

                    DB.insertAppointmentDetail(16, "SP_4_6");
                    DB.insertAppointmentDetail(17, "SP_4_7");
                    DB.insertAppointmentDetail(18, "SP_4_8");
                    DB.insertAppointmentDetail(19, "SP_4_9");

                    Toast.makeText(home.this, "Insert Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(home.this, LogIn.class));
                }
                else
                    startActivity(new Intent(home.this, LogIn.class));
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,About.class));
            }
        });
    }



}