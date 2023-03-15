package com.example.carserviceandroidapp;

public class Customer_Account {
    String cName;
    String cPassword;
    String cEmail;
    String cMobile;
    String cAddress;

    public Customer_Account(){

    }

    public Customer_Account(String cName, String cPassword, String cEmail, String cMobile, String cAddress) {
        this.cName = cName;
        this.cPassword = cPassword;
        this.cEmail = cEmail;
        this.cMobile = cMobile;
        this.cAddress = cAddress;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getcMobile() {
        return cMobile;
    }

    public void setcMobile(String cMobile) {
        this.cMobile = cMobile;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }
}
