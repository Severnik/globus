package ru.app.globus.globustest.controller.handler;

import android.os.AsyncTask;

/**
 * Created by Server on 14.01.2016.
 */
public abstract class AbstractHandler<T> extends AsyncTask<Void, Void, T> {
    @Override
    protected T doInBackground(Void... voids) {
        return executeRoutine(voids);
    }

    protected T executeRoutine(Void... voids) {
        return null;
    }
}
