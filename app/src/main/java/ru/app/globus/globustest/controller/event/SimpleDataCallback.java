package ru.app.globus.globustest.controller.event;

import ru.app.globus.globustest.model.Fail;

/**
 * Created by Server on 15.01.2016.
 */
public class SimpleDataCallback<E> implements IDataCallback<E> {
    @Override
    public void onStartTask() {

    }

    @Override
    public void onSuccess(E result) {

    }

    @Override
    public void onFailure(Fail fail) {

    }

    @Override
    public void onFinishTask() {

    }
}
