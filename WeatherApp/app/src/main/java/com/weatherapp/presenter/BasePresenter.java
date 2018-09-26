package com.weatherapp.presenter;

import com.weatherapp.weather.WeatherView;

public interface BasePresenter {

    void onStart();

    void onDestroy();

    void updateResponse(String response);

    WeatherView getBaseView();
}
