package com.weatherapp.weather;

import android.os.Handler;

import com.weatherapp.AppConstants;
import com.weatherapp.logging.Logger;
import com.weatherapp.parsing.JSONParser;
import com.weatherapp.presenter.BasePresenter;


public class WeatherPresenter implements BasePresenter {

    protected WeatherView weatherView;
    protected WeatherDataModel mWeatherModel;
    protected Handler handler = new Handler();

    protected Runnable scheduleRunnable = new Runnable() {
        @Override
        public void run() {
            weatherView.showProgress();
            Logger.i("Testing", "Request for Temp updates");

            new WeatherModelTask(WeatherPresenter.this).execute(getCityIdString());

            // Repeat this the same runnable code block again another 5 Mins
            handler.postDelayed(scheduleRunnable, AppConstants.UPDATE_FREQUENCY);

        }
    };

    public WeatherPresenter(WeatherView view) {
        weatherView = view;
    }

    @Override
    public void onStart() {
        handler.post(scheduleRunnable);
    }

    @Override
    public void onDestroy() {
        weatherView = null;
        handler.removeCallbacks(scheduleRunnable);
    }

    @Override
    public WeatherView getBaseView() {
        return weatherView;
    }

    @Override
    public void updateResponse(String response) {
        updateWeather(JSONParser.parseJSON(response, WeatherDataModel.class));
    }

    public void updateWeather(WeatherDataModel model) {
        if (model != null) {
            getBaseView().updateTemperature(model.getWeatherInfo());
        } else {
            getBaseView().hideProgress();
        }
    }

    public String getCityIdString() {
        int cityId = 2637126;
        return "&id=" + cityId;
    }
}
