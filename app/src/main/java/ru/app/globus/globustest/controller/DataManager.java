package ru.app.globus.globustest.controller;

import android.os.AsyncTask;

import java.util.List;

import ru.app.globus.globustest.controller.event.IDataCallback;
import ru.app.globus.globustest.controller.handler.SaveTestItemsHandler;
import ru.app.globus.globustest.controller.handler.TestItemsHandler;
import ru.app.globus.globustest.model.TestItem;

/**
 * Created by Server on 14.01.2016.
 */
public class DataManager {

    public static AsyncTask saveTestItem(IDataCallback<Void> callback, List<TestItem> testItems) {
        return new SaveTestItemsHandler(callback, testItems).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }


    public static AsyncTask getTestItem(IDataCallback<List<TestItem>> callback) {
        return new TestItemsHandler(callback).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }
}
