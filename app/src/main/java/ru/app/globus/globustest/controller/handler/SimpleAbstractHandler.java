package ru.app.globus.globustest.controller.handler;

import ru.app.globus.globustest.controller.event.IDataCallback;
import ru.app.globus.globustest.model.Fail;
import ru.app.globus.globustest.model.Response;

/**
 * Created by Server on 14.01.2016.
 */
public class SimpleAbstractHandler<T> extends AbstractHandler<Response<T>> {
    protected IDataCallback<T> callback;

    public SimpleAbstractHandler(IDataCallback<T> callback) {
        this.callback = callback;
    }

    public void fireStart() {
        if (callback != null) {
            callback.onStartTask();
        }
    }

    public void fireFinish() {
        if (callback != null) {
            callback.onFinishTask();
        }
    }

    @Override
    protected Response<T> doInBackground(Void... voids) {
        Response<T> result = null;
        try {
            result = super.doInBackground(voids);
        } catch (Exception e) {
            result = getFailResponse(e);
        }
        return result;
    }

    protected Response<T> getFailResponse(Exception e) {
        Response<T> result = new Response<T>();
        result.setFail(new Fail(e.getLocalizedMessage(), e));
        return result;
    }

    @Override
    protected void onPostExecute(Response<T> responseObject) {
        super.onPostExecute(responseObject);
        postExecute(responseObject);
        fireFinish();
    }

    protected void postExecute(Response<T> responseObject) {
        fireResult(responseObject);
    }

    public void fireResult(Response<T> response) {
        if (callback != null) {
            if (checkSuccess(response)) {
                callback.onSuccess(response.getResult());
            } else {
                callback.onFailure(response.getFail());
            }
        }
    }

    protected boolean checkSuccess(Response<T> result) {
        return result != null && result.getResult() != null;
    }

}
