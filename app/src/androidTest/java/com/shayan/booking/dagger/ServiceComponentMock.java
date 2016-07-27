package com.shayan.booking.dagger;

import com.shayan.booking.dagger.component.ServiceComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Shayan on 7/27/2016.
 */
@Singleton
@Component(modules = ServiceModuleMock.class)
public interface ServiceComponentMock extends ServiceComponent {

}
