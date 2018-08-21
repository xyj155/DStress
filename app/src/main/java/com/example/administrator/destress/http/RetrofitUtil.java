package com.example.administrator.destress.http;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/6/25.
 */

public class RetrofitUtil {
    public static final String BASE_URL = "http://122.152.231.185/";
    private Retrofit retrofit;
    private static RetrofitUtil sInstance;
    private static RetrofitUtil javaInstance;
    public RetrofitUtil(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtil getInstance() {
        synchronized (RetrofitUtil.class) {
            if (sInstance == null) {
                sInstance = new RetrofitUtil(BASE_URL);
            }
        }
        return sInstance;
    }

    public API getServerices() {
        return retrofit.create(API.class);
    }
}