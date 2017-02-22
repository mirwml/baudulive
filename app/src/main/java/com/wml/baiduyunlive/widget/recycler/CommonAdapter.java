package com.wml.baiduyunlive.widget.recycler;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 王苗亮 on 2017/1/2.
 * 公共的adapter
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 布局id
     */
    protected int mLayoutID;

    /**
     * 数据集合
     */
    protected List<T> mData;

    /**
     * 当前Adapter所关联的Adapter
     */
    private ViewHolder mViewHolder;

    /**
     * 改变背景色的view的id
     */
    private int mViewID;

    /**
     * 资源ID,用于改变背景颜色
     */
    private int mResID = 0;

    /**
     * 记录点击的position
     */
    private int mPosition = 0;

    /**
     * 记录是否改变Item颜色的flag
     */
    boolean isChange = false;



    public CommonAdapter(Context context, int layoutID,List<T> data){
        this.mData = data;
        this.mContext = context;
        this.mLayoutID = layoutID;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //实例化ViewHolder
        mViewHolder = ViewHolder.getViewHolder(mContext,mLayoutID,parent);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //具体赋值逻辑留给用户处理
        conver(holder,mData.get(position));
    }

    public abstract void conver(ViewHolder viewHodler,T o);

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
