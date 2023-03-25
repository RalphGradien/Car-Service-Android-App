package com.example.carserviceandroidapp;

import java.util.Date;

public class CustomerApointmentItems {
    int histappointmentIDInt;
    String histbookedServiceProviderName;
    String histserviceAvailed;
    String histbookedServiceProviderAddress;
    String histbookingStatus;
    String histdropoffAppointmentDate;
    String histcustomDropOffTime;
    String histcustomDropOffLoc;
    String histpickupAppointmentDate;
    String histcustomPickupTime;
    String histcustomPickupLoc;
    String histSPPhone;
    String histSPEmail;
    String histAppointType;

    public String getHistcustomDropOffLoc() {
        return histcustomDropOffLoc;
    }

    public void setHistcustomDropOffLoc(String histcustomDropOffLoc) {
        this.histcustomDropOffLoc = histcustomDropOffLoc;
    }

    public String getHistcustomPickupLoc() {
        return histcustomPickupLoc;
    }

    public void setHistcustomPickupLoc(String histcustomPickupLoc) {
        this.histcustomPickupLoc = histcustomPickupLoc;
    }

    public CustomerApointmentItems(int histappointmentIDInt, String histbookedServiceProviderName,
                                   String histserviceAvailed, String histbookedServiceProviderAddress,
                                   String histbookingStatus, String histdropoffAppointmentDate,
                                   String histcustomDropOffTime, String histpickupAppointmentDate,
                                   String histcustomPickupTime, String histcustomDropOffLoc, String histcustomPickupLoc, String histSPPhone,
                                    String histSPEmail, String histAppointType) {
        this.histappointmentIDInt = histappointmentIDInt;
        this.histbookedServiceProviderName = histbookedServiceProviderName;
        this.histserviceAvailed = histserviceAvailed;
        this.histbookedServiceProviderAddress = histbookedServiceProviderAddress;
        this.histbookingStatus = histbookingStatus;
        this.histdropoffAppointmentDate = histdropoffAppointmentDate;
        this.histcustomDropOffTime = histcustomDropOffTime;
        this.histpickupAppointmentDate = histpickupAppointmentDate;
        this.histcustomPickupTime = histcustomPickupTime;
        this.histcustomDropOffLoc = histcustomDropOffLoc;
        this.histcustomPickupLoc = histcustomPickupLoc;
        this.histSPPhone = histSPPhone;
        this.histSPEmail = histSPEmail;
        this.histAppointType = histAppointType;
    }

    public String getHistAppointType() {
        return histAppointType;
    }

    public void setHistAppointType(String histAppointType) {
        this.histAppointType = histAppointType;
    }

    public String getHistSPEmail() {
        return histSPEmail;
    }

    public void setHistSPEmail(String histSPEmail) {
        this.histSPEmail = histSPEmail;
    }

    public String getHistSPPhone() {
        return histSPPhone;
    }

    public void setHistSPPhone(String histSPPhone) {
        this.histSPPhone = histSPPhone;
    }

    public int getHistappointmentIDInt() {
        return histappointmentIDInt;
    }

    public void setHistappointmentIDInt(int histappointmentIDInt) {
        this.histappointmentIDInt = histappointmentIDInt;
    }

    public String getHistbookedServiceProviderName() {
        return histbookedServiceProviderName;
    }

    public void setHistbookedServiceProviderName(String histbookedServiceProviderName) {
        this.histbookedServiceProviderName = histbookedServiceProviderName;
    }

    public String getHistserviceAvailed() {
        return histserviceAvailed;
    }

    public void setHistserviceAvailed(String histserviceAvailed) {
        this.histserviceAvailed = histserviceAvailed;
    }

    public String getHistbookedServiceProviderAddress() {
        return histbookedServiceProviderAddress;
    }

    public void setHistbookedServiceProviderAddress(String histbookedServiceProviderAddress) {
        this.histbookedServiceProviderAddress = histbookedServiceProviderAddress;
    }

    public String getHistbookingStatus() {
        return histbookingStatus;
    }

    public void setHistbookingStatus(String histbookingStatus) {
        this.histbookingStatus = histbookingStatus;
    }

    public String getHistdropoffAppointmentDate() {
        return histdropoffAppointmentDate;
    }

    public void setHistdropoffAppointmentDate(String histdropoffAppointmentDate) {
        this.histdropoffAppointmentDate = histdropoffAppointmentDate;
    }

    public String getHistcustomDropOffTime() {
        return histcustomDropOffTime;
    }

    public void setHistcustomDropOffTime(String histcustomDropOffTime) {
        this.histcustomDropOffTime = histcustomDropOffTime;
    }

    public String getHistpickupAppointmentDate() {
        return histpickupAppointmentDate;
    }

    public void setHistpickupAppointmentDate(String histpickupAppointmentDate) {
        this.histpickupAppointmentDate = histpickupAppointmentDate;
    }

    public String getHistcustomPickupTime() {
        return histcustomPickupTime;
    }

    public void setHistcustomPickupTime(String histcustomPickupTime) {
        this.histcustomPickupTime = histcustomPickupTime;
    }
}
