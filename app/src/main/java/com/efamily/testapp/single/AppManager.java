package com.efamily.testapp.single;

import android.content.Context;

/**
 * Created by admin on 2017/12/19.
 */

public class AppManager {
    private static AppManager instance;
    private Context mContext;

    private AppManager(Context mContext) {
        this.mContext = mContext.getApplicationContext();
        //this.mContext = mContext;
    }

    public static AppManager getInstance(Context mContext) {
        if (instance == null) {
            instance = new AppManager(mContext);
        }
        return instance;
    }
}
