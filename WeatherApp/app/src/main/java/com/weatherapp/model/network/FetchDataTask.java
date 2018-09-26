package com.weatherapp.model.network;

import android.os.AsyncTask;

import com.weatherapp.AppConstants;
import com.weatherapp.logging.Logger;
import com.weatherapp.utils.StreamUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchDataTask extends AsyncTask<String, Void, String> {
    private static final String TAG = FetchDataTask.class.getSimpleName();

    @Override
    protected String doInBackground(String... params) {

        String response = null;

        try {
            URL url = new URL(AppConstants.BASE_URL + params[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Logger.d(TAG, "ResponseCode : " + connection.getResponseCode());
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                response = StreamUtils.convertStreamToString(in);
            }

        } catch (UnsupportedEncodingException e) {
            Logger.e(TAG, "UnsupportedEncodingException : " + e.getMessage());
        } catch (MalformedURLException e) {
            Logger.e(TAG, "MalformedURLException : " + e.getMessage());
        } catch (IOException e) {
            Logger.e(TAG, "IOException : " + e.getMessage());
        }

        return response;
    }

}
