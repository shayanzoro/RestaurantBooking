package com.shayan.booking.di.component;

import com.shayan.booking.di.module.ServiceModule;
import com.shayan.booking.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Shayan on 6/15/16.
 */
@Singleton
@Component(modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(MainActivity barcodeScanActivity);

}
