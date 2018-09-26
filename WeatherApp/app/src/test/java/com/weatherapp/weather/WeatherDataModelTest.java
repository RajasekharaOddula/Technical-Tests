package com.weatherapp.weather;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class WeatherDataModelTest {

    private WeatherDataModel mWeatherDataModel;

    @Before
    public void setUp() {
        mWeatherDataModel = new WeatherDataModel();
    }

    @After
    public void tearDown() {
        mWeatherDataModel = null;
    }

    @Test
    public void getWeatherInfo() {
        assertNotNull(mWeatherDataModel);
    }

    @Test
    public void getWeatherInfo_nullCheck() {
        assertNotNull(mWeatherDataModel.getWeatherInfo());
    }
}