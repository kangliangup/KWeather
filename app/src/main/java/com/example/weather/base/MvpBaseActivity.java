package com.example.weather.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import com.example.weather.widget.MyProgressDialog;


public abstract class MvpBaseActivity<V extends BaseView, P extends MvpBasePresenter<V>> extends BaseActivity {

    protected LayoutInflater mLayoutInflater;

    public abstract P initPresenter();

    protected P mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = initPresenter();
        mPresenter.attachView((V) this);
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mPresenter.disposeAll();
    }

    protected void showLoading() {
        MyProgressDialog.showDialog(this);
    }

    protected void showLoading(String msg) {
        MyProgressDialog.showDialog(this,msg);
    }

    protected void dismissLoading() {
        MyProgressDialog.dismissDialog();
    }

    //添加fragment
    protected void addFragment(MvpBaseFragment fragment, int frameId) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(frameId, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    //移除fragment
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

}

//    //返回键返回事件
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (KeyEvent.KEYCODE_BACK == keyCode) {
//            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
//                finish();
//                return true;
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }


