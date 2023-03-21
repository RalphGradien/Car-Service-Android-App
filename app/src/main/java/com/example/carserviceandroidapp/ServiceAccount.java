package com.example.carserviceandroidapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        //Retrieves Service Provider Name from DBHelper
        DBHelper db = new DBHelper(getActivity());
        int spID = ServiceProvider.ServiceProviderID;
        Cursor cursor = db.getServiceProviderName(spID);
        if (cursor.moveToFirst()) {
            String spName = cursor.getString(cursor.getColumnIndexOrThrow("serviceProviderFullName"));
            profileName.setText(spName);
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
