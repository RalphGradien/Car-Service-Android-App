package com.example.carserviceandroidapp;

public class ServiceHistoryItems {

    String Servicename;
    String Servicenumber;
    String Serviceemail;
    String ServiceappointmentType;
    String ServiceappointmentStatus;
    String ServicecompletedDate;
    String ServicepickupDate;
    String ServicedropOffDate;

    public ServiceHistoryItems(String servicename, String servicenumber, String serviceemail, String serviceappointmentType, String serviceappointmentStatus, String servicecompletedDate, String servicepickupDate, String servicedropOffDate) {
        Servicename = servicename;
        Servicenumber = servicenumber;
        Serviceemail = serviceemail;
        ServiceappointmentType = serviceappointmentType;
        ServiceappointmentStatus = serviceappointmentStatus;
        ServicecompletedDate = servicecompletedDate;
        ServicepickupDate = servicepickupDate;
        ServicedropOffDate = servicedropOffDate;
    }

    public String getServicename() {
        return Servicename;
    }

    public void setServicename(String servicename) {
        Servicename = servicename;
    }

    public String getServicenumber() {
        return Servicenumber;
    }

    public void setServicenumber(String servicenumber) {
        Servicenumber = servicenumber;
    }

    public String getServiceemail() {
        return Serviceemail;
    }

    public void setServiceemail(String serviceemail) {
        Serviceemail = serviceemail;
    }

    public String getServiceappointmentType() {
        return ServiceappointmentType;
    }

    public void setServiceappointmentType(String serviceappointmentType) {
        ServiceappointmentType = serviceappointmentType;
    }

    public String getServiceappointmentStatus() {
        return ServiceappointmentStatus;
    }

    public void setServiceappointmentStatus(String serviceappointmentStatus) {
        ServiceappointmentStatus = serviceappointmentStatus;
    }

    public String getServicecompletedDate() {
        return ServicecompletedDate;
    }

    public void setServicecompletedDate(String servicecompletedDate) {
        ServicecompletedDate = servicecompletedDate;
    }

    public String getServicepickupDate() {
        return ServicepickupDate;
    }

    public void setServicepickupDate(String servicepickupDate) {
        ServicepickupDate = servicepickupDate;
    }

    public String getServicedropOffDate() {
        return ServicedropOffDate;
    }

    public void setServicedropOffDate(String servicedropOffDate) {
        ServicedropOffDate = servicedropOffDate;
    }
}
