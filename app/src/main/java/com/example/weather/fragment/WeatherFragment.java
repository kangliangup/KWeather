package com.example.weather.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weather.R;
import com.example.weather.base.MvpBaseFragment;
import com.example.weather.contract.WeatherContract;
import com.example.weather.presenter.WeatherPresenterImpl;
import com.orhanobut.logger.Logger;

import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;


public class WeatherFragment extends MvpBaseFragment<WeatherContract.View,WeatherPresenterImpl>
        implements  WeatherContract.View{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;




    public WeatherFragment() {
        // Required empty public constructor
    }


    public static WeatherFragment newInstance(String city) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public WeatherPresenterImpl initPresenter() {
        return new WeatherPresenterImpl(mContext);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    protected void lazyLoadData() {
        Logger.e(mParam1);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_weather, container, false);
    }





    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void showWeatherNow(Now now) {

    }



}
