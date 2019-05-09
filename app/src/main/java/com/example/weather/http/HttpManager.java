package com.example.weather.http;





import com.example.weather.base.BaseApplication;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static final String baseUrl = "https://www.wanandroid.com";//瑞福德外网服务器测试地址
//    private static final String baseUrl = "http://hmcwcs.haima.com:9002";//瑞福德外网服务器测试地址
    //时间超时
    private static final int TIMEOUT = 30;
    private HttpService mApiService;
    private static volatile OkHttpClient mOkHttpClient;
    //保证多个地址的
    private static Map<String, HttpManager> managers = new HashMap<>();
    private final Retrofit retrofit;

    public static HttpManager getInstance(String url) {
        HttpManager instance = managers.get(url);
        if (instance == null) {
            instance = new HttpManager(url);
            managers.put(url, instance);
        }
        return instance;
    }

    public static HttpManager getInstance() {
        return getInstance(baseUrl);
    }

    private HttpManager(String url) {

        initOkHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        mApiService = retrofit.create(HttpService.class);
    }

    public  Retrofit getRetrofit(){
        return retrofit;
    }


    public  static HttpService API() {
        return getInstance().mApiService;
    }

    public  HttpService API(String url) {
        return getInstance(url).mApiService;
    }

    private void initOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (HttpManager.class) {
                //方便调试打印请求的参数和地址
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

//                if (mOkHttpClient == null) {
                    // 指定缓存路径,缓存大小100Mb
                    Cache cache = new Cache(new File(BaseApplication.getInstance().getExternalCacheDir(), "okhttpcache"), 1024 * 1024 * 100);
                    mOkHttpClient = new OkHttpClient.Builder()
//                            .addInterceptor(netInterceptor)
//                            .addNetworkInterceptor(netInterceptor)
                            .cache(cache)
                            .addInterceptor(logging)
                            .retryOnConnectionFailure(true) //错误重连
                            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)//设置超时
                            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                            .build();
                }
            }
//        }
    }

//
    //头部统一处理,自定义Cookies拦截器
    Interceptor netInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
//            //网络不可用
//            if (!NetUtil.isConnected(AppOneApplication.getInstance().getApplicationContext())) {
//                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
//            } else {

//            }
//            if(SPUtils.isLogin(AppOneApplication.getInstance().getApplicationContext())) {
//                request=  request.newBuilder().addHeader("rsa","").build();
//            }else {
//                request=request.newBuilder().addHeader("rsa",SPUtils.getUser(AppOneApplication.getInstance().getApplicationContext()).token+"").build();
//            }
            Response response = chain.proceed(request);
//            if (NetUtil.isConnected(AppOneApplication.getInstance().getApplicationContext())) {
                int maxAge = 60 * 60; // read from cache for 1 minute
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
//            }
//            else {
//                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale  缓存设置为4周
//                response.newBuilder()
//                        .removeHeader("Pragma")
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                        .build();
//            }
            return response;
        }
    };

}
