package com.shayan.booking.model.rest;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Shayan on 7/25/2016.
 */
@DatabaseTable(tableName = "customer")
@NoArgsConstructor //needed for ORMLite
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
public class Customer {
    @DatabaseField(id = true)
    private long id;
    @DatabaseField
    private String customerFirstName;
    @DatabaseField
    private String customerLastName;

    public String getFullName() {
        return customerFirstName.concat(" ").concat(customerLastName);
    }

    public static class Columns{
        public static String FNAME = "customerFirstName";
        public static String LNAME = "customerLastName";
    }
}
