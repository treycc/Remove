package com.jdp.hls.page.operate.review;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.SimpleSpinnerAdapter;
import com.jdp.hls.base.App;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.model.entiy.ReceivePerson;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.view.dialog.BaseDialog;
import com.kingja.supershapeview.view.SuperShapeEditText;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:TODO
 * Create Time:2018/10/15 0015 下午 6:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReviewDialog extends BaseDialog implements ReviewNodeContract.View {

    @Inject
    ReviewNodePresenter reviewNodePresenter;
    private SuperShapeEditText set_dialog_reason;
    private TextView tv_dialog_receiveName;
    private AppCompatSpinner spinner_dialog_receivePerson;
    private int targetStatusId = -1;

    public ReviewDialog(Context context, String buildingId, String buildingType, String statusId) {
        super(context, buildingId, buildingType, statusId);
    }

    public ReviewDialog(Context context) {
        super(context);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.dialog_review;
    }

    @Override
    public void initView() {
        DaggerBaseCompnent.builder()
                .appComponent(App.getContext().getAppComponent())
                .build()
                .inject(this);
        reviewNodePresenter.attachView(this);
        set_dialog_reason = findViewById(R.id.set_dialog_reason);
        tv_dialog_receiveName = findViewById(R.id.tv_dialog_receiveName);
        spinner_dialog_receivePerson = findViewById(R.id.spinner_dialog_receivePerson);
    }

    @Override
    public void initNet() {
        LogUtil.e(TAG, "buildingId:" + buildingId + " buildingType:" + buildingType);
        reviewNodePresenter.getReviewReceiverList(buildingId, buildingType);
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
        LogUtil.e(TAG, "复查statusId:" + String.valueOf(targetStatusId));
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("buildingId", buildingId)
                .addFormDataPart("buildingType", buildingType)
                .addFormDataPart("statusId", String.valueOf(targetStatusId))
                .addFormDataPart("Reason", reason)
                .build();
    }

    @Override
    public void onReviewNodeSuccess() {

    }

    @Override
    public void onGetReviewReceiverListSuccess(List<ReceivePerson> receivePersonList) {
        if (receivePersonList != null && receivePersonList.size() > 0) {
            tv_dialog_receiveName.setText(receivePersonList.get(0).getRealName());
            targetStatusId = receivePersonList.get(0).getStatusId();
            SimpleSpinnerAdapter receiverSpinnerAdapter = new SimpleSpinnerAdapter(getContext(), receivePersonList);
            spinner_dialog_receivePerson.setAdapter(receiverSpinnerAdapter);
            spinner_dialog_receivePerson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ReceivePerson receivePerson = (ReceivePerson) parent.getItemAtPosition(position);
                    tv_dialog_receiveName.setText(receivePerson.getRealName());
                    targetStatusId = receivePerson.getStatusId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }
}
