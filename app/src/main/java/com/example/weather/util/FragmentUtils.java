package com.example.weather.util;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.weather.R;


public class FragmentUtils {

    //添加fragment
    public static void replaceFragmentWithAnim(FragmentManager fragmentManager, Fragment fragment, String tag, int frameId) {
        if (fragment != null) {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.in_from_right, R.anim.out_from_left,
                    R.anim.in_from_left, R.anim.out_from_right)
                    .replace(frameId, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(tag)
                    .commitAllowingStateLoss();
        }
    }

    //移除fragment
    public static void removeFragment(FragmentManager fragmentManager) {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
//
//        else {
//            finish();
//        }
    }

    //添加fragment
    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int frameId) {
        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(frameId, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
