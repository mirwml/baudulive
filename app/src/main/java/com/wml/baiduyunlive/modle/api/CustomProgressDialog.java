package com.wml.baiduyunlive.modle.api;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.wml.baiduyunlive.R;


/**
 * Created by 王苗亮 on 2017/2/20.
 * 自定义对话框及其内部的一些操作
 */

public class CustomProgressDialog{

    /**
     * 私有化类全局变量
     */
    private static CustomProgressDialog mCpd;
    /**
     * 私有化接口的全局声明
     */
    private final ProgressCancelListener mPcl;
    /**
     * 通过从外部传递布尔值来决定是否可点击消失
     */
    private final boolean mCancelable;
    /**
     * 私有化dialog的全局变量声明，以便本类中其它方法使用
     */
    private Dialog mDialog;
    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 对话框的构造方法
     * @param context
     * @param mPcl
     * @param cancelable
     */
    private CustomProgressDialog(Context context,ProgressCancelListener mPcl,boolean cancelable){
        this.mPcl = mPcl;
        this.mCancelable = cancelable;
        this.mContext = context;
        initDialod();
    }

    /**
     * 对话框的单例模式,将参数传递给实例后，可使私有化的构造方法获得参数
     * @param context  上下文
     * @param mPcl    本类的接口，可以为null
     * @param cancelable    外部决定是否取消
     * @return            返回本类对象
     */
    public static CustomProgressDialog getInstance(Context context, ProgressCancelListener mPcl, boolean cancelable){
       if(mCpd == null){
            synchronized (CustomProgressDialog.class){
                if(mCpd == null){
                    mCpd = new CustomProgressDialog(context,mPcl,cancelable);
                }
            }
       }
        return mCpd;
    }

    /**
     * 初始化对话框
     */
    private void initDialod() {
       if(mDialog == null){
           mDialog = new Dialog(mContext);
           View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_query,null);
           mDialog.setCanceledOnTouchOutside(false);//点击对话框外边不会消失
           mDialog.setCancelable(mCancelable);//点击消失按钮消失
           mDialog.setContentView(view);

           //设置Dialog位置
           Window dialogWindow = mDialog.getWindow();
           dialogWindow.setGravity(Gravity.CENTER_VERTICAL
                   | Gravity.CENTER_HORIZONTAL);
       }
        /**
         * 对话框的监听
         */
        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if(mPcl != null){
                    mPcl.onProgressCancel();
                }
            }
        });
    }

    /**
     * 对话框的显示方法
     */
    public void show(){
        if(mDialog != null && !mDialog.isShowing()){
            mDialog.show();
        }
    }

    /**
     * 对话框的隐藏方法
     */
    public void dissmiss(){
        if(mDialog != null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    /**
     * 定义对话框的接口，可以让外部实现本接口，进行逻辑操
     */
    public interface ProgressCancelListener{
        void onProgressCancel();
    }


}
