package com.jdp.hls.page.node.age.personal;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.model.entiy.NodePersonalAge;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.page.otherarea.list.OtherAreaListActivity;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MultipartBody;

/**
 * Description:年限鉴定-个人
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodePersonalAgeActivity extends BaseNodeActivity implements NodePersonalAgeContract.View {
    @Inject
    NodePersonalAgePresenter nodePersonalAgePresenter;
    @BindView(R.id.tv_age_totalNotRecordArea)
    StringTextView tvAgeTotalNotRecordArea;
    @BindView(R.id.tv_age_landCertTotalArea)
    StringTextView tvAgeLandCertTotalArea;
    @BindView(R.id.tv_age_buildOccupyArea)
    StringTextView tvAgeBuildOccupyArea;
    @BindView(R.id.tv_age_simpleHouseMeasure)
    StringTextView tvAgeSimpleHouseMeasure;
    @BindView(R.id.tv_age_tanArea)
    StringTextView tvAgeTanArea;
    @BindView(R.id.tv_age_realName)
    TextView tvAgeRealName;
    @BindView(R.id.tv_age_affirmUnit)
    TextView tvAgeAffirmUnit;
    @BindView(R.id.tv_age_date)
    TextView tvAgeDate;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.et_age_area8486)
    EnableEditText etAgeArea8486;
    @BindView(R.id.et_age_area94)
    EnableEditText etAgeArea94;
    @BindView(R.id.et_age_area98)
    EnableEditText etAgeArea98;
    @BindView(R.id.et_age_area90y)
    EnableEditText etAgeArea90y;
    @BindView(R.id.et_age_area90n)
    EnableEditText etAgeArea90n;
    @BindView(R.id.et_age_area8690)
    EnableEditText etAgeArea8690;
    @BindView(R.id.et_age_area98n)
    EnableEditText etAgeArea98n;
    @BindView(R.id.et_age_area98y)
    EnableEditText etAgeArea98y;
    @BindView(R.id.et_age_asLegitimateArea)
    EnableEditText etAgeAsLegitimateArea;
    @BindView(R.id.et_age_animalHouseArea)
    EnableEditText etAgeAnimalHouseArea;
    @BindView(R.id.et_age_basement)
    EnableEditText etAgeBasement;
    @BindView(R.id.et_age_simpleHouseAge)
    EnableEditText etAgeSimpleHouseAge;
    @BindView(R.id.et_age_totalLegitimateArea)
    EnableEditText etAgeTotalLegitimateArea;
    @BindView(R.id.et_age_totalIllegalArea)
    EnableEditText etAgeTotalIllegalArea;
    @BindView(R.id.et_ageRemark)
    EnableEditText etAgeRemark;
    @BindView(R.id.et_age_subsidiaryHouse)
    EnableEditText etAgeSubsidiaryHouse;
    @BindView(R.id.ll_operateContent)
    LinearLayout llOperateContent;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    @BindView(R.id.et_age_area98l)
    EnableEditText etAgeArea98l;
    private String identifierId;
    private boolean isShowNotRecordArea;
    private List<ImgInfo> files;

    @OnClick({R.id.rl_otherArea})
    public void rl_protocol_otherArea(View view) {
        switch (view.getId()) {
            case R.id.rl_otherArea:
                OtherAreaListActivity.goActivity(this, mBuildingId, String.valueOf(Status.BuildingType
                        .PERSONAL), allowEdit);
                break;
            default:
                break;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_node_personal_age;
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
        return getString(R.string.title_node_age);
    }

    @Override
    protected void initView() {
        nodePersonalAgePresenter.attachView(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void initNet() {
        nodePersonalAgePresenter.getPersonalAge(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        boolean isOperatorAccount = SpSir.getInstance().isOperatorAccount();
        setDateSelector(ivDateSelector, tvAgeDate, !isOperatorAccount && allowEdit);
        tvAgeTotalNotRecordArea.setEnabled(!isOperatorAccount && allowEdit);
        tvAgeLandCertTotalArea.setEnabled(!isOperatorAccount && allowEdit);
        tvAgeBuildOccupyArea.setEnabled(!isOperatorAccount && allowEdit);
        tvAgeSimpleHouseMeasure.setEnabled(!isOperatorAccount && allowEdit);
        tvAgeTanArea.setEnabled(!isOperatorAccount && allowEdit);
        etAgeArea8486.setEnabled(!isOperatorAccount && allowEdit);
        etAgeArea94.setEnabled(!isOperatorAccount && allowEdit);
        etAgeArea98.setEnabled(!isOperatorAccount && allowEdit);
        etAgeArea98l.setEnabled(!isOperatorAccount && allowEdit);
        etAgeRemark.setEnabled(!isOperatorAccount && allowEdit);
        rvPhotoPreview.setData(files, getFileConfig(), !isOperatorAccount && allowEdit);

        llOperateContent.setVisibility(isShowNotRecordArea ? View.VISIBLE : View.GONE);
        etAgeAnimalHouseArea.setEnabled(allowEdit);
        etAgeBasement.setEnabled(allowEdit);
        etAgeSimpleHouseAge.setEnabled(allowEdit);
        etAgeSubsidiaryHouse.setEnabled(allowEdit);
        etAgeTotalIllegalArea.setEnabled(allowEdit);
        etAgeTotalLegitimateArea.setEnabled(allowEdit);
        etAgeArea90y.setEnabled(allowEdit);
        etAgeArea90n.setEnabled(allowEdit);
        etAgeArea8690.setEnabled(allowEdit);
        etAgeArea98n.setEnabled(allowEdit);
        etAgeArea98y.setEnabled(allowEdit);
        etAgeAsLegitimateArea.setEnabled(allowEdit);
        etRemark.setEnabled(allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String idenDate = tvAgeDate.getText().toString().trim();
        String area8486 = etAgeArea8486.getText().toString().trim();
        String area94 = etAgeArea94.getText().toString().trim();
        String area98 = etAgeArea98.getText().toString().trim();
        String area90y = etAgeArea90y.getText().toString().trim();
        String area90n = etAgeArea90n.getText().toString().trim();
        String area8690 = etAgeArea8690.getText().toString().trim();
        String area98n = etAgeArea98n.getText().toString().trim();
        String area98y = etAgeArea98y.getText().toString().trim();
        String area98l = etAgeArea98l.getText().toString().trim();
        String asLegitimateArea = etAgeAsLegitimateArea.getText().toString().trim();
        String animalHouseArea = etAgeAnimalHouseArea.getText().toString().trim();
        String basement = etAgeBasement.getText().toString().trim();
        String simpleHouseAge = etAgeSimpleHouseAge.getText().toString().trim();
        String simpleHouseMeasure = tvAgeSimpleHouseMeasure.getText().toString().trim();
        String totalLegitimateArea = etAgeTotalLegitimateArea.getText().toString().trim();
        String totalIllegalArea = etAgeTotalIllegalArea.getText().toString().trim();
        String subsidiaryHouse = etAgeSubsidiaryHouse.getText().toString().trim();
        String ageRemark = etAgeRemark.getText().toString().trim();
        String remark = etRemark.getText().toString().trim();
        nodePersonalAgePresenter.modifyPersonalAge(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", mBuildingId)
                .addFormDataPart("IdentifierId", String.valueOf(identifierId))
                .addFormDataPart("SimpleHouseArea", simpleHouseMeasure)
                .addFormDataPart("IdenDate", idenDate)
                .addFormDataPart("Area84_86", area8486)
                .addFormDataPart("Area94", area94)
                .addFormDataPart("Area98", area98)
                .addFormDataPart("Area90Y", area90y)
                .addFormDataPart("Area90N", area90n)
                .addFormDataPart("Area86_90", area8690)
                .addFormDataPart("Area98Y", area98y)
                .addFormDataPart("Area98N", area98n)
                .addFormDataPart("Area98Building", area98l)
                .addFormDataPart("AsLegitimateArea", asLegitimateArea)
                .addFormDataPart("Animal", animalHouseArea)
                .addFormDataPart("Basement", basement)
                .addFormDataPart("SimpleHouse", simpleHouseAge)
                .addFormDataPart("TotalLegitimateArea", totalLegitimateArea)
                .addFormDataPart("TotalIllegalArea", totalIllegalArea)
                .addFormDataPart("SubsidiaryHouse", subsidiaryHouse)
                .addFormDataPart("Remark", ageRemark)
                .addFormDataPart("RemarkOperator", remark)
                .build());
    }

    @Override
    public void onGetPersonalAgeSuccess(NodePersonalAge nodePersonalAge) {
        allowEdit = nodePersonalAge.isAllowEdit();
        isShowNotRecordArea = nodePersonalAge.isShowNotRecordArea();
        files = nodePersonalAge.getFiles();
        setEditable(allowEdit);
        identifierId = nodePersonalAge.getIdentifierId();
        tvAgeRealName.setText(nodePersonalAge.getRealName());
        tvAgeTotalNotRecordArea.setString(nodePersonalAge.getTotalNotRecordArea());
        tvAgeLandCertTotalArea.setString(nodePersonalAge.getLandCertTotalArea());
        tvAgeBuildOccupyArea.setString(nodePersonalAge.getBuildOccupyArea());
        tvAgeSimpleHouseMeasure.setString(nodePersonalAge.getSimpleHouseArea());
        etAgeSimpleHouseAge.setString(nodePersonalAge.getSimpleHouse());
        tvAgeAffirmUnit.setText(nodePersonalAge.getCompanyName());
        tvAgeTanArea.setString(nodePersonalAge.getTanArea());
        etAgeArea8486.setString(nodePersonalAge.getArea84_86());
        etAgeArea94.setString(nodePersonalAge.getArea94());
        etAgeArea98.setString(nodePersonalAge.getArea98());
        etAgeArea90y.setString(nodePersonalAge.getArea90Y());
        etAgeArea90n.setString(nodePersonalAge.getArea90N());
        etAgeArea8690.setString(nodePersonalAge.getArea86_90());
        etAgeArea98n.setString(nodePersonalAge.getArea98N());
        etAgeArea98y.setString(nodePersonalAge.getArea98Y());
        etAgeArea98l.setString(nodePersonalAge.getArea98Building());
        etAgeSubsidiaryHouse.setString(nodePersonalAge.getSubsidiaryHouse());
        etAgeAsLegitimateArea.setString(nodePersonalAge.getAsLegitimateArea());
        etAgeAnimalHouseArea.setString(nodePersonalAge.getAnimal());
        etAgeBasement.setString(nodePersonalAge.getBasement());
        etAgeTotalLegitimateArea.setString(nodePersonalAge.getTotalLegitimateArea());
        etAgeTotalIllegalArea.setString(nodePersonalAge.getTotalIllegalArea());
        etAgeRemark.setString(nodePersonalAge.getRemark());
        etRemark.setString(nodePersonalAge.getRemarkOperator());
        tvAgeDate.setText(DateUtil.getShortDate(nodePersonalAge.getIdenDate()));


    }

    @Override
    public void onModifyPersonalAgeSuccess() {
        showSuccessDialogAndFinish();
    }
}
