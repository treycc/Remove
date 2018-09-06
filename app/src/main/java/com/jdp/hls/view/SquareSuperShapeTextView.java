package com.jdp.hls.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.kingja.supershapeview.view.SuperShapeTextView;

/**
 * Description:TODO
 * Create Time:2018/9/5 0005 上午 10:03
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SquareSuperShapeTextView extends SuperShapeTextView {
    public SquareSuperShapeTextView(Context context) {
        super(context);
    }

    public SquareSuperShapeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(),getMeasuredWidth());
    }
}
