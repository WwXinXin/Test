package com.efamily.testapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.efamily.testapp.Base.BaseActivity;
import com.efamily.testapp.activity.SecondActivity;
import com.efamily.testapp.adapter.MyPageAdapter;
import com.efamily.testapp.single.AppManager;
import com.efamily.testapp.util.AndroidBug5497Workaround;
import com.efamily.testapp.util.AndroidBugWorkaround;

import java.lang.reflect.Field;

public class MainActivity extends BaseActivity {

    private TabLayout tabLayout;
    private AppBarLayout appbar;
    private ViewPager viewpager;
    private TextView et;
    private Toolbar toolBar;

    private static final String[] titles = {"全部", "麻将"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        appbar = (AppBarLayout) findViewById(R.id.appBar);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        et = (TextView) findViewById(R.id.et);
        toolBar = (Toolbar) findViewById(R.id.toolbar);

        setStatusBarPaddingAndHeight(toolBar);

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("111", "off：" + verticalOffset);

                if (verticalOffset < -100) {
                    et.setBackgroundResource(R.drawable.btn_search_scroll_shape);
                    et.setTextColor(getResources().getColor(R.color.white));
                } else {
                    et.setBackgroundResource(R.drawable.btn_search_shape);
                    et.setTextColor(getResources().getColor(R.color.gray));
                }

                toolBar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.colorPrimary), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));
            }
        });

        viewpager.setAdapter(new MyPageAdapter(getSupportFragmentManager(), titles));
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    /**
     * 根据百分比改变颜色透明度
     */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }
}
