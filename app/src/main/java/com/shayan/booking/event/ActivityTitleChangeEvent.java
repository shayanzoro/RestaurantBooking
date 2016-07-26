package com.shayan.booking.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Shayan on 7/26/2016.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
public class ActivityTitleChangeEvent {
    //can be string res or String itself
    @Getter
    private Object title;

}
