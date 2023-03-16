package com.example.carserviceandroidapp;

public class ServiceHistoryItems {
    public ServiceHistoryItems() {
    }

    public int getServiceProviderID() {
        return ServiceProviderID;
    }

    public void setServiceProviderID(int serviceProviderID) {
        ServiceProviderID = serviceProviderID;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    int CustomerId;
    int ServiceProviderID;
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
