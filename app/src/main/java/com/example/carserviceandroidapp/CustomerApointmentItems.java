package com.example.carserviceandroidapp;

import java.util.Date;

public class CustomerApointmentItems {
    int appointmentIDText;
    String appointmentDate;  //to change later to DateType
    String appointmentTime;   //to change later to TimeType
    String customerName;
    String bookedServiceProviderName;
    String bookedServiceProviderAddress;
    String serviceAvailed;
    String bookingStatus;

    public CustomerApointmentItems(int appointmentIDText, String appointmentDate, String appointmentTime, String customerName, String bookedServiceProviderName, String bookedServiceProviderAddress, String serviceAvailed, String bookingStatus) {
        this.appointmentIDText = appointmentIDText;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.customerName = customerName;
        this.bookedServiceProviderName = bookedServiceProviderName;
        this.bookedServiceProviderAddress = bookedServiceProviderAddress;
        this.serviceAvailed = serviceAvailed;
        this.bookingStatus = bookingStatus;
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

    public String getBookedServiceProviderName() {
        return bookedServiceProviderName;
    }

    public void setBookedServiceProviderName(String bookedServiceProviderName) {
        this.bookedServiceProviderName = bookedServiceProviderName;
    }

    public String getBookedServiceProviderAddress() {
        return bookedServiceProviderAddress;
    }

    public void setBookedServiceProviderAddress(String bookedServiceProviderAddress) {
        this.bookedServiceProviderAddress = bookedServiceProviderAddress;
    }

    public String getServiceAvailed() {
        return serviceAvailed;
    }

    public void setServiceAvailed(String serviceAvailed) {
        this.serviceAvailed = serviceAvailed;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
