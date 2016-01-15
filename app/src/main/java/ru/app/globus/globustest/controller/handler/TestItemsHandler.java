package ru.app.globus.globustest.controller.handler;

import java.util.List;

import ru.app.globus.globustest.TheApplication;
import ru.app.globus.globustest.controller.event.IDataCallback;
import ru.app.globus.globustest.database.DataBaseGateway;
import ru.app.globus.globustest.model.Response;
import ru.app.globus.globustest.model.TestItem;

/**
 * Created by Server on 14.01.2016.
 */
public class TestItemsHandler extends SimpleAbstractHandler<List<TestItem>>{

    public TestItemsHandler(IDataCallback<List<TestItem>> callback) {
        super(callback);
    }

    @Override
    protected Response<List<TestItem>> executeRoutine(Void... voids) {
        Response<List<TestItem>> response = new Response<>();
        response.setResult(DataBaseGateway.getInstance().getTestItems());
        return response;
    }
}
