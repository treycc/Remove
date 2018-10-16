package com.jdp.hls.page.operate.back;

import android.content.Context;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.App;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.view.dialog.BaseDialog;
import com.kingja.supershapeview.view.SuperShapeEditText;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:TODO
 * Create Time:2018/10/15 0015 下午 6:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BackDialog extends BaseDialog implements BackNodeContract.View {
    @Inject
    BackNodePresenter backNodePresenter;
    private SuperShapeEditText set_dialog_reason;
    private TextView tv_dialog_receiveName;

    public BackDialog(Context context, String buildingId, String buildingType, String statusId) {
        super(context, buildingId,  buildingType,  statusId);
    }
    public BackDialog(Context context) {
        super(context);
    }
    @Override
    protected int getContentViewId() {
        return R.layout.dialog_back;
    }

    @Override
    public void initView() {
        DaggerBaseCompnent.builder()
                .appComponent(App.getContext().getAppComponent())
                .build()
                .inject(this);
        backNodePresenter.attachView(this);
        set_dialog_reason = findViewById(R.id.set_dialog_reason);
        tv_dialog_receiveName = findViewById(R.id.tv_dialog_receiveName);
    }

    @Override
    public void initNet() {
        backNodePresenter.getOperatePerson(buildingId,buildingType);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    public RequestBody getRequestBody() {
        String reason = set_dialog_reason.getText().toString().trim();
//        backNodePresenter.backNode(new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("buildingId", buildingId)
//                .addFormDataPart("buildingType", buildingType)
//                .addFormDataPart("statusId", statusId)
//                .addFormDataPart("Reason", reason)
//                .build());

        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("buildingId", buildingId)
                .addFormDataPart("buildingType", buildingType)
                .addFormDataPart("statusId", statusId)
                .addFormDataPart("Reason", reason)
                .build();
    }

    @Override
    public void onBackNodeSuccess() {

    }

    @Override
    public void onGetOperatePersonSuccess(String name) {
        tv_dialog_receiveName.setText(name);
    }
}
