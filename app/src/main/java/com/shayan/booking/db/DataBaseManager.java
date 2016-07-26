package com.shayan.booking.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.shayan.booking.model.rest.Customer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Shayan on 6/29/16.
 */
public class DataBaseManager extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "db";
    private static final int DATABASE_VERSION = 1;

    private RuntimeExceptionDao<Customer, Long> customerDao = null;

    public DataBaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Customer.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    @Override
    public void close() {
        super.close();
//        customerDao = null;
    }

    public void clearTable(Class dataClass) {
        SQLiteDatabase db = getWritableDatabase();
        this.connectionSource = new AndroidConnectionSource(db);
        try {
            TableUtils.clearTable(connectionSource, dataClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearTables(Class... dataClasses) {
        SQLiteDatabase db = getWritableDatabase();
        this.connectionSource = new AndroidConnectionSource(db);
        try {
            for (Class cls : dataClasses)
                TableUtils.clearTable(connectionSource, cls);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    region Customer
    private RuntimeExceptionDao<Customer, Long> getCustomerDao() {
        if (customerDao == null) {
            customerDao = getRuntimeExceptionDao(Customer.class);
        }
        return customerDao;
    }

    public List<Customer> getAllCustomers() {
        return getCustomerDao().queryForAll();
    }

    public List<Customer> getMatchedCustomers(String searchWord) {
        try {
            Where<Customer, Long> where = getCustomerDao().queryBuilder().where();

            //searching each word of search query
            String[] parts = searchWord.split("\\s");
            for (int i = 0; i < parts.length; i++) {
                String part = parts[i];
                where.like(Customer.Columns.FNAME, "%".concat(part).concat("%"))
                        .or()
                        .like(Customer.Columns.LNAME, "%".concat(part).concat("%"));

                //if it's not the final loop
                if (i < parts.length - 1)
                    where.and();
            }

            return where.query();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public long getCustomersCount() {
        return getCustomerDao().countOf();
    }

    public void insertOrUpdateCustomer(Customer customer) {
        getCustomerDao().createOrUpdate(customer);
    }

    public void insertOrUpdateCustomers(List<Customer> customers) {
        getCustomerDao().callBatchTasks(() -> {
            for (Customer customer : customers)
                getCustomerDao().createOrUpdate(customer);

            return null;
        });
    }
    //endregion
}
