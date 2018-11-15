package com.jdp.hls.page.node.evaluate.company.moneyevaluate;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodeCompanyMoneyEvaluate;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.util.MathUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:资产评估
 * Create Time:2018/11/8 0008 上午 10:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyMoneyEvaluateActivity extends BaseNodeActivity implements NodeCompanyMoneyEvaluateContract
        .View {
    @Inject
    NodeCompanyMoneyEvaluatePresenter nodeCompanyMoneyEvaluatePresenter;
    @BindView(R.id.tv_companyName)
    StringTextView tvCompanyName;
    @BindView(R.id.tv_evaluatorName)
    StringTextView tvEvaluatorName;
    @BindView(R.id.tv_evalDate)
    StringTextView tvEvalDate;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.tv_assetEvaluatAmount)
    StringTextView tvAssetEvaluatAmount;
    @BindView(R.id.et_nonMobileDevicePay)
    EnableEditText etNonMobileDevicePay;
    @BindView(R.id.et_mobileDevicePay)
    EnableEditText etMobileDevicePay;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;

    @Override
    protected int getContentView() {
        return R.layout.activity_node_company_money_evaluate;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        nodeCompanyMoneyEvaluatePresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "资产评估";
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        super.initData();
        etNonMobileDevicePay.addTextChangedListener(calculateTotalMoneyWatcher);
        etMobileDevicePay.addTextChangedListener(calculateTotalMoneyWatcher);

    }

    private TextWatcher calculateTotalMoneyWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            calculateTotal();
        }
    };

    private void calculateTotal() {
        String nonMobileDevicePayStr = etNonMobileDevicePay.getText().toString().trim();
        String mobileDevicePayStr = etMobileDevicePay.getText().toString().trim();
        double nonMobileDevicePay =nonMobileDevicePayStr.startsWith(".")|| TextUtils.isEmpty(nonMobileDevicePayStr) ? 0d : Double.valueOf
                (nonMobileDevicePayStr);
        double mobileDevicePay = mobileDevicePayStr.startsWith(".")|| TextUtils.isEmpty(mobileDevicePayStr) ? 0d : Double.valueOf(mobileDevicePayStr);
        tvAssetEvaluatAmount.setText(String.valueOf(MathUtil.add(nonMobileDevicePay, mobileDevicePay)));
    }

    @Override
    protected void initNet() {
        nodeCompanyMoneyEvaluatePresenter.getCompanyMoneyevaluate(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        etNonMobileDevicePay.setEnabled(allowEdit);
        etMobileDevicePay.setEnabled(allowEdit);
        etRemark.setEnabled(allowEdit);
        setDateSelector(ivDateSelector, tvEvalDate, allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String evalDate = tvEvalDate.getText().toString().trim();
        String mobileDevicePay = etMobileDevicePay.getText().toString().trim();
        String nonMobileDevicePay = etNonMobileDevicePay.getText().toString().trim();
        String remark = etRemark.getText().toString().trim();
        nodeCompanyMoneyEvaluatePresenter.modifyCompanyMoneyevaluate(new MultipartBody.Builder().setType
                (MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("EvalDate", evalDate)
                .addFormDataPart("MobileDevicePay", mobileDevicePay)
                .addFormDataPart("NonMobileDevicePay", nonMobileDevicePay)
                .addFormDataPart("Remark", remark)
                .build());
    }

    @Override
    public void onGetCompanyMoneyevaluateSuccess(NodeCompanyMoneyEvaluate nodeCompanyMoneyEvaluate) {
        boolean allowEdit = nodeCompanyMoneyEvaluate.isAllowEdit();
        setEditable(allowEdit);
        tvCompanyName.setString(nodeCompanyMoneyEvaluate.getCompanyName());
        tvEvaluatorName.setString(nodeCompanyMoneyEvaluate.getEvaluatorName());
        tvEvalDate.setString(nodeCompanyMoneyEvaluate.getEvalDate());
        etNonMobileDevicePay.setString(nodeCompanyMoneyEvaluate.getNonMobileDevicePay());
        etMobileDevicePay.setString(nodeCompanyMoneyEvaluate.getMobileDevicePay());
        etRemark.setString(nodeCompanyMoneyEvaluate.getRemark());
        rvPhotoPreview.setData(nodeCompanyMoneyEvaluate.getFiles(), getFileConfig(), allowEdit);
        calculateTotal();
    }

    @Override
    public void onModifyCompanyMoneyevaluateSuccess() {
        showSuccessDialogAndFinish();
    }
}
