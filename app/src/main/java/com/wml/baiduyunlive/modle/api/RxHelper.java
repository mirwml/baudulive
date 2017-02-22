package com.wml.baiduyunlive.modle.api;


import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wml.baiduyunlive.api.Contains;
import com.wml.baiduyunlive.modle.BaseBean;
import com.wml.baiduyunlive.modle.bean.HttpResult;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 王苗亮 on 2017/1/11.
 * 这是被观察者
 */

public class RxHelper {

    private static ApiServer sApiServer;

    public static <T> void toSubscribe(Observable<BaseBean<T>> observable, final Observer<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .map(new Function<BaseBean<T>, T>() {
                    @Override
                    public T apply(BaseBean<T> httpResult) throws Exception {
                        if (!httpResult.getStatus().equals(ApiConfig.SUCCESS_FLAG)
                                && httpResult.getCode() != ApiConfig.SUCCESS_CODE) {
                            throw new ApiException(httpResult.getCode());
                        }
                        return httpResult.getData();
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private static void getInstans() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contains.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        sApiServer = retrofit.create(ApiServer.class);

    }

    private static <R> void fromSubcribe(Observer<R> observer) {
        sApiServer.requestData("")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
