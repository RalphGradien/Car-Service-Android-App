package com.example.carserviceandroidapp;

public class Provider_Appointment_Class {
    int appointmentID;
    String customerName, selectedService, customerAddress, appointmentStatus,
            dropOffDateTime, pickUpDateTime, pickUpLocation, dropOffLocation, customerContact, customerEmail;

    public Provider_Appointment_Class(int appointmentID, String customerName, String customerContact, String customerEmail,
                                      String selectedService, String customerAddress,
                                      String appointmentStatus, String dropOffDateTime,
                                      String pickUpDateTime, String pickUpLocation, String dropOffLocation) {
        this.appointmentID = appointmentID;
        this.customerName = customerName;
        this.customerContact = customerContact;
        this.customerEmail = customerEmail;
        this.selectedService = selectedService;
        this.customerAddress = customerAddress;
        this.appointmentStatus = appointmentStatus;
        this.dropOffDateTime = dropOffDateTime;
        this.pickUpDateTime = pickUpDateTime;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(String selectedService) {
        this.selectedService = selectedService;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(String dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public String getPickUpDateTime() {
        return pickUpDateTime;
    }

    public void setPickUpDateTime(String pickUpDateTime) {
        this.pickUpDateTime = pickUpDateTime;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

}

