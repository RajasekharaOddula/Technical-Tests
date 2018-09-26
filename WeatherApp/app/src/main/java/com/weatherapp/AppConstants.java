package com.weatherapp;

public class AppConstants {
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?APPID=57badc64bb12730b0eaa2666eedbb425&units=metric";

    public static final long UPDATE_FREQUENCY = 1000*60*5; // Milli secs * secs * number of mins
}
