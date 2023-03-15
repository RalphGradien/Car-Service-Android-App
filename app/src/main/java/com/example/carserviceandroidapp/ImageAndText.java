package com.example.carserviceandroidapp;

public class ImageAndText {
    private String txt;
    private int imageId;
    private String serviceProviderID;

    public ImageAndText(String txt,int imgId, String serviceProviderID){
        this.txt = txt;
        imageId = imgId;
        this.serviceProviderID = serviceProviderID;
    }

    public String getTxt(){
        return txt;
    }

    public int getImageId(){
        return imageId;
    }

    public String getServiceProviderID() {
        return serviceProviderID;
    }

}
