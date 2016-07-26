package com.shayan.booking.application;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.shayan.booking.R;
import com.shayan.booking.dagger.component.DaggerServiceComponent;
import com.shayan.booking.dagger.component.ServiceComponent;
import com.shayan.booking.dagger.module.ServiceModule;

import lombok.Getter;
import lombok.Setter;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by shayan on 2/28/16.
 */
public class App extends MultiDexApplication {

    @Getter
    @Setter //for Test
    private ServiceComponent serviceComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        initCalligraphy();
    }

    private void initDagger() {
        serviceComponent = DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule(this))
                .build();
    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath(getString(R.string.font_roboto))
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
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