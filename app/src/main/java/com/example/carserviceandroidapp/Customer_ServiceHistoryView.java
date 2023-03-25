package com.example.carserviceandroidapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

public class Customer_ServiceHistoryView extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DBHelper dbh = new DBHelper(getActivity());
        View view = inflater.inflate(R.layout.activity_customer_service_history_view, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.customer_history_recyclerview);

        List<Customer_ServiceHistory_Items> customer_serviceHistory_itemsList = new ArrayList<Customer_ServiceHistory_Items>();
        List<Customer_ServiceHistory_Items> customer_serviceHistory_items_noOngoing = new ArrayList<Customer_ServiceHistory_Items>();

        int userId = Customer.CustomerID;
        Cursor cursorAppointment = dbh.getAppointment();
        Cursor cursorServiceProvider = dbh.getServiceProviderDataAll();
        Cursor cursorAppDetail = dbh.getAppointmentDetail();
        Cursor cursorServiceList = dbh.getServiceList();
        Cursor cursorServiceDetail = dbh.getServiceDetails();

        String spName = "";
        String spStreet = "";
        String spCity = "";
        String spProvince = "";
        String spPostal = "";
        String spPhone = "";
        String spEmail = "";
        String serviceAvailed = "";
        String serviceListID = "";
        int serviceDetailID = 0;
        int appID = 0;
        int appSPID = -1;

        try {
            if (cursorAppointment.getCount() > 0) {
                while (cursorAppointment.moveToNext()) {
                    if (cursorAppointment.getInt(cursorAppointment.getColumnIndexOrThrow("Userid")) == userId) {
                        appID = cursorAppointment.getInt(cursorAppointment.getColumnIndexOrThrow("AppointmentID"));
                        appSPID = cursorAppointment.getInt(cursorAppointment.getColumnIndexOrThrow("ServiceProviderID"));

                        if (cursorServiceProvider.getCount() > 0) {
                            cursorServiceProvider.moveToPosition(-1);
                            while (cursorServiceProvider.moveToNext()) {
                                if (cursorServiceProvider.getInt(cursorServiceProvider.getColumnIndexOrThrow("ServiceProviderID")) == appSPID) {
                                    spName = cursorServiceProvider.getString(cursorServiceProvider.getColumnIndexOrThrow("serviceProviderFullName"));
                                    spStreet = cursorServiceProvider.getString(cursorServiceProvider.getColumnIndexOrThrow("street"));
                                    spCity = cursorServiceProvider.getString(cursorServiceProvider.getColumnIndexOrThrow("city"));
                                    spProvince = cursorServiceProvider.getString(cursorServiceProvider.getColumnIndexOrThrow("province"));
                                    spPostal = cursorServiceProvider.getString(cursorServiceProvider.getColumnIndexOrThrow("postalCode"));
                                    spPhone = cursorServiceProvider.getString(cursorServiceProvider.getColumnIndexOrThrow("phone"));
                                    spEmail = cursorServiceProvider.getString(cursorServiceProvider.getColumnIndexOrThrow("email"));
                                }
                            }
                        }

                        String spAddress = spStreet + ", " + spCity + ", " + spProvince + " " + spPostal;
                        String appStatus = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("AppointmentStatus"));
                        String dropOffDT = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("DropOffTimeDate"));
                        String dropOffLoc = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("DropOffLocation"));
                        String pickupDT = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("PickUpDateTime"));
                        String pickupLoc = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("PickUpLocation"));
                        String appType = cursorAppointment.getString(cursorAppointment.getColumnIndexOrThrow("AppointmentType"));
//
                        if (cursorAppDetail.getCount() > 0) {
                            cursorAppDetail.moveToPosition(-1);
                            while (cursorAppDetail.moveToNext()) {
                                if (cursorAppDetail.getInt(cursorAppDetail.getColumnIndexOrThrow("AppointmentID")) == appID) {
                                    serviceListID = cursorAppDetail.getString(cursorAppDetail.getColumnIndexOrThrow("ServiceListID"));
                                }
                            }
                        }
                        if (cursorServiceList.getCount() > 0) {
                            cursorServiceList.moveToPosition(-1);
                            while (cursorServiceList.moveToNext()) {
                                if (cursorServiceList.getString(cursorServiceList.getColumnIndexOrThrow("ServiceListID")).equals(serviceListID)) {
                                    serviceDetailID = cursorServiceList.getInt(cursorServiceList.getColumnIndexOrThrow("ServiceDetailID"));
                                }
                            }
                        }
                        if (cursorServiceDetail.getCount() > 0) {
                            cursorServiceDetail.moveToPosition(-1);
                            while (cursorServiceDetail.moveToNext()) {
                                if (cursorServiceDetail.getInt(cursorServiceDetail.getColumnIndexOrThrow("ServiceDetailID")) == serviceDetailID) {
                                    serviceAvailed = cursorServiceDetail.getString(cursorServiceDetail.getColumnIndexOrThrow("ServiceName"));
                                }
                            }
                        }
                        customer_serviceHistory_itemsList.add(new Customer_ServiceHistory_Items(appID, spName, serviceAvailed, spAddress, appStatus, dropOffDT, "", pickupDT, "",appType));

                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        for (Customer_ServiceHistory_Items historyItem : customer_serviceHistory_itemsList) {
            if (!historyItem.histbookingStatus.equals("Ongoing")) {
                customer_serviceHistory_items_noOngoing.add(historyItem);
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new Customer_ServiceHistory_Adapter(getActivity(), customer_serviceHistory_items_noOngoing));
        return view;
    }

}
