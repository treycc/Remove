package com.jdp.hls.page.node.protocol.company.lastst;

import android.view.View;
import android.widget.ImageView;
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
import com.jdp.hls.page.node.protocol.company.NodeCompanyProtocolContract;
import com.jdp.hls.page.node.protocol.company.NodeCompanyProtocolPresenter;
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
public class NodeCompanyProtocolNewActivity extends BaseNodeActivity implements NodeCompanyProtocolContract.View {
    @BindView(R.id.tv_protocol_companyName)
    StringTextView tvProtocolCompanyName;
    @BindView(R.id.tv_protocol_realName)
    StringTextView tvProtocolRealName;
    @BindView(R.id.tv_protocol_date)
    TextView tvProtocolDate;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.tv_protocol_totalBuildingArea)
    StringTextView tvProtocolTotalBuildingArea;
    @BindView(R.id.tv_protocol_totalNotRecordArea)
    StringTextView tvProtocolTotalNotRecordArea;
    @BindView(R.id.tv_protocol_landCertArea)
    StringTextView tvProtocolLandCertArea;
    @BindView(R.id.spinner_payType)
    KSpinner spinnerPayType;
    @BindView(R.id.iv_arrow_right)
    ImageView ivArrowRight;
    @BindView(R.id.rl_photo_preview)
    RelativeLayout rlPhotoPreview;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    private int payType;

    @Inject
    NodeCompanyProtocolPresenter nodeCompanyProtocolPresenter;
    private List<TDict> payTypeList;
    private int pcId;

    @OnClick({R.id.rl_protocol_otherArea})
    public void rl_protocol_otherArea(View view) {
        switch (view.getId()) {
            case R.id.rl_protocol_otherArea:
//                OtherAreaListActivity.goActivity(this, String.valueOf(pcId), String.valueOf(Status.BuildingType
//                        .COMPANY), allowEdit);
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {
        super.initVariable();
        payTypeList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.COMPENSATION_TYPE);
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
        });
    }

    @Override
    public void initNet() {
        nodeCompanyProtocolPresenter.getCompanyProtocol(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        spinnerPayType.enable(allowEdit);
        setDateSelector(ivDateSelector, tvProtocolDate, allowEdit);
        etRemark.setEnabled(allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String remark = etRemark.getText().toString().trim();
        String pCDate = tvProtocolDate.getText().toString().trim();
        MultipartBody.Builder requestBuilder = getRequestBuilder(payType);
        nodeCompanyProtocolPresenter.modifyCompanyProtocol(requestBuilder
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("PayType", String.valueOf(payType))
                .addFormDataPart("PCDate", pCDate)
                .addFormDataPart("Remark", remark)
                .build());
    }

    private MultipartBody.Builder getRequestBuilder(int payType) {
        MultipartBody.Builder builder = null;
        switch (payType) {
            case 1:
                //货币置换
//                builder = payChangeFragment.getRequestBuilder();
                break;
            case 2:
                //土地退购
//                builder = payMoneyFragment.getRequestBuilder();
                break;
            case 3:
                //功能回购
//                builder = payRebuyFragment.getRequestBuilder();
                break;
            case 4:
                //小微园置换
//                builder = payRebuyFragment.getRequestBuilder();
                break;
            default:
//                builder = payChangeFragment.getRequestBuilder();
                break;
        }
        return builder;
    }

    @Override
    public void onGetCompanyProtocolSuccess(NodeCompanyProtocol nodeCompanyProtocol) {
        allowEdit = nodeCompanyProtocol.isAllowEdit();
        setEditable(allowEdit);
        pcId = nodeCompanyProtocol.getPCId();
        payType = nodeCompanyProtocol.getPayType();
        spinnerPayType.setSelectItem(payType);
        tvProtocolCompanyName.setString(nodeCompanyProtocol.getCompanyName());
        tvProtocolRealName.setString(nodeCompanyProtocol.getRealName());
        tvProtocolTotalBuildingArea.setString(nodeCompanyProtocol.getTotalBuildingArea());
        tvProtocolTotalNotRecordArea.setString(nodeCompanyProtocol.getTotalNotRecordArea());
        tvProtocolLandCertArea.setString(nodeCompanyProtocol.getLandCertArea());
        tvProtocolDate.setText(nodeCompanyProtocol.getPCDate());
        etRemark.setString(nodeCompanyProtocol.getRemark());
        rvPhotoPreview.setData(nodeCompanyProtocol.getFiles(), getFileConfig(), allowEdit);
    }

    @Override
    public void onModifyCompanyProtocolSuccess() {
        showSuccessDialogAndFinish();
    }
}
