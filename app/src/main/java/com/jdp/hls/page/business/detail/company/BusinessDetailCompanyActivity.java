package com.jdp.hls.page.business.detail.company;

import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;

/**
 * Description:公司业务详情
 * Create Time:2018/9/10 0010 上午 8:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BusinessDetailCompanyActivity extends BaseTitleActivity {
    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_business_company_detail;
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
}
