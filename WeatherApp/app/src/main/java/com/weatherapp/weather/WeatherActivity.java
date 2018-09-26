package com.weatherapp.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.weatherapp.R;

public class WeatherActivity extends AppCompatActivity implements WeatherView {

    private ProgressBar progressBar;
    private WeatherPresenter presenter;
    private TextView weatherTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherTv = findViewById(R.id.text);
        progressBar = findViewById(R.id.progress);
        presenter = new WeatherPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onStart();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void updateTemperature(String temp) {
        weatherTv.setText(Html.fromHtml(temp));
        hideProgress();
    }
}
