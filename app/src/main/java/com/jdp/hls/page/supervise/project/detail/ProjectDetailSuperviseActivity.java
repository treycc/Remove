package com.jdp.hls.page.supervise.project.detail;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.CompanySupervise;
import com.jdp.hls.model.entiy.ProjectSuperviseDetail;
import com.jdp.hls.page.supervise.statistics.progress.progress.StatisticsProgressInfoActivity;
import com.jdp.hls.page.supervise.statistics.total.StatisticsTotalActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.view.FixedListView;
import com.jdp.hls.view.StringTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/11/27 0027 下午 2:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectDetailSuperviseActivity extends BaseTitleActivity implements ProjectDetailSuperviseContract.View {
    @BindView(R.id.rl_statistics_progress)
    RelativeLayout rlStatisticsProgress;
    @BindView(R.id.rl_statistics_total)
    RelativeLayout rlStatisticsTotal;
    @BindView(R.id.rl_statistics_table)
    RelativeLayout rlStatisticsTable;
    @BindView(R.id.flv)
    FixedListView flv;
    @BindView(R.id.tv_projectName)
    StringTextView tvProjectName;
    @BindView(R.id.tv_address)
    StringTextView tvAddress;
    private List<CompanySupervise> companySuperviseList = new ArrayList<>();
    private CommonAdapter<CompanySupervise> adapter;
    @Inject
    ProjectDetailSupervisePresenter projectDetailSupervisePresenter;
    private String projectId;

    @OnClick({R.id.rl_statistics_progress, R.id.rl_statistics_total, R.id.rl_statistics_table})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_statistics_progress:
                GoUtil.goActivity(this, StatisticsProgressInfoActivity.class);
                break;
            case R.id.rl_statistics_total:
                GoUtil.goActivity(this, StatisticsTotalActivity.class);
                break;
            case R.id.rl_statistics_table:
//                GoUtil.goActivity(this, StatisticsTableActivity.class);
                break;
        }
    }

    @Override
    public void initVariable() {
        projectId = getIntent().getStringExtra(Constants.Extra.PROJECTID);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_project_detail_supervise;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        projectDetailSupervisePresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "项目详情";
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void initData() {
        flv.setAdapter(adapter = new CommonAdapter<CompanySupervise>(this, companySuperviseList, R.layout
                .item_supervise_company) {
            @Override
            public void convert(ViewHolder helper, CompanySupervise item) {
                helper.setText(R.id.tv_companyTypeName, item.getCompanyTypeName());
                helper.setText(R.id.tv_companyName, item.getCompanyName());
            }
        });
    }

    @Override
    public void initNet() {
        projectDetailSupervisePresenter.getProjectSuperviseDetail(projectId);
    }

    @Override
    public void onGetProjectSuperviseDetailSuccess(ProjectSuperviseDetail projectSuperviseDetail) {
        tvProjectName.setString(projectSuperviseDetail.getProjectName());
        tvAddress.setString(projectSuperviseDetail.getAddress());
        adapter.setData(projectSuperviseDetail.getLstProjectCompany());
    }

    public static void goActivity(Context context, String projectId) {
        Intent intent = new Intent(context, ProjectDetailSuperviseActivity.class);
        intent.putExtra(Constants.Extra.PROJECTID, projectId);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
