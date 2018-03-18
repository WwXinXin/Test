package com.efamily.testapp.factory;


import android.support.v4.app.Fragment;

import com.efamily.testapp.fragment.TestOneFragment;
import com.efamily.testapp.fragment.TestSecondFragment;

import java.util.HashMap;

/**
 * Created by admin on 2016/7/13.
 */
public class TabFragmentFactory
{
    private static HashMap<Integer, Fragment> mFragmentHashMap = new HashMap<>();

    public static Fragment createFragment(int pos)
    {
        Fragment fragment = mFragmentHashMap.get(pos);
        if (fragment == null)
        {
            switch (pos)
            {
                case 0:
                    fragment = new TestOneFragment();
                    break;

                case 1:
                    fragment = new TestSecondFragment();
                    break;
            }
            mFragmentHashMap.put(pos, fragment);
        }
        return fragment;
    }
}
