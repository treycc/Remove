package com.jdp.hls.page.business.main;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.business.detail.personal.BusinessDetailPersonalActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:个人业务首页
 * Create Time:2018/9/10 0010 上午 8:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BusinessMainPersonalActivity extends BaseTitleActivity {

    @BindView(R.id.rl_business_detail)
    RelativeLayout rlBusinessDetail;

    @OnClick({R.id.rl_business_detail})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_business_detail:
                GoUtil.goActivity(this, BusinessDetailPersonalActivity.class);
                break;
        }
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_business_main;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "高二路";
    }

    @Override
    protected void initView() {

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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
