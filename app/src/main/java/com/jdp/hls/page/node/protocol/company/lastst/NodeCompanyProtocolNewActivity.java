package com.jdp.hls.page.node.protocol.company.lastst;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodeCompanyProtocol;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.page.node.protocol.company.NodeCompanyProtocolContract;
import com.jdp.hls.page.node.protocol.company.NodeCompanyProtocolPresenter;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.StringTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;

/**
 * Description:协议生成-住宅
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyProtocolNewActivity extends BaseNodeActivity implements NodeCompanyProtocolContract.View {
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.spinner_payType)
    KSpinner spinnerPayType;
    @BindView(R.id.iv_arrow_right)
    ImageView ivArrowRight;
    @BindView(R.id.rl_photo_preview)
    RelativeLayout rlPhotoPreview;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    @BindView(R.id.tv_cusCode)
    StringTextView tvCusCode;
    @BindView(R.id.tv_enterpriseName)
    StringTextView tvEnterpriseName;
    @BindView(R.id.tv_address)
    StringTextView tvAddress;
    @BindView(R.id.tv_legalName)
    StringTextView tvLegalName;
    @BindView(R.id.tv_propertyArea)
    StringTextView tvPropertyArea;
    @BindView(R.id.tv_landArea)
    StringTextView tvLandArea;
    @BindView(R.id.tv_auditCompany)
    StringTextView tvAuditCompany;
    @BindView(R.id.tv_auditName)
    StringTextView tvAuditName;
    @BindView(R.id.tv_auditDate)
    StringTextView tvAuditDate;
    @BindView(R.id.tv_legalArea)
    StringTextView tvLegalArea;
    @BindView(R.id.tv_after90Area)
    StringTextView tvAfter90Area;
    @BindView(R.id.tv_otherArea)
    StringTextView tvOtherArea;
    private int payType;

    @Inject
    NodeCompanyProtocolPresenter nodeCompanyProtocolPresenter;
    private List<TDict> payTypeList;
    private int pcId;
    private CompanyMoneyChangeFragment companyMoneyChangeFragment;


    @Override
    public void initVariable() {
        super.initVariable();
        payTypeList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.COMPENSATION_TYPE_COMPANY);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_node_company_protocol_new;
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
        super.initData();
        spinnerPayType.setDicts(payTypeList, typeId -> {
            payType = typeId;
            //根据补偿方式切换数据
            companyMoneyChangeFragment.switchPayType(payType);
        });
    }

    @Override
    public void initNet() {
        nodeCompanyProtocolPresenter.getCompanyProtocol(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        spinnerPayType.enable(allowEdit);
        setDateSelector(ivDateSelector, tvAuditDate, allowEdit);
        etRemark.setEnabled(allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String remark = etRemark.getText().toString().trim();
        String pCDate = tvAuditDate.getText().toString().trim();
        MultipartBody.Builder requestBuilder = companyMoneyChangeFragment.getRequestBuilder();
        nodeCompanyProtocolPresenter.modifyCompanyProtocol(requestBuilder
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("PayType", String.valueOf(payType))
                .addFormDataPart("PCDate", pCDate)
                .addFormDataPart("Remark", remark)
                .build());
    }


    @Override
    public void onGetCompanyProtocolSuccess(NodeCompanyProtocol nodeCompanyProtocol) {
        allowEdit = nodeCompanyProtocol.isAllowEdit();
        setEditable(allowEdit);
        payType = nodeCompanyProtocol.getPayType();
        spinnerPayType.setSelectItem(payType);
        tvAuditCompany.setString(nodeCompanyProtocol.getAuditCompany());
        tvAuditName.setString(nodeCompanyProtocol.getAuditName());
        tvAuditDate.setText(nodeCompanyProtocol.getAuditDate());
        etRemark.setString(nodeCompanyProtocol.getRemark());

        tvCusCode.setString(nodeCompanyProtocol.getCusCode());
        tvEnterpriseName.setString(nodeCompanyProtocol.getEnterpriseName());
        tvAddress.setString(nodeCompanyProtocol.getAddress());
        tvLegalName.setString(nodeCompanyProtocol.getLegalName());
        tvPropertyArea.setString(nodeCompanyProtocol.getPropertyArea());
        tvLandArea.setString(nodeCompanyProtocol.getLandArea());

       tvLegalArea.setString(nodeCompanyProtocol.getLandArea());
       tvAfter90Area.setString(nodeCompanyProtocol.getAfter90Area());
       tvOtherArea.setString(nodeCompanyProtocol.getOtherArea());

        rvPhotoPreview.setData(nodeCompanyProtocol.getFiles(), getFileConfig(), allowEdit);
        companyMoneyChangeFragment = CompanyMoneyChangeFragment.newInstance(nodeCompanyProtocol.getItems(), payType,
                allowEdit);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_payType, companyMoneyChangeFragment).commit();


    }

    @Override
    public void onModifyCompanyProtocolSuccess() {
        showSuccessDialogAndFinish();
    }
}
