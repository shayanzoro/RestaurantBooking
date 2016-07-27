package com.shayan.booking.model.rest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class CustomerTest {
    @Test
    public void fullNameIsCorrect() throws Exception {
        String fName = "Shayan";
        String lName = "Aryan";
        Customer customer = new Customer(1, fName, lName);
        String expectedResult = fName.concat(" ").concat(lName);
        assertEquals("Full name is not correct", expectedResult, customer.getFullName());
    }
}