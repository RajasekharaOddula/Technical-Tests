package com.weatherapp.weather;

import android.os.Handler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class WeatherPresenterTest {

    @Mock
    WeatherView mWeatherView;

    @Mock
    Handler handler;

    private WeatherPresenter mWeatherPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mWeatherPresenter = new WeatherPresenter(Mockito.mock(WeatherView.class));
    }

    @After
    public void tearDown() {
        mWeatherPresenter = null;
    }

    @Test
    public void checkIfShowsProgressOnStart() {
        mWeatherPresenter.onStart();
        mWeatherView.showProgress();
        verify(mWeatherView).showProgress();
        verify(mWeatherView, times(1)).showProgress();
    }

    @Test
    public void checkIfHideProgress() {
        mWeatherView.hideProgress();
        verify(mWeatherView).hideProgress();
    }

    @Test
    public void checkIfViewIsReleasedOnDestroy() {
        mWeatherPresenter.onDestroy();
        assertNull(mWeatherPresenter.getBaseView());
        assertNotNull(mWeatherView);
    }

    @Test
    public void checkIfUpdateResponse_NullResponse() {
        mWeatherPresenter.updateResponse(null);
    }

    @Test
    public void checkIfUpdateResponse_EmptyResponse() {
        mWeatherPresenter.updateResponse("");
    }

    @Test
    public void checkIfUpdateResponse_WrongJsonResponse() {
        mWeatherPresenter.updateResponse("{test:\"wrong json response\"}");
    }

    @Test
    public void checkIfUpdateResponse_InvalidResponse() {
        mWeatherPresenter.updateResponse("Invalid Response test");
    }

    @Test
    public void checkIfDataModelIsNullInUpdateWeather() {
        mWeatherPresenter.updateWeather(null);
    }

    @Test
    public void checkIfDataModelIsEmptyinUpdateWeather() {
        mWeatherPresenter.updateWeather(new WeatherDataModel());
    }


}