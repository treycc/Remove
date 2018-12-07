package com.jdp.hls.page.supervise.statistics.progress.report.buildinglist;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ReportBuilding;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

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

    @Override
    public void initVariable() {

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
                helper.setText(R.id.tv_table_number, item.getSysCode());
                helper.setText(R.id.tv_table_cusCode, item.getCusCode());
                helper.setText(R.id.tv_table_name, item.getRealName());
                helper.setText(R.id.tv_table_mobile, item.getMobilePhone());
                helper.setText(R.id.tv_table_address, item.getAddress());
                helper.setText(R.id.tv_table_node, item.getStatusDesc());
            }
        });
        rsrl.stepRefresh(this);
    }

    @Override
    public void initNet() {
        reportBuildingListPresenter.getReportBuildingList();
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    public void onGetReportBuildingListSuccess(List<ReportBuilding> reportBuildingList) {
        setListView(reportBuildingList, adapter);
    }
}
