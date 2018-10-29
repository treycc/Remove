package com.jdp.hls.view.dialog;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Description:TODO
 * Create Time:2018/10/29 0029 上午 11:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FixLengthTextView extends TextView {
    public FixLengthTextView(Context context) {
        super(context);
    }

    public FixLengthTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FixLengthTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static final int MAX_LENGTH = 12;

    @Override
    public void setText(CharSequence text, BufferType type) {
        String s = text.toString();
        if (s.length() > MAX_LENGTH) {
            super.setText(new StringBuffer(s.substring(0, MAX_LENGTH)).append("...").toString(), type);
        } else {
            super.setText(text, type);
        }

    }
}
