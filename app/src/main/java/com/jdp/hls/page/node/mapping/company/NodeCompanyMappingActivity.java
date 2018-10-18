package com.jdp.hls.page.node.mapping.company;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodeCompanyMapping;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.PreviewRecyclerView;
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
    @BindView(R.id.et_mapping_shedArea)
    EnableEditText etMappingShedArea;
    @BindView(R.id.et_mapping_totalBuildingArea)
    EnableEditText etMappingTotalBuildingArea;
    @BindView(R.id.tv_mapping_totalLegalArea)
    StringTextView tvMappingTotalLegalArea;
    @BindView(R.id.et_mapping_appurtenanceArea)
    EnableEditText etMappingAppurtenanceArea;
    @BindView(R.id.tv_mapping_companyName)
    StringTextView tvMappingCompanyName;
    @BindView(R.id.tv_mapping_address)
    StringTextView tvMappingAddress;
    @BindView(R.id.tv_mapping_date)
    TextView tvMappingDate;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;

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
    protected void initNet() {
        nodeCompanyMappingPresenter.getCompanyMapping(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        etMappingTotalNotRecordArea.setEnabled(allowEdit);
        etMappingSimpleHouseArea.setEnabled(allowEdit);
        etMappingShedArea.setEnabled(allowEdit);
        etMappingTotalBuildingArea.setEnabled(allowEdit);
        tvMappingTotalLegalArea.setEnabled(allowEdit);
        etMappingAppurtenanceArea.setEnabled(allowEdit);
        setDateSelector(ivDateSelector, tvMappingDate, allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String totalNotRecordArea = etMappingTotalNotRecordArea.getText().toString().trim();
        String simpleHouseArea = etMappingSimpleHouseArea.getText().toString().trim();
        String shedArea = etMappingShedArea.getText().toString().trim();
        String appurtenanceArea = etMappingAppurtenanceArea.getText().toString().trim();
        String mapDate = tvMappingDate.getText().toString().trim();
        String totalBuildingArea = etMappingTotalBuildingArea.getText().toString().trim();
        String remark = etRemark.getText().toString().trim();
        nodeCompanyMappingPresenter.modifyCompanyMapping(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("TotalNotRecordArea", totalNotRecordArea)
                .addFormDataPart("SimpleHouseArea", simpleHouseArea)
                .addFormDataPart("ShedArea", shedArea)
                .addFormDataPart("AppurtenanceArea", appurtenanceArea)
                .addFormDataPart("MapDate", mapDate)
                .addFormDataPart("TotalBuildingArea", totalBuildingArea)
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
        etMappingShedArea.setString(nodeCompanyMapping.getShedArea());
        etMappingTotalBuildingArea.setString(nodeCompanyMapping.getTotalBuildingArea());
        tvMappingTotalLegalArea.setString(nodeCompanyMapping.getTotalLegalArea());
        etMappingAppurtenanceArea.setString(nodeCompanyMapping.getAppurtenanceArea());
        etRemark.setString(nodeCompanyMapping.getRemark());
        tvMappingCompanyName.setText(nodeCompanyMapping.getCompanyName());
        tvMappingAddress.setText(nodeCompanyMapping.getAddress());
        tvMappingDate.setText(DateUtil.getShortDate(nodeCompanyMapping.getMapDate()));
        rvPhotoPreview.setData(nodeCompanyMapping.getFiles(), getFileConfig(), this.allowEdit);
    }

    @Override
    public void onModifyCompanyMappingSuccess() {
        showSuccessAndFinish();
    }
}
