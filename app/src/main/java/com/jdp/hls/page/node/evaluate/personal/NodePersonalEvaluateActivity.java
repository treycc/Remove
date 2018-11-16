package com.jdp.hls.page.node.evaluate.personal;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodePersonalEvaluate;
import com.jdp.hls.page.innerdecoration.list.DecorationListActivity;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.PreviewRecyclerView;
import com.jdp.hls.view.StringTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
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
    @BindView(R.id.et_remark)
    EnableEditText etAgeRemark;
    @Inject
    NodePersonalEvaluatePresenter nodePersonalEvaluatePresenter;
    @BindView(R.id.et_evaluate_newHouseEstimatePrice)
    EnableEditText etEvaluateNewHouseEstimatePrice;
    @BindView(R.id.et_evaluate_buyBackPrice)
    EnableEditText etEvaluateBuyBackPrice;
    @BindView(R.id.et_evaluate_benchmarkPriceBusinessOccupancy)
    EnableEditText etEvaluateBenchmarkPriceBusinessOccupancy;
    private int evalId;

    @OnClick({R.id.rl_evaluate_innerDecoratedDetail, R.id.rl_evaluate_appurtenanceDetail})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_evaluate_innerDecoratedDetail:
                DecorationListActivity.goActivity(this, String.valueOf(evalId), String.valueOf(Status.BuildingType
                        .PERSONAL), Status.CompensationType.DECORATION, allowEdit);
                break;
            case R.id.rl_evaluate_appurtenanceDetail:
                DecorationListActivity.goActivity(this, String.valueOf(evalId), String.valueOf(Status.BuildingType
                        .PERSONAL), Status.CompensationType.APPENDANT, allowEdit);
                break;
        }
    }

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
        super.initData();
    }

    @Override
    public void initNet() {
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
        etEvaluateNewHouseEstimatePrice.setEnabled(allowEdit);
        etEvaluateBuyBackPrice.setEnabled(allowEdit);
        etEvaluateBenchmarkPriceBusinessOccupancy.setEnabled(allowEdit);
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

        String newHouseEstimatePrice = etEvaluateNewHouseEstimatePrice.getText().toString().trim();
        String buyBackPrice = etEvaluateBuyBackPrice.getText().toString().trim();
        String benchmarkPriceBusinessOccupancy = etEvaluateBenchmarkPriceBusinessOccupancy.getText().toString().trim();

        nodePersonalEvaluatePresenter.modifyPersonalEvaluate(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", mBuildingId)
                .addFormDataPart("HouseResetMoney", houseResetMoney)
                .addFormDataPart("InnerDecorateMoney", innerDecorateMoney)
                .addFormDataPart("AppurtenancePay", appurtenancePay)
                .addFormDataPart("OldHouseMarketMoney", oldHouseMarketMoney)
                .addFormDataPart("OldHouseMarketTotalMoney", oldHouseMarketTotalMoney)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("EvalDate", evalDate)
                .addFormDataPart("NewHouseEstimatePrice", newHouseEstimatePrice)
                .addFormDataPart("BuyBackPrice", buyBackPrice)
                .addFormDataPart("BenchmarkPriceBusinessOccupancy", benchmarkPriceBusinessOccupancy)
                .build());
    }


    @Override
    public void onGetPersonalEvaluateSuccess(NodePersonalEvaluate nodePersonalEvaluate) {
        allowEdit = nodePersonalEvaluate.isAllowEdit();
        setEditable(allowEdit);
        evalId = nodePersonalEvaluate.getEvalId();
        tvEvaluateRealName.setText(nodePersonalEvaluate.getRealName());
        etEvaluateHouseResetMoney.setString(nodePersonalEvaluate.getHouseResetMoney());
        etEvaluateInnerDecorateMoney.setString(nodePersonalEvaluate.getInnerDecorateMoney());
        etEvaluateAppurtenancePay.setString(nodePersonalEvaluate.getAppurtenancePay());
        etEvaluateOldHouseMarketMoney.setString(nodePersonalEvaluate.getOldHouseMarketMoney());
        etEvaluateOldHouseMarketTotalMoney.setString(nodePersonalEvaluate.getOldHouseMarketTotalMoney());
        tvEvaluateAddress.setString(nodePersonalEvaluate.getAddress());
        etAgeRemark.setString(nodePersonalEvaluate.getRemark());
        tvEvaluateDate.setText(DateUtil.getShortDate(nodePersonalEvaluate.getEvalDate()));
        rvPhotoPreview.setData(nodePersonalEvaluate.getFiles(), getFileConfig(), allowEdit);

        etEvaluateNewHouseEstimatePrice.setString(nodePersonalEvaluate.getNewHouseEstimatePrice());
        etEvaluateBuyBackPrice.setString(nodePersonalEvaluate.getBuyBackPrice());
        etEvaluateBenchmarkPriceBusinessOccupancy.setString(nodePersonalEvaluate.getBenchmarkPriceBusinessOccupancy());
    }

    @Override
    public void onModifyPersonalEvaluateSuccess() {
        showSuccessDialogAndFinish();
    }

}
