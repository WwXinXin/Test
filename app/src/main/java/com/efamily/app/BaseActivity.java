package com.efamily.app;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * Created by admin on 2017/12/22.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ActivityStackManager2.getInstance().addActivity(this);
        ActivityManagerUtil.pushActivity(new WeakReference<>(this));
        //ActivityStackManager.getInstance().addActivity(new WeakReference<>(this));
    }
}
