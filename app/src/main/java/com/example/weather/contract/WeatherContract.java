package com.example.weather.contract;

import com.example.weather.base.BasePresenter;
import com.example.weather.base.BaseView;

import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;

/**
 * Created by 小康生活 on 2018/5/16.
 * 虽然长得丑，但是想的美
 */

public class WeatherContract {

   public interface  View extends BaseView{

       /**
        * 显示实况天气
        * @param  now 实况天气数据
        */
       void showWeatherNow(Now now);

    }

    public interface Presenter extends BasePresenter{

        /**
         * 获取事实天气
         */
        void getWeatherNow(String city);

        /**
         * 获取7天天气
         */
        void getWeatherForDays(String city);

    }
}
