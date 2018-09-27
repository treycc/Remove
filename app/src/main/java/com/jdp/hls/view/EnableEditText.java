package com.jdp.hls.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;

import com.jdp.hls.R;

/**
 * Description:TODO
 * Create Time:2018/9/10 0010 上午 11:23
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class EnableEditText extends AppCompatEditText implements View.OnFocusChangeListener {

    private Paint mPaint;
    private int mFocusOnColor = 0xff13A4F9;
    private int mFocusOffColor = 0xffCCCCCC;
    private int mDisableColor = 0x00000000;
    private boolean hasFocus;

    public EnableEditText(Context context) {
        this(context, null);
    }

    public EnableEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public EnableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initEnableEditText();
    }

    private void initEnableEditText() {
        setBackgroundColor(0xffffffff);
//        setBackground(null);
        setOnFocusChangeListener(this);
        mPaint = new Paint();
        // mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2.0f);
        int color = Color.parseColor("#ff0000");
        mPaint.setColor(color);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setHint("");
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isEnabled()) {
            mPaint.setColor(hasFocus ? mFocusOnColor : mFocusOffColor);
        }else{
            mPaint.setColor(mDisableColor);
        }
        canvas.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1, mPaint);
        super.onDraw(canvas);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        invalidate();
    }


}
