


package com.example.weather.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
//        System.out.println(getClass().getSimpleName()+"------onAttach");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        System.out.println(getClass().getSimpleName()+"------onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
//        System.out.println(getClass().getSimpleName()+"------onDetach");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        System.out.println(getClass().getSimpleName()+"------onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        System.out.println(getClass().getSimpleName()+"------onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        System.out.println(getClass().getSimpleName()+"------onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
//        System.out.println(getClass().getSimpleName()+"------onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
//        System.out.println(getClass().getSimpleName()+"------onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
//        System.out.println(getClass().getSimpleName()+"------onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
//        System.out.println(getClass().getSimpleName()+"------onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        System.out.println(getClass().getSimpleName()+"------onDestroyView");
    }


}
