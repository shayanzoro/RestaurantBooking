package com.shayan.booking.dagger;

import android.content.Context;

import com.shayan.booking.application.Config;
import com.shayan.booking.db.DataBaseManager;
import com.shayan.booking.db.DataBaseManagerMock;
import com.shayan.booking.rest.ServiceHelper;
import com.shayan.booking.rest.ServiceHelperMock;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shayan on 7/27/2016.
 */
@Module
@Singleton
public class ServiceModuleMock {

    protected Context context;

    public ServiceModuleMock(Context context) {
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
        return new DataBaseManagerMock(context);
    }

    @Singleton
    @Provides
    public ServiceHelper provideServiceHelper(Config config) {
        return new ServiceHelperMock();
    }
}
