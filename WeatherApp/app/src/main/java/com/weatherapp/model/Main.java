package com.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    private String temperature;

    private String pressure;

    private String humidity;

    private String temp_min;

    private String temp_max;

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumudity() {
        return humidity;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }
}
