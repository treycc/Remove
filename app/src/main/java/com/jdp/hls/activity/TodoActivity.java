package com.jdp.hls.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.deed.company.license.DeedCompanyBusinessActivity;

/**
 * Description:TODO
 * Create Time:2018/12/13 0013 下午 7:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TodoActivity extends BaseTitleActivity {

    private String title;

    @Override
    public void initVariable() {
        title = getIntent().getStringExtra(Constants.Extra.TITLE);
    }

    @Override
    protected int getContentView() {
        return R.layout.layout_todo;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return title;
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

    public static void goActivity(Context context, String titlle) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, TodoActivity.class);
                intent.putExtra(Constants.Extra.TITLE, titlle);
                context.startActivity(intent);
            }
        },500);

    }
}
