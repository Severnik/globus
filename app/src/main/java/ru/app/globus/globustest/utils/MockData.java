package ru.app.globus.globustest.utils;

import java.util.ArrayList;
import java.util.List;


import ru.app.globus.globustest.R;
import ru.app.globus.globustest.TheApplication;
import ru.app.globus.globustest.model.TestItem;


/**
 * Created by Server on 14.01.2016.
 */
public class MockData {

    public static List<TestItem> getMockData() {
        List<TestItem> result = new ArrayList<>();
        String[] arr = TheApplication.getInstance().getString(R.string.loreum).split(" ");
        for (String value : arr) {
            result.add(getData(value));
        }
        return result;
    }

    private static TestItem getData(String value) {
        TestItem data = new TestItem();
        data.setName(value);
        return data;
    }
}
