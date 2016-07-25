package com.shayan.booking.di;

import android.content.Context;

import com.shayan.booking.di.component.DaggerServiceComponent;
import com.shayan.booking.di.component.ServiceComponent;
import com.shayan.booking.di.module.ServiceModule;

import lombok.Getter;

/**
 * Created by Shayan on 6/15/16.
 */
public class ServiceComponentBuilder {

    private static ServiceComponentBuilder instance;

    @Getter
    private ServiceComponent serviceComponent;

    public static ServiceComponentBuilder getInstance(Context context) {
        if (instance == null) {
            instance = new ServiceComponentBuilder(context);
        }
        return instance;
    }

    public ServiceComponentBuilder(Context context) {
        init(context);
    }

    private void init(Context context) {
        serviceComponent=DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule(context))
                .build();
    }
}
