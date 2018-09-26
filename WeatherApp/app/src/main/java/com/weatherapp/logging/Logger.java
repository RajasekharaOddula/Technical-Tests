package com.weatherapp.logging;

import android.util.Log;

public class Logger {

    private static boolean DEBUG = true;

    public static void i(String tag, String message) {
        if (DEBUG)
            Log.i(tag, message);
    }

    public static void d(String tag, String message) {
        if (DEBUG)
            Log.d(tag, message);
    }

    public static void e(String tag, String message) {
        if (DEBUG)
            Log.e(tag, message);
    }
}
