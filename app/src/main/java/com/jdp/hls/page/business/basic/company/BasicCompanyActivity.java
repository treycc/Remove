package com.jdp.hls.page.business.basic.company;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.FlowNodeAdapter;
import com.jdp.hls.base.BaseBasicActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.BaiscCompany;
import com.jdp.hls.model.entiy.FlowNode;
import com.jdp.hls.page.business.detail.company.DetailCompanyActivity;
import com.jdp.hls.page.node.age.company.NodeCompanyAgeActivity;
import com.jdp.hls.page.node.evaluate.company.NodeCompanyEvaluateActivity;
import com.jdp.hls.page.node.mapping.company.NodeCompanyMappingActivity;
import com.jdp.hls.page.node.measure.company.NodeCompanyMeasureActivity;
import com.jdp.hls.page.node.protocol.company.NodeCompanyProtocolActivity;
import com.jdp.hls.page.operate.OperateNodeContract;
import com.jdp.hls.page.operate.OperateNodePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import okhttp3.RequestBody;

/**
 * Description:企业业务首页
 * Create Time:2018/9/10 0010 上午 8:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BasicCompanyActivity extends BaseBasicActivity implements BaiscCompanyContract.View, OperateNodeContract.View {
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
    private List<FlowNode> flowNodes = new ArrayList<>();
    @Inject
    BasicCompanyPresenter basicCompanyPresenter;

    @Inject
    OperateNodePresenter operateNodePresenter;
    private String buildingId;
    private FlowNodeAdapter flowNodeAdapter;

    @OnItemClick({R.id.lv_business_node})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        FlowNode flowNode = (FlowNode) adapterView.getItemAtPosition(position);
        if (flowNode.isAvailable()) {
            switch (flowNode.getNodeStatusId()) {
                case Constants.BusinessNode.MEASURE:
                    //入户丈量
                    NodeCompanyMeasureActivity.goActivity(this, NodeCompanyMeasureActivity.class, buildingId);
                    break;
                case Constants.BusinessNode.MAPPING:
                    //测绘出图
                    NodeCompanyMappingActivity.goActivity(this, NodeCompanyMappingActivity.class, buildingId);
                    break;
                case Constants.BusinessNode.AGE:
                    //年限鉴定
                    NodeCompanyAgeActivity.goActivity(this, NodeCompanyAgeActivity.class, buildingId);
                    break;
                case Constants.BusinessNode.EVALUATE:
                    //入户评估
                    NodeCompanyEvaluateActivity.goActivity(this, NodeCompanyEvaluateActivity.class, buildingId);
                    break;
                case Constants.BusinessNode.PROTOCOL:
                    //协议生成
                    NodeCompanyProtocolActivity.goActivity(this, NodeCompanyProtocolActivity.class, buildingId);
                    break;
            }
        }

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
        return "高二路";
    }

    @Override
    protected void initView() {
        basicCompanyPresenter.attachView(this);
        operateNodePresenter.attachView(this);
        flowNodeAdapter = new FlowNodeAdapter(this, flowNodes, R.layout.item_business_node);
        lvBusinessNode.setAdapter(flowNodeAdapter);

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initNet() {
        basicCompanyPresenter.getCompanyBasic(buildingId);
    }

    @Override
    protected void onSendNode(RequestBody requestBody) {
        operateNodePresenter.sendNode(requestBody);
    }

    @Override
    protected void onBackNode(RequestBody requestBody) {
        operateNodePresenter.backNode(requestBody);
    }

    @Override
    protected void onReviewNode(RequestBody requestBody) {
        operateNodePresenter.reviewNode(requestBody);
    }

    @Override
    protected void onDeleteNode(RequestBody requestBody) {
        operateNodePresenter.deleteNode(requestBody);
    }

    public static void goActivity(Context context, String buildingId) {
        Intent intent = new Intent(context, BasicCompanyActivity.class);
        intent.putExtra("buildingId", buildingId);
        context.startActivity(intent);
    }

    @Override
    public void onGetCompanyBasicSuccess(BaiscCompany baiscCompany) {
        tvBasicSyscode.setText(baiscCompany.getSysCode());
        tvBasicName.setText(baiscCompany.getEnterpriseName());
        tvBasicAddress.setText(baiscCompany.getAddress());
        List<FlowNode> flowNodes = baiscCompany.getFlowNodes();
        if (flowNodes != null && flowNodes.size() > 0) {
            flowNodeAdapter.setData(flowNodes);
        }
        setSingleAuth(baiscCompany.getAuth(), baiscCompany.getHouseId(), String.valueOf(Status.BuildingType.COMPANY),
                String.valueOf(baiscCompany.getStatusId()));
    }

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    public void onDeleteNodeSuccess() {
        showSuccessAndFinish("废弃成功");
    }

    @Override
    public void onSendNodeSuccess() {
        showSuccessAndFinish("发送成功");
    }

    @Override
    public void onReviewNodeSuccess() {
        showSuccessAndFinish("复查成功");
    }

    @Override
    public void onBackNodeSuccess() {
        showSuccessAndFinish("退回成功");
    }
}
