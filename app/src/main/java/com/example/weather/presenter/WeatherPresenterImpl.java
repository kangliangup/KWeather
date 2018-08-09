package com.example.weather.presenter;

import android.content.Context;
import android.util.Log;

import com.example.weather.base.MvpBasePresenter;
import com.example.weather.contract.MainContract;
import com.example.weather.contract.WeatherContract;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

/**
 * Created by 小康生活 on 2018/5/15.
 * 虽然长得丑，但是想的美
 */

public  class WeatherPresenterImpl extends MvpBasePresenter<WeatherContract.View> implements WeatherContract.Presenter{


    public WeatherPresenterImpl(Context context) {
        super(context);
    }

    @Override
    public void getWeatherNow(String city) {

    }

    @Override
    public void getWeatherForDays(String city) {

    }
}
