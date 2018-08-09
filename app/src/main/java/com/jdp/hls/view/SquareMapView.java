package com.jdp.hls.view;

import android.content.Context;
import android.util.AttributeSet;

import com.amap.api.maps.MapView;

/**
 * Description:TODO
 * Create Time:2018/8/7 0007 上午 11:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SquareMapView extends MapView {
    public SquareMapView(Context context) {
        super(context);
    }

    public SquareMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquareMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(),getMeasuredWidth());
    }
}
