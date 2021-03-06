package com.shayan.booking.rest;

import com.shayan.booking.model.rest.Customer;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by Shayan on 7/27/2016.
 */
public class ServiceHelperMock extends ServiceHelper{

    @Override
    public Observable<List<Customer>> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Shayan", "Aryan"));
        customers.add(new Customer(2, "Shayan", "Aryan"));
        customers.add(new Customer(3, "Shayan", "Aryan"));
        customers.add(new Customer(4, "Shayan", "Aryan"));


        BehaviorSubject<List<Customer>> subject = BehaviorSubject.create();
        subject.onNext(customers);
        return subject;
    }

    @Override
    public Observable<boolean[]> getTableMap(long customerId) {
        boolean[] tables = new boolean[]{false, false, false, false, true, false, false, true, true, false, false, false};
        BehaviorSubject<boolean[]> subject = BehaviorSubject.create();
        subject.onNext(tables);
        return subject;
    }
}
