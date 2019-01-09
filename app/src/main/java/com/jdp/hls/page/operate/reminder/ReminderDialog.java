package com.jdp.hls.page.operate.reminder;

import android.content.Context;

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
public class ReminderDialog extends BaseDialog implements ReminderNodeContract.View {

    private SuperShapeEditText set_dialog_reason;
    @Inject
    ReminderNodePresenter reminderNodePresenter;

    public ReminderDialog(Context context, String buildingId, String buildingType, String statusId) {
        super(context, buildingId, buildingType, statusId);
    }

    public ReminderDialog(Context context) {
        super(context);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.dialog_reminder;
    }

    @Override
    public void initView() {
        DaggerBaseCompnent.builder()
                .appComponent(App.getContext().getAppComponent())
                .build()
                .inject(this);
        reminderNodePresenter.attachView(this);
        set_dialog_reason = findViewById(R.id.set_dialog_reason);
    }

    @Override
    public void initNet() {

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
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("buildingId", buildingId)
                .addFormDataPart("buildingType", buildingType)
                .addFormDataPart("StatusId", statusId)
                .addFormDataPart("Reason", reason)
                .build();
    }

    @Override
    public void onReminderNodeSuccess() {

    }
}
