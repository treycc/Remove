package com.jdp.hls.page.node.protocol.personal.lastst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/12/14 0014 上午 11:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PersonalPayChangeFragment extends BasePayFragment {
    @BindView(R.id.tv_houseResetMoney)
    StringTextView tvHouseResetMoney;
    @BindView(R.id.tv_innerDecorateMoney)
    StringTextView tvInnerDecorateMoney;
    @BindView(R.id.tv_appurtenancePay)
    StringTextView tvAppurtenancePay;
    @BindView(R.id.et_pulledDownPayAmount)
    EnableEditText etPulledDownPayAmount;
    @BindView(R.id.et_clearObstaclePay)
    EnableEditText etClearObstaclePay;
    @BindView(R.id.et_otherFee)
    EnableEditText etOtherFee;
    @BindView(R.id.et_totalPurchasePrice)
    EnableEditText etTotalPurchasePrice;
    @BindView(R.id.tv_needPayBuildingAmount)
    StringTextView tvNeedPayBuildingAmount;
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
    @BindView(R.id.tv_oldHouseTotalPay)
    StringTextView tvOldHouseTotalPay;
    @BindView(R.id.et_protocol_otherArea)
    EnableEditText etProtocolOtherArea;
    @BindView(R.id.et_protocol_overAuditArea)
    EnableEditText etProtocolOverAuditArea;
    Unbinder unbinder;
    @BindView(R.id.et_moveBackFee)
    EnableEditText etMoveBackFee;
    @BindView(R.id.iv_arrow_taoType)
    ImageView ivArrowTaoType;
    private NodePersonalProtocol nodePersonalProtocol;
    private List<TaoType> taoTypeList;

    @OnClick({R.id.ll_taoType})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_taoType:
                TaoTypeSelectActivity.goActivity(this, taoTypeList, nodePersonalProtocol.isAllowEdit());
                break;
        }
    }

    public static PersonalPayChangeFragment newInstance(NodePersonalProtocol nodePersonalProtocol) {
        PersonalPayChangeFragment fragment = new PersonalPayChangeFragment();
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
        etClearObstaclePay.setString(nodePersonalProtocol.getClearObstaclePay());
        etOtherFee.setString(nodePersonalProtocol.getOtherFee());
        etTotalPurchasePrice.setString(nodePersonalProtocol.getTotalPurchasePrice());
        tvNeedPayBuildingAmount.setString(nodePersonalProtocol.getNeedPayBuildingAmount());
        etTempPlacementFee.setString(nodePersonalProtocol.getTempPlacementFee());
        etRemoveFee.setString(nodePersonalProtocol.getRemoveFee());
        etFixedFacilitiesAmount.setString(nodePersonalProtocol.getFixedFacilitiesAmount());
        etAZBuildingArea.setString(nodePersonalProtocol.getAZBuildingArea());
        etAZTNArea.setString(nodePersonalProtocol.getAZTNArea());
        etMoveBackFee.setString(nodePersonalProtocol.getMoveBackFee());

        etProtocolOtherArea.setString(nodePersonalProtocol.getOtherArea());
        etProtocolOverAuditArea.setString(nodePersonalProtocol.getOverAuditArea());

        etPulledDownPayAmount.addTextChangedListener(calculateWatcher);
        etClearObstaclePay.addTextChangedListener(calculateWatcher);
        etOtherFee.addTextChangedListener(calculateWatcher);

        etTotalPurchasePrice.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                calculateNeedPayMoney();
            }
        });
        etProtocolOtherArea.setEnabled(allowEdit);
        etProtocolOverAuditArea.setEnabled(allowEdit);
        etPulledDownPayAmount.setEnabled(allowEdit);
        etClearObstaclePay.setEnabled(allowEdit);
        etOtherFee.setEnabled(allowEdit);
        etTotalPurchasePrice.setEnabled(allowEdit);
        etTempPlacementFee.setEnabled(allowEdit);
        etRemoveFee.setEnabled(allowEdit);
        etFixedFacilitiesAmount.setEnabled(allowEdit);
        etAZBuildingArea.setEnabled(allowEdit);
        etAZTNArea.setEnabled(allowEdit);
        etMoveBackFee.setEnabled(allowEdit);
        calculate();
        calculateNeedPayMoney();
    }


    private void calculateNeedPayMoney() {
        tvNeedPayBuildingAmount.setString(CalculateUtil.getNumber(etTotalPurchasePrice) - CalculateUtil
                .getNumber(tvOldHouseTotalPay));
    }

    @Override
    public void initNet() {

    }

    @Override
    protected MultipartBody.Builder getRequestBuilder() {

        String pulledDownPayAmount = etPulledDownPayAmount.getText().toString().trim();
        String clearObstaclePay = etClearObstaclePay.getText().toString().trim();
        String otherFee = etOtherFee.getText().toString().trim();
        String totalPurchasePrice = etTotalPurchasePrice.getText().toString().trim();
        String tempPlacementFee = etTempPlacementFee.getText().toString().trim();
        String removeFee = etRemoveFee.getText().toString().trim();
        String fixedFacilitiesAmount = etFixedFacilitiesAmount.getText().toString().trim();
        String aZBuildingArea = etAZBuildingArea.getText().toString().trim();
        String aZTNArea = etAZTNArea.getText().toString().trim();
        String otherArea = etProtocolOtherArea.getText().toString().trim();
        String overAuditArea = etProtocolOverAuditArea.getText().toString().trim();
        String moveBackFee = etMoveBackFee.getText().toString().trim();
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("PulledDownPayAmount", pulledDownPayAmount)
                .addFormDataPart("ClearObstaclePay", clearObstaclePay)
                .addFormDataPart("OtherFee", otherFee)
                .addFormDataPart("TotalPurchasePrice", totalPurchasePrice)
                .addFormDataPart("TempPlacementFee", tempPlacementFee)
                .addFormDataPart("RemoveFee", removeFee)
                .addFormDataPart("FixedFacilitiesAmount", fixedFacilitiesAmount)
                .addFormDataPart("AZBuildingArea", aZBuildingArea)
                .addFormDataPart("JsonPattern", getTaoTypeJson(taoTypeList))
                .addFormDataPart("OtherArea", otherArea)
                .addFormDataPart("OverAuditArea", overAuditArea)
                .addFormDataPart("MoveBackFee", moveBackFee)
                .addFormDataPart("AZTNArea", aZTNArea);
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_pay_change;
    }

    protected void calculate() {
        tvOldHouseTotalPay.setString(CalculateUtil.getTotalValue(tvHouseResetMoney, tvInnerDecorateMoney,
                tvAppurtenancePay, etPulledDownPayAmount, etClearObstaclePay, etOtherFee));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.e(TAG, "onActivityResult：");
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
