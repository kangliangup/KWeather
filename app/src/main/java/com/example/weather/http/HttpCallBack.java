package com.example.weather.http;


import io.reactivex.disposables.Disposable;

public interface HttpCallBack<T> {
    void onFail(Throwable e);
    void onStart(Disposable d);
    void onSuccess(HttpResultData<T> t);
}
