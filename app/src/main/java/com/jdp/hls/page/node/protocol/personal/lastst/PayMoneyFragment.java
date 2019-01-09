package com.jdp.hls.page.node.protocol.personal.lastst;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdp.hls.R;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodePersonalProtocol;
import com.jdp.hls.util.CalculateUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/12/14 0014 上午 11:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PayMoneyFragment extends BasePayFragment {

    @BindView(R.id.tv_oldHouseMarketTotalMoney)
    StringTextView tvOldHouseMarketTotalMoney;
    @BindView(R.id.tv_innerDecorateMoney)
    StringTextView tvInnerDecorateMoney;
    @BindView(R.id.tv_appurtenancePay)
    StringTextView tvAppurtenancePay;
    @BindView(R.id.et_kongFanPayAmount)
    EnableEditText etKongFanPayAmount;
    @BindView(R.id.et_pulledDownPayAmount)
    EnableEditText etPulledDownPayAmount;
    @BindView(R.id.et_otherFee)
    EnableEditText etOtherFee;
    @BindView(R.id.et_tempPlacementFee)
    EnableEditText etTempPlacementFee;
    @BindView(R.id.et_removeFee)
    EnableEditText etRemoveFee;
    @BindView(R.id.et_fixedFacilitiesAmount)
    EnableEditText etFixedFacilitiesAmount;
    @BindView(R.id.tv_totalBackPay)
    StringTextView tvTotalBackPay;
    @BindView(R.id.et_protocol_otherArea)
    EnableEditText etProtocolOtherArea;
    @BindView(R.id.et_protocol_overAuditArea)
    EnableEditText etProtocolOverAuditArea;
    Unbinder unbinder;
    @BindView(R.id.et_moveBackFee)
    EnableEditText etMoveBackFee;
    private NodePersonalProtocol nodePersonalProtocol;

    public static PayMoneyFragment newInstance(NodePersonalProtocol nodePersonalProtocol) {
        PayMoneyFragment fragment = new PayMoneyFragment();
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
        tvOldHouseMarketTotalMoney.setString(nodePersonalProtocol.getOldHouseMarketTotalMoney());
        tvInnerDecorateMoney.setString(nodePersonalProtocol.getInnerDecorateMoney());
        tvAppurtenancePay.setString(nodePersonalProtocol.getAppurtenancePay());
        etKongFanPayAmount.setString(nodePersonalProtocol.getKongFanPayAmount());
        etPulledDownPayAmount.setString(nodePersonalProtocol.getPulledDownPayAmount());
        etOtherFee.setString(nodePersonalProtocol.getOtherFee());
        etTempPlacementFee.setString(nodePersonalProtocol.getTempPlacementFee());
        etRemoveFee.setString(nodePersonalProtocol.getRemoveFee());
        etFixedFacilitiesAmount.setString(nodePersonalProtocol.getFixedFacilitiesAmount());
        etProtocolOtherArea.setString(nodePersonalProtocol.getOtherArea());
        etProtocolOverAuditArea.setString(nodePersonalProtocol.getOverAuditArea());
        etMoveBackFee.setString(nodePersonalProtocol.getMoveBackFee());


        etKongFanPayAmount.addTextChangedListener(calculateWatcher);
        etPulledDownPayAmount.addTextChangedListener(calculateWatcher);
        etOtherFee.addTextChangedListener(calculateWatcher);
        etTempPlacementFee.addTextChangedListener(calculateWatcher);
        etRemoveFee.addTextChangedListener(calculateWatcher);

        etProtocolOtherArea.setEnabled(allowEdit);
        etProtocolOverAuditArea.setEnabled(allowEdit);
        etKongFanPayAmount.setEnabled(allowEdit);
        etPulledDownPayAmount.setEnabled(allowEdit);
        etOtherFee.setEnabled(allowEdit);
        etTempPlacementFee.setEnabled(allowEdit);
        etRemoveFee.setEnabled(allowEdit);
        etMoveBackFee.setEnabled(allowEdit);
        etFixedFacilitiesAmount.setEnabled(allowEdit);
        calculate();
    }

    @Override
    public void initNet() {
    }

    @Override
    protected MultipartBody.Builder getRequestBuilder() {
        String kongFanPayAmount = etKongFanPayAmount.getText().toString().trim();
        String pulledDownPayAmount = etPulledDownPayAmount.getText().toString().trim();
        String otherFee = etOtherFee.getText().toString().trim();
        String tempPlacementFee = etTempPlacementFee.getText().toString().trim();
        String removeFee = etRemoveFee.getText().toString().trim();
        String fixedFacilitiesAmount = etFixedFacilitiesAmount.getText().toString().trim();
        String otherArea = etProtocolOtherArea.getText().toString().trim();
        String overAuditArea = etProtocolOverAuditArea.getText().toString().trim();
        String moveBackFee = etMoveBackFee.getText().toString().trim();
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("KongFanPayAmount", kongFanPayAmount)
                .addFormDataPart("PulledDownPayAmount", pulledDownPayAmount)
                .addFormDataPart("OtherFee", otherFee)
                .addFormDataPart("TempPlacementFee", tempPlacementFee)
                .addFormDataPart("RemoveFee", removeFee)
                .addFormDataPart("OtherArea", otherArea)
                .addFormDataPart("OverAuditArea", overAuditArea)
                .addFormDataPart("MoveBackFee", moveBackFee)
                .addFormDataPart("FixedFacilitiesAmount", fixedFacilitiesAmount);
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_pay_money;
    }


    protected void calculate() {
        tvTotalBackPay.setString(CalculateUtil.getTotalValue(tvOldHouseMarketTotalMoney, tvInnerDecorateMoney,
                tvAppurtenancePay, etKongFanPayAmount, etPulledDownPayAmount, etOtherFee, etTempPlacementFee,
                etRemoveFee));
    }
}
