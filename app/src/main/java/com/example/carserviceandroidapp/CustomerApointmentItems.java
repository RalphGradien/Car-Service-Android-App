package com.example.carserviceandroidapp;

import java.util.Date;

public class CustomerApointmentItems {
    int appointmentIDText;
    String appointmentDate;  //to change later to DateType
    String appointmentTime;   //to change later to TimeType
    String customerName;
    String serviceAvailed;

    public CustomerApointmentItems(int appointmentIDText, String appointmentDate, String appointmentTime, String customerName, String serviceAvailed) {
        this.appointmentIDText = appointmentIDText;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.customerName = customerName;
        this.serviceAvailed = serviceAvailed;
    }

    public int getAppointmentIDText() {
        return appointmentIDText;
    }

    public void setAppointmentIDText(int appointmentIDText) {
        this.appointmentIDText = appointmentIDText;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getServiceAvailed() {
        return serviceAvailed;
    }

    public void setServiceAvailed(String serviceAvailed) {
        this.serviceAvailed = serviceAvailed;
    }
}
