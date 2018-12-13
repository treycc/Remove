package com.jdp.hls.page.supervise.statistics.progress.report.buildinglist;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ReportBuilding;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/12/7 0007 下午 4:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReportBuildingListActivity extends BaseTitleActivity implements ReportBuildingListContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.rsrl)
    RefreshSwipeRefreshLayout rsrl;
    private CommonAdapter adapter;
    @Inject
    ReportBuildingListPresenter reportBuildingListPresenter;
    private int reportType;
    private String startDate;
    private String endDate;

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
//        ReportBuilding reportBuilding = (ReportBuilding) adapterView.getItemAtPosition(position);
//        if (reportBuilding.getBuildingType() == Status.BuildingType.PERSONAL) {
//            BasicPersonalActivity.goActivity(this, reportBuilding.getBuildingId());
//        } else {
//            BasicCompanyActivity.goActivity(this, reportBuilding.getBuildingId());
//        }
    }
    @Override
    public void initVariable() {
        reportType = getIntent().getIntExtra(Constants.Extra.ReportType, 1);
        startDate = getIntent().getStringExtra(Constants.Extra.StartDate);
        endDate = getIntent().getStringExtra(Constants.Extra.EndDate);
    }

    @Override
    protected int getContentView() {
        return R.layout.common_lv_rsl;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        reportBuildingListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "征收人列表";
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void initData() {
        plv.setAdapter(adapter = new CommonAdapter<ReportBuilding>(this, null, R.layout.item_report_building) {
            @Override
            public void convert(ViewHolder helper, ReportBuilding item) {
                helper.setText(R.id.tv_table_sysCode, item.getSysCode());
                helper.setText(R.id.tv_table_name, item.getRealName());
                helper.setText(R.id.tv_table_mobile, item.getMobilePhone());
                helper.setText(R.id.tv_table_idcard, item.getIdcard());
                helper.setText(R.id.tv_table_address, item.getAddress());
                helper.setText(R.id.tv_table_statusDesc, item.getStatusDesc());
            }
        });
        rsrl.stepRefresh(this);
    }

    @Override
    public void initNet() {
        reportBuildingListPresenter.getReportBuildingList(SpSir.getInstance().getProjectId(), reportType, startDate, endDate);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    public void onGetReportBuildingListSuccess(List<ReportBuilding> reportBuildingList) {
        setListView(reportBuildingList, adapter);
    }

    public static void goActivity(Context context, int reportType, String startDate, String endDate) {
        Intent intent = new Intent(context, ReportBuildingListActivity.class);
        intent.putExtra(Constants.Extra.ReportType, reportType);
        intent.putExtra(Constants.Extra.StartDate, startDate);
        intent.putExtra(Constants.Extra.EndDate, endDate);
        context.startActivity(intent);
    }
}
