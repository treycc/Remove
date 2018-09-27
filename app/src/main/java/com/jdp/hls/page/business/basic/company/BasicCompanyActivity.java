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
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.BaiscCompany;
import com.jdp.hls.model.entiy.FlowNode;
import com.jdp.hls.page.business.detail.company.DetailCompanyActivity;
import com.jdp.hls.page.node.measure.personal.NodePersonalMeasureActivity;
import com.jdp.hls.page.node.age.company.NodeCompanyAgeActivity;
import com.jdp.hls.page.node.evaluate.company.NodeCompanyEvaluateActivity;
import com.jdp.hls.page.node.mapping.company.NodeCompanyMappingActivity;
import com.jdp.hls.page.node.protocol.company.NodeCompanyProtocolActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Description:企业业务首页
 * Create Time:2018/9/10 0010 上午 8:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BasicCompanyActivity extends BaseTitleActivity implements BaiscCompanyContract.View {
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
    private int roleCode = 3;
    @Inject
    BasicCompanyPresenter basicCompanyPresenter;
    private String buildingId;
    private FlowNodeAdapter flowNodeAdapter;

    @OnItemClick({R.id.lv_business_node})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        FlowNode flowNode = (FlowNode) adapterView.getItemAtPosition(position);
        if (roleCode >= flowNode.getNodeStatusId()) {
            switch (flowNode.getNodeStatusId()) {
                case Constants.BusinessNode.MEASURE:
                    GoUtil.goActivity(this, NodePersonalMeasureActivity.class);
                    break;
                case Constants.BusinessNode.MAPPING:
                    GoUtil.goActivity(this, NodeCompanyMappingActivity.class);
                    break;
                case Constants.BusinessNode.AGE:
                    GoUtil.goActivity(this, NodeCompanyAgeActivity.class);
                    break;
                case Constants.BusinessNode.EVALUATE:
                    GoUtil.goActivity(this, NodeCompanyEvaluateActivity.class);
                    break;
                case Constants.BusinessNode.PROTOCOL:
                    GoUtil.goActivity(this, NodeCompanyProtocolActivity.class);
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
        String[] businessDes = getResources().getStringArray(R.array.business_nodes);
        for (int i = 0; i < businessDes.length; i++) {
            flowNodes.add(new FlowNode(i, businessDes[i]));
        }
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
        flowNodeAdapter = new FlowNodeAdapter(this, flowNodes, R.layout.item_business_node);
        lvBusinessNode.setAdapter(flowNodeAdapter);

    }

    @Override
    protected void initData() {
        setRightClick("流程", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                ToastUtil.showText("流程");
            }
        });
    }

    @Override
    protected void initNet() {
        basicCompanyPresenter.getCompanyBasic(buildingId);
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
    }

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }
}
