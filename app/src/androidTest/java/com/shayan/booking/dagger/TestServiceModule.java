package com.shayan.booking.dagger;

import android.content.Context;

import com.shayan.booking.application.Config;
import com.shayan.booking.db.DataBaseManager;
import com.shayan.booking.rest.ServiceHelper;
import com.shayan.booking.rest.TestServiceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shayan on 7/27/2016.
 */
@Module
@Singleton
public class TestServiceModule {

    protected Context context;

    public TestServiceModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context providesContext() {
        return context.getApplicationContext();
    }

    @Singleton
    @Provides
    public Config providesConfig(Context context) {
        return new Config(context);
    }

    @Singleton
    @Provides
    public DataBaseManager provideDataBaseManager(Context context) {
        return new DataBaseManager(context);
    }

    @Singleton
    @Provides
    public ServiceHelper provideServiceHelper(Config config) {
        return new TestServiceHelper();
    }
}
