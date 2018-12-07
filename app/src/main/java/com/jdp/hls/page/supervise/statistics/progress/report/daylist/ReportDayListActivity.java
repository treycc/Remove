package com.jdp.hls.page.supervise.statistics.progress.report.daylist;

import android.content.Context;
import android.content.Intent;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ReportItem;
import com.jdp.hls.model.entiy.ReportResult;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/11/28 0028 上午 9:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReportDayListActivity extends BaseTitleActivity implements ReportDayListContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.rsrl)
    RefreshSwipeRefreshLayout rsrl;
    private CommonAdapter reportAdapter;
    private int reportType;

    private String startDate = "";
    private String endDate = "";
    @Inject
    ReportDayListPresenter reportDayListPresenter;

    @Override
    public void initVariable() {
        reportType = getIntent().getIntExtra(Constants.Extra.ReportType, 1);

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
        reportDayListPresenter.attachView(this);
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
        plv.setAdapter(reportAdapter = new CommonAdapter<ReportItem>(this, null, R.layout
                .item_report) {
            @Override
            public void convert(ViewHolder helper, ReportItem item) {
                helper.setText(R.id.tv_finishedTime, item.getFinishedDate());
                helper.setText(R.id.tv_quantity, item.getQuantity());
                helper.setText(R.id.tv_time, item.getTime());
                helper.setText(R.id.tv_timeAvg, item.getTimeAvg());
                helper.setText(R.id.tv_speed, item.getSpeed());
            }
        });
    }

    @Override
    public void initNet() {
        reportDayListPresenter.getDayReport(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("ProjectId", SpSir.getInstance().getProjectId())
                .addFormDataPart("StartDate", startDate)
                .addFormDataPart("EndDate", endDate)
                .addFormDataPart("ReportType", String.valueOf(reportType))
                .addFormDataPart("PageSize", String.valueOf(Constants.PAGE_SIZE_100))
                .addFormDataPart("PageIndex", String.valueOf(Constants.PAGE_FIRST))
                .build());
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    public static void goActivity(Context context, int reportType) {
        Intent intent = new Intent(context, ReportDayListActivity.class);
        intent.putExtra(Constants.Extra.ReportType, reportType);
        context.startActivity(intent);
    }

    @Override
    public void onGetDayReportSuccess(ReportResult reportResult) {
        ReportResult.ReportListInfo reportDataList = reportResult.getReportDataList();
        if (reportDataList != null) {
            setListView(reportDataList.getResultList(), reportAdapter);
        }
        setContentTitle(reportResult.getName());
    }
}
