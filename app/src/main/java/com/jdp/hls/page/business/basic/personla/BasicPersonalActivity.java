package com.jdp.hls.page.business.basic.personla;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.NodeAdapter;
import com.jdp.hls.base.BaseBasicActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.ModifyBusinessEvent;
import com.jdp.hls.event.ModifyMainContactsEvent;
import com.jdp.hls.event.RefreshReminderEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.BaiscPersonal;
import com.jdp.hls.model.entiy.FlowNode;
import com.jdp.hls.page.business.detail.personal.DetailPersonalActivity;
import com.jdp.hls.page.operate.OperateNodeContract;
import com.jdp.hls.page.operate.OperateNodePresenter;
import com.jdp.hls.page.supervise.project.contrast.VRDetailActivity;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NodeUtil;
import com.jdp.hls.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import okhttp3.RequestBody;

/**
 * Description:个人业务首页
 * Create Time:2018/9/10 0010 上午 8:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BasicPersonalActivity extends BaseBasicActivity implements BaiscPersonalContract.View,
        OperateNodeContract.View {
    @BindView(R.id.rl_business_detail)
    RelativeLayout rlBusinessDetail;
    @BindView(R.id.lv_business_node)
    ListView lvBusinessNode;
    @BindView(R.id.tv_basic_syscode)
    TextView tvBasicSyscode;
    @BindView(R.id.tv_basic_name)
    TextView tvBasicName;
    @BindView(R.id.tv_basic_address)
    TextView tvBasicAddress;
    @BindView(R.id.tv_msgTitle)
    TextView tvMsgTitle;
    @BindView(R.id.tv_msgContent)
    TextView tvMsgContent;
    @BindView(R.id.ll_msgRoot)
    LinearLayout llMsgRoot;
    @BindView(R.id.tv_vrTip)
    TextView tvVrTip;
    private List<FlowNode> flowNodes = new ArrayList<>();
    @Inject
    BasicPersonalPresenter basicPersonalPresenter;
    @Inject
    OperateNodePresenter operateNodePresenter;
    private String buildingId;
    private NodeAdapter nodeAdapter;
    private String vrUrl;


    @OnItemClick({R.id.lv_business_node})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        FlowNode flowNode = (FlowNode) adapterView.getItemAtPosition(position);
        if (flowNode.isTitle()) {
            nodeAdapter.setVisibility(position);
        } else if (flowNode.isAvailable()) {
            NodeUtil.goNodeActivity(this, flowNode.getId(), buildingId);
        }
    }

    @OnClick({R.id.rl_business_detail, R.id.rl_business_vr})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_business_detail:
                DetailPersonalActivity.goActivity(this, buildingId);
                break;
            case R.id.rl_business_vr:
                if (TextUtils.isEmpty(vrUrl)) {
                    ToastUtil.showText("暂无VR信息");
                } else {
                    VRDetailActivity.goActivity(this, vrUrl, "全景VR");
                }
                break;
        }
    }

    @Override
    public void initVariable() {
        buildingId = getIntent().getStringExtra("buildingId");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_business_personal_basic;
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
        return "";
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        basicPersonalPresenter.attachView(this);
        operateNodePresenter.attachView(this);
        nodeAdapter = new NodeAdapter(this, flowNodes, buildingId);
        lvBusinessNode.setAdapter(nodeAdapter);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void initNet() {
        basicPersonalPresenter.getPersonalBasic(buildingId);
    }

    @Override
    protected void onSendNode(RequestBody requestBody, String buildingIds) {
        operateNodePresenter.sendNode(requestBody, buildingIds);
    }

    @Override
    protected void onBackNode(RequestBody requestBody, String buildingIds) {
        operateNodePresenter.backNode(requestBody, buildingIds);
    }

    @Override
    protected void onReviewNode(RequestBody requestBody, String buildingIds) {
        operateNodePresenter.reviewNode(requestBody, buildingIds);
    }

    @Override
    protected void onDeleteNode(RequestBody requestBody, String buildingIds) {
        operateNodePresenter.deleteNode(requestBody, buildingIds);
    }

    @Override
    protected void onRecoverNode(RequestBody requestBody, String buildingIds) {
        operateNodePresenter.recoverNode(requestBody, buildingIds);
    }

    @Override
    protected void onReminderNode(RequestBody requestBody, String buildingIds) {
        operateNodePresenter.reminderNode(requestBody, buildingIds);
    }

    public static void goActivity(Context context, String buildingId) {
        Intent intent = new Intent(context, BasicPersonalActivity.class);
        intent.putExtra("buildingId", buildingId);
        context.startActivity(intent);
    }

    @Override
    public void onGetPersonalBasicSuccess(BaiscPersonal baiscPersonal) {
        tvBasicSyscode.setText(baiscPersonal.getSysCode());
        tvBasicName.setText(baiscPersonal.getRealName());
        tvBasicAddress.setText(baiscPersonal.getAddress());
        vrUrl = baiscPersonal.getVRUrl();
        tvVrTip.setVisibility(TextUtils.isEmpty(vrUrl) ? View.VISIBLE : View.GONE);

        if (!TextUtils.isEmpty(baiscPersonal.getMsgTitle())) {
            llMsgRoot.setVisibility(View.VISIBLE);
            tvMsgTitle.setText(baiscPersonal.getMsgTitle());
            tvMsgContent.setText(baiscPersonal.getMsgContent());
        }
        setContentTitle(baiscPersonal.getAddress());
        List<FlowNode> flowNodes = baiscPersonal.getFlowNodes();
        if (flowNodes != null && flowNodes.size() > 0) {
            nodeAdapter.setData(flowNodes);
        }
        setSingleAuth(baiscPersonal.getAuth(), baiscPersonal.getHouseId(), String.valueOf(Status.BuildingType.PERSONAL),
                String.valueOf(baiscPersonal.getStatusId()), String.valueOf(baiscPersonal.getGroupId()));
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    public void onDeleteNodeSuccess(String buildingIds) {
        onOperateSuccess("废弃成功", buildingIds);
    }

    @Override
    public void onSendNodeSuccess(String buildingIds) {
        onOperateSuccess("发送成功", buildingIds);
    }

    @Override
    public void onReviewNodeSuccess(String buildingIds) {
        onOperateSuccess("复查成功", buildingIds);
    }

    @Override
    public void onBackNodeSuccess(String buildingIds) {
        onOperateSuccess("退回成功", buildingIds);
    }

    @Override
    public void onRecoverNodeSuccess(String buildingIds) {
        onOperateSuccess("恢复成功", buildingIds);
    }

    @Override
    public void onReminderNodeSuccess(String buildingIds) {
        EventBus.getDefault().post(new RefreshReminderEvent(buildingIds));
        showSuccessDialogAndFinish("催办成功");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyBusinessEvent(ModifyBusinessEvent event) {
        if (event.getBuildingType() == Status.BuildingType.PERSONAL) {
            tvBasicName.setText(event.getRealName());
            tvBasicAddress.setText(event.getAddress());
            vrUrl = event.getVRUrl();
            tvVrTip.setVisibility(TextUtils.isEmpty(vrUrl) ? View.VISIBLE : View.GONE);
            LogUtil.e(TAG, "vrUrl:" + vrUrl);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyMainContacts(ModifyMainContactsEvent event) {
        int buildingType = event.getRoster().isEnterprise() ? 1 : 0;
        if (buildingType == Status.BuildingType.PERSONAL) {
           tvBasicName.setText(event.getRoster().getRealName());
        }
    }
}
