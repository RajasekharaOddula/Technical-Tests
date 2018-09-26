package com.weatherapp.parsing;

import android.util.Log;

import com.google.gson.GsonBuilder;

public class JSONParser {

    public static final String STR_MATCH = "matches";

    public static <T> T parseJSON(String responseJson, Class<T> modelType) {
        if (responseJson == null)
            return null;

        T t = null;
        try {
            t = new GsonBuilder().create().fromJson(responseJson, modelType);

        } catch (Exception e) {
            Log.e("JSONParsing", "Exception in parseJSON() : " + e.getMessage());
        }
        return t;
    }
}
