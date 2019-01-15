package com.jdp.hls.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Description:TODO
 * Create Time:2019/1/14 0014 下午 4:57
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MoveListView extends ListView {
    private GestureDetector mGestureDetector;

    public MoveListView(Context context) {
        this(context, null);
    }

    public MoveListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoveListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMoveSwipeRefreshLayout();
    }

    private void initMoveSwipeRefreshLayout() {
        mGestureDetector = new GestureDetector(getContext(), new MoveListView.YScrollDetector());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return Math.abs(distanceY) > Math.abs(distanceX);
        }
    }
}
