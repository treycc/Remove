package com.jdp.hls.page.node.protocol.company;

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
import com.jdp.hls.model.entiy.NodeCompanyProtocol;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.page.otherarea.list.OtherAreaListActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.MathUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.PreviewRecyclerView;
import com.jdp.hls.view.StringTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MultipartBody;

/**
 * Description:协议生成-个人
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyProtocolActivity extends BaseNodeActivity implements NodeCompanyProtocolContract.View {
    @BindView(R.id.rl_protocol_otherArea)
    RelativeLayout rlProtocolOtherArea;
    @BindView(R.id.tv_protocol_cusCode)
    StringTextView tvProtocolCusCode;
    @BindView(R.id.tv_protocol_totalBuildingArea)
    StringTextView tvProtocolTotalBuildingArea;
    @BindView(R.id.tv_protocol_totalNotRecordArea)
    StringTextView tvProtocolTotalNotRecordArea;
    @BindView(R.id.tv_protocol_landCertArea)
    StringTextView tvProtocolLandCertArea;
    @BindView(R.id.et_protocol_totalLandAZArea)
    EnableEditText etProtocolTotalLandAZArea;
    @BindView(R.id.spinner_payType)
    KSpinner spinnerPayType;
    @BindView(R.id.et_protocol_totalPurchasePrice)
    EnableEditText etProtocolTotalPurchasePrice;
    @BindView(R.id.et_protocol_clearObstaclePay)
    EnableEditText etProtocolClearObstaclePay;
    @BindView(R.id.et_protocol_totalPay)
    EnableEditText etProtocolTotalPay;
    @BindView(R.id.et_protocol_removeFee)
    EnableEditText etProtocolRemoveFee;
    @BindView(R.id.et_protocol_tempPlacementFee)
    EnableEditText etProtocolTempPlacementFee;
    @BindView(R.id.et_protocol_otherFee)
    EnableEditText etProtocolOtherFee;
    @BindView(R.id.tv_protocol_address)
    StringTextView tvProtocolAddress;
    @BindView(R.id.tv_protocol_date)
    TextView tvProtocolDate;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    @BindView(R.id.ll_photo_preview)
    LinearLayout llPhotoPreview;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    @BindView(R.id.et_protocol_changeArea)
    EnableEditText etProtocolChangeArea;
    @BindView(R.id.tv_protocol_needPayAmount)
    StringTextView tvProtocolNeedPayAmount;
    @BindView(R.id.et_protocol_damagesAmount)
    EnableEditText etProtocolDamagesAmount;
    private int payType;

    @Inject
    NodeCompanyProtocolPresenter nodeCompanyProtocolPresenter;
    private List<TDict> payTypeList;
    private int pcId;

    @OnClick({R.id.rl_protocol_otherArea})
    public void rl_protocol_otherArea(View view) {
        switch (view.getId()) {
            case R.id.rl_protocol_otherArea:
                OtherAreaListActivity.goActivity(this, String.valueOf(pcId), String.valueOf(Status.BuildingType
                        .COMPANY));
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
        return R.layout.activity_node_company_protocol;
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
        nodeCompanyProtocolPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        rvPhotoPreview.create();
        spinnerPayType.setDicts(payTypeList, typeId -> {
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
        tvProtocolNeedPayAmount.setText(String.valueOf(MathUtil.sub(totalPurchasePrice, totalPay)));
    }

    @Override
    protected void initNet() {
        nodeCompanyProtocolPresenter.getCompanyProtocol(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        spinnerPayType.setEnabled(allowEdit);
        etProtocolTotalPurchasePrice.setEnabled(allowEdit);
        etProtocolClearObstaclePay.setEnabled(allowEdit);
        etProtocolTotalPay.setEnabled(allowEdit);
        etProtocolRemoveFee.setEnabled(allowEdit);
        etProtocolTempPlacementFee.setEnabled(allowEdit);
        etProtocolOtherFee.setEnabled(allowEdit);
        etProtocolTotalLandAZArea.setEnabled(allowEdit);
        setDateSelector(ivDateSelector, tvProtocolDate, allowEdit);
        etRemark.setEnabled(allowEdit);
        etProtocolTotalPurchasePrice.setEnabled(allowEdit);
        etProtocolTotalPay.setEnabled(allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String remark = etRemark.getText().toString().trim();
        String totalPurchasePrice = etProtocolTotalPurchasePrice.getText().toString().trim();
        String totalPay = etProtocolTotalPay.getText().toString().trim();
        String removeFee = etProtocolRemoveFee.getText().toString().trim();
        String tempPlacementFee = etProtocolTempPlacementFee.getText().toString().trim();
        String otherFee = etProtocolOtherFee.getText().toString().trim();
        String clearObstaclePay = etProtocolClearObstaclePay.getText().toString().trim();
        String changeArea = etProtocolChangeArea.getText().toString().trim();
        String damagesAmount = etProtocolDamagesAmount.getText().toString().trim();
        String pCDate = tvProtocolDate.getText().toString().trim();
        nodeCompanyProtocolPresenter.modifyCompanyProtocol(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("PayType", String.valueOf(payType))
                .addFormDataPart("TotalPurchasePrice", totalPurchasePrice)
                .addFormDataPart("TotalPay", totalPay)
                .addFormDataPart("RemoveFee", removeFee)
                .addFormDataPart("TempPlacementFee", tempPlacementFee)
                .addFormDataPart("OtherFee", otherFee)
                .addFormDataPart("PCDate", pCDate)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("ChangeArea", changeArea)
                .addFormDataPart("DamagesAmount", damagesAmount)
                .addFormDataPart("ClearObstaclePay", clearObstaclePay)
                .build());
    }

    @Override
    public void onGetCompanyProtocolSuccess(NodeCompanyProtocol nodeCompanyProtocol) {
        setEditable(true);
        pcId = nodeCompanyProtocol.getPCId();
        payType = nodeCompanyProtocol.getPayType();
        spinnerPayType.setSelectItem(payType);
        tvProtocolCusCode.setString(nodeCompanyProtocol.getCusCode());
        tvProtocolTotalBuildingArea.setString(nodeCompanyProtocol.getTotalBuildingArea());
        tvProtocolTotalNotRecordArea.setString(nodeCompanyProtocol.getTotalNotRecordArea());
        tvProtocolLandCertArea.setString(nodeCompanyProtocol.getLandCertArea());
        etProtocolTotalLandAZArea.setString(nodeCompanyProtocol.getTotalLandAZArea());
        etProtocolTotalPurchasePrice.setString(nodeCompanyProtocol.getTotalPurchasePrice());
        etProtocolClearObstaclePay.setString(nodeCompanyProtocol.getClearObstaclePay());
        etProtocolTotalPay.setString(nodeCompanyProtocol.getTotalPay());
        etProtocolRemoveFee.setString(nodeCompanyProtocol.getRemoveFee());
        etProtocolTempPlacementFee.setString(nodeCompanyProtocol.getTempPlacementFee());
        etProtocolOtherFee.setString(nodeCompanyProtocol.getOtherFee());
        tvProtocolAddress.setString(nodeCompanyProtocol.getAddress());
        tvProtocolDate.setText(nodeCompanyProtocol.getPCDate());
        etRemark.setString(nodeCompanyProtocol.getRemark());
        etProtocolDamagesAmount.setString(nodeCompanyProtocol.getDamagesAmount());
        etProtocolChangeArea.setString(nodeCompanyProtocol.getChangeArea());
        calculateTotalMoney();
    }

    @Override
    public void onModifyCompanyProtocolSuccess() {
        showSuccessAndFinish();
    }

}
