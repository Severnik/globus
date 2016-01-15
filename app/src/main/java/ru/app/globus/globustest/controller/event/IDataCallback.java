package ru.app.globus.globustest.controller.event;

import ru.app.globus.globustest.model.Fail;

/**
 * Created by Server on 14.01.2016.
 */
public interface IDataCallback<E> {

    void onStartTask();

    void onSuccess(E result);

    void onFailure(Fail fail);

    void onFinishTask();
}
