
package com.example.weather.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.weather.widget.MyProgressDialog;


public abstract class MvpBaseFragment<V extends BaseView, P extends MvpBasePresenter<V>> extends BaseFragment {

    private boolean isVisible;                  //是否可见状态
    private boolean isPrepared;                 //标志位，View已经初始化完成。
    private boolean isFirstLoad = true;         //是否第一次加载
//    protected Context mContext;
    protected P mPresenter;

    /**
     * 实例化presenter
     */
    public abstract P initPresenter();


    protected void showLoading() {
        MyProgressDialog.showDialog(mContext);
    }

    protected void hideLoading() {
        MyProgressDialog.dismissDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirstLoad = true;
        isPrepared = true;
        lazyLoad();
    }
    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

//    /**
//     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
//     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
//     */
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            isVisible = true;
//            onVisible();
//        } else {
//            isVisible = false;
//            onInvisible();
//        }
//    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        this.mContext = context;
        mPresenter = initPresenter();
        mPresenter.attachView((V) this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.disposeAll();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
        mPresenter.detachView();
    }


//    //重写setMenuVisibility方法，不然会出现叠层的现象
//    @Override
//    public void setMenuVisibility(boolean menuVisibility) {
//        super.setMenuVisibility(menuVisibility);
//        if (this.getView() != null) {
//            this.getView().setVisibility(menuVisibility ? View.VISIBLE : View.GONE);
//        }
//    }


}
