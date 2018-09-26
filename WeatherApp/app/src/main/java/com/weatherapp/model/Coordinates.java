package com.weatherapp.model;


import com.google.gson.annotations.SerializedName;

public class Coordinates {

    @SerializedName("lon")
    private String longitude;

    @SerializedName("lat")
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}
