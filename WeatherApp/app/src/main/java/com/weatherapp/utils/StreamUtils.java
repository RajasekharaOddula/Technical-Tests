package com.weatherapp.utils;

import com.weatherapp.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtils {

    private static final String TAG = StreamUtils.class.getSimpleName();

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            Logger.e(TAG, "IOException in convertStreamToString() : " + e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Logger.e(TAG, "IOException-1 in convertStreamToString() : " + e.getMessage());
            }
        }
        return sb.toString();
    }

}
