package com.jdp.hls.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.jdp.hls.base.BaseInit;
import com.jdp.hls.base.BaseView;


/**
 * Description:TODO
 * Create Time:2018/7/7 15:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RefreshSwipeRefreshLayout extends SwipeRefreshLayout {


    public RefreshSwipeRefreshLayout(@NonNull Context context) {
        super(context);
    }

    public RefreshSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void stepRefresh(BaseInit target) {
        setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                setRefreshing(false);
                target.initNet();
            }
        });
    }


}
