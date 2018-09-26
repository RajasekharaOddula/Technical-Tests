package com.last.fm.poc.parsing;

import android.util.Log;

import com.google.gson.Gson;
import com.last.fm.poc.AppConfigurations;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class JSONParser {

    public static final String STR_MATCH = "matches";

    public static <T> T parseJSON(String responseJson, Type listType) {
        if (responseJson == null)
            return null;

        T t = null;
        try {
            JSONObject jsonObj = new JSONObject(responseJson);
            JSONObject results = jsonObj.getJSONObject("results");

            String searchTypeString = AppConfigurations.getSearchTypeString();
            JSONObject matches = results.getJSONObject(searchTypeString+STR_MATCH);
            t = new Gson().fromJson(matches.getJSONArray(searchTypeString).toString(), listType);

        } catch (Exception e) {
            Log.e("JSONParsing", "Exception in parseJSON() : " + e.getMessage());
        }
        return t;
    }
}
