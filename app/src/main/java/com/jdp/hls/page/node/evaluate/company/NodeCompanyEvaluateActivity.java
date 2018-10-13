package com.jdp.hls.page.node.evaluate.company;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodeCompanyEvaluate;
import com.jdp.hls.page.innerdecoration.list.DecorationListActivity;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.PreviewRecyclerView;
import com.jdp.hls.view.StringTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
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
    private int evalId;

    @OnClick({R.id.rl_evaluate_innerDecoratedDetail, R.id.rl_evaluate_appurtenanceDetail})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_evaluate_innerDecoratedDetail:
                DecorationListActivity.goActivity(this, String.valueOf(evalId), String.valueOf(Status.BuildingType
                        .COMPANY),Status.CompensationType.DECORATION);
                break;
            case R.id.rl_evaluate_appurtenanceDetail:
                DecorationListActivity.goActivity(this, String.valueOf(evalId), String.valueOf(Status.BuildingType
                        .COMPANY),Status.CompensationType.APPENDANT);
                break;
        }
    }
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
        etEvaluateNonMobileDevicePay.addTextChangedListener(calculateTotalMoneyWatcher);
        etEvaluateMobileDevicePay.addTextChangedListener(calculateTotalMoneyWatcher);
        etEvaluateLegalLandPay.addTextChangedListener(calculateTotalPropertyWatcher);
        etEvaluateLegalBuildingPay.addTextChangedListener(calculateTotalPropertyWatcher);
        etEvaluateLegalDecorationPay.addTextChangedListener(calculateTotalPropertyWatcher);
        etEvaluateAppurtenancePay.addTextChangedListener(calculateTotalPropertyWatcher);

    }

    private TextWatcher calculateTotalPropertyWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            calculateTotalProperty();
        }
    };

    private TextWatcher calculateTotalMoneyWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            calculateTotalMoney();
        }
    };

    private void calculateTotalProperty() {
        String legalLandPayStr = etEvaluateLegalLandPay.getText().toString().trim();
        String legalBuildingPayStr = etEvaluateLegalBuildingPay.getText().toString().trim();
        String legalDecorationPayStr = etEvaluateLegalDecorationPay.getText().toString().trim();
        String appurtenancePayStr = etEvaluateAppurtenancePay.getText().toString().trim();
        double before90Area = TextUtils.isEmpty(legalLandPayStr) ? 0d : Double.valueOf(legalLandPayStr);
        double legalBuildingPay = TextUtils.isEmpty(legalBuildingPayStr) ? 0d : Double.valueOf(legalBuildingPayStr);
        double legalDecorationPay = TextUtils.isEmpty(legalDecorationPayStr) ? 0d : Double.valueOf
                (legalDecorationPayStr);
        double appurtenancePay = TextUtils.isEmpty(appurtenancePayStr) ? 0d : Double.valueOf(appurtenancePayStr);
        tvTotalProperty.setText(String.valueOf(before90Area + legalBuildingPay + legalDecorationPay + appurtenancePay));
    }

    private void calculateTotalMoney() {
        String nonMobileDevicePayStr = etEvaluateNonMobileDevicePay.getText().toString().trim();
        String mobileDevicePayStr = etEvaluateMobileDevicePay.getText().toString().trim();
        double nonMobileDevicePay = TextUtils.isEmpty(nonMobileDevicePayStr) ? 0d : Double.valueOf
                (nonMobileDevicePayStr);
        double mobileDevicePay = TextUtils.isEmpty(mobileDevicePayStr) ? 0d : Double.valueOf(mobileDevicePayStr);
        tvTotalMoney.setText(String.valueOf(nonMobileDevicePay + mobileDevicePay));
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
        evalId = nodeCompanyEvaluate.getEvalId();
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
        calculateTotalMoney();
        calculateTotalProperty();
    }

    @Override
    public void onModifyCompanyEvaluateSuccess() {
        showSuccessAndFinish();
    }

}
