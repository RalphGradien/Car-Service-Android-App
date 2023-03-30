package com.example.carserviceandroidapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class ServiceAccount extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        Button editProfile = rootView.findViewById(R.id.editProfileBtn);
        TextView profileName = rootView.findViewById(R.id.profileName);


        ImageView imageProfile = rootView.findViewById(R.id.imageView6);

        //Retrieves Service Provider Name from DBHelper
        DBHelper db = new DBHelper(getActivity());


        int spID = ServiceProvider.ServiceProviderID;
        int cID = Customer.CustomerID;

        if(spID >0){
            Cursor cursor = db.getServiceProviderName(spID);
            cursor.moveToFirst();
            Cursor imageCursor = db.getServiceProviderImage(spID);
            imageCursor.moveToFirst();

            String imageLetter = imageCursor.getString(0);
            int image = 0;

            switch(imageLetter){
                case "a":
                    image = com.example.gark.R.drawable.a;
                    break;
                case "b":
                    image = com.example.gark.R.drawable.b;
                    break;
                case "c":
                    image = com.example.gark.R.drawable.c;
                    break;
                case "d":
                    image = com.example.gark.R.drawable.d;
                    break;
                case "e":
                    image = com.example.gark.R.drawable.e;
                    break;
                case "f":
                    image = com.example.gark.R.drawable.f;
                    break;
                case "g":
                    image = com.example.gark.R.drawable.g;
                    break;
                case "h":
                    image = com.example.gark.R.drawable.h;
                    break;
            }
            profileName.setText(cursor.getString(0));
            imageProfile.setImageResource(image);
        }

        if(cID> 0){
            Cursor custCursor = db.getCustomerName(cID);
            custCursor.moveToFirst();
            Cursor imageCursor = db.getServiceProviderImage(spID);
            imageCursor.moveToFirst();

            Random random = new Random();
            char randomChar = (char) (random.nextInt(8) + 'a');
            int image = 0;

            switch(randomChar) {
                case 'a':
                    image = R.drawable.c1;
                    break;
                case 'b':
                    image = R.drawable.c2;
                    break;
                case 'c':
                    image = R.drawable.c3;
                    break;
                case 'd':
                    image = R.drawable.c4;
                    break;
                case 'e':
                    image = R.drawable.c5;
                    break;
                case 'f':
                    image = R.drawable.c6;
                    break;
                case 'g':
                    image = R.drawable.c7;
                    break;
                case 'h':
                    image = R.drawable.c8;
                    break;
            }
            profileName.setText(custCursor.getString(0));
            imageProfile.setImageResource(image);
        }

        Button logOut = rootView.findViewById(R.id.logOut);


        //Logs out user, clearing global ID variables.
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer.CustomerID = 0;
                ServiceProvider.ServiceProviderID =0;
                Log.d("ServiceProviderID LOG:", String.valueOf(ServiceProvider.ServiceProviderID));
                Log.d("ServiceProviderID LOG:", String.valueOf( Customer.CustomerID));
                startActivity(new Intent(rootView.getContext(), LogIn.class));
            }
        });

        //Starts EditProfileActivity for Service Provider
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( ServiceProvider.ServiceProviderID == 0){
                    startActivity(new Intent(rootView.getContext(), CustomerEditProfile.class)); }
                else {
                    startActivity(new Intent(rootView.getContext(), Provider_EditProfile.class));
                }

            }
        });
        return rootView;
    }


}
