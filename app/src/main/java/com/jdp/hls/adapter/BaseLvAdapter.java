package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jdp.hls.i.ILvSetData;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：
 * 类描述：ListView,GridView基类
 * 创建人：KingJA
 * 创建时间：2016/3/24 9:13
 * 修改备注：
 */
public abstract class BaseLvAdapter<T> extends BaseAdapter implements ILvSetData<T> {
    protected static final String TAG = "PhotoPreviewAdapter";
    protected Context context;
    protected List<T> list;
    protected int selectPosition = -1;

    public BaseLvAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = (list == null ? new ArrayList<>() : list);
    }

    @Override
    public void setData(List<T> list) {
        this.list = list == null ? new ArrayList<>() : list;
        selectPosition = -1;
        this.notifyDataSetChanged();
    }

    public List<T> getData() {
        return list;
    }

    public void addData(List<T> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void addFirst(T item) {
        this.list.add(0, item);
        this.notifyDataSetChanged();
    }

    public void addLast(T item) {
        this.list.add(item);
        this.notifyDataSetChanged();
    }

    public void reset() {
        this.list.clear();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return simpleGetView(position, convertView, parent);
    }

    public abstract View simpleGetView(int position, View convertView, ViewGroup parent);

    public void selectItem(int selectPosition) {
        this.selectPosition = selectPosition;
        this.notifyDataSetChanged();
    }

    public void removeItem(int position) {
        list.remove(position);
        this.notifyDataSetChanged();
    }

    public void removeItem(T item) {
        list.remove(item);
        this.notifyDataSetChanged();
    }
}
