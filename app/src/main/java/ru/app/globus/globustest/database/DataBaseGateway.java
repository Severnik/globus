package ru.app.globus.globustest.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import ru.app.globus.globustest.TheApplication;
import ru.app.globus.globustest.model.TestItem;

/**
 * Created by Server on 14.01.2016.
 */
public class DataBaseGateway {

    private static final String TAG = DataBaseGateway.class.getSimpleName();
    private static DataBaseGateway instance;
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;
    public static final Object mutex = new Object();

    private DataBaseGateway(Context c) {
        dataBaseHelper = new DataBaseHelper(c, DataBaseUtils.DATABASE_NAME, null);
    }

    public static DataBaseGateway getInstance() {
        if (instance == null) {
            instance = new DataBaseGateway(TheApplication.getInstance());
        }
        return instance;
    }

    public static DataBaseGateway getInstance(Context c) {
        if (instance == null && c != null) {
            instance = new DataBaseGateway(c);
        } else if (instance == null) {
            instance = new DataBaseGateway(TheApplication.getInstance());
        }
        return instance;
    }

    private SQLiteDatabase getWritableDatabase() {
        if (database == null) {
            synchronized (mutex) {
                if (database == null) {
                    database = dataBaseHelper.getWritableDatabase();
                }
            }
        }
        return database;
    }


    private void closeCursor(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }


    public void clearData() {
        getWritableDatabase().delete(TableName.TEST_ITEM.getTableName(), null, null);
    }


    public List<TestItem> getTestItems() {
        LinkedList<TestItem> testItems = new LinkedList<>();
        Cursor cursor = getWritableDatabase().query(TableName.TEST_ITEM.getTableName(),
                null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                testItems.add(DataBaseUtils.parseItem(cursor));
            } while (cursor.moveToNext());
        } else {
            Log.d(TAG, "TestItem table have no TestItems");
        }
        closeCursor(cursor);
        return testItems;
    }


    public long saveTestItem(TestItem item) {
        long id = getWritableDatabase().insert(TableName.TEST_ITEM.getTableName(),
                null,
                DataBaseUtils.getCV(item)
        );
        Log.d(TAG, "Add row to table: " + TableName.TEST_ITEM.getTableName());
        return id;
    }


    public void saveTestItems(List<TestItem> items) {
        ListIterator<TestItem> listIterator = items.listIterator();
        while (listIterator.hasNext()) {
            saveTestItem(listIterator.next());
        }
    }
}
