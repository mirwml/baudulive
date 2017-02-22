package com.wml.baiduyunlive.modle.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 王苗亮 on 2017/2/20.
 *
 */

public class Api {

    private static ApiServer mServer;

    public static ApiServer getRetrofitInstance(){
        init();
        return RetrofitInstance.mServer;
    }

    private static void init() {
        new OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)  //连接超时
        .readTimeout(20,TimeUnit.SECONDS)    //读取超时
        .writeTimeout(20,TimeUnit.SECONDS)  //写入超时
        .retryOnConnectionFailure(true);  //错误重连机制

    }

    static class RetrofitInstance{
        private static  ApiServer mServer = new Retrofit.Builder()
                .baseUrl(ApiConfig.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiServer.class);
    }
}
