package com.jdp.hls.page.node.protocol.company.lastst;

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
    private CompanyMoneyChangeFragment companyMoneyChangeFragment;


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
        setDateSelector(ivDateSelector, tvProtocolDate, allowEdit);
        etRemark.setEnabled(allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String remark = etRemark.getText().toString().trim();
        String pCDate = tvProtocolDate.getText().toString().trim();
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
        pcId = nodeCompanyProtocol.getPCId();
        payType = nodeCompanyProtocol.getPayType();
        spinnerPayType.setSelectItem(payType);
        tvProtocolCompanyName.setString(nodeCompanyProtocol.getCompanyName());
        tvProtocolRealName.setString(nodeCompanyProtocol.getRealName());
        tvProtocolDate.setText(nodeCompanyProtocol.getPCDate());
        etRemark.setString(nodeCompanyProtocol.getRemark());
        rvPhotoPreview.setData(nodeCompanyProtocol.getFiles(), getFileConfig(), allowEdit);


        companyMoneyChangeFragment = CompanyMoneyChangeFragment.newInstance(null, 1);


    }

    @Override
    public void onModifyCompanyProtocolSuccess() {
        showSuccessDialogAndFinish();
    }
}
