package com.example.carserviceandroidapp;

public class Customer_ServiceHistory_Items {
    int histappointmentIDInt;
    String histbookedServiceProviderName;
    String histserviceAvailed;
    String histbookedServiceProviderAddress;
    String histbookingStatus;
    String histdropoffAppointmentDate;
    String histcustomDropOffTime;
    String histpickupAppointmentDate;
    String histcustomPickupTime;
    String histcustomAppType;

    public Customer_ServiceHistory_Items(int histappointmentIDInt, String histbookedServiceProviderName,
                                         String histserviceAvailed, String histbookedServiceProviderAddress,
                                         String histbookingStatus, String histdropoffAppointmentDate,
                                         String histcustomDropOffTime, String histpickupAppointmentDate,
                                         String histcustomPickupTime, String histcustomAppType) {
        this.histappointmentIDInt = histappointmentIDInt;
        this.histbookedServiceProviderName = histbookedServiceProviderName;
        this.histserviceAvailed = histserviceAvailed;
        this.histbookedServiceProviderAddress = histbookedServiceProviderAddress;
        this.histbookingStatus = histbookingStatus;
        this.histdropoffAppointmentDate = histdropoffAppointmentDate;
        this.histcustomDropOffTime = histcustomDropOffTime;
        this.histpickupAppointmentDate = histpickupAppointmentDate;
        this.histcustomPickupTime = histcustomPickupTime;
        this.histcustomAppType = histcustomAppType;
    }

    public String getHistcustomAppType() {
        return histcustomAppType;
    }

    public void setHistcustomAppType(String histcustomAppType) {
        this.histcustomAppType = histcustomAppType;
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
