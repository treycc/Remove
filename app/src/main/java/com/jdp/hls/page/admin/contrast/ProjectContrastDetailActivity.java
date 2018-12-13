package com.jdp.hls.page.admin.contrast;

import android.content.Context;
import android.content.Intent;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;

/**
 * Description:TODO
 * Create Time:2018/12/12 0012 下午 7:48
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectContrastDetailActivity extends BaseTitleActivity {

    private String projectId;

    @Override
    public void initVariable() {
        projectId = getIntent().getStringExtra(Constants.Extra.PROJECTID);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_contrast_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "新旧对比";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initNet() {

    }
    public static void goActivity(Context context, String projectId) {
        Intent intent = new Intent(context, ProjectContrastDetailActivity.class);
        intent.putExtra(Constants.Extra.PROJECTID, projectId);
        context.startActivity(intent);
    }
}
