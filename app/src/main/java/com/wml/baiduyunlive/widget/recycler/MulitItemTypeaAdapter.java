package com.wml.baiduyunlive.widget.recycler;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 王苗亮 on 2017/1/2.
 * 多条目类型加载数据
 */

public abstract class MulitItemTypeaAdapter<T> extends CommonAdapter<T> {

    private MulitItemTypeSupport mulitItemTypeSupport;

    public MulitItemTypeaAdapter(Context context,List<T> data,
                                 MulitItemTypeSupport mulitItemTypeSupport) {
        super(context, -1, data);
        this.mulitItemTypeSupport = mulitItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mulitItemTypeSupport.getItemViewType(position,mData.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutID = mulitItemTypeSupport.getLayoutID(viewType);
        ViewHolder viewHolder = ViewHolder.getViewHolder(mContext,layoutID,parent);
        return viewHolder;
    }
}
