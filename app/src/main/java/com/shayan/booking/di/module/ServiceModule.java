package com.shayan.booking.di.module;

import android.content.Context;

import com.shayan.booking.application.Config;
import com.shayan.booking.db.DataBaseManager;
import com.shayan.booking.rest.ServiceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shayan on 6/13/16.
 */
@Module
@Singleton
public class ServiceModule {

    private Context context;

    public ServiceModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context providesContext() {
        return context.getApplicationContext();
    }

    @Singleton
    @Provides
    Config providesConfig(Context context) {
        return new Config(context);
    }

    @Singleton
    @Provides
    DataBaseManager provideDataBaseManager(Context context) {
        return new DataBaseManager(context);
    }

    @Singleton
    @Provides
    ServiceHelper provideServiceHelper(Config config) {
        return new ServiceHelper(config);
    }
}
