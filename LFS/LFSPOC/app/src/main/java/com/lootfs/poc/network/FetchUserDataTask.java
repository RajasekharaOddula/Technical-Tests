package com.lootfs.poc.network;

import android.os.AsyncTask;
import android.util.Log;

import com.lootfs.poc.AppConstants;
import com.lootfs.poc.UserListAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class FetchUserDataTask extends AsyncTask<Void, Void, List<String>> {
    private final WeakReference<UserListAdapter> mUserListAdapter;

    public FetchUserDataTask(UserListAdapter userListAdapter) {
        this.mUserListAdapter = new WeakReference<>(userListAdapter);
    }

    @Override
    protected List<String> doInBackground(Void... urls) {
        String response = null;

        try {
            URL url = new URL(AppConstants.USERS_URL);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            Log.d("FetchUserDataTask", "doInBackground -> ResponseCode : " + connection.getResponseCode());
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                response = convertStreamToString(in);
            }

            connection.disconnect();
        } catch (MalformedURLException e) {
            Log.e("FetchUserDataTask", "MalformedURL : " + e.getMessage());
        } catch (IOException e) {
            Log.e("FetchUserDataTask", "IOException : " + e.getMessage());
        }

        return parseJSONString(response);
    }

    @Override
    protected void onPostExecute(List<String> users) {
        super.onPostExecute(users);

        if (mUserListAdapter.get() != null && users != null) {
            mUserListAdapter.get().updateUsersList(users);
        }
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
            Log.e("FetchUserDataTask", "IOException - convertStreamToString() : " + e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e("FetchUserDataTask", "IOException - convertStreamToString()-1 : " + e.getMessage());
            }
        }
        return sb.toString();
    }

    private List<String> parseJSONString(String responseJson) {
        if (responseJson == null)
            return null;

        List<String> userNames = new ArrayList<>();
        try {
            Log.i("FetchUserDataTask", "UsersResponse : " + responseJson);
            JSONArray users = new JSONArray(responseJson);

            for (int index = 0; index < users.length(); index++) {
                JSONObject user = users.getJSONObject(index);
                userNames.add(user.getString("name"));
            }

        } catch (Exception e) {
            Log.e("FetchUserDataTask", "Exception - parseJSON() : " + e.getMessage());
        }
        return userNames;
    }
}
