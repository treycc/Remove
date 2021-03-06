package com.jdp.hls.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 上午 10:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class CommonPositionAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    public CommonPositionAdapter(Context context, List<T> datas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
        this.mItemLayoutId = itemLayoutId;
    }

    public CommonPositionAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
        this.mItemLayoutId = getItemLayoutId();
    }

    protected int getItemLayoutId() {
        return 0;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder helper, T item, int position);

    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }

    public void setData(List<T> list) {
        this.mDatas = list;
        notifyDataSetChanged();
    }

    public void addData(T data) {
        this.mDatas.add(0, data);
        notifyDataSetChanged();
    }


}