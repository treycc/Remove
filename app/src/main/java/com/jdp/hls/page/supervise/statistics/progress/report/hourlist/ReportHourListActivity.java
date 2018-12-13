package com.jdp.hls.page.supervise.statistics.progress.report.hourlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ReportHourResult;
import com.jdp.hls.model.entiy.ReportItem;
import com.jdp.hls.model.entiy.ReportTitle;
import com.jdp.hls.page.supervise.statistics.progress.report.buildinglist.ReportBuildingListActivity;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.FixedGridView;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/11/28 0028 上午 9:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReportHourListActivity extends BaseTitleActivity implements ReportHourListContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.rsrl)
    RefreshSwipeRefreshLayout rsrl;
    @BindView(R.id.gv)
    FixedGridView gv;
    private CommonAdapter reportAdapter;
    private CommonAdapter titleAdapter;
    private int reportType;
    @Inject
    ReportHourListPresenter reportHourListPresenter;
    private String date;

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        ReportItem reportItem = (ReportItem) adapterView.getItemAtPosition(position);
        ReportBuildingListActivity.goActivity(this, reportType, reportItem.getFinishedDate(),reportItem.getDisplayTime());
    }

    @Override
    public void initVariable() {
        reportType = getIntent().getIntExtra(Constants.Extra.ReportType, 1);
        date = getIntent().getStringExtra(Constants.Extra.Date);
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
        reportHourListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return date;
    }

    @Override
    protected void initView() {
        rsrl.stepRefresh(this);
    }

    @Override
    protected void initData() {
        gv.setAdapter(titleAdapter = new CommonAdapter<ReportTitle>(this, null, R.layout
                .item_title) {
            @Override
            public void convert(ViewHolder helper, ReportTitle item) {
                helper.setText(R.id.tv_title, item.getDisplayName());
            }
        });
        plv.setAdapter(reportAdapter = new CommonAdapter<ReportItem>(this, null, R.layout
                .item_report) {
            @Override
            public void convert(ViewHolder helper, ReportItem item) {
                helper.setText(R.id.tv_displayTime, item.getDisplayTime());
                helper.setText(R.id.tv_quantity, item.getQuantity());
                helper.setText(R.id.tv_time, item.getTime());
                helper.setText(R.id.tv_timeAvg, item.getTimeAvg());
                helper.setText(R.id.tv_speed, item.getSpeed());
            }
        });
    }

    @Override
    public void initNet() {
        reportHourListPresenter.getHourReport(SpSir.getInstance().getProjectId(), reportType, date, date);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    public static void goActivity(Context context, int reportType, String date) {
        Intent intent = new Intent(context, ReportHourListActivity.class);
        intent.putExtra(Constants.Extra.ReportType, reportType);
        intent.putExtra(Constants.Extra.Date, date);
        context.startActivity(intent);
    }


    @Override
    public void onGetHourReportSuccess(ReportHourResult reportResult) {
        List<ReportItem> reportDataList = reportResult.getReportDataList();
        List<ReportTitle> titles = reportResult.getTitles();
        setListView(reportDataList, reportAdapter);
        if (titles != null) {
            titleAdapter.setData(titles);
        }
        setContentTitle(reportResult.getName());
    }
}
