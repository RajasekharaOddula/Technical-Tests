package com.krown9.poc.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.krown9.poc.LeagueDetailActivity;
import com.krown9.poc.LeaguesActivity;
import com.krown9.poc.constants.AppConstants;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * This Class is responsible to download json data from the-Sports-db website,
 * parse the response JSON & update it to Leagues List Data
 */

public class FetchDataTask extends AsyncTask<String, Void, String> {

    private final WeakReference<Context> ctContext;
    private final ProgressDialog pDialog;


    public FetchDataTask(Context context) {
        ctContext = new WeakReference<>(context);
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog.show();

    }

    @Override
    protected String doInBackground(String... urls) {
        String response = null;

        try {
            URL url = new URL(AppConstants.BASE_URL + urls[0]);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                response = convertStreamToString(in);

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }

    @Override
    protected void onPostExecute(String responseJson) {
        super.onPostExecute(responseJson);

        if(ctContext.get() instanceof LeaguesActivity) {
            ((LeaguesActivity)ctContext.get()).parseJSON(responseJson);
        } else if(ctContext.get() instanceof LeagueDetailActivity)  {
            ((LeagueDetailActivity)ctContext.get()).parseJSON(responseJson);
        }

        if (pDialog != null && pDialog.isShowing())
            pDialog.dismiss();
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
