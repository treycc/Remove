package com.jdp.hls.page.operate.send;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.App;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.dialog.BaseDialog;
import com.kingja.supershapeview.view.SuperShapeTextView;

import javax.inject.Inject;

import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/10/15 0015 下午 6:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SendDialog extends BaseDialog implements SendNodeContract.View {
    private String buildingId;
    private String buildingType;
    private String statusId;
    private String currentNodeName;

    @Inject
    SendNodePresenter sendNodePresenter;
    private TextView tv_nodeName;
    private TextView tv_receiveName;
    private SuperShapeTextView stv_confirm;
    private SuperShapeTextView stv_cancle;

    public SendDialog(Context context) {
        super(context);
    }

    public SendDialog(Context context, String buildingId, String buildingType, String statusId, String
            currentNodeName) {
        super(context);
        this.buildingId = buildingId;
        this.buildingType = buildingType;
        this.statusId = statusId;
        this.currentNodeName = currentNodeName;
    }

    @Override
    public void initView() {
        DaggerBaseCompnent.builder()
                .appComponent(App.getContext().getAppComponent())
                .build()
                .inject(this);
        sendNodePresenter.attachView(this);
        setContentView(R.layout.dialog_send);
        tv_nodeName = findViewById(R.id.tv_nodeName);
        tv_receiveName = findViewById(R.id.tv_receiveName);
        tv_receiveName = findViewById(R.id.tv);
        stv_confirm = findViewById(R.id.stv_confirm);
        stv_cancle = findViewById(R.id.stv_cancle);

    }

    @Override
    public void initNet() {
        sendNodePresenter.getNextNodePersonName(buildingId, buildingType);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        tv_nodeName.setText(currentNodeName);
        stv_confirm.setOnClickListener(this);
        stv_cancle.setOnClickListener(this);
    }

    @Override
    public void childClick(View v) {
        switch (v.getId()) {
            case R.id.stv_confirm:
                sendNodePresenter.sendNode(new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("buildingId", buildingId)
                        .addFormDataPart("buildingType", buildingType)
                        .addFormDataPart("statusId", statusId)
                        .build());
                break;
            case R.id.stv_cancle:
                dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void onGetNextNodePersonNameSuccess(String name) {
        tv_receiveName.setText(name);
    }

    @Override
    public void onSendNodeSuccess() {
        ToastUtil.showText("发送成功");
    }
}
