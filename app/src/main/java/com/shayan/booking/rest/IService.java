package com.shayan.booking.rest;

import com.shayan.booking.model.rest.Customer;

import retrofit2.http.GET;
import rx.Observable;

public interface IService {
    @GET("/quandoo-assessment/customer-list.json")
    Observable<Customer> getCustomers();

    @GET("/quandoo-assessment/table-map.json")
    Observable<Object> getTableMap();
}