package com.jdp.hls.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.jdp.hls.R;

import okhttp3.RequestBody;

/**
 * Description:TODO
 * Create Time:2018/10/15 0015 下午 6:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseDialog extends Dialog implements View.OnClickListener {
    protected Context context;
    protected TextView tv_confirm;
    protected TextView tv_cancle;
    protected String buildingId;
    protected String buildingType;
    protected String statusId;
    protected String groupId;
    private OnOperateConfirmListener onOperateConfirmListener;

    public BaseDialog(@NonNull Context context) {
        super(context, R.style.CustomAlertDialog);
    }

    public BaseDialog(Context context, String buildingId, String buildingType, String statusId) {
        this(context, buildingId, buildingType, statusId, "");
    }

    public BaseDialog(Context context, String buildingId, String buildingType, String statusId, String groupId) {
        super(context, R.style.CustomAlertDialog);
        this.buildingId = buildingId;
        this.buildingType = buildingType;
        this.statusId = statusId;
        this.groupId = groupId;
    }

    public void setData(String buildingId, String buildingType, String statusId) {
        setData(buildingId, buildingType, statusId, "");
    }

    public void setData(String buildingId, String buildingType, String statusId, String groupId) {
        this.buildingId = buildingId;
        this.buildingType = buildingType;
        this.statusId = statusId;
        this.groupId = groupId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        tv_confirm = findViewById(R.id.tv_confirm);
        tv_cancle = findViewById(R.id.tv_cancle);
        tv_confirm.setOnClickListener(this);
        tv_cancle.setOnClickListener(this);
        initView();
        initNet();
        initEvent();
        initData();
    }

    protected abstract int getContentViewId();

    protected abstract void initView();

    protected abstract void initNet();

    protected abstract void initEvent();

    protected abstract void initData();

    protected void childClick(View v) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                if (onOperateConfirmListener != null) {
                    onOperateConfirmListener.onOperateConfirm(getRequestBody());
                    dismiss();
                }
                break;
            case R.id.tv_cancle:
                dismiss();
                break;
            default:
                break;
        }
        childClick(v);
    }

    public abstract RequestBody getRequestBody();

    public interface OnOperateConfirmListener {
        void onOperateConfirm(RequestBody requestBody);
    }

    public void setOnOperateConfirmListener(OnOperateConfirmListener onOperateConfirmListener) {
        this.onOperateConfirmListener = onOperateConfirmListener;
    }

}