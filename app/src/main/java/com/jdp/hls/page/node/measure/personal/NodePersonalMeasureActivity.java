package com.jdp.hls.page.node.measure.personal;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodePersonalMeasure;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.PreviewRecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:入户丈量-个人
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodePersonalMeasureActivity extends BaseNodeActivity implements NodePersonalMeasureContract.View {
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
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    private String measureDate;
    private int measurerId;

    @Override
    public void initVariable() {
        super.initVariable();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_node_measure;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
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
        rvPhotoPreview.create();
    }

    @Override
    protected void initNet() {
        nodePersonalMeasurePresenter.getPersonalMeasure(mBuildingId);
    }

    @Override
    public void onGetPersonalMeasureSuccess(NodePersonalMeasure nodePersonalMeasure) {
        setEditable(nodePersonalMeasure.isAllowEdit());
        tvMeasureName.setText(nodePersonalMeasure.getRealName());
        etMeasureRemark.setText(nodePersonalMeasure.getRemark());
        etMeasureAddress.setText(nodePersonalMeasure.getAddress());
        tvMeasureDate.setText(DateUtil.getShortDate(nodePersonalMeasure.getMeaDate()));
        allowEdit = nodePersonalMeasure.isAllowEdit();
        measurerId = nodePersonalMeasure.getMeasurerId();

    }

    @Override
    public void onModifyPersonalMeasureSuccess() {
        showSaveSuccess();
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        etMeasureAddress.setEnabled(allowEdit);
        etMeasureRemark.setEnabled(allowEdit);
        createDateSelector(ivDateSelector, tvMeasureDate, allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String remark = etMeasureRemark.getText().toString().trim();
        String address = etMeasureAddress.getText().toString().trim();
        nodePersonalMeasurePresenter.modifyPersonalMeasure(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", mBuildingId)
                .addFormDataPart("MeasurerId", String.valueOf(measurerId))
                .addFormDataPart("MeaDate", measureDate)
                .addFormDataPart("Address", address)
                .addFormDataPart("Remark", remark).build());
    }

}
