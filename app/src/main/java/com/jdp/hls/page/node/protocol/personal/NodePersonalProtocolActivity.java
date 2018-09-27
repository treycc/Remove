package com.jdp.hls.page.node.protocol.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.otherarea.OtherAreaListActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.EncryptUtil;
import com.jdp.hls.util.GoUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:协议生成-个人
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodePersonalProtocolActivity extends BaseTitleActivity {
    @BindView(R.id.rl_protocol_otherArea)
    RelativeLayout rlProtocolOtherArea;

    @OnClick({R.id.stv_login_confirm})
    public void rl_protocol_otherArea(View view) {
        switch (view.getId()) {
            case R.id.rl_protocol_otherArea:
                GoUtil.goActivity(this, OtherAreaListActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_node_personal_protocol;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "协议生成";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
