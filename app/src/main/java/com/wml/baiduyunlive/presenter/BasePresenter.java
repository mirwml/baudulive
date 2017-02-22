package com.wml.baiduyunlive.presenter;

import com.wml.baiduyunlive.ui.iview.IBaseView;

/**
 * Created by 王苗亮 on 2017/2/20.
 */

public abstract class BasePresenter<T extends IBaseView> {
    protected  T IView;

    public T getIView() {
        return IView;
    }

    public void setIView(T IView) {
        this.IView = IView;
    }
    public abstract void loadDataFromNet(String url);
    public abstract void postDataFromNeet(String url);
}
