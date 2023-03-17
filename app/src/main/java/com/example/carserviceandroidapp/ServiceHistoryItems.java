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
    String ServiceAppointmentType;

    public ServiceHistoryItems(String customerName, String customerNumber, String customerEmail, String serviceCompletedDate, String servicePickupDate, String serviceDropOffDate, String serviceAppointmentStatus, String serviceAppointmentType) {
        CustomerName = customerName;
        CustomerNumber = customerNumber;
        CustomerEmail = customerEmail;
        ServiceCompletedDate = serviceCompletedDate;
        ServicePickupDate = servicePickupDate;
        ServiceDropOffDate = serviceDropOffDate;
        ServiceAppointmentStatus = serviceAppointmentStatus;
        ServiceAppointmentType = serviceAppointmentType;
    }

    public ServiceHistoryItems(String customerName, String customerNumber, String customerEmail, String serviceAppointmentStatus, String serviceAppointmentType,String serviceDropOffDate) {
        CustomerName = customerName;
        CustomerNumber = customerNumber;
        CustomerEmail = customerEmail;
        ServiceAppointmentStatus = serviceAppointmentStatus;
        ServiceAppointmentType = serviceAppointmentType;
        ServiceDropOffDate = serviceDropOffDate;
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

    public String getServiceAppointmentType() {
        return ServiceAppointmentType;
    }

    public void setServiceAppointmentType(String serviceAppointmentType) {
        ServiceAppointmentType = serviceAppointmentType;
    }
}
