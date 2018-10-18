package com.jdp.hls.page.operate.review;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.ReceiverSpinnerAdapter;
import com.jdp.hls.base.App;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.model.entiy.ReceivePerson;
import com.jdp.hls.view.KSpinner;
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
    private Spinner spinner_dialog_receivePerson;

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
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("buildingId", buildingId)
                .addFormDataPart("buildingType", buildingType)
                .addFormDataPart("statusId", statusId)
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
            ReceiverSpinnerAdapter receiverSpinnerAdapter = new ReceiverSpinnerAdapter(getContext(), receivePersonList);
            spinner_dialog_receivePerson.setAdapter(receiverSpinnerAdapter);
            spinner_dialog_receivePerson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ReceivePerson receivePerson = (ReceivePerson) parent.getItemAtPosition(position);
                    tv_dialog_receiveName.setText(receivePerson.getRealName());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }


    }
}
