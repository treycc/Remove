package com.jdp.hls.page.business.basic.company;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.ModifyBusinessEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.BasicCompany;
import com.jdp.hls.model.entiy.FlowNode;
import com.jdp.hls.page.business.detail.company.DetailCompanyActivity;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.page.node.age.company.NodeCompanyAgeActivity;
import com.jdp.hls.page.node.evaluate.company.NodeCompanyEvaluateActivity;
import com.jdp.hls.page.node.mapping.company.NodeCompanyMappingActivity;
import com.jdp.hls.page.node.measure.company.NodeCompanyMeasureActivity;
import com.jdp.hls.page.node.protocol.company.NodeCompanyProtocolActivity;
import com.jdp.hls.page.operate.OperateNodeContract;
import com.jdp.hls.page.operate.OperateNodePresenter;
import com.jdp.hls.util.NodeUtil;
import com.jdp.hls.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import okhttp3.RequestBody;

/**
 * Description:企业业务首页
 * Create Time:2018/9/10 0010 上午 8:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BasicCompanyActivity extends BaseBasicActivity implements BaiscCompanyContract.View, OperateNodeContract
        .View {
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
    private List<FlowNode> flowNodes = new ArrayList<>();
    @Inject
    BasicCompanyPresenter basicCompanyPresenter;

    @Inject
    OperateNodePresenter operateNodePresenter;
    private String buildingId;
    private NodeAdapter nodeAdapter;

    @OnItemClick({R.id.lv_business_node})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        FlowNode flowNode = (FlowNode) adapterView.getItemAtPosition(position);
        if (flowNode.isTitle()) {
            nodeAdapter.setVisibility(position);
        } else if (flowNode.isAvailable()) {
            NodeUtil.goNodeActivity(this, flowNode.getId(), buildingId);
        }
    }

    private void goNodeActivity(Class<? extends BaseNodeActivity> clazz, int fileType) {
        BaseNodeActivity.goActivity(this, clazz, String.valueOf(fileType), buildingId, Status.BuildingTypeStr
                .COMPANY);
    }

    @OnClick({R.id.rl_business_detail})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_business_detail:
                DetailCompanyActivity.goActivity(this, buildingId);
                break;
        }
    }

    @Override
    public void initVariable() {
        buildingId = getIntent().getStringExtra("buildingId");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_business_company_basic;
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
        basicCompanyPresenter.attachView(this);
        operateNodePresenter.attachView(this);
        nodeAdapter = new NodeAdapter(this, flowNodes, buildingId);
        lvBusinessNode.setAdapter(nodeAdapter);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initNet() {
        basicCompanyPresenter.getCompanyBasic(buildingId);
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

    public static void goActivity(Context context, String buildingId) {
        Intent intent = new Intent(context, BasicCompanyActivity.class);
        intent.putExtra("buildingId", buildingId);
        context.startActivity(intent);
    }

    @Override
    public void onGetCompanyBasicSuccess(BasicCompany basicCompany) {
        tvBasicSyscode.setText(basicCompany.getSysCode());
        tvBasicName.setText(basicCompany.getEnterpriseName());
        tvBasicAddress.setText(basicCompany.getAddress());
        if (!TextUtils.isEmpty(basicCompany.getMsgTitle())) {
            llMsgRoot.setVisibility(View.VISIBLE);
            tvMsgTitle.setText(basicCompany.getMsgTitle());
            tvMsgContent.setText(basicCompany.getMsgContent());
        }
        setContentTitle(basicCompany.getAddress());
        List<FlowNode> flowNodes = basicCompany.getFlowNodes();
        if (flowNodes != null && flowNodes.size() > 0) {
            nodeAdapter.setData(flowNodes);
        }
        setSingleAuth(basicCompany.getAuth(), basicCompany.getHouseId(), String.valueOf(Status.BuildingType.COMPANY),
                String.valueOf(basicCompany.getStatusId()));
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyBusinessEvent(ModifyBusinessEvent event) {
        if (event.getBuildingType() == Status.BuildingType.COMPANY) {
            tvBasicName.setText(event.getRealName());
            tvBasicAddress.setText(event.getAddress());
        }
    }
}
