package com.weatherapp.weather;

public interface WeatherView {

    void showProgress();

    void hideProgress();

    void updateTemperature(String temp);
}
