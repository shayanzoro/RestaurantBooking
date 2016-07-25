package com.shayan.booking.util;

import android.util.Log;

public class ShayanLogger {

    private static final String TAG = "Logger: ";
    private static final int LOG_LEVEL = Log.VERBOSE;
    private static final boolean IS_UNDER_DEVELOPMENT = true;

    public static void e(String message) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.ERROR)
                Log.e(TAG, message);
    }

    public static void e(String message, Throwable e) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.ERROR)
                Log.e(TAG, message, e);
    }

    public static void w(String message) {
        if (IS_UNDER_DEVELOPMENT)
            Log.w(TAG, message);
    }

    public static void w(String message, Throwable e) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.WARN)
                Log.w(TAG, message, e);
    }

    public static void i(String message) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.INFO)
                Log.i(TAG, message);
    }

    public static void i(String message, Throwable e) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.INFO)
                Log.i(TAG, message, e);
    }

    public static void d(String message, Throwable e) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.DEBUG)
                Log.d(TAG, message, e);
    }

    public static void d(String message) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.DEBUG)
                Log.d(TAG, message);
    }

    public static void v(String message) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.VERBOSE)
                Log.v(TAG, message);
    }


    //******************

    public static void e(String TAG, String message) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.ERROR)
                Log.e(ShayanLogger.TAG + TAG, message);
    }

    public static void e(String TAG, String message, Throwable e) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.ERROR)
                Log.e(ShayanLogger.TAG + TAG, message, e);
    }

    public static void w(String TAG, String message) {
        if (IS_UNDER_DEVELOPMENT)
            Log.w(ShayanLogger.TAG + TAG, message);
    }

    public static void w(String TAG, String message, Throwable e) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.WARN)
                Log.w(ShayanLogger.TAG + TAG, message, e);
    }

    public static void i(String TAG, String message) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.INFO)
                Log.i(ShayanLogger.TAG + TAG, message);
    }

    public static void i(String TAG, String message, Throwable e) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.INFO)
                Log.i(ShayanLogger.TAG + TAG, message, e);
    }

    public static void d(String TAG, String message, Throwable e) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.DEBUG)
                Log.d(ShayanLogger.TAG + TAG, message, e);
    }

    public static void d(String TAG, String message) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.DEBUG)
                Log.d(ShayanLogger.TAG + TAG, message);
    }

    public static void v(String TAG, String message) {
        if (IS_UNDER_DEVELOPMENT)
            if (LOG_LEVEL <= Log.VERBOSE)
                Log.v(ShayanLogger.TAG + TAG, message);
    }
}

