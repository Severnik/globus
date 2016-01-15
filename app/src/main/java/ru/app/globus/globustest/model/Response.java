package ru.app.globus.globustest.model;

/**
 * Created by Server on 14.01.2016.
 */
public class Response<T> {

    private Fail fail;

    private T result;

    public Fail getFail() {
        return fail;
    }

    public void setFail(Fail fail) {
        this.fail = fail;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
