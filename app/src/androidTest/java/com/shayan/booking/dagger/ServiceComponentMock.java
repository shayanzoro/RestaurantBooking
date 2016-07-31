package com.shayan.booking.dagger;

import com.shayan.booking.dagger.component.ServiceComponent;
import com.shayan.booking.view.fragment.CustomerFragmentTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Shayan on 7/27/2016.
 */
@Singleton
@Component(modules = ServiceModuleMock.class)
public interface ServiceComponentMock extends ServiceComponent {

    void inject(CustomerFragmentTest customerFragmentTest);

}
