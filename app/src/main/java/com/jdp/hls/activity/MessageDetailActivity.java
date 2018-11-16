package com.jdp.hls.activity;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;

/**
 * Description:TODO
 * Create Time:2018/7/30 0030 上午 10:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MessageDetailActivity extends BaseTitleActivity {
    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "消息内容";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setContentTitle("发送者");

    }

    @Override
    public void initNet() {

    }
}
