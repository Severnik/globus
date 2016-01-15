package ru.app.globus.globustest.database;

/**
 * Created by Server on 14.01.2016.
 */
public enum TableName {

    TEST_ITEM("test_item");

    private String tableKey;

    TableName(String tableKey) {
        this.tableKey = tableKey;
    }

    public String getTableName() {
        return tableKey;
    }
}
