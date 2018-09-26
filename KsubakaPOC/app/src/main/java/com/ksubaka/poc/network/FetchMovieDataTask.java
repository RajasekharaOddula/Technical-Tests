package com.ksubaka.poc.network;

import android.os.AsyncTask;
import android.util.Log;

import com.ksubaka.poc.AppConstants;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class FetchMovieDataTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {
        String response = null;

        try {
            Log.d("FetchMovieDataTask", "doInBackground -> SearchKey : " + URLEncoder.encode(urls[1], "UTF-8"));

            URL url = new URL(AppConstants.BASE_URL + urls[0] + URLEncoder.encode(urls[1], "UTF-8"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Log.d("FetchMovieDataTask", "doInBackground -> getResponseCode : " + connection.getResponseCode());
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                response = convertStreamToString(in);
            }

        } catch (UnsupportedEncodingException e) {
            Log.e("FetchMoviesBySearchTask", "UnsupportedEncoding : " + e.getMessage());
        } catch (MalformedURLException e) {
            Log.e("FetchMoviesBySearchTask", "MalformedURL : " + e.getMessage());
        } catch (IOException e) {
            Log.e("FetchMoviesBySearchTask", "IOException : " + e.getMessage());
        }

        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
