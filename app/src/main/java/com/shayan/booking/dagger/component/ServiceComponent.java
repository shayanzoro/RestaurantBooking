package com.shayan.booking.dagger.component;

import com.shayan.booking.dagger.module.ServiceModule;
import com.shayan.booking.viewmodel.CustomerViewModel;
import com.shayan.booking.viewmodel.TablesViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Shayan on 6/15/16.
 */
@Singleton
@Component(modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(CustomerViewModel customerViewModel);

    void inject(TablesViewModel tablesViewModel);

}
