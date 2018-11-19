package com.jdp.hls.page.admin.message;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;

/**
 * Description:TODO
 * Create Time:2018/11/19 0019 下午 6:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NotificationDetailActivity extends BaseTitleActivity {
    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.common_lv;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "消息";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initNet() {
        showEmptyCallback();

    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
