package com.weatherapp.weather;

import com.google.gson.annotations.SerializedName;
import com.weatherapp.model.Clouds;
import com.weatherapp.model.Coordinates;
import com.weatherapp.model.Main;
import com.weatherapp.model.Sys;
import com.weatherapp.model.Weather;
import com.weatherapp.model.Wind;

import java.util.List;

public class WeatherDataModel {

    @SerializedName(value = "coord")
    private Coordinates coordinates;

    private List<Weather> weather;

    private String base;

    private Main main;

    private int visibility;

    private Wind wind;

    private Clouds clouds;

    private String dt;

    private Sys sys;

    @SerializedName("id")
    private int cityId;

    @SerializedName("name")
    private String cityName;

    private String cod;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public int getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public String getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCod() {
        return cod;
    }

    public String getWeatherInfo() {
        StringBuilder stringBuilder = new StringBuilder("<big> City Name : ");
        stringBuilder.append(getCityName()).append("<br><br>");
        if (getMain() != null) {
            stringBuilder.append("<big><big><big><b> <font color=red>").append(getMain().getTemperature())
                    .append(" &deg;C </font> </b></big></big></big> <br><br>")
                    .append("Humidity : ").append(getMain().getHumudity()).append(" % <br><br>")
                    .append("Min Temp : ").append(getMain().getTemp_min()).append(" &deg;C <br><br>")
                    .append("Max Temp : ").append(getMain().getTemp_max()).append(" &deg;C </big><br><br>");
        }
        if (getMain() != null) {
            stringBuilder.append("<big>Wind Speed : ").append(getWind().getSpeed()).append(" MPH </big><br><br>");
        }

        return stringBuilder.toString();
    }

}
