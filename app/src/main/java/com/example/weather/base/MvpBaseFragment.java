
package com.example.weather.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.weather.widget.MyProgressDialog;


public abstract class MvpBaseFragment<V extends BaseView, P extends MvpBasePresenter<V>> extends BaseFragment {


    protected P mPresenter;

    /**
     * fragment是否创建完成
     */
    private boolean isViewCreated;

    /**
     * fragment是否可见
     */
    private boolean isUIVisible;

    /**
     * 是否加载过数据
     */
    private boolean isDataLoaded;


    /**
     * 实例化presenter
     */
    public abstract P initPresenter();

    /**
     * 懒加载数据
     */
    protected abstract  void lazyLoadData();


    protected void showLoading() {
        MyProgressDialog.showDialog(mContext);
    }

    protected void hideLoading() {
        MyProgressDialog.dismissDialog();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDataLoaded =false;
    }
    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

//    /**
//     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
//     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
//     */
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//
//        if (!hidden) {
//            isVisible = true;
//            onVisible();
//        } else {
//            isVisible = false;
//            onInvisible();
//        }
//    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,
        // 并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated&&isUIVisible&& !isDataLoaded) {
            lazyLoadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
            isDataLoaded =true;


        }
    }





    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }



}
