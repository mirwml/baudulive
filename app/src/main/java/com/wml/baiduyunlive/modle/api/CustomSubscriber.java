package com.wml.baiduyunlive.modle.api;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import org.reactivestreams.Subscription;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 王苗亮 on 2017/2/20.
 * 实现observer观察者的抽象类，通过定义泛型来指定具体的操做
 * 实现对话框的接口，当用户给予取消对话框的操作时，进行的逻辑操作
 */

public abstract class CustomSubscriber<T> implements CustomProgressDialog.ProgressCancelListener,Observer<T> {
    /**
     * 上下文
     */
    private final Context mContext;
    /**
     * 对话框的对象声明
     */
    private CustomProgressDialog mCpd;
    /**
     * 是否显示对话框的boolean
     */
    private boolean isShowDislog = true;

    /**
     * 构造方法，只有上下文
     * @param context
     */
    public CustomSubscriber(Context context){
        this.mContext = context;
        mCpd = CustomProgressDialog.getInstance(context,this,true);
    }

    /**
     * 两个参数的构造方法
     * @param context
     * @param b     可直接传值来决定是否显示
     */
    public CustomSubscriber(Context context, boolean b) {
        mContext = context;
        isShowDislog = b;
    }

    /**
     * 通过对话框对象来调用显示对话框
     */
    public void showDialog(){
        if(mCpd != null){
            mCpd.show();
        }
    }

    /**
     * 通过对话框对象来调用隐藏对话框
     */
    public void dissmissDialog(){
        if(mCpd != null){
            mCpd.dissmiss();
        }
    }

    /**
     * 实现observer接口，重写的方法，
     * 完成请求后消失对话框
     */
    @Override
    public void onComplete() {
        dissmissDialog();
    }

    /**
     * 传递数据异常时，给用户的提示
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiException){
            Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_SHORT).show();
        } else if ((e instanceof UnknownHostException)) {
            Toast.makeText(mContext,"网络异常",Toast.LENGTH_SHORT).show();
        } else if (e instanceof JsonSyntaxException) {
            Toast.makeText(mContext,"数据异常",Toast.LENGTH_SHORT).show();
        } else if (e instanceof SocketTimeoutException) {
            Toast.makeText(mContext,"连接超时",Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(mContext, "连接服务器失败", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext,"未知错误",Toast.LENGTH_SHORT).show();
        }
        Log.d("TAG","e:"+e);
        dissmissDialog();
    }

    /**
     * 用户有多少次发送数据，这里会有多少次调用本方法，依次传递数据
     * @param result
     */
    @Override
    public void onNext(T result) {
        if(result != null){
            onSuccess(result);
        }
    }

    /**
     * 可以在本方法内进行数据的拦截,
     * 数据发送完毕成功后，本方法第一次调用，也可以在本方法进行数据的拦截
     * @param d
     */
    @Override
    public void onSubscribe(Disposable d) {
        if(isShowDislog){
            showDialog();
        }
        isShowDislog = true;
    }

    /**
     * 这个方法很重要，现在的逻辑还没写
     * 预留本方法里要处理：当用户点击取消了加载进度条，
     * 需要将本次发起的数据请求退回
     */
    @Override
    public void onProgressCancel(){
          
    }

    /**
     * 在onnext方法，也就是数据回传成功的方法内调用，谁调用本类都必须重写该方法
     * @param result
     */
    public abstract void onSuccess(T result);
}
