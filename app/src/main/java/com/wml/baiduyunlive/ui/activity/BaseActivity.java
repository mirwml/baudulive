package com.wml.baiduyunlive.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.wml.baiduyunlive.R;
import com.wml.baiduyunlive.modle.api.ActivityCollections;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 王苗亮 on 2017/2/20.
 * activity的基类
 */

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 定义上下文
     */
    protected Activity mActivity;
    /**
     * 定义最底层的布局
     */
    private FrameLayout mFrameLayout;
    /**
     * 进度条
     */
    private ProgressBar mBar;

    /**
     * 使用final修饰oncreate方法后，继承自这个类后不容许重写这个方法，
     * 防止后续的初始化方法不知道顺序，引起空指针异常
     * @param savedInstanceState
     */

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        /**
         * eventbus事件注册
         */
        EventBus.getDefault().register(this);
        /**
         * 将activity对象添加到集合管理器中
         */
        ActivityCollections.addActivity(this);
        mActivity = this;
        mFrameLayout = (FrameLayout) findViewById(R.id.base_frame);
        /**
         * 初始化继承自本类的子类视图，并添加到mFraglayout视图中来
         */
        View view = LayoutInflater.from(mActivity).inflate(getLayoutId(),null,false);
        mFrameLayout.addView(view);

        /**
         * 默认添加加载进度条
         */
        addProgress();
    }

    protected abstract int getLayoutId();

    /**
     * 在开始方法中，onCreate方法初始完视图之后
     * 再进行初始化控件及数据
     */
    @Override
    protected final void onStart() {
        super.onStart();
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();
    /**
     * 单例进度条
     */
    private ProgressBar getDefaultBar(){
        if (mBar==null){
            synchronized (BaseActivity.class){
                if (mBar==null)
                    mBar = new ProgressBar(this);
            }
        }
        return mBar;
    }

    /**
     * 加载进度条
     */
    protected  void addProgress(){

        getDefaultBar();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        mBar.setLayoutParams(params);
        mFrameLayout.addView(mBar);
    }
    /**
     * 移除进度条
     */
    protected void removeProgress(){
        if(mBar != null){
            mFrameLayout.removeView(mBar);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//事件注销
        ActivityCollections.removeActivity(this);
    }
}
