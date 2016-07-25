package com.shayan.booking.application;


import android.content.Context;

import com.shayan.booking.BuildConfig;

import java.io.IOException;
import java.util.Properties;

import lombok.Getter;

/**
 * Created by Shayan on 6/15/16.
 */
@Getter
public class Config {

    //web service api
    private String apiBaseUrl;

    public Config(Context context) {
        Properties properties = new Properties();
        String configFileName = BuildConfig.DEBUG ? "config.dev.properties" : "config.prod.properties";
        try {
            properties.load(context.getAssets().open(configFileName));
            apiBaseUrl = properties.getProperty("api.base.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
