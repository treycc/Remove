package com.jdp.hls.page.supervise.statistics.progress.detail;

import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.NoDoubleClickListener;

/**
 * Description:TODO
 * Create Time:2018/11/28 0028 上午 8:49
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsProgressDetailActivity extends BaseTitleActivity {
    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_statistics_progress_detail;
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
        return "统计详情";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setRightClick("查看报表", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {

            }
        });

    }

    @Override
    public void initNet() {

    }
}
