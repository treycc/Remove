package com.jdp.hls.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;

import org.angmarch.views.NiceSpinner;

/**
 * Description:TODO
 * Create Time:2018/9/14 0014 下午 5:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class KSpinner  extends NiceSpinner{
    public KSpinner(Context context) {
        this(context,null);
    }

    public KSpinner(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public KSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(0x00000000);
        setPadding(dp2px(12),dp2px(12),0,dp2px(12));
    }
    private int dp2px(int dpValue){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpValue,getContext().getResources().getDisplayMetrics());
    }
}
