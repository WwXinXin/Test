package com.efamily.testapp;

import android.app.Application;

/**
 * Created by admin on 2017/12/19.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }
}
