package com.wml.baiduyunlive.api;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 王苗亮 on 2017/2/20.
 * 系统一启动就可以去初始化的一些东东
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 启用EventBus3.0加速功能
//        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }

}
