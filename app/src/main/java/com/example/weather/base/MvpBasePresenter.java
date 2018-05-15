package com.example.weather.base;


import android.content.Context;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class MvpBasePresenter<V extends BaseView> {

    protected Context mContext;

    public MvpBasePresenter(Context context) {
        this.mContext = context;
    }

    protected V mView;
    private WeakReference<V> mViewRef;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    void attachView(V view) {
//        mView = view;
        mViewRef = new WeakReference<>(view);
        mView = mViewRef.get();
    }

    void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 取消网络请求
     */
    protected void dispose(Disposable d) {
        if (d != null && !d.isDisposed())
            d.dispose();
    }

    /**
     * 取消所有网络请求
     */
    void disposeAll() {
        if (mCompositeDisposable != null)
            mCompositeDisposable.clear();
    }

    /**
     * 添加到集合
     */
    protected void addToComposite(Disposable d) {
        if (d == null) return;
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(d);
    }

}
