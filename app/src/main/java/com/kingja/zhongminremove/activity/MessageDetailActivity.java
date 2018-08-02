package com.kingja.zhongminremove.activity;

import com.kingja.zhongminremove.R;
import com.kingja.zhongminremove.base.BaseTitleActivity;
import com.kingja.zhongminremove.injector.component.AppComponent;

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
    protected void initNet() {

    }
}
