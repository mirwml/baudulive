package com.wml.baiduyunlive.widget.recycler;

/**
 * Created by 王苗亮 on 2017/1/2.
 * 支持多类型条目的接口
 */

public interface MulitItemTypeSupport<T> {

    int getLayoutID(int ItemType);

    int getItemViewType(int position, T o);
}
