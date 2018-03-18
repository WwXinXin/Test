package com.efamily.testapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.efamily.testapp.factory.TabFragmentFactory;

public class MyPageAdapter extends FragmentPagerAdapter {
    private String[] titles;

    public MyPageAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    //要滑几个页面
    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = TabFragmentFactory.createFragment(position);
        return fragment;
    }

    //1.2 重写获取title
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}