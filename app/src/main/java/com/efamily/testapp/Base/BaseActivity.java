package com.efamily.testapp.Base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

import com.efamily.testapp.R;
import com.efamily.testapp.util.AndroidBug5497Workaround;
import com.efamily.testapp.util.AndroidBugWorkaround;

import java.lang.reflect.Field;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //AndroidBug5497Workaround.assistActivity(findViewById(android.R.id.content));
        //AndroidBugWorkaround.assistActivity(this);
        //沉浸式状态栏只有在android4.4以上设置才有用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    protected void setStatusBarPaddingAndHeight(View toolBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (toolBar != null) {
                int statusBarHeight = getStatusBarHeight(this);
                toolBar.setPadding(toolBar.getPaddingLeft(), statusBarHeight, toolBar.getPaddingRight(),
                        toolBar.getPaddingBottom());
                toolBar.getLayoutParams().height = statusBarHeight +
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics());
            }
        }
    }

    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            //1、通过反射获取类
            c = Class.forName("com.android.internal.R$dimen");
            //2、创建对象
            obj = c.newInstance();
            //3、拿到状态栏对象
            field = c.getField("status_bar_height");
            //4、获取其高度
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }
}
