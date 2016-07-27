package com.shayan.booking.model.db;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.lang.reflect.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Shayan on 7/27/2016.
 */
@DatabaseTable(tableName = "table_map")
@NoArgsConstructor //needed for ORMLite
@Getter
public class TableMap {

    private static int BOOKED_DEFAULT_POSITION = -1;

    @DatabaseField(id = true)
    private long customerId;

    @DatabaseField
    private String tableMap;

    @DatabaseField(defaultValue = "-1")
    @Getter
    @Setter
    private int bookedTable;

    private Gson gson = new Gson();

    public TableMap(long customerId, boolean[] tables) {
        this.customerId = customerId;
        setTableMap(tables);
        bookedTable = BOOKED_DEFAULT_POSITION;
    }

    public void setTableMap(boolean[] tables) {
        tableMap = gson.toJson(tables);
    }

    public boolean[] getTableMap() {
        Type type = new TypeToken<boolean[]>() {}.getType();
        return gson.fromJson(tableMap, type);
    }

    public boolean isBooked() {
        return bookedTable != BOOKED_DEFAULT_POSITION;
    }

    public void clearBookedTable() {
        bookedTable = BOOKED_DEFAULT_POSITION;
    }
}
