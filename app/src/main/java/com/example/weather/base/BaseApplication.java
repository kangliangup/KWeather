package com.example.weather.base;

import android.app.Application;
import android.os.StrictMode;
import android.os.Vibrator;




public abstract  class BaseApplication extends Application {
    private static BaseApplication app;
//    public LocationService locationService;
    public Vibrator mVibrator;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        GreenDaoHelper.initDatabase();
//        locationService = new LocationService(this);
//        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
//        SDKInitializer.initialize(getApplicationContext());
//        Stetho.initializeWithDefaults(this);
//        init();
        cameraProblem();
    }

    //解决android 7.0相机相机调用问题,文件存储问题provider
    private void cameraProblem() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//        builder.detectFileUriExposure()
        StrictMode.setVmPolicy(builder.build());
    }

    public static BaseApplication getInstance() {
        return app;
    }

//    private void init() {
//        Stetho.initializeWithDefaults(this);
//        new OkHttpClient.Builder()
//                .addNetworkInterceptor(new StethoInterceptor())
//                .build();
//    }

}
