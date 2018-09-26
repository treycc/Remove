package com.jdp.hls.page.business.basic.personla;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.BusinessNodeAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.BusinessNode;
import com.jdp.hls.model.entiy.BaiscPersonal;
import com.jdp.hls.page.business.detail.personal.DetailPersonalActivity;
import com.jdp.hls.page.node.measure.personal.NodePersonalMeasureActivity;
import com.jdp.hls.page.business.node.personal.NodePersonalAgeActivity;
import com.jdp.hls.page.business.node.personal.NodePersonalEvaluateActivity;
import com.jdp.hls.page.business.node.personal.NodePersonalMappingActivity;
import com.jdp.hls.page.business.node.personal.NodePersonalProtocolActivity;
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
 * Description:个人业务首页
 * Create Time:2018/9/10 0010 上午 8:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BasicPersonalActivity extends BaseTitleActivity implements BaiscPersonalContract.View {
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
    private List<BusinessNode> businessNodes = new ArrayList<>();
    private int roleCode = 3;

    @Inject
    BasicPersonalPresenter basicPersonalPresenter;
    private String buildingId;


    @OnItemClick({R.id.lv_business_node})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        BusinessNode businessNode = (BusinessNode) adapterView.getItemAtPosition(position);
        if (roleCode >= businessNode.getNodeCode()) {
            switch (businessNode.getNodeCode()) {
                case Constants.BusinessNode.MEASURE:
                    GoUtil.goActivity(this, NodePersonalMeasureActivity.class);
                    break;
                case Constants.BusinessNode.MAPPING:
                    GoUtil.goActivity(this, NodePersonalMappingActivity.class);
                    break;
                case Constants.BusinessNode.AGE:
                    GoUtil.goActivity(this, NodePersonalAgeActivity.class);
                    break;
                case Constants.BusinessNode.EVALUATE:
                    GoUtil.goActivity(this, NodePersonalEvaluateActivity.class);
                    break;
                case Constants.BusinessNode.PROTOCOL:
                    GoUtil.goActivity(this, NodePersonalProtocolActivity.class);
                    break;
            }
        }

    }

    @OnClick({R.id.rl_business_detail})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_business_detail:
                DetailPersonalActivity.goActivity(this,buildingId);
                break;
        }
    }

    @Override
    public void initVariable() {
        buildingId = getIntent().getStringExtra("buildingId");
        String[] businessDes = getResources().getStringArray(R.array.business_nodes);
        for (int i = 0; i < businessDes.length; i++) {
            businessNodes.add(new BusinessNode(i, businessDes[i]));
        }
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
        return "高二路";
    }

    @Override
    protected void initView() {
        basicPersonalPresenter.attachView(this);
        BusinessNodeAdapter businessNodeAdapter = new BusinessNodeAdapter(this, businessNodes, R.layout
                .item_business_node, roleCode);
        lvBusinessNode.setAdapter(businessNodeAdapter);

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
        basicPersonalPresenter.getPersonalBasic(buildingId);
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
    }

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }
}
