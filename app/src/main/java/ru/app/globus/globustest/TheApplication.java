package ru.app.globus.globustest;

import android.app.Application;

/**
 * Created by Server on 14.01.2016.
 */
public class TheApplication extends Application {

    private static TheApplication application;

    public static TheApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
