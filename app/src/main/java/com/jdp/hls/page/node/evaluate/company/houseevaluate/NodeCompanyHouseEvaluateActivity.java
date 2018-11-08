package com.jdp.hls.page.node.evaluate.company.houseevaluate;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodeCompanyHouseEvaluate;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.util.MathUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:房地产评估
 * Create Time:2018/11/8 0008 上午 10:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyHouseEvaluateActivity extends BaseNodeActivity implements NodeCompanyMoneyHouseContract.View {
    @Inject
    NodeCompanyHouseEvaluatePresenter nodeCompanyHouseEvaluatePresenter;
    @BindView(R.id.tv_companyName)
    StringTextView tvCompanyName;
    @BindView(R.id.tv_realName)
    StringTextView tvRealName;
    @BindView(R.id.tv_evalDate)
    StringTextView tvEvalDate;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.tv_assetEvaluatAmount)
    StringTextView tvAssetEvaluatAmount;
    @BindView(R.id.et_legalLandPay)
    EnableEditText etLegalLandPay;
    @BindView(R.id.et_legalBuildingPay)
    EnableEditText etLegalBuildingPay;
    @BindView(R.id.et_legalDecorationPay)
    EnableEditText etLegalDecorationPay;
    @BindView(R.id.et_appurtenancePay)
    EnableEditText etAppurtenancePay;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;

    @Override
    protected int getContentView() {
        return R.layout.activity_node_company_house_evaluate;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        nodeCompanyHouseEvaluatePresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "房地产评估";
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        super.initData();
        etLegalLandPay.addTextChangedListener(calculateTotalMoneyWatcher);
        etLegalBuildingPay.addTextChangedListener(calculateTotalMoneyWatcher);
        etLegalDecorationPay.addTextChangedListener(calculateTotalMoneyWatcher);
        etAppurtenancePay.addTextChangedListener(calculateTotalMoneyWatcher);
    }

    private TextWatcher calculateTotalMoneyWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            calculateTotal();
        }
    };

    private void calculateTotal() {
        String legalLandPayStr = etLegalLandPay.getText().toString().trim();
        String legalBuildingPayStr = etLegalBuildingPay.getText().toString().trim();
        String legalDecorationPayStr = etLegalDecorationPay.getText().toString().trim();
        String appurtenancePayStr = etAppurtenancePay.getText().toString().trim();
        double nonMobileDevicePay = TextUtils.isEmpty(legalLandPayStr) ? 0d : Double.valueOf
                (legalLandPayStr);
        double mobileDevicePay = TextUtils.isEmpty(legalBuildingPayStr) ? 0d : Double.valueOf(legalBuildingPayStr);
        double legalDecorationPay = TextUtils.isEmpty(legalDecorationPayStr) ? 0d : Double.valueOf
                (legalDecorationPayStr);
        double appurtenancePay = TextUtils.isEmpty(appurtenancePayStr) ? 0d : Double.valueOf(appurtenancePayStr);
        tvAssetEvaluatAmount.setText(String.valueOf(MathUtil.add(nonMobileDevicePay, mobileDevicePay) + MathUtil
                .add(legalDecorationPay, appurtenancePay)));
    }

    @Override
    protected void initNet() {
        nodeCompanyHouseEvaluatePresenter.getCompanyHouseEvaluate(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        etLegalLandPay.setEnabled(allowEdit);
        etLegalBuildingPay.setEnabled(allowEdit);
        etLegalDecorationPay.setEnabled(allowEdit);
        etAppurtenancePay.setEnabled(allowEdit);
        etRemark.setEnabled(allowEdit);
        setDateSelector(ivDateSelector, tvEvalDate, allowEdit);

    }

    @Override
    protected void onSaveDate() {
        String evalDate = tvEvalDate.getText().toString().trim();
        String appurtenancePay = etAppurtenancePay.getText().toString().trim();
        String legalLandPay = etLegalLandPay.getText().toString().trim();
        String legalBuildingPay = etLegalBuildingPay.getText().toString().trim();
        String legalDecorationPay = etLegalDecorationPay.getText().toString().trim();
        String remark = etRemark.getText().toString().trim();
        nodeCompanyHouseEvaluatePresenter.modifyCompanyHouseEvaluate(new MultipartBody.Builder().setType
                (MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("EvalDate", evalDate)
                .addFormDataPart("AppurtenancePay", appurtenancePay)
                .addFormDataPart("LegalLandPay", legalLandPay)
                .addFormDataPart("LegalBuildingPay", legalBuildingPay)
                .addFormDataPart("LegalDecorationPay", legalDecorationPay)
                .addFormDataPart("Remark", remark)
                .build());
    }


    @Override
    public void onGetCompanyHouseEvaluateSuccess(NodeCompanyHouseEvaluate nodeCompanyHouseEvaluate) {
        boolean allowEdit = nodeCompanyHouseEvaluate.isAllowEdit();
        setEditable(allowEdit);
        tvCompanyName.setString(nodeCompanyHouseEvaluate.getCompanyName());
        tvRealName.setString(nodeCompanyHouseEvaluate.getRealName());
        tvEvalDate.setString(nodeCompanyHouseEvaluate.getEvalDate());
        etLegalLandPay.setString(nodeCompanyHouseEvaluate.getLegalLandPay());
        etLegalBuildingPay.setString(nodeCompanyHouseEvaluate.getLegalBuildingPay());
        etLegalDecorationPay.setString(nodeCompanyHouseEvaluate.getLegalDecorationPay());
        etAppurtenancePay.setString(nodeCompanyHouseEvaluate.getAppurtenancePay());
        etRemark.setString(nodeCompanyHouseEvaluate.getRemark());
        rvPhotoPreview.setData(nodeCompanyHouseEvaluate.getFiles(), getFileConfig(),allowEdit);
        calculateTotal();

    }

    @Override
    public void onModifyCompanyHouseEvaluateSuccess() {
        showSuccessAndFinish();
    }
}
