package com.jdp.hls.view;

import android.content.Context;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.jdp.hls.util.AppUtil;

/**
 * Description:TODO
 * Create Time:2018/10/31 0031 下午 3:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class HorProgressBar extends ProgressBar {
    public HorProgressBar(Context context) {
        super(context);
    }

    public HorProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public final int roundCorners = 10;//就是改变这个值，就可以改变自定义progressbar左右两端的圆角大小了，使用于自定义图片的情况，

    Shape getDrawableShape() {
        final float[] roundedCorners = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < roundedCorners.length; i++) {
            roundedCorners[i] = AppUtil.dp2px(roundCorners);
        }
        return new RoundRectShape(roundedCorners, null, null);
    }
}
