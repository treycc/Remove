package com.jdp.hls.page.node.protocol.personal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodePersonalProtocol;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.page.otherarea.list.OtherAreaListActivity;
import com.jdp.hls.util.MathUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.PreviewRecyclerView;
import com.jdp.hls.view.StringTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MultipartBody;

/**
 * Description:协议生成-个人
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodePersonalProtocolActivity extends BaseNodeActivity implements NodePersonalProtocolContract.View {
    @BindView(R.id.tv_protocol_cusCode)
    StringTextView tvProtocolCusCode;
    @BindView(R.id.tv_protocol_ownerName)
    StringTextView tvProtocolOwnerName;
    @BindView(R.id.tv_protocol_totalArea)
    StringTextView tvProtocolTotalArea;
    @BindView(R.id.tv_protocol_totalNotRecordArea)
    StringTextView tvProtocolTotalNotRecordArea;
    @BindView(R.id.tv_protocol_buildOccupyArea)
    StringTextView tvProtocolBuildOccupyArea;
    @BindView(R.id.tv_protocol_landCertArea)
    StringTextView tvProtocolLandCertArea;
    @BindView(R.id.tv_address)
    StringTextView tvAddress;
    @BindView(R.id.tv_protocol_date)
    TextView tvProtocolDate;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.et_protocol_hzArea)
    EnableEditText etProtocolHzArea;
    @BindView(R.id.et_protocol_azBuildingArea)
    EnableEditText etProtocolAzBuildingArea;
    @BindView(R.id.et_protocol_azntArea)
    EnableEditText etProtocolAzntArea;
    @BindView(R.id.et_protocol_taoTypeName)
    EnableEditText etProtocolTaoTypeName;
    @BindView(R.id.et_protocol_damagesAmount)
    EnableEditText etProtocolDamagesAmount;
    @BindView(R.id.spinner_protocol_payType)
    KSpinner spinnerProtocolPayType;
    @BindView(R.id.tv_protocol_needPayBuildingAmount)
    StringTextView tvProtocolNeedPayBuildingAmount;
    @BindView(R.id.et_protocol_totalPurchasePrice)
    EnableEditText etProtocolTotalPurchasePrice;
    @BindView(R.id.et_protocol_totalPay)
    EnableEditText etProtocolTotalPay;
    @BindView(R.id.et_protocol_otherArea)
    EnableEditText etProtocolOtherArea;
    @BindView(R.id.et_protocol_overAuditArea)
    EnableEditText etProtocolOverAuditArea;
    @BindView(R.id.et_protocol_removeFee)
    EnableEditText etProtocolRemoveFee;
    @BindView(R.id.et_protocol_tempPlacementFee)
    EnableEditText etProtocolTempPlacementFee;
    @BindView(R.id.et_protocol_otherFee)
    EnableEditText etProtocolOtherFee;
    @BindView(R.id.rl_protocol_otherArea)
    RelativeLayout rlProtocolOtherArea;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    private int payType;
    @Inject
    NodePersonalProtocolPresenter nodePersonalProtocolPresenter;
    private List<TDict> payTypeList;
    private int pcId;

    @OnClick({R.id.rl_protocol_otherArea})
    public void rl_protocol_otherArea(View view) {
        switch (view.getId()) {
            case R.id.rl_protocol_otherArea:
                OtherAreaListActivity.goActivity(this, String.valueOf(pcId), String.valueOf(Status.BuildingType
                        .PERSONAL));
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {
        super.initVariable();
        payTypeList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PAY_TYPE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_node_personal_protocol;
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
        return "协议生成";
    }

    @Override
    protected void initView() {
        nodePersonalProtocolPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        super.initData();
        spinnerProtocolPayType.setDicts(payTypeList, typeId -> {
            payType = typeId;
        });
        etProtocolTotalPurchasePrice.addTextChangedListener(calculateTotalMoneyWatcher);
        etProtocolTotalPay.addTextChangedListener(calculateTotalMoneyWatcher);
    }

    private TextWatcher calculateTotalMoneyWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            calculateTotalMoney();
        }
    };

    private void calculateTotalMoney() {
        String totalPurchasePriceStr = etProtocolTotalPurchasePrice.getText().toString().trim();
        String totalPayStr = etProtocolTotalPay.getText().toString().trim();
        double totalPurchasePrice = TextUtils.isEmpty(totalPurchasePriceStr) ? 0d : Double.valueOf
                (totalPurchasePriceStr);
        double totalPay = TextUtils.isEmpty(totalPayStr) ? 0d : Double.valueOf(totalPayStr);
        tvProtocolNeedPayBuildingAmount.setText(String.valueOf(MathUtil.sub(totalPurchasePrice, totalPay)));
    }

    @Override
    protected void initNet() {
        nodePersonalProtocolPresenter.getPersonalProtocol(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        etProtocolTotalPurchasePrice.setEnabled(allowEdit);
        etProtocolTotalPay.setEnabled(allowEdit);
        etProtocolOtherArea.setEnabled(allowEdit);
        etProtocolOverAuditArea.setEnabled(allowEdit);
        etProtocolRemoveFee.setEnabled(allowEdit);
        etProtocolTempPlacementFee.setEnabled(allowEdit);
        etProtocolOtherFee.setEnabled(allowEdit);
        etRemark.setEnabled(allowEdit);
        spinnerProtocolPayType.setEnabled(allowEdit);
        setDateSelector(ivDateSelector, tvProtocolDate, allowEdit);
        etProtocolHzArea.setEnabled(allowEdit);
        etProtocolAzBuildingArea.setEnabled(allowEdit);
        etProtocolAzntArea.setEnabled(allowEdit);
        etProtocolTaoTypeName.setEnabled(allowEdit);
        etProtocolDamagesAmount.setEnabled(allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String remark = etRemark.getText().toString().trim();
        String totalPurchasePrice = etProtocolTotalPurchasePrice.getText().toString().trim();
        String totalPay = etProtocolTotalPay.getText().toString().trim();
        String removeFee = etProtocolRemoveFee.getText().toString().trim();
        String tempPlacementFee = etProtocolTempPlacementFee.getText().toString().trim();
        String otherFee = etProtocolOtherFee.getText().toString().trim();
        String otherArea = etProtocolOtherArea.getText().toString().trim();
        String overAuditArea = etProtocolOverAuditArea.getText().toString().trim();
        String pCDate = tvProtocolDate.getText().toString().trim();

        String hzArea = etProtocolHzArea.getText().toString().trim();
        String azBuildingArea = etProtocolAzBuildingArea.getText().toString().trim();
        String azntArea = etProtocolAzntArea.getText().toString().trim();
        String taoTypeName = etProtocolTaoTypeName.getText().toString().trim();
        String damagesAmount = etProtocolDamagesAmount.getText().toString().trim();
        nodePersonalProtocolPresenter.modifyPersonalProtocol(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", mBuildingId)
                .addFormDataPart("TotalPurchasePrice", totalPurchasePrice)
                .addFormDataPart("TotalPay", totalPay)
                .addFormDataPart("PayType", String.valueOf(payType))
                .addFormDataPart("OtherArea", otherArea)
                .addFormDataPart("OverAuditArea", overAuditArea)
                .addFormDataPart("RemoveFee", removeFee)
                .addFormDataPart("TempPlacementFee", tempPlacementFee)
                .addFormDataPart("OtherFee", otherFee)
                .addFormDataPart("PCDate", pCDate)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("HZArea", hzArea)
                .addFormDataPart("AZBuildingArea", azBuildingArea)
                .addFormDataPart("AZTNArea", azntArea)
                .addFormDataPart("TaoTypeName", taoTypeName)
                .addFormDataPart("DamagesAmount", damagesAmount)
                .build());
    }

    @Override
    public void onGetPersonalProtocolSuccess(NodePersonalProtocol nodePersonalProtocol) {
        allowEdit = nodePersonalProtocol.isAllowEdit();
        setEditable(allowEdit);
        pcId = nodePersonalProtocol.getPCId();
        tvProtocolCusCode.setText(nodePersonalProtocol.getCusCode());
        tvProtocolOwnerName.setText(nodePersonalProtocol.getOwnerName());
        tvProtocolTotalArea.setText(nodePersonalProtocol.getTotalArea());
        tvProtocolTotalNotRecordArea.setText(nodePersonalProtocol.getTotalNotRecordArea());
        tvProtocolBuildOccupyArea.setText(nodePersonalProtocol.getBuildOccupyArea());
        tvProtocolLandCertArea.setText(nodePersonalProtocol.getLandCertArea());
        tvProtocolDate.setText(nodePersonalProtocol.getPCDate());
        etProtocolTotalPurchasePrice.setString(nodePersonalProtocol.getTotalPurchasePrice());
        etProtocolTotalPay.setString(nodePersonalProtocol.getTotalPay());
        etProtocolOtherArea.setString(nodePersonalProtocol.getOtherArea());
        etProtocolOverAuditArea.setString(nodePersonalProtocol.getOverAuditArea());
        etProtocolRemoveFee.setString(nodePersonalProtocol.getRemoveFee());
        etProtocolTempPlacementFee.setString(nodePersonalProtocol.getTempPlacementFee());
        etProtocolOtherFee.setString(nodePersonalProtocol.getOtherFee());
        etRemark.setString(nodePersonalProtocol.getRemark());
        payType = nodePersonalProtocol.getPayType();
        spinnerProtocolPayType.setSelectItem(payType);

        etProtocolHzArea.setString(nodePersonalProtocol.getHZArea());
        etProtocolAzBuildingArea.setString(nodePersonalProtocol.getAZBuildingArea());
        etProtocolAzntArea.setString(nodePersonalProtocol.getAZTNArea());
        etProtocolTaoTypeName.setString(nodePersonalProtocol.getTaoTypeName());
        etProtocolDamagesAmount.setString(nodePersonalProtocol.getDamagesAmount());
        calculateTotalMoney();
        rvPhotoPreview.setData(nodePersonalProtocol.getFiles(), getFileConfig(), allowEdit);

    }

    @Override
    public void onModifyPersonalProtocolSuccess() {
        showSuccessAndFinish();
    }

}
