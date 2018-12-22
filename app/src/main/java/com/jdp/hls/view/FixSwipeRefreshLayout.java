package com.jdp.hls.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.jdp.hls.base.BaseInit;


/**
 * Description:TODO
 * Create Time:2018/7/7 15:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FixSwipeRefreshLayout extends RefreshSwipeRefreshLayout {


    public FixSwipeRefreshLayout(@NonNull Context context) {
        super(context);
    }

    public FixSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
