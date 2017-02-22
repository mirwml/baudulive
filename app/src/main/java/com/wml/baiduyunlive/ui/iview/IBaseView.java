package com.wml.baiduyunlive.ui.iview;

import com.wml.baiduyunlive.modle.BaseBean;

/**
 * Created by 王苗亮 on 2017/2/20.
 */

public interface IBaseView<T extends BaseBean> {
    void onSuccess(T result);
    void onFail();
}
