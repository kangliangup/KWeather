package com.example.weather.http;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Date：2018/2/7
 * Author：小康de生活
 * Signature:好好学习，天天向上
 * Description:
 */

@SuppressWarnings("unchecked")
public class HttpTransformer {
    public static <T> ObservableTransformer<T, T> applyIoSchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        /*.map(new Function<T, T>() {
                            @Override
                            public T apply(T t) throws Exception {
                                if (!HttpResultData.OK.equals(((HttpResultData<T>) t).getStatus())) {
                                    throw new HttpException(((HttpResultData<T>) t).getStatus(), ((HttpResultData<T>) t).getMsg());
                                }
                                return t;
                            }
                        })*/;
            }
        };
    }

    public static <T> ObservableTransformer<T, T> setThreadList() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<T, T>() {
                            @Override
                            public T apply(T t) throws Exception {
                                if (!HttpResultData.OK.equals(((HttpResultList<T>) t).getStatus())) {
                                    throw new HttpException(((HttpResultList<T>) t).getStatus(), ((HttpResultList<T>) t).getMsg());
                                }
                                return t;
                            }
                        });
            }
        };
    }

}