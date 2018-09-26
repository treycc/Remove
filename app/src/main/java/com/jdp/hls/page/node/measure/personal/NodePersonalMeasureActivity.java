package com.jdp.hls.page.node.measure.personal;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodePersonalMeasure;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.PreviewRecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:入户丈量-个人
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodePersonalMeasureActivity extends BaseTitleActivity implements NodePersonalMeasureContract.View {
    @BindView(R.id.tv_measure_name)
    TextView tvMeasureName;
    @BindView(R.id.et_measure_address)
    EnableEditText etMeasureAddress;
    @BindView(R.id.tv_measure_date)
    TextView tvMeasureDate;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    @BindView(R.id.ll_photo_preview)
    LinearLayout llPhotoPreview;
    @BindView(R.id.et_measure_remark)
    EditText etMeasureRemark;
    @Inject
    NodePersonalMeasurePresenter nodePersonalMeasurePresenter;
    @BindView(R.id.ll_measure_dateSelector)
    LinearLayout llMeasureDateSelector;
    private boolean allowEdit;

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_node_measure;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "入户丈量";
    }

    @Override
    protected void initView() {
        nodePersonalMeasurePresenter.attachView(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {

    }


    @Override
    public void onGetPersonalMeasureSuccess(NodePersonalMeasure nodePersonalMeasure) {
        tvMeasureName.setText(nodePersonalMeasure.getRealName());
        etMeasureAddress.setText(nodePersonalMeasure.getAddress());
        tvMeasureDate.setText(nodePersonalMeasure.getMeaDate());
        allowEdit = nodePersonalMeasure.isAllowEdit();
        measurerId = nodePersonalMeasure.getMeasurerId();
        setEditable();
    }

    private void setEditable() {
        if (allowEdit) {
            setRightClick("保存", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    RequestBody requestBody = getRequestBody();
                    nodePersonalMeasurePresenter.modifyPersonalMeasure(requestBody);
                }
            });
        } else {
            //全部禁用
        }
    }

    private String measureDate;
    private String houseId;
    private int measurerId;

    @NonNull
    private RequestBody getRequestBody() {
        String remark = etMeasureRemark.getText().toString().trim();
        String address = etMeasureAddress.getText().toString().trim();
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", houseId)
                .addFormDataPart("MeasurerId", String.valueOf(measurerId))
                .addFormDataPart("MeaDate", measureDate)
                .addFormDataPart("Address", address)
                .addFormDataPart("Remark", remark).build();
    }

    @Override
    public void onModifyPersonalMeasureSuccess() {

    }

}
