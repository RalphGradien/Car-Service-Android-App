package com.example.carserviceandroidapp;

public class ServiceHistoryItems {
    public ServiceHistoryItems() {
    }
    String CustomerName;
    String CustomerNumber;
    String CustomerEmail;
    String ServiceCompletedDate;
    String ServicePickupDate;
    String ServiceDropOffDate;
    String ServiceAppointmentStatus;
    String ServiceDetails;

    public String getServiceAppointmentID() {
        return ServiceAppointmentID;
    }

    public void setServiceAppointmentID(String serviceAppointmentID) {
        this.ServiceAppointmentID = serviceAppointmentID;
    }

    String ServiceAppointmentID;
    public String getServiceAppointmentType() {
        return ServiceAppointmentType;
    }

    public void setServiceAppointmentType(String serviceAppointmentType) {
        ServiceAppointmentType = serviceAppointmentType;
    }

    String ServiceAppointmentType;
    // Constructor for "Completed Status"
//    public ServiceHistoryItems(String customerName, String customerNumber, String customerEmail, String serviceCompletedDate, String servicePickupDate, String serviceDropOffDate, String serviceAppointmentStatus, String serviceDetails) {
//        CustomerName = customerName;
//        CustomerNumber = customerNumber;
//        CustomerEmail = customerEmail;
//        ServiceCompletedDate = serviceCompletedDate;
//        ServicePickupDate = servicePickupDate;
//        ServiceDropOffDate = serviceDropOffDate;
//        ServiceAppointmentStatus = serviceAppointmentStatus;
//        ServiceDetails = serviceDetails;
//    }
    public ServiceHistoryItems(String serviceAppointmentID, String customerName, String customerNumber, String customerEmail, String serviceCompletedDate, String servicePickupDate, String serviceDropOffDate, String serviceAppointmentStatus, String serviceDetails, String serviceAppointmentType) {
        ServiceAppointmentID = serviceAppointmentID;
        CustomerName = customerName;
        CustomerNumber = customerNumber;
        CustomerEmail = customerEmail;
        ServiceCompletedDate = serviceCompletedDate;
        ServicePickupDate = servicePickupDate;
        ServiceDropOffDate = serviceDropOffDate;
        ServiceAppointmentStatus = serviceAppointmentStatus;
        ServiceDetails = serviceDetails;
        ServiceAppointmentType = serviceAppointmentType;
    }
    // Constructor for "Cancelled Status"
//    public ServiceHistoryItems(String customerName, String customerNumber, String customerEmail, String serviceAppointmentStatus, String serviceDetails, String serviceDropOffDate) {
//        CustomerName = customerName;
//        CustomerNumber = customerNumber;
//        CustomerEmail = customerEmail;
//        ServiceAppointmentStatus = serviceAppointmentStatus;
//        ServiceDetails = serviceDetails;
//        ServiceDropOffDate = serviceDropOffDate;
//    }
    public ServiceHistoryItems(String serviceAppointmentID, String customerName, String customerNumber, String customerEmail, String serviceAppointmentStatus, String serviceDetails, String serviceDropOffDate, String serviceAppointmentType) {
        ServiceAppointmentID = serviceAppointmentID;
        CustomerName = customerName;
        CustomerNumber = customerNumber;
        CustomerEmail = customerEmail;
        ServiceAppointmentStatus = serviceAppointmentStatus;
        ServiceDetails = serviceDetails;
        ServiceDropOffDate = serviceDropOffDate;
        ServiceAppointmentType = serviceAppointmentType;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerNumber() {
        return CustomerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        CustomerNumber = customerNumber;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public String getServiceCompletedDate() {
        return ServiceCompletedDate;
    }

    public void setServiceCompletedDate(String serviceCompletedDate) {
        ServiceCompletedDate = serviceCompletedDate;
    }

    public String getServicePickupDate() {
        return ServicePickupDate;
    }

    public void setServicePickupDate(String servicePickupDate) {
        ServicePickupDate = servicePickupDate;
    }

    public String getServiceDropOffDate() {
        return ServiceDropOffDate;
    }

    public void setServiceDropOffDate(String serviceDropOffDate) {
        ServiceDropOffDate = serviceDropOffDate;
    }

    public String getServiceAppointmentStatus() {
        return ServiceAppointmentStatus;
    }

    public void setServiceAppointmentStatus(String serviceAppointmentStatus) {
        ServiceAppointmentStatus = serviceAppointmentStatus;
    }

    public String getServiceDetails() {
        return ServiceDetails;
    }

    public void setServiceDetails(String serviceDetails) {
        ServiceDetails = serviceDetails;
    }
}
