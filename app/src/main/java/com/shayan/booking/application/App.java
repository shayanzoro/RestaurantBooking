package com.shayan.booking.application;

import android.app.Application;
import android.content.Context;

import com.shayan.booking.R;
import com.shayan.booking.di.ServiceComponentBuilder;
import com.shayan.booking.di.component.ServiceComponent;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by shayan on 2/28/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        initCalligraphy();
    }

    private void initDagger() {
        ServiceComponentBuilder.getInstance(this);
    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath(getString(R.string.font_roboto))
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }

    public ServiceComponent getServiceComponent() {
        return ServiceComponentBuilder.getInstance(this).getServiceComponent();
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }
}