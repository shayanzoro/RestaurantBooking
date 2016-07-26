package com.shayan.booking.application;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.shayan.booking.R;
import com.shayan.booking.di.ServiceComponentBuilder;
import com.shayan.booking.di.component.ServiceComponent;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by shayan on 2/28/16.
 */
public class App extends MultiDexApplication {

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

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}