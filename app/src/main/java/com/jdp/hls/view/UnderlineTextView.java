package com.jdp.hls.view;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Description:TODO
 * Create Time:2018/12/27 0027 上午 11:26
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UnderlineTextView extends TextView {
    public UnderlineTextView(Context context) {
        this(context, null);
    }

    public UnderlineTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderlineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
    }
}
