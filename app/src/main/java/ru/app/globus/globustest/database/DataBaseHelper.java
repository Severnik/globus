package ru.app.globus.globustest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Server on 14.01.2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    protected DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTables(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            deleteDataBase(sqLiteDatabase);
            createTables(sqLiteDatabase);
        }
    }

    private void createTables(SQLiteDatabase sqLiteDatabase) {

        String[] QUERIES_CREATE = new String[]{
                DataBaseUtils.CREATE + TableName.TEST_ITEM.getTableName() + "("
                        + DataBaseUtils.TEST_ITEM_ID + " integer primary key autoincrement, "
                        + DataBaseUtils.TEST_ITEM_NAME + " TEXT" + ");",
        };

        if (sqLiteDatabase == null) {
            throw new RuntimeException("Database not available now!");
        }
        for (String query : QUERIES_CREATE) {
            sqLiteDatabase.execSQL(query);
        }
    }

    private void deleteDataBase(SQLiteDatabase sqLiteDatabase) {

    }
}
