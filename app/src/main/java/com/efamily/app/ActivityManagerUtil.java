package com.efamily.app;

import android.app.Activity;

import com.efamily.testapp.Base.*;

import java.lang.ref.WeakReference;
import java.util.Stack;

public class ActivityManagerUtil {

    private static Stack<WeakReference<BaseActivity>> activityStack;
    private static ActivityManagerUtil instance;

    public static ActivityManagerUtil getScreenManager() {
        if (instance == null) {
            instance = new ActivityManagerUtil();
        }
        return instance;
    }

    // 退出栈顶Activity
    public static void popActivity(WeakReference<BaseActivity> activity) {
        if (activity != null) {
            // 在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作
            activity.get().finish();
            activityStack.remove(activity);
            activity = null;
        }
    }

    // 退出栈中的指定的Activity
    public static void popActivity(Class<? extends WeakReference<BaseActivity>> clazz) {
        for (int i = 0; i < activityStack.size(); i++) {
            WeakReference<BaseActivity> activity = activityStack.get(i);
            if (activity == null) {
                continue;
            }
            if (activity.getClass().equals(clazz)) {
                popActivity(activity);
            }
        }
    }


    // 获得当前栈顶Activity
    public static  WeakReference<BaseActivity> currentActivity() {
        WeakReference<BaseActivity> activity = null;
        if (!activityStack.empty())
          activity = activityStack.lastElement();
        return activity;
    }

    // 将当前Activity推入栈中
    public static void pushActivity(WeakReference<BaseActivity> activity) {
        if (activityStack == null) {
            activityStack = new Stack<WeakReference<BaseActivity>>();
        }
        activityStack.add(activity);
    }

    // 退出栈中所有Activity
    public static void popAllActivity() {
        while (true) {
            WeakReference<BaseActivity> activity = currentActivity();
            if (activity == null) {
                break;
            }
            popActivity(activity);
        }
    }

    public static void stopAllActivityUnlessOne() {
        popAllActivityExceptOne((Class<? extends WeakReference<BaseActivity>>) currentActivity().getClass());
    }

    // 退出栈中所有Activity, 留下clazz
    public static void popAllActivityExceptOne(Class<? extends WeakReference<BaseActivity>> clazz) {
        for (int i = 0; i < activityStack.size(); i++) {
            WeakReference<BaseActivity> activity = activityStack.get(i);
            if (activity == null) {
                continue;
            }
            if (!activity.getClass().equals(clazz)) {
                popActivity(activity);
            }
        }
    }

    public static int getActivitySize() {
        return activityStack.size();
    }

}
