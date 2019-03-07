package com.jdp.hls.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.jdp.hls.R;

/**
 * Description:TODO
 * Create Time:2018/9/10 0010 上午 11:23
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NumberEnableEditText extends EnableEditText {


    public NumberEnableEditText(Context context) {
        super(context);
    }

    public NumberEnableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberEnableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Editable getText() {
        String text = super.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            setText("0");
        }
        return super.getText();
    }
}
