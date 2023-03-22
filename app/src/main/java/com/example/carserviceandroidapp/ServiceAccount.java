package com.example.carserviceandroidapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
        Cursor cursor = db.getServiceProviderName(spID);
        cursor.moveToFirst();
        Cursor custCursor = db.getCustomerName(cID);
        custCursor.moveToFirst();

        Cursor imageCursor = db.getServiceProviderImage(spID);
        imageCursor.moveToFirst();

        String imageLetter=imageCursor.getString(0);


        int image = 0;
        if(imageLetter .equals("a")){
            image = com.example.gark.R.drawable.a;
        }
        if(imageLetter .equals("b")){
            image = com.example.gark.R.drawable.b;
        }
        if(imageLetter .equals("c")){
            image = com.example.gark.R.drawable.c;
        }
        if(imageLetter .equals("d")){
            image = com.example.gark.R.drawable.d;
        }
        if(imageLetter .equals("e")){
            image = com.example.gark.R.drawable.e;
        }

        if(imageLetter .equals("f")){
            image = com.example.gark.R.drawable.f;
        }
        if(imageLetter .equals("g")){
            image = com.example.gark.R.drawable.g;
        }
        if(imageLetter .equals("h")){
            image = com.example.gark.R.drawable.h;
        }

        if(spID>0){
            profileName.setText(cursor.getString(0));
            imageProfile.setImageResource(image);
        }
        else if (cID>0){
            profileName.setText(custCursor.getString(0));
        }


        Button logOut = rootView.findViewById(R.id.logOut);


        //Logs out user, clearing global ID variables.
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer.CustomerID = 0;
                ServiceProvider.ServiceProviderID =0;
                startActivity(new Intent(rootView.getContext(), LogIn.class));
            }
        });

        //Starts EditProfileActivity for Service Provider
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(rootView.getContext(), Provider_EditProfile.class));
            }
        });
        return rootView;
    }


}
