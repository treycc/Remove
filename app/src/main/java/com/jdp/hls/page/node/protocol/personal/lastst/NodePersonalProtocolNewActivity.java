package com.jdp.hls.page.node.protocol.personal.lastst;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodePersonalProtocol;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.page.node.protocol.personal.NodePersonalProtocolContract;
import com.jdp.hls.page.node.protocol.personal.NodePersonalProtocolPresenter;
import com.jdp.hls.page.node.protocol.personal.lastst.pay.list.PayListActivity;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.StringTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MultipartBody;

/**
 * Description:协议生成-住宅
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodePersonalProtocolNewActivity extends BaseNodeActivity implements NodePersonalProtocolContract.View {
    @BindView(R.id.tv_protocol_companyName)
    StringTextView tvProtocolCompanyName;
    @BindView(R.id.tv_protocol_realName)
    StringTextView tvProtocolRealName;
    @BindView(R.id.tv_protocol_date)
    StringTextView tvProtocolDate;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.tv_protocol_totalArea)
    StringTextView tvProtocolTotalArea;
    @BindView(R.id.tv_protocol_totalNotRecordArea)
    StringTextView tvProtocolTotalNotRecordArea;
    @BindView(R.id.tv_protocol_buildOccupyArea)
    StringTextView tvProtocolBuildOccupyArea;
    @BindView(R.id.tv_protocol_landCertArea)
    StringTextView tvProtocolLandCertArea;
    @BindView(R.id.spinner_protocol_payType)
    KSpinner spinnerProtocolPayType;
    @BindView(R.id.fl_payType)
    FrameLayout flPayType;
    @BindView(R.id.rl_photo_preview)
    RelativeLayout rlPhotoPreview;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    @BindView(R.id.tv_payableAmount)
    StringTextView tvPayableAmount;
    @BindView(R.id.tv_paidAmount)
    StringTextView tvPaidAmount;
    @BindView(R.id.tv_balanceAmount)
    StringTextView tvBalanceAmount;
    private int payType;
    @Inject
    NodePersonalProtocolPresenter nodePersonalProtocolPresenter;
    private List<TDict> payTypeList;
    private PayMoneyFragment payMoneyFragment;
    private PayChangeFragment payChangeFragment;
    private PayRebuyFragment payRebuyFragment;


    @OnClick({R.id.rl_payList})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_payList:
                PayListActivity.goActivity(this, mBuildingId, mBuildingType, SpSir.getInstance().isOperatorAccount());
                break;
        }
    }

    @Override
    public void initVariable() {
        super.initVariable();
        payTypeList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.COMPENSATION_TYPE);
        LogUtil.e(TAG, "payTypeList:" + payTypeList.size());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_node_personal_protocol_new;
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
            switchPayType(typeId);
            LogUtil.e(TAG, "typeId:" + typeId);
        });
    }

    private void switchPayType(int typeId) {
        switch (typeId) {
            case 1:
                //产权置换
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_payType, payChangeFragment).commit();
                break;
            case 2:
                //货币退购
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_payType, payMoneyFragment).commit();
                break;
            case 3:
                //权益回购
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_payType, payRebuyFragment).commit();
                break;
            default:
                //产权置换
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_payType, payChangeFragment).commit();
        }
    }


    @Override
    public void initNet() {
        nodePersonalProtocolPresenter.getPersonalProtocol(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        etRemark.setEnabled(allowEdit);
        spinnerProtocolPayType.enable(allowEdit);
        setDateSelector(ivDateSelector, tvProtocolDate, allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String remark = etRemark.getText().toString().trim();
        String pCDate = tvProtocolDate.getText().toString().trim();
        MultipartBody.Builder requestBuilder = getRequestBuilder(payType);
        nodePersonalProtocolPresenter.modifyPersonalProtocol(requestBuilder
                .addFormDataPart("HouseId", mBuildingId)
                .addFormDataPart("PayType", String.valueOf(payType))
                .addFormDataPart("PCDate", pCDate)
                .addFormDataPart("Remark", remark)
                .build());
    }

    private MultipartBody.Builder getRequestBuilder(int payType) {
        MultipartBody.Builder builder = null;
        switch (payType) {
            case 1:
                //产权置换
                builder = payChangeFragment.getRequestBuilder();
                break;
            case 2:
                //货币退购
                builder = payMoneyFragment.getRequestBuilder();
                break;
            case 3:
                //权益回购
                builder = payRebuyFragment.getRequestBuilder();
                break;
            default:
                builder = payChangeFragment.getRequestBuilder();
                break;
        }
        return builder;
    }

    @Override
    public void onGetPersonalProtocolSuccess(NodePersonalProtocol nodePersonalProtocol) {
        allowEdit = nodePersonalProtocol.isAllowEdit();
        setEditable(allowEdit);
        etRemark.setString(nodePersonalProtocol.getRemark());
        tvProtocolCompanyName.setString(nodePersonalProtocol.getCompanyName());
        tvProtocolRealName.setString(nodePersonalProtocol.getRealName());
        tvProtocolDate.setText(nodePersonalProtocol.getPCDate());
        tvProtocolTotalArea.setString(nodePersonalProtocol.getTotalArea());
        tvProtocolTotalNotRecordArea.setString(nodePersonalProtocol.getTotalNotRecordArea());
        tvProtocolBuildOccupyArea.setString(nodePersonalProtocol.getBuildOccupyArea());
        tvProtocolLandCertArea.setString(nodePersonalProtocol.getLandCertArea());

        tvPayableAmount.setString(nodePersonalProtocol.getPayableAmount());
        tvPaidAmount.setString(nodePersonalProtocol.getPaidAmount());
        tvBalanceAmount.setString(nodePersonalProtocol.getBalanceAmount());

        payType = nodePersonalProtocol.getPayType();
        if (payType == 99) {
            payType=spinnerProtocolPayType.getDefaultTypeId();
        }
        payMoneyFragment = PayMoneyFragment.newInstance(nodePersonalProtocol);
        payChangeFragment = PayChangeFragment.newInstance(nodePersonalProtocol);
        payRebuyFragment = PayRebuyFragment.newInstance(nodePersonalProtocol);
        spinnerProtocolPayType.setSelectItem(payType);
        switchPayType(payType);
        rvPhotoPreview.setData(nodePersonalProtocol.getFiles(), getFileConfig(), allowEdit);


    }

    @Override
    public void onModifyPersonalProtocolSuccess() {
        showSuccessDialogAndFinish();
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
