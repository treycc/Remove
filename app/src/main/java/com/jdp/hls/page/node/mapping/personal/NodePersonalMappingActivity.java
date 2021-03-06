package com.jdp.hls.page.node.mapping.personal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodePersonalMapping;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.MathUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;

/**
 * Description:测绘出图-住宅
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodePersonalMappingActivity extends BaseNodeActivity implements NodePersonalMappingContract.View {

    @BindView(R.id.tv_mapping_realName)
    TextView tvMappingRealName;
    @BindView(R.id.et_mapping_totalBuildingArea)
    EnableEditText etTotalBuildingArea;
    @BindView(R.id.tv_mapping_propertyCertTotalArea)
    StringTextView tvMappingPropertyCertTotalArea;
    @BindView(R.id.et_mapping_totalNotRecordArea)
    EnableEditText etMappingTotalNotRecordArea;
    @BindView(R.id.tv_mapping_landCertTotalArea)
    StringTextView tvMappingLandCertTotalArea;
    @BindView(R.id.et_mapping_simpleHouseArea)
    EnableEditText etMappingSimpleHouseArea;
    @BindView(R.id.et_mapping_tanArea)
    EnableEditText etMappingTanArea;
    @BindView(R.id.et_mapping_shedArea)
    EnableEditText etMappingShedArea;
    @BindView(R.id.tv_mapping_date)
    TextView tvMappingDate;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.et_remark)
    EnableEditText etMeasureRemark;
    @Inject
    NodePersonalMappingPresenter nodePersonalMappingPresenter;
    @BindView(R.id.tv_mapping_companyName)
    StringTextView tvMappingCompanyName;
    @BindView(R.id.tv_mapping_confirmer)
    StringTextView tvMappingConfirmer;
    private int mapperId;

    @Override
    protected int getContentView() {
        return R.layout.activity_node_personal_mapping;
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
        nodePersonalMappingPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        super.initData();
//        tvMappingPropertyCertTotalArea.addTextChangedListener(calculateTextWatcher);
        etMappingTotalNotRecordArea.addTextChangedListener(calculateTextWatcher);
    }

    private TextWatcher calculateTextWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            calculateArea();
        }
    };

    private void calculateArea() {
        String propertyCertTotalAreaStr = tvMappingPropertyCertTotalArea.getText().toString().trim();
        String totalNotRecordAreaStr = etMappingTotalNotRecordArea.getText().toString().trim();
        double propertyCertTotalArea = propertyCertTotalAreaStr.startsWith(".") || TextUtils.isEmpty
                (propertyCertTotalAreaStr) ? 0d : Double.valueOf
                (propertyCertTotalAreaStr);
        double totalNotRecordArea = totalNotRecordAreaStr.startsWith(".") || TextUtils.isEmpty(totalNotRecordAreaStr)
                ? 0d : Double.valueOf
                (totalNotRecordAreaStr);
        etTotalBuildingArea.setText(String.valueOf(MathUtil.add(propertyCertTotalArea, totalNotRecordArea)));
    }

    @Override
    public void initNet() {
        nodePersonalMappingPresenter.getPersonalMapping(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        tvMappingPropertyCertTotalArea.setEnabled(allowEdit);
        etMappingTotalNotRecordArea.setEnabled(allowEdit);
        tvMappingLandCertTotalArea.setEnabled(allowEdit);
        etMappingSimpleHouseArea.setEnabled(allowEdit);
        etMappingTanArea.setEnabled(allowEdit);
        etMappingShedArea.setEnabled(allowEdit);
        etMeasureRemark.setEnabled(allowEdit);
        etTotalBuildingArea.setEnabled(allowEdit);
        setDateSelector(ivDateSelector, tvMappingDate, allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String remark = etMeasureRemark.getText().toString().trim();
        String totalNotRecordArea = etMappingTotalNotRecordArea.getText().toString().trim();
        String propertyCertTotalArea = tvMappingPropertyCertTotalArea.getText().toString().trim();
        String landCertTotalArea = tvMappingLandCertTotalArea.getText().toString().trim();
        String simpleHouseArea = etMappingSimpleHouseArea.getText().toString().trim();
        String shedArea = etMappingShedArea.getText().toString().trim();
        String tanArea = etMappingTanArea.getText().toString().trim();
        String mapDate = tvMappingDate.getText().toString().trim();
        String totalBuildingArea = etTotalBuildingArea.getText().toString().trim();
        nodePersonalMappingPresenter.modifyPersonalMapping(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("houseId", mBuildingId)
                .addFormDataPart("MapperId", String.valueOf(mapperId))
                .addFormDataPart("TotalNotRecordArea", totalNotRecordArea)
                .addFormDataPart("PropertyCertTotalArea", propertyCertTotalArea)
                .addFormDataPart("LandCertTotalArea", landCertTotalArea)
                .addFormDataPart("SimpleHouseArea", simpleHouseArea)
                .addFormDataPart("ShedArea", shedArea)
                .addFormDataPart("TanArea", tanArea)
                .addFormDataPart("MapDate", mapDate)
                .addFormDataPart("TotalBuildingArea", totalBuildingArea)
                .addFormDataPart("Remark", remark)
                .build());
    }

    @Override
    public void onGetPersonalMappingSuccess(NodePersonalMapping nodePersonalMapping) {
        allowEdit = nodePersonalMapping.isAllowEdit();
        setEditable(allowEdit);
        mapperId = nodePersonalMapping.getMapperId();
        tvMappingRealName.setText(nodePersonalMapping.getRealName());
        tvMappingPropertyCertTotalArea.setString(nodePersonalMapping.getPropertyCertTotalArea());
        etMappingTotalNotRecordArea.setString(nodePersonalMapping.getTotalNotRecordArea());
        tvMappingLandCertTotalArea.setString(nodePersonalMapping.getLandCertTotalArea());
        etMappingSimpleHouseArea.setString(nodePersonalMapping.getSimpleHouseArea());
        etMappingTanArea.setString(nodePersonalMapping.getTanArea());
        etMappingShedArea.setString(nodePersonalMapping.getShedArea());
        etMeasureRemark.setString(nodePersonalMapping.getRemark());
        etTotalBuildingArea.setString(nodePersonalMapping.getTotalBuildingArea());
        tvMappingConfirmer.setString(nodePersonalMapping.getConfirmer());
        tvMappingCompanyName.setString(nodePersonalMapping.getCompanyName());
        tvMappingDate.setText(DateUtil.getShortDate(nodePersonalMapping.getMapDate()));
//        calculateArea();
        rvPhotoPreview.setData(nodePersonalMapping.getFiles(), getFileConfig(), allowEdit);
    }

    @Override
    public void onModifyPersonalMappingSuccess() {
        showSuccessDialogAndFinish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
