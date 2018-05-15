package com.example.weather.http;


import android.content.Context;



import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public abstract class HttpObserverList<T> implements Observer<HttpResultList<T>> {
    private Context mContext;
    protected abstract void onSuccess(HttpResultList<T> t);
    protected abstract void onFailure(Throwable e);
    protected abstract void onStart(Disposable d);

    protected HttpObserverList(Context context) {
        this.mContext = context;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        onStart(d);
    }


    @Override
    public void onNext(@NonNull HttpResultList<T> result) {

//        if(!HttpResultData.OK.equals(result.getStatus())){
//             ToastUtils.showShort(mContext, result.getMsg());
//        }
        onSuccess(result);

    }


    @Override
    public void onError(@NonNull Throwable e) {
        if (e instanceof SocketTimeoutException) {
//            ToastUtils.showShort(mContext, "网络连接超时，请检查您的网络状态");
        }/*else if(e instanceof IndexOutOfBoundsException){
            Toast.makeText(mContext,"json解析异常", Toast.LENGTH_SHORT).show();
        } */else if (e instanceof ConnectException) {
//            ToastUtils.showShort(mContext, "网络连接中断，请检查您的网络状态");
        } else {
//            ToastUtils.showShort(mContext, e.getMessage());
        }
        onFailure(e);

    }

    @Override
    public void onComplete() {
    }


}
