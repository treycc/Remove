package com.jdp.hls.page.node.mapping.company;

import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodeCompanyMapping;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:测绘出图-企业
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyMappingActivity extends BaseNodeActivity implements NodeCompanyMappingContract.View {
    @Inject
    NodeCompanyMappingPresenter nodeCompanyMappingPresenter;
    @BindView(R.id.tv_mapping_realName)
    StringTextView tvMappingRealName;
    @BindView(R.id.et_mapping_totalNotRecordArea)
    EnableEditText etMappingTotalNotRecordArea;
    @BindView(R.id.et_mapping_simpleHouseArea)
    EnableEditText etMappingSimpleHouseArea;
    @BindView(R.id.et_mapping_totalBuildingArea)
    EnableEditText etMappingTotalBuildingArea;
    @BindView(R.id.et_mapping_totalLegalArea)
    EnableEditText etMappingTotalLegalArea;
    @BindView(R.id.et_mapping_appurtenanceArea)
    EnableEditText etMappingAppurtenanceArea;
    @BindView(R.id.tv_mapping_companyName)
    StringTextView tvMappingCompanyName;
    @BindView(R.id.tv_mapping_date)
    TextView tvMappingDate;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    @BindView(R.id.et_mapping_currentOccupyArea)
    EnableEditText etMappingCurrentOccupyArea;
    @BindView(R.id.tv_mapping_confirmer)
    StringTextView tvMappingConfirmer;
    @BindView(R.id.tv_mapping_estateCertPropertyArea)
    StringTextView tvMappingEstateCertPropertyArea;
    @BindView(R.id.tv_mapping_estateCertLandArea)
    StringTextView tvMappingEstateCertLandArea;
    @BindView(R.id.et_mapping_otherLandArea)
    EnableEditText etMappingOtherLandArea;

    @Override
    protected int getContentView() {
        return R.layout.activity_node_company_mapping;
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
        return "测绘出图";
    }

    @Override
    protected void initView() {
        nodeCompanyMappingPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }


    @Override
    public void initNet() {
        nodeCompanyMappingPresenter.getCompanyMapping(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        etMappingTotalNotRecordArea.setEnabled(allowEdit);
        etMappingSimpleHouseArea.setEnabled(allowEdit);
        etMappingTotalBuildingArea.setEnabled(allowEdit);
        etMappingTotalLegalArea.setEnabled(allowEdit);
        etMappingAppurtenanceArea.setEnabled(allowEdit);
        etMappingOtherLandArea.setEnabled(allowEdit);
        etMappingCurrentOccupyArea.setEnabled(allowEdit);
        etRemark.setEnabled(allowEdit);
        setDateSelector(ivDateSelector, tvMappingDate, allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String totalNotRecordArea = etMappingTotalNotRecordArea.getText().toString().trim();
        String simpleHouseArea = etMappingSimpleHouseArea.getText().toString().trim();
        String appurtenanceArea = etMappingAppurtenanceArea.getText().toString().trim();
        String mapDate = tvMappingDate.getText().toString().trim();
        String totalBuildingArea = etMappingTotalBuildingArea.getText().toString().trim();
        String otherLandArea = etMappingOtherLandArea.getText().toString().trim();
        String currentOccupyArea = etMappingCurrentOccupyArea.getText().toString().trim();
        String totalLegalArea = etMappingTotalLegalArea.getText().toString().trim();
        String remark = etRemark.getText().toString().trim();
        nodeCompanyMappingPresenter.modifyCompanyMapping(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("TotalNotRecordArea", totalNotRecordArea)
                .addFormDataPart("SimpleHouseArea", simpleHouseArea)
                .addFormDataPart("AppurtenanceArea", appurtenanceArea)
                .addFormDataPart("MapDate", mapDate)
                .addFormDataPart("TotalBuildingArea", totalBuildingArea)
                .addFormDataPart("TotalLegalArea", totalLegalArea)
                .addFormDataPart("OtherLandArea", otherLandArea)
                .addFormDataPart("CurrentOccupyArea", currentOccupyArea)
                .addFormDataPart("Remark", remark)
                .build());
    }

    @Override
    public void onGetCompanyMappingSuccess(NodeCompanyMapping nodeCompanyMapping) {
        allowEdit = nodeCompanyMapping.isAllowEdit();
        setEditable(allowEdit);
        tvMappingRealName.setText(nodeCompanyMapping.getRealName());
        etMappingTotalNotRecordArea.setString(nodeCompanyMapping.getTotalNotRecordArea());
        etMappingSimpleHouseArea.setString(nodeCompanyMapping.getSimpleHouseArea());
        etMappingTotalBuildingArea.setString(nodeCompanyMapping.getTotalBuildingArea());
        etMappingTotalLegalArea.setString(nodeCompanyMapping.getTotalLegalArea());
        etMappingAppurtenanceArea.setString(nodeCompanyMapping.getAppurtenanceArea());

        tvMappingConfirmer.setString(nodeCompanyMapping.getConfirmer());
        tvMappingEstateCertLandArea.setString(nodeCompanyMapping.getEstateCertLandArea());
        tvMappingEstateCertPropertyArea.setString(nodeCompanyMapping.getEstateCertPropertyArea());
        etMappingOtherLandArea.setString(nodeCompanyMapping.getOtherLandArea());
        etMappingCurrentOccupyArea.setString(nodeCompanyMapping.getCurrentOccupyArea());

        etRemark.setString(nodeCompanyMapping.getRemark());
        tvMappingCompanyName.setText(nodeCompanyMapping.getCompanyName());
        tvMappingDate.setText(DateUtil.getShortDate(nodeCompanyMapping.getMapDate()));
        rvPhotoPreview.setData(nodeCompanyMapping.getFiles(), getFileConfig(), this.allowEdit);
    }

    @Override
    public void onModifyCompanyMappingSuccess() {
        showSuccessDialogAndFinish();
    }
}
