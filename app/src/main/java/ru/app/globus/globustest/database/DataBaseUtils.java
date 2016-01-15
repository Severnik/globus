package ru.app.globus.globustest.database;

import android.content.ContentValues;
import android.database.Cursor;

import ru.app.globus.globustest.model.TestItem;

/**
 * Created by Server on 14.01.2016.
 */
public class DataBaseUtils {
    public static String DATABASE_NAME = "globustest_db";
    public static final String CREATE = "CREATE TABLE ";

    public static final String TEST_ITEM_ID = "id";
    public static final String TEST_ITEM_NAME = "name";


    public static TestItem parseItem(Cursor cursor) {
        TestItem model = new TestItem();
        model.setName(cursor.getString(cursor.getColumnIndex(TEST_ITEM_NAME)));
        return model;
    }

    public static ContentValues getCV(TestItem item) {
        ContentValues cv = new ContentValues();
        cv.put(TEST_ITEM_NAME, item.getName());
        return cv;
    }
}
