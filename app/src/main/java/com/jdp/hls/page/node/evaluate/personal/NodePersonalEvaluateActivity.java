package com.jdp.hls.page.node.evaluate.personal;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodePersonalEvaluate;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.PreviewRecyclerView;
import com.jdp.hls.view.StringTextView;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:入户评估-个人
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodePersonalEvaluateActivity extends BaseNodeActivity implements NodePersonalEvaluateContract.View {
    @BindView(R.id.tv_evaluate_realName)
    TextView tvEvaluateRealName;
    @BindView(R.id.et_evaluate_houseResetMoney)
    EnableEditText etEvaluateHouseResetMoney;
    @BindView(R.id.et_evaluate_innerDecorateMoney)
    EnableEditText etEvaluateInnerDecorateMoney;
    @BindView(R.id.et_evaluate_appurtenancePay)
    EnableEditText etEvaluateAppurtenancePay;
    @BindView(R.id.et_evaluate_oldHouseMarketMoney)
    EnableEditText etEvaluateOldHouseMarketMoney;
    @BindView(R.id.et_evaluate_oldHouseMarketTotalMoney)
    EnableEditText etEvaluateOldHouseMarketTotalMoney;
    @BindView(R.id.tv_evaluate_address)
    StringTextView tvEvaluateAddress;
    @BindView(R.id.tv_evaluate_date)
    TextView tvEvaluateDate;
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
    EnableEditText etAgeRemark;
    @Inject
    NodePersonalEvaluatePresenter nodePersonalEvaluatePresenter;
    private int evaluatorId;

    @Override
    protected int getContentView() {
        return R.layout.activity_node_personal_evaluate;
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
        nodePersonalEvaluatePresenter.attachView(this);
    }

    @Override
    protected void initData() {
        rvPhotoPreview.create();
    }

    @Override
    protected void initNet() {
        nodePersonalEvaluatePresenter.getPersonalEvaluate(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        etEvaluateHouseResetMoney.setEnabled(allowEdit);
        etEvaluateInnerDecorateMoney.setEnabled(allowEdit);
        etEvaluateAppurtenancePay.setEnabled(allowEdit);
        etEvaluateOldHouseMarketMoney.setEnabled(allowEdit);
        etEvaluateOldHouseMarketTotalMoney.setEnabled(allowEdit);
        tvEvaluateAddress.setEnabled(allowEdit);
        etAgeRemark.setEnabled(allowEdit);
        setDateSelector(ivDateSelector, tvEvaluateDate, allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String remark = etAgeRemark.getText().toString().trim();
        String houseResetMoney = etEvaluateHouseResetMoney.getText().toString().trim();
        String innerDecorateMoney = etEvaluateInnerDecorateMoney.getText().toString().trim();
        String appurtenancePay = etEvaluateAppurtenancePay.getText().toString().trim();
        String oldHouseMarketMoney = etEvaluateOldHouseMarketMoney.getText().toString().trim();
        String oldHouseMarketTotalMoney = etEvaluateOldHouseMarketTotalMoney.getText().toString().trim();
        String evalDate = tvEvaluateDate.getText().toString().trim();

        nodePersonalEvaluatePresenter.modifyPersonalEvaluate(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", mBuildingId)
                .addFormDataPart("EvaluatorId", String.valueOf(evaluatorId))
                .addFormDataPart("HouseResetMoney", houseResetMoney)
                .addFormDataPart("InnerDecorateMoney", innerDecorateMoney)
                .addFormDataPart("AppurtenancePay", appurtenancePay)
                .addFormDataPart("OldHouseMarketMoney", oldHouseMarketMoney)
                .addFormDataPart("OldHouseMarketTotalMoney", oldHouseMarketTotalMoney)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("EvalDate", evalDate)
                .build());
    }


    @Override
    public void onGetPersonalEvaluateSuccess(NodePersonalEvaluate nodePersonalEvaluate) {
        setEditable(true);
        evaluatorId = nodePersonalEvaluate.getEvaluatorId();
        tvEvaluateRealName.setText(nodePersonalEvaluate.getRealName());
        etEvaluateHouseResetMoney.setString(nodePersonalEvaluate.getHouseResetMoney());
        etEvaluateInnerDecorateMoney.setString(nodePersonalEvaluate.getInnerDecorateMoney());
        etEvaluateAppurtenancePay.setString(nodePersonalEvaluate.getAppurtenancePay());
        etEvaluateOldHouseMarketMoney.setString(nodePersonalEvaluate.getOldHouseMarketMoney());
        etEvaluateOldHouseMarketTotalMoney.setString(nodePersonalEvaluate.getOldHouseMarketTotalMoney());
        tvEvaluateAddress.setString(nodePersonalEvaluate.getAddress());
        etAgeRemark.setString(nodePersonalEvaluate.getRemark());
        tvEvaluateDate.setText(DateUtil.getShortDate(nodePersonalEvaluate.getEvalDate()));
    }

    @Override
    public void onModifyPersonalEvaluateSuccess() {
        showSuccessAndFinish();
    }
}
