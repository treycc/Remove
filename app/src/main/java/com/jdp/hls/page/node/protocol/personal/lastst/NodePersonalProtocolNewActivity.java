package com.jdp.hls.page.node.protocol.personal.lastst;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import com.jdp.hls.page.node.protocol.personal.NodePersonalProtocolContract;
import com.jdp.hls.page.node.protocol.personal.NodePersonalProtocolPresenter;
import com.jdp.hls.page.otherarea.list.OtherAreaListActivity;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.StringTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:协议生成-个人
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
    TextView tvProtocolDate;
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
    @BindView(R.id.rl_protocol_otherArea)
    RelativeLayout rlProtocolOtherArea;
    @BindView(R.id.rl_photo_preview)
    RelativeLayout rlPhotoPreview;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    private int payType;
    @Inject
    NodePersonalProtocolPresenter nodePersonalProtocolPresenter;
    private List<TDict> payTypeList;
    private int pcId;
    private PayMoneyFragment payMoneyFragment;
    private PayChangeFragment payChangeFragment;
    private PayRebuyFragment payRebuyFragment;

    @OnClick({R.id.rl_protocol_otherArea})
    public void rl_protocol_otherArea(View view) {
        switch (view.getId()) {
            case R.id.rl_protocol_otherArea:
                OtherAreaListActivity.goActivity(this, String.valueOf(pcId), String.valueOf(Status.BuildingType
                        .PERSONAL), allowEdit);
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
        });
        spinnerProtocolPayType.setSelectItem(spinnerProtocolPayType.getDefaultTypeId());
        payMoneyFragment = new PayMoneyFragment();
        payChangeFragment = new PayChangeFragment();
        payRebuyFragment = new PayRebuyFragment();


        switchPayType(1);
    }

    private void switchPayType(int typeId) {
        switch (typeId) {
            case 1:
                //产权置换
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_payType, payChangeFragment).commit();
                break;
            case 2:
                //货币补偿
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_payType, payMoneyFragment).commit();
                break;
            case 3:
                //权益回购
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_payType, payRebuyFragment).commit();
                break;

        }
    }


    @Override
    public void initNet() {
//        nodePersonalProtocolPresenter.getPersonalProtocol(mBuildingId);


    }

    @Override
    protected void onUiEditable(boolean allowEdit) {

    }

    @Override
    protected void onSaveDate() {

    }

    @Override
    public void onGetPersonalProtocolSuccess(NodePersonalProtocol nodePersonalProtocol) {

    }

    @Override
    public void onModifyPersonalProtocolSuccess() {
        showSuccessDialogAndFinish();
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return false;
    }
}
