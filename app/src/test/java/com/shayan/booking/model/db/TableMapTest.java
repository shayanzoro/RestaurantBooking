package com.shayan.booking.model.db;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TableMapTest {
    @Test
    public void tablesAreCorrectlySaved() throws Exception {
        boolean[] tables = new boolean[]{true, false, true, true, true, false};
        TableMap tableMap = new TableMap(1, tables);
        assertTrue("Tables map is not correctly saved", Arrays.equals(tables, tableMap.getTableMap()));
    }
}