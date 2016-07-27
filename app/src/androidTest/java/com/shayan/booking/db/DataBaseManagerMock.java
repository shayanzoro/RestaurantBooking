package com.shayan.booking.db;

import android.content.Context;

/**
 * Created by Shayan on 7/27/2016.
 * This class is responsible for separating test database from original one
 */
public class DataBaseManagerMock extends DataBaseManager {

    private static final String DATABASE_NAME = "db_test";

    public DataBaseManagerMock(Context context) {
        super(context, DATABASE_NAME);
    }
}
