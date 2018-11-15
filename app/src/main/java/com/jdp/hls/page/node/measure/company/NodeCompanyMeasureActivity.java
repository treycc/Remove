package com.jdp.hls.page.node.measure.company;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodeCompanyMeasure;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.util.DateUtil;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:入户丈量-个人
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyMeasureActivity extends BaseNodeActivity implements NodeCompanyMeasureContract.View {
    @BindView(R.id.tv_measure_name)
    TextView tvMeasureName;
    @BindView(R.id.tv_measure_date)
    TextView tvMeasureDate;
    @BindView(R.id.et_remark)
    EditText etMeasureRemark;
    @Inject
    NodeCompanyMeasurePresenter nodeCompanyMeasurePresenter;
    @BindView(R.id.ll_measure_dateSelector)
    LinearLayout llMeasureDateSelector;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.tv_measure_companyName)
    TextView tvMeasureCompanyName;

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
        nodeCompanyMeasurePresenter.attachView(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initNet() {
        nodeCompanyMeasurePresenter.getCompanyMeasure(mBuildingId);
    }

    @Override
    public void onGetCompanyMeasureSuccess(NodeCompanyMeasure nodeCompanyMeasure) {
        allowEdit = nodeCompanyMeasure.isAllowEdit();
        setEditable(allowEdit);
        tvMeasureName.setText(nodeCompanyMeasure.getRealName());
        etMeasureRemark.setText(nodeCompanyMeasure.getRemark());
        tvMeasureCompanyName.setText(nodeCompanyMeasure.getCompanyName());
        tvMeasureDate.setText(DateUtil.getShortDate(nodeCompanyMeasure.getMeaDate()));
        rvPhotoPreview.setData(nodeCompanyMeasure.getFiles(), getFileConfig(), allowEdit);
    }

    @Override
    public void onModifyCompanyMeasureSuccess() {
        showSuccessDialogAndFinish();
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        etMeasureRemark.setEnabled(allowEdit);
        setDateSelector(ivDateSelector, tvMeasureDate, allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String remark = etMeasureRemark.getText().toString().trim();
        String measureDate = tvMeasureDate.getText().toString().trim();
        nodeCompanyMeasurePresenter.modifyCompanyMeasure(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("MeaDate", measureDate)
                .addFormDataPart("Remark", remark)
                .build());
    }

}
