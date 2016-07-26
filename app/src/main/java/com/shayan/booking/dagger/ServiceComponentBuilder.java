package com.shayan.booking.dagger;

import android.content.Context;

import com.shayan.booking.dagger.component.DaggerServiceComponent;
import com.shayan.booking.dagger.component.ServiceComponent;
import com.shayan.booking.dagger.module.ServiceModule;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Shayan on 6/15/16.
 */
public class ServiceComponentBuilder {

    private static ServiceComponentBuilder instance;

    @Getter
    @Setter
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
