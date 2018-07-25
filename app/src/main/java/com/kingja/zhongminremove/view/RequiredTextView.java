package com.kingja.zhongminremove.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

/**
 * Description:TODO
 * Create Time:2018/7/25 0025 下午 4:46
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RequiredTextView extends AppCompatTextView {

    public RequiredTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SpannableString spanString = new SpannableString("*"+this.getText());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        spanString.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        this.setText(spanString);
    }
}