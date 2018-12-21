package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jdp.hls.i.OnSearchListener;
import com.jdp.hls.model.entiy.Employee;
import com.jdp.hls.model.entiy.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/16 0016 下午 3:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseSearchAdapter<T> extends BaseLvAdapter<T> implements OnSearchListener {
    protected List<T> resultList = new ArrayList<>();

    public BaseSearchAdapter(Context context, List<T> list) {
        super(context, list);
        this.resultList = this.list;
    }

    @Override
    public abstract View simpleGetView(int position, View convertView, ViewGroup parent);

    @Override
    public void onSearch(String keyword) {
        resultList = new ArrayList<>();
        doSearch(list, resultList, keyword);
        notifyDataSetChanged();
    }

    public void addSearchFirst(T t) {
        list.add(0, t);
        resultList.add(0, t);
        notifyDataSetChanged();
    }

    public void modifySearchItem(T t) {
    }

    public void removeSearchItem(int position) {
        resultList.remove(position);
        notifyDataSetChanged();
    }
    public void removeSearchItem(T t) {
        resultList.remove(t);
        list.remove(t);
        notifyDataSetChanged();
    }

    protected abstract void doSearch(List<T> list, List<T> resultList, String keyword);

    @Override
    public Object getItem(int position) {
        return resultList.get(position);
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public void setData(List<T> list) {
        this.resultList = list;
        this.list = list;
        notifyDataSetChanged();
    }
}
