package com.example.weather.base;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.weather.util.ActivityManager;


/**
 * Date：2018/2/8
 * Author：小康de生活
 * Signature:好好学习，天天向上
 * Description:
 */

@SuppressLint("Registered")
public abstract  class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected BaseApplication mApp;

    public static final int STATUSBAR_TRANSLANT=0;//状态栏透明StatusBar
    protected int statusBarState;


    protected int setStatusBarState(){
        return STATUSBAR_TRANSLANT;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        mApp = BaseApplication.getInstance();


        ActivityManager.getInstance().addActivity(this);
//        registerReceiver();

    }


    /**
     * 监听网络状态
     */
    BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            if (!NetUtils.isConnected(mContext)) {
//                Toast.makeText(context, "亲，网络未连接，请检查网络", Toast.LENGTH_SHORT).show();
//            }
//            if(NetUtils.isWifiEnable(mContext)){
//                Toast.makeText(context, "wifi连接", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(context, "wifi断开", Toast.LENGTH_SHORT).show();
//            }

        }
    };


    private void registerReceiver() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(myReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//      unregisterReceiver(myReceiver);
    }
}
