package com.jdp.hls.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.jdp.hls.R;

/**
 * Description:TODO
 * Create Time:2018/10/15 0015 下午 6:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseDialog extends AlertDialog implements View.OnClickListener {
    protected Context context;

    protected BaseDialog(Context context) {
        super(context, R.style.CustomAlertDialog);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initNet();
        initEvent();
        initData();

    }

    public abstract void initView();

    public abstract void initNet();

    public abstract void initEvent();

    public abstract void initData();

    public abstract void childClick(View v);

    @Override
    public void onClick(View v) {
        childClick(v);

    }

}