package com.jdp.hls.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jdp.hls.R;
import com.jdp.hls.base.App;
import com.jdp.hls.injector.component.AppComponent;

import butterknife.ButterKnife;

/**
 * Description:TODO
 * Create Time:2018/11/19 0019 下午 7:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class CommonDialog extends Dialog {
    protected OnConfirmListener onConfirmListener;
    protected OnCancelListener onCancelListener;

    public CommonDialog(@NonNull Context context) {
        super(context, R.style.CustomAlertDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initComponent(App.getContext().getAppComponent());
        initVariable();
        initView();
        initData();
        initNet();
    }


    public abstract int getLayoutId();

    protected abstract void initComponent(AppComponent appComponent);

    public abstract void initVariable();

    public abstract void initView();

    public abstract void initData();

    public abstract void initNet();

    public interface OnConfirmListener {
        void onConfirm();
    }

    public interface OnCancelListener {
        void onCancel();
    }

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    public void setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }
}
