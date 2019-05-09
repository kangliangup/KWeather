package com.example.weather.presenter;

import android.content.Context;

import com.example.weather.base.MvpBasePresenter;
import com.example.weather.bean.ChapterData;
import com.example.weather.contract.WeatherContract;
import com.example.weather.http.HttpManager;
import com.example.weather.http.HttpObserverData;
import com.example.weather.http.HttpResultData;
import com.example.weather.http.HttpTransformer;
import com.example.weather.util.GsonUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.disposables.Disposable;

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
        HttpManager.API()
                .getChapters()
                .compose(HttpTransformer.<HttpResultData<List<ChapterData>>>applyIoSchedulers())
                .subscribe(new HttpObserverData<List<ChapterData>>(mContext) {
                    @Override
                    protected void onSuccess(HttpResultData<List<ChapterData>> result) {
                       Logger.json( GsonUtils.GsonString(result));
                        System.out.println("onSuccess");
                    }

                    @Override
                    protected void onFailure(Throwable e) {
                        System.out.println("onFailure");
                        System.out.println(e.getMessage());
                    }

                    @Override
                    protected void onStart(Disposable d) {
                        addToComposite(d);
                    }

                });

    }

    @Override
    public void getWeatherForDays(String city) {

    }
}
