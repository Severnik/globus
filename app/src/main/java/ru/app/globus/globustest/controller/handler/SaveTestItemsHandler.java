package ru.app.globus.globustest.controller.handler;

import java.util.List;

import ru.app.globus.globustest.controller.event.IDataCallback;
import ru.app.globus.globustest.database.DataBaseGateway;
import ru.app.globus.globustest.model.Response;
import ru.app.globus.globustest.model.TestItem;

/**
 * Created by Server on 14.01.2016.
 */
public class SaveTestItemsHandler extends SimpleAbstractHandler<Void> {
    private List<TestItem> testItems;

    public SaveTestItemsHandler(IDataCallback<Void> callback, List<TestItem> testItems) {
        super(callback);
        this.testItems = testItems;
    }

    @Override
    protected Response<Void> executeRoutine(Void... voids) {
        DataBaseGateway.getInstance().clearData();
        DataBaseGateway.getInstance().saveTestItems(testItems);
        return super.executeRoutine(voids);
    }
}
