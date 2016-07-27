package com.shayan.booking.rest;

import com.shayan.booking.model.rest.Customer;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface IService {
    @GET("/quandoo-assessment/customer-list.json")
    Observable<List<Customer>> getCustomers();

    @GET("/quandoo-assessment/table-map.json")
    Observable<boolean[]> getTableMap();
}