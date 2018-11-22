package com.jdp.hls.page.admin.message;

import android.content.Context;
import android.content.Intent;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.admin.query.list.QueryListActivity;

/**
 * Description:TODO
 * Create Time:2018/11/19 0019 下午 6:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NotificationDetailActivity extends BaseTitleActivity {

    private String messageTypeName;

    @Override
    public void initVariable() {
        messageTypeName = getIntent().getStringExtra(Constants.Extra.MessageTypeName);
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
        return messageTypeName;
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

    public static void GoActivity(Context context, String messageTypeName) {
        Intent intent = new Intent(context, NotificationDetailActivity.class);
        intent.putExtra(Constants.Extra.MessageTypeName, messageTypeName);
        context.startActivity(intent);
    }
}
