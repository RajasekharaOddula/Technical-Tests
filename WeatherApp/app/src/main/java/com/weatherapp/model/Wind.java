package com.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class Wind {

    private String speed;

    @SerializedName("deg")
    private String degrees;

    public String getSpeed() {
        return speed;
    }

    public String getDegrees() {
        return degrees;
    }
}
