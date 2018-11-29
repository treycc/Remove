package com.jdp.hls.page.supervise.statistics.progress.report.daylist;

import android.os.Bundle;

import com.jdp.hls.R;
import com.jdp.hls.adapter.ReportAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:TODO
 * Create Time:2018/11/28 0028 上午 9:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReportListDayActivity extends BaseTitleActivity {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.rsrl)
    RefreshSwipeRefreshLayout rsrl;
    private ReportAdapter reportAdapter;

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_report_list;
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
        return "日报表";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        reportAdapter = new ReportAdapter(this, null);
        plv.setAdapter(reportAdapter);

    }

    @Override
    public void initNet() {

    }

    @Override
    public boolean ifRegisterLoadSir() {
        return false;
    }
}
