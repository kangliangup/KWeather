package com.example.weather.adapter;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.weather.fragment.WeatherFragment;

import java.util.List;


public class WeatherFragmentAdapter extends FragmentPagerAdapter {

    private List<WeatherFragment> fragments;

    public WeatherFragmentAdapter(FragmentManager fragmentManager, List<WeatherFragment> fragments) {
        super(fragmentManager);
        this.fragments = fragments;
    }


    @Override
    public WeatherFragment getItem(int position) {
//        fragment.setRetainInstance(true);
        return fragments.get(position);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "wuhan"+position;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(((Fragment) object).getView());
//        super.destroyItem(container, position, object);
//    }
}