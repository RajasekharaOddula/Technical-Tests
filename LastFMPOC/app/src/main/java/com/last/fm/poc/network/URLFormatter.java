package com.last.fm.poc.network;

import com.last.fm.poc.AppConfigurations;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLFormatter {

    public static String generateURL(String key) {
        StringBuilder url = new StringBuilder(NetworkConstants.BASE_URL);
        if(AppConfigurations.isJsonFormat) {
            url.append("&format=json");
        }
        try {
            url.append("&").append(AppConfigurations.getSearchKey(URLEncoder.encode(key, "UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return url.toString();
    }
}
