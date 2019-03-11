package com.jdp.hls.page.node.protocol.personal.lastst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodePersonalProtocol;
import com.jdp.hls.model.entiy.TaoType;
import com.jdp.hls.page.node.protocol.BasePayFragment;
import com.jdp.hls.page.node.protocol.personal.lastst.taotype.TaoTypeSelectActivity;
import com.jdp.hls.util.CalculateUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/12/14 0014 上午 11:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PersonalPayRebuyFragment extends BasePayFragment {
    @BindView(R.id.tv_houseResetMoney)
    StringTextView tvHouseResetMoney;
    @BindView(R.id.tv_innerDecorateMoney)
    StringTextView tvInnerDecorateMoney;
    @BindView(R.id.tv_appurtenancePay)
    StringTextView tvAppurtenancePay;
    @BindView(R.id.et_pulledDownPayAmount)
    EnableEditText etPulledDownPayAmount;
    @BindView(R.id.et_otherFee)
    EnableEditText etOtherFee;
    @BindView(R.id.tv_totalPayMoney)
    StringTextView tvTotalPayMoney;
    @BindView(R.id.et_totalPurchasePrice)
    EnableEditText etTotalPurchasePrice;
    @BindView(R.id.tv_needPayBuildingAmount)
    StringTextView tvNeedPayBuildingAmount;
    @BindView(R.id.et_equityRepurchaseTotalAmount)
    EnableEditText etEquityRepurchaseTotalAmount;
    @BindView(R.id.tv_needPayEquityRepurchase)
    StringTextView tvNeedPayEquityRepurchase;
    @BindView(R.id.et_equityRepurchaseBountyAmount)
    EnableEditText etEquityRepurchaseBountyAmount;
    @BindView(R.id.et_tempPlacementFee)
    EnableEditText etTempPlacementFee;
    @BindView(R.id.et_removeFee)
    EnableEditText etRemoveFee;
    @BindView(R.id.et_fixedFacilitiesAmount)
    EnableEditText etFixedFacilitiesAmount;
    @BindView(R.id.et_aZBuildingArea)
    EnableEditText etAZBuildingArea;
    @BindView(R.id.et_aZTNArea)
    EnableEditText etAZTNArea;
    @BindView(R.id.tv_taoType)
    StringTextView tvTaoType;
    @BindView(R.id.ll_taoType)
    LinearLayout llTaoType;
    @BindView(R.id.et_protocol_otherArea)
    EnableEditText etProtocolOtherArea;
    @BindView(R.id.et_protocol_overAuditArea)
    EnableEditText etProtocolOverAuditArea;
    @BindView(R.id.et_moveBackFee)
    EnableEditText etMoveBackFee;
    @BindView(R.id.iv_arrow_taoType)
    ImageView ivArrowTaoType;
    @BindView(R.id.et_equityRepurchaseRatio)
    EnableEditText etEquityRepurchaseRatio;
    private NodePersonalProtocol nodePersonalProtocol;
    private List<TaoType> taoTypeList;

    @OnClick({R.id.ll_taoType})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_taoType:
                TaoTypeSelectActivity.goActivity(this, nodePersonalProtocol.getPatterns(), nodePersonalProtocol
                        .isAllowEdit());
                break;
        }
    }

    public static PersonalPayRebuyFragment newInstance(NodePersonalProtocol nodePersonalProtocol) {
        PersonalPayRebuyFragment fragment = new PersonalPayRebuyFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.Extra.Object, nodePersonalProtocol);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            nodePersonalProtocol = (NodePersonalProtocol) getArguments().getSerializable
                    (Constants.Extra.Object);
        }
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        boolean allowEdit = nodePersonalProtocol.isAllowEdit();
        taoTypeList = nodePersonalProtocol.getPatterns();
        if (taoTypeList != null && taoTypeList.size() > 0) {
            tvTaoType.setHint(String.format("已选择%d个套型", getTaoTypeCount(taoTypeList)));
        } else if (!allowEdit) {
            tvTaoType.setText(nodePersonalProtocol.getPatternInfo());
            llTaoType.setClickable(false);
            ivArrowTaoType.setVisibility(View.GONE);
        }
        tvHouseResetMoney.setString(nodePersonalProtocol.getHouseResetMoney());
        tvInnerDecorateMoney.setString(nodePersonalProtocol.getInnerDecorateMoney());
        tvAppurtenancePay.setString(nodePersonalProtocol.getAppurtenancePay());
        etPulledDownPayAmount.setString(nodePersonalProtocol.getPulledDownPayAmount());
        etOtherFee.setString(nodePersonalProtocol.getOtherFee());
        etTotalPurchasePrice.setString(nodePersonalProtocol.getTotalPurchasePrice());
        etEquityRepurchaseTotalAmount.setString(nodePersonalProtocol.getEquityRepurchaseTotalAmount());
        etEquityRepurchaseBountyAmount.setString(nodePersonalProtocol.getEquityRepurchaseBountyAmount());
        etTempPlacementFee.setString(nodePersonalProtocol.getTempPlacementFee());
        etRemoveFee.setString(nodePersonalProtocol.getRemoveFee());
        etFixedFacilitiesAmount.setString(nodePersonalProtocol.getFixedFacilitiesAmount());
        etAZBuildingArea.setString(nodePersonalProtocol.getAZBuildingArea());
        etAZTNArea.setString(nodePersonalProtocol.getAZTNArea());
        etProtocolOtherArea.setString(nodePersonalProtocol.getOtherArea());
        etProtocolOverAuditArea.setString(nodePersonalProtocol.getOverAuditArea());
        etMoveBackFee.setString(nodePersonalProtocol.getMoveBackFee());
        etEquityRepurchaseRatio.setString(nodePersonalProtocol.getEquityRepurchaseRatio());
        etPulledDownPayAmount.addTextChangedListener(calculateWatcher);
        etOtherFee.addTextChangedListener(calculateWatcher);
        calculate();
        tvNeedPayBuildingAmount.setString(CalculateUtil.getLeftValue(etTotalPurchasePrice, tvTotalPayMoney));
        tvNeedPayEquityRepurchase.setString(CalculateUtil.getLeftValue(etEquityRepurchaseTotalAmount,
                tvNeedPayBuildingAmount));

        etTotalPurchasePrice.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                tvNeedPayBuildingAmount.setString(CalculateUtil.getLeftValue(etTotalPurchasePrice, tvTotalPayMoney));
            }
        });
        etEquityRepurchaseTotalAmount.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                tvNeedPayEquityRepurchase.setString(CalculateUtil.getLeftValue(etEquityRepurchaseTotalAmount,
                        tvNeedPayBuildingAmount));
            }
        });

        etMoveBackFee.setEnabled(allowEdit);
        etProtocolOtherArea.setEnabled(allowEdit);
        etProtocolOverAuditArea.setEnabled(allowEdit);
        etPulledDownPayAmount.setEnabled(allowEdit);
        etOtherFee.setEnabled(allowEdit);
        etTotalPurchasePrice.setEnabled(allowEdit);
        etEquityRepurchaseTotalAmount.setEnabled(allowEdit);
        etEquityRepurchaseBountyAmount.setEnabled(allowEdit);
        etTempPlacementFee.setEnabled(allowEdit);
        etRemoveFee.setEnabled(allowEdit);
        etFixedFacilitiesAmount.setEnabled(allowEdit);
        etAZBuildingArea.setEnabled(allowEdit);
        etAZTNArea.setEnabled(allowEdit);
        etEquityRepurchaseRatio.setEnabled(allowEdit);

    }

    @Override
    public void initNet() {

    }

    @Override
    protected MultipartBody.Builder getRequestBuilder() {
        String pulledDownPayAmount = etPulledDownPayAmount.getText().toString().trim();
        String otherFee = etOtherFee.getText().toString().trim();
        String totalPurchasePrice = etTotalPurchasePrice.getText().toString().trim();
        String equityRepurchaseTotalAmount = etEquityRepurchaseTotalAmount.getText().toString().trim();
        String equityRepurchaseBountyAmount = etEquityRepurchaseBountyAmount.getText().toString().trim();
        String tempPlacementFee = etTempPlacementFee.getText().toString().trim();
        String removeFee = etRemoveFee.getText().toString().trim();
        String fixedFacilitiesAmount = etFixedFacilitiesAmount.getText().toString().trim();
        String aZBuildingArea = etAZBuildingArea.getText().toString().trim();
        String aZTNArea = etAZTNArea.getText().toString().trim();
        String otherArea = etProtocolOtherArea.getText().toString().trim();
        String overAuditArea = etProtocolOverAuditArea.getText().toString().trim();
        String moveBackFee = etMoveBackFee.getText().toString().trim();
        String equityRepurchaseRatio = etEquityRepurchaseRatio.getText().toString().trim();
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("PulledDownPayAmount", pulledDownPayAmount)
                .addFormDataPart("OtherFee", otherFee)
                .addFormDataPart("TotalPurchasePrice", totalPurchasePrice)
                .addFormDataPart("EquityRepurchaseTotalAmount", equityRepurchaseTotalAmount)
                .addFormDataPart("EquityRepurchaseBountyAmount", equityRepurchaseBountyAmount)
                .addFormDataPart("TempPlacementFee", tempPlacementFee)
                .addFormDataPart("RemoveFee", removeFee)
                .addFormDataPart("FixedFacilitiesAmount", fixedFacilitiesAmount)
                .addFormDataPart("AZBuildingArea", aZBuildingArea)
                .addFormDataPart("JsonPattern", getTaoTypeJson(taoTypeList))
                .addFormDataPart("OtherArea", otherArea)
                .addFormDataPart("OverAuditArea", overAuditArea)
                .addFormDataPart("MoveBackFee", moveBackFee)
                .addFormDataPart("EquityRepurchaseRatio", equityRepurchaseRatio)
                .addFormDataPart("AZTNArea", aZTNArea);
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_pay_rebuy;
    }

    protected void calculate() {
        tvTotalPayMoney.setString(CalculateUtil.getTotalValue(tvHouseResetMoney, tvInnerDecorateMoney,
                tvAppurtenancePay, etPulledDownPayAmount, etOtherFee));
        tvNeedPayBuildingAmount.setString(CalculateUtil.getLeftValue(etTotalPurchasePrice, tvTotalPayMoney));
        tvNeedPayEquityRepurchase.setString(CalculateUtil.getLeftValue(etEquityRepurchaseTotalAmount,
                tvNeedPayBuildingAmount));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.TaoTypeSelect:
                    taoTypeList = (List<TaoType>) data.getSerializableExtra(Constants.Extra.LIST);
                    if (taoTypeList != null && taoTypeList.size() > 0) {
                        tvTaoType.setHint(String.format("已选择%d个套型", getTaoTypeCount(taoTypeList)));
                    }
                    break;
            }
        }
    }

}
