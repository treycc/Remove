package com.jdp.hls.page.node.evaluate.company;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodeCompanyEvaluate;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.PreviewRecyclerView;
import com.jdp.hls.view.StringTextView;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:入户评估-企业
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyEvaluateActivity extends BaseNodeActivity implements NodeCompanyEvaluateContract.View {
    @Inject
    NodeCompanyEvaluatePresenter nodeCompanyEvaluatePresenter;
    @BindView(R.id.tv_mapping_realName)
    StringTextView tvMappingRealName;
    @BindView(R.id.tv_totalMoney)
    StringTextView tvTotalMoney;
    @BindView(R.id.et_evaluate_nonMobileDevicePay)
    EnableEditText etEvaluateNonMobileDevicePay;
    @BindView(R.id.et_evaluate_mobileDevicePay)
    EnableEditText etEvaluateMobileDevicePay;
    @BindView(R.id.tv_totalProperty)
    StringTextView tvTotalProperty;
    @BindView(R.id.et_evaluate_legalLandPay)
    EnableEditText etEvaluateLegalLandPay;
    @BindView(R.id.et_evaluate_legalBuildingPay)
    EnableEditText etEvaluateLegalBuildingPay;
    @BindView(R.id.et_evaluate_legalDecorationPay)
    EnableEditText etEvaluateLegalDecorationPay;
    @BindView(R.id.et_evaluate_appurtenancePay)
    EnableEditText etEvaluateAppurtenancePay;
    @BindView(R.id.tv_evaluate_address)
    StringTextView tvEvaluateAddress;
    @BindView(R.id.tv_evaluate_companyName)
    StringTextView tvEvaluateCompanyName;
    @BindView(R.id.tv_evaluate_date)
    StringTextView tvEvaluateDate;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.rl_evaluate_innerDecoratedDetail)
    RelativeLayout rlEvaluateInnerDecoratedDetail;
    @BindView(R.id.rl_evaluate_appurtenanceDetail)
    RelativeLayout rlEvaluateAppurtenanceDetail;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    @BindView(R.id.ll_photo_preview)
    LinearLayout llPhotoPreview;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;

    @Override
    protected int getContentView() {
        return R.layout.activity_node_company_evaluate;
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
        return "入户评估";
    }

    @Override
    protected void initView() {
        nodeCompanyEvaluatePresenter.attachView(this);
    }

    @Override
    protected void initData() {
        rvPhotoPreview.create();
    }

    @Override
    protected void initNet() {
        nodeCompanyEvaluatePresenter.getCompanyEvaluate(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        etEvaluateNonMobileDevicePay.setEnabled(allowEdit);
        etEvaluateMobileDevicePay.setEnabled(allowEdit);
        etEvaluateLegalLandPay.setEnabled(allowEdit);
        etEvaluateLegalBuildingPay.setEnabled(allowEdit);
        etEvaluateLegalDecorationPay.setEnabled(allowEdit);
        etEvaluateAppurtenancePay.setEnabled(allowEdit);
        setDateSelector(ivDateSelector, tvEvaluateDate, allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String appurtenancePay = etEvaluateAppurtenancePay.getText().toString().trim();
        String mobileDevicePay = etEvaluateMobileDevicePay.getText().toString().trim();
        String nonMobileDevicePay = etEvaluateNonMobileDevicePay.getText().toString().trim();
        String legalDecorationPay = etEvaluateLegalDecorationPay.getText().toString().trim();
        String legalBuildingPay = etEvaluateLegalBuildingPay.getText().toString().trim();
        String legalLandPay = etEvaluateLegalLandPay.getText().toString().trim();
        String evalDate = tvEvaluateDate.getText().toString().trim();
        String remark = etRemark.getText().toString().trim();

        nodeCompanyEvaluatePresenter.modifyCompanyEvaluate(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("AppurtenancePay", appurtenancePay)
                .addFormDataPart("MobileDevicePay", mobileDevicePay)
                .addFormDataPart("NonMobileDevicePay", nonMobileDevicePay)
                .addFormDataPart("LegalLandPay", legalLandPay)
                .addFormDataPart("LegalBuildingPay", legalBuildingPay)
                .addFormDataPart("LegalDecorationPay", legalDecorationPay)
                .addFormDataPart("EvalDate", evalDate)
                .addFormDataPart("Remark", remark)
                .build());
    }


    @Override
    public void onGetCompanyEvaluateSuccess(NodeCompanyEvaluate nodeCompanyEvaluate) {
        setEditable(true);
        tvMappingRealName.setText(nodeCompanyEvaluate.getRealName());
        etEvaluateNonMobileDevicePay.setString(nodeCompanyEvaluate.getNonMobileDevicePay());
        etEvaluateMobileDevicePay.setString(nodeCompanyEvaluate.getMobileDevicePay());
        etEvaluateLegalLandPay.setString(nodeCompanyEvaluate.getLegalLandPay());
        etEvaluateLegalBuildingPay.setString(nodeCompanyEvaluate.getLegalBuildingPay());
        etEvaluateLegalDecorationPay.setString(nodeCompanyEvaluate.getLegalDecorationPay());
        etEvaluateAppurtenancePay.setString(nodeCompanyEvaluate.getAppurtenancePay());
        tvEvaluateCompanyName.setString(nodeCompanyEvaluate.getCompanyName());
        tvEvaluateAddress.setString(nodeCompanyEvaluate.getAddress());
        tvEvaluateDate.setText(DateUtil.getShortDate(nodeCompanyEvaluate.getEvalDate()));
    }

    @Override
    public void onModifyCompanyEvaluateSuccess() {
        showSuccessAndFinish();
    }

}
