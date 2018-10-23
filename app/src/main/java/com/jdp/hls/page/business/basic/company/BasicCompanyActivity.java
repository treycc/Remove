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
                    goNodeActivity(NodeCompanyMeasureActivity.class, Status.FileType.NODE_MEASURE);
                    break;
                case Constants.BusinessNode.MAPPING:
                    //测绘出图
                    goNodeActivity(NodeCompanyMappingActivity.class, Status.FileType.NODE_MAPPING);
                    break;
                case Constants.BusinessNode.AGE:
                    //年限鉴定
                    goNodeActivity(NodeCompanyAgeActivity.class, Status.FileType.NODE_AGE);
                    break;
                case Constants.BusinessNode.EVALUATE:
                    //入户评估
                    goNodeActivity(NodeCompanyEvaluateActivity.class, Status.FileType.NODE_EVALUATE);
                    break;
                case Constants.BusinessNode.PROTOCOL:
                    //协议生成
                    goNodeActivity(NodeCompanyProtocolActivity.class, Status.FileType.NODE_PROTOCOL);
                    break;
            }
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
        List<FlowNode> flowNodes = basicCompany.getFlowNodes();
        if (flowNodes != null && flowNodes.size() > 0) {
            flowNodeAdapter.setData(flowNodes);
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
        showSuccessAndFinish("发送成功");
    }

    @Override
    public void onReviewNodeSuccess(String buildingIds) {
        showSuccessAndFinish("复查成功");
    }

    @Override
    public void onBackNodeSuccess(String buildingIds) {
        showSuccessAndFinish("退回成功");
    }
}
