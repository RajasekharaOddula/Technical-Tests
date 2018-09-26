package com.last.fm.poc.network;

import android.os.AsyncTask;
import android.util.Log;

import com.last.fm.poc.listeners.UpdateEventListener;
import com.last.fm.poc.utils.StringUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchSearchDataTask extends AsyncTask<String, Void, String> {
    private final WeakReference<UpdateEventListener> updateEventListener;

    public FetchSearchDataTask(UpdateEventListener updateListener) {
        this.updateEventListener = new WeakReference<>(updateListener);
    }

    @Override
    protected String doInBackground(String... searchkey) {
        String response = null;

        try {
            Log.i("FetchSearchDataTask", "doInBackground -> SearchKey : " + URLFormatter.generateURL(searchkey[0]));

            URL url = new URL(URLFormatter.generateURL(searchkey[0]));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Log.i("FetchSearchDataTask", "doInBackground -> getResponseCode : " + connection.getResponseCode());
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                response = StringUtils.convertStreamToString(in);
            }

        } catch (MalformedURLException e) {
            Log.e("FetchSearchDataTask", "MalformedURL : " + e.getMessage());
        } catch (IOException e) {
            Log.e("FetchSearchDataTask", "IOException : " + e.getMessage());
        }

        return response;
    }

    @Override
    protected void onPostExecute(String data) {
        super.onPostExecute(data);

        if (updateEventListener.get() != null && data != null) {
            updateEventListener.get().updateEvent(data);
        }
    }
}
