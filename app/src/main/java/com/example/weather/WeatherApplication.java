package com.example.weather;

import com.example.weather.base.BaseApplication;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import interfaces.heweather.com.interfacesmodule.view.HeConfig;


public class    WeatherApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        HeConfig.init("HE1805151425251375", "e8614a1950234292a3d6db1a6af1759b");
        HeConfig.switchToFreeServerNode();

        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
