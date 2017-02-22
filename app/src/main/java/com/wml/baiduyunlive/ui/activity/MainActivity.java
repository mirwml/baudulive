package com.wml.baiduyunlive.ui.activity;


import com.wml.baiduyunlive.R;
import com.wml.baiduyunlive.modle.bean.MainBean;
import com.wml.baiduyunlive.ui.iview.IMainView;

public class MainActivity extends BaseActivity implements IMainView{


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    /**
     * 数据请求成功的回传
     * @param result
     */
    @Override
    public void onSuccess(MainBean result) {

    }

    /**
     * 数据请求失败的回传
     */
    @Override
    public void onFail() {

    }
}
