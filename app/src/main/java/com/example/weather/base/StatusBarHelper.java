package com.example.weather.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class StatusBarHelper {

    private  static  int INVALID=-1;

    /**
     * 生成一个和状态栏大小相同的矩形条 * * @param activity 需要设置的activity * * @param color 状态栏颜色值 * * @return 状态栏矩形条
     */
    private static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int statusBarHeight = getStatusBarHeight(activity);
        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        int screenWidth = activity.getResources().getDisplayMetrics().widthPixels;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(screenWidth, statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }


    private static int getStatusBarHeight(Activity activity) {
        int statusBarHeight = 0;
        if (activity != null) {
            int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 沉浸模式
     */
    public static void setStatusTranslucent(Activity activity) {
       setColor(activity, Color.TRANSPARENT,null);
    }

    /**
     * 沉浸模式，title距离顶部有间距
     */
    public static void setStatusTranslucent(Activity activity,ViewGroup titleView) {
        setColor(activity,Color.TRANSPARENT,titleView);
    }


        private static  void setColor(Activity activity, int statusColor, ViewGroup titleView){

            Window window = activity.getWindow();
            //android 4.4
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT&&Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP) {
                // 设置状态栏透明
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            }
            //5.0以上
            else if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                //取消设置悬浮透明状态栏,ContentView便不会进入状态栏的下方了
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(statusColor);
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
//            // 生成一个状态栏大小的矩形
//            View statusView = createStatusView(activity, statusColor);
////            // 添加 statusView 到布局中
////            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
////            decorView.addView(statusView);
//            titleView.addView(statusView,0);

            if(titleView!=null){
                titleView.setPadding(0, getStatusBarHeight(activity), 0, 0);
            }


            // 设置根布局的参数
//            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
//            rootView.setFitsSystemWindows(true);
//            rootView.setClipToPadding(true);
        }





    /**
     * 设置状态栏颜色，非沉浸模式
     */
    public static  void setStatusColor(Activity activity, int statusColor){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(statusColor);
        }

    }


}