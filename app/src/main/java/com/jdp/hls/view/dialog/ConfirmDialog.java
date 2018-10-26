package com.jdp.hls.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;

/**
 * Description:TODO
 * Create Time:2018/10/15 0015 下午 6:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ConfirmDialog extends Dialog implements View.OnClickListener {
    private String message = "";
    private String confirmText = "";
    private String cancelText = "";
    protected Context context;
    protected TextView tv_confirm;
    protected TextView tv_dialog_message;
    protected TextView tv_cancle;
    protected LinearLayout ll_cancel;
    private OnConfirmListener onConfirmListener;
    private OnCancelListener onCancelListener;

    public ConfirmDialog(@NonNull Context context) {
        super(context, R.style.CustomAlertDialog);
    }

    public ConfirmDialog(@NonNull Context context, String message, String confirmText, String cancelText,
                         OnConfirmListener
                                 onConfirmListener, OnCancelListener onCancelListener) {
        super(context, R.style.CustomAlertDialog);
        this.message = message;
        this.confirmText = confirmText;
        this.cancelText = cancelText;
        this.onConfirmListener = onConfirmListener;
        this.onCancelListener = onCancelListener;
    }

    public ConfirmDialog(@NonNull Context context, String message, OnConfirmListener
            onConfirmListener, OnCancelListener onCancelListener) {
        this(context, message, "确定", "取消", onConfirmListener, onCancelListener);
    }

    public ConfirmDialog(@NonNull Context context, String message, OnConfirmListener
            onConfirmListener) {
        this(context, message, "确定", "取消", onConfirmListener, null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comfirm);
        tv_confirm = findViewById(R.id.tv_confirm);
        tv_cancle = findViewById(R.id.tv_cancle);
        ll_cancel = findViewById(R.id.ll_cancel);
        tv_confirm.setText(confirmText);
        tv_cancle.setText(cancelText);
        tv_dialog_message = findViewById(R.id.tv_dialog_message);
        ll_cancel.setVisibility(onCancelListener == null ? View.GONE : View.VISIBLE);
        tv_dialog_message.setText(message);
        tv_confirm.setOnClickListener(this);
        tv_cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                if (onConfirmListener != null) {
                    onConfirmListener.onConfirm();
                }
                dismiss();
                break;
            case R.id.tv_cancle:
                if (onCancelListener != null) {
                    onCancelListener.onCancel();
                }
                dismiss();
                break;
            default:
                break;
        }
    }

    public interface OnConfirmListener {
        void onConfirm();
    }

    public interface OnCancelListener {
        void onCancel();
    }

}