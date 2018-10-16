package com.jdp.hls.page.operate.send;

import android.content.Context;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.App;
import com.jdp.hls.base.BaseBasicActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.model.entiy.ReceivePerson;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.dialog.BaseDialog;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:TODO
 * Create Time:2018/10/15 0015 下午 6:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SendDialog extends BaseDialog implements SendNodeContract.View {
    @Inject
    SendNodePresenter sendNodePresenter;
    private TextView tv_nodeName;
    private TextView tv_receiveName;

    public SendDialog(Context context) {
        super(context);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.dialog_send;
    }

    public SendDialog(Context context, String buildingId, String buildingType, String statusId) {
        super(context, buildingId, buildingType, statusId);
    }


    @Override
    public void initView() {
        DaggerBaseCompnent.builder()
                .appComponent(App.getContext().getAppComponent())
                .build()
                .inject(this);
        sendNodePresenter.attachView(this);
        tv_nodeName = findViewById(R.id.tv_nodeName);
        tv_receiveName = findViewById(R.id.tv_receiveName);
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

    }

    @Override
    public RequestBody getRequestBody() {
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("buildingId", buildingId)
                .addFormDataPart("buildingType", buildingType)
                .addFormDataPart("statusId", statusId)
                .build();
    }

    @Override
    public void onGetNextNodePersonNameSuccess(ReceivePerson receivePerson) {
        tv_receiveName.setText(receivePerson.getRealName());
        tv_nodeName.setText(receivePerson.getStatusDesc());
    }

    @Override
    public void onSendNodeSuccess() {
        ToastUtil.showText("发送成功");
    }
}
