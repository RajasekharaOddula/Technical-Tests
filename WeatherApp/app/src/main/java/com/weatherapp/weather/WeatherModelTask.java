package com.weatherapp.weather;

import com.weatherapp.model.network.FetchDataTask;
import com.weatherapp.presenter.BasePresenter;

import java.lang.ref.WeakReference;

public class WeatherModelTask extends FetchDataTask {

    private final WeakReference<BasePresenter> mPresenter;

    public WeatherModelTask(BasePresenter presenter) {
        this.mPresenter = new WeakReference<>(presenter);
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);

        if (mPresenter.get() != null) {
            mPresenter.get().updateResponse(response);
        }

    }
}
