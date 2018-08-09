package com.example.weather.presenter;

import android.content.Context;
import android.util.Log;

import com.example.weather.base.MvpBasePresenter;
import com.example.weather.contract.MainContract;
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

public  class MainPresenterImpl extends MvpBasePresenter<MainContract.View> implements MainContract.Presenter{

    public MainPresenterImpl(Context context) {
        super(context);
    }


    @Override
    public void getWeatherNow(String city) {

        HeWeather.getWeatherNow(mContext, "武汉", Lang.CHINESE_SIMPLIFIED, Unit.METRIC,

                new HeWeather.OnResultWeatherNowBeanListener() {
                    @Override
                    public void onError(Throwable e) {
                        Log.i("Log", "onError: ", e);
                    }

                    @Override
                    public void onSuccess(List dataObject) {
                        Now now= (Now) dataObject.get(0);
                        String json=new Gson().toJson(dataObject);
                        Log.i("Log", "onSuccess: " + json);
                        Logger.json(json);
                        mView.showWeatherNow(now);

                    }
                });
    }
}
