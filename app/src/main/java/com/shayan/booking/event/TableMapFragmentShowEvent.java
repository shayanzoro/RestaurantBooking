package com.shayan.booking.event;

import com.shayan.booking.model.rest.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Shayan on 7/27/2016.
 */
@Getter
@AllArgsConstructor(suppressConstructorProperties = true)
public class TableMapFragmentShowEvent {
    private Customer customer;
}
