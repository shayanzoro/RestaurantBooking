package com.shayan.booking.model.rest;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Shayan on 9/12/16.
 */
@Getter
@Setter
public class Hotel {
    private String title;
    private int rate;
    private String imageUrl;
    private boolean isApartment;

    public Hotel(String title, int rate, String imageUrl) {
        this.title = title;
        this.rate = rate;
        this.imageUrl = imageUrl;
    }
}
