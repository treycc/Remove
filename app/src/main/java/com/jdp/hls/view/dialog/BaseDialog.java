package com.jdp.hls.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;

import com.jdp.hls.R;
import com.jdp.hls.util.LogUtil;
import com.kingja.supershapeview.view.SuperShapeTextView;

import okhttp3.RequestBody;

/**
 * Description:TODO
 * Create Time:2018/10/15 0015 下午 6:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseDialog extends Dialog implements View.OnClickListener {
    protected Context context;
    protected SuperShapeTextView stv_confirm;
    protected SuperShapeTextView stv_cancle;
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
        LogUtil.e("BaseDialog", "buildingId:" + buildingId);
        LogUtil.e("BaseDialog", "buildingType:" + buildingType);
        LogUtil.e("BaseDialog", "statusId:" + statusId);
        LogUtil.e("BaseDialog", "groupId:" + groupId);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        stv_confirm = findViewById(R.id.stv_confirm);
        stv_cancle = findViewById(R.id.stv_cancle);
        stv_confirm.setOnClickListener(this);
        stv_cancle.setOnClickListener(this);
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
            case R.id.stv_confirm:
                if (onOperateConfirmListener != null) {
                    onOperateConfirmListener.onOperateConfirm(getRequestBody());
                    dismiss();
                }
                break;
            case R.id.stv_cancle:
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