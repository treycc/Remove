package com.jdp.hls.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Description:TODO
 * Create Time:2018/12/6 0006 下午 2:46
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public NoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    // 表示事件是否拦截, 返回false表示不拦截, 可以让嵌套在内部的viewpager相应左右划的事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }

    /**
     * 重写onTouchEvent事件,什么都不用做
     */
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }
}
