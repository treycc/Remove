package com.jdp.hls.page.supervise.project.list;

import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.ProjectSuperviseAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ProjectSupervise;
import com.jdp.hls.model.entiy.ProjectSuperviseInfo;
import com.jdp.hls.page.supervise.project.detail.ProjectDetailSuperviseActivity;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/11/27 0027 下午 1:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectListSuperviseActivity extends BaseTitleActivity implements ProjectListSuperviseContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.rsrl)
    RefreshSwipeRefreshLayout rsrl;
    private ProjectSuperviseAdapter adapter;
    @Inject
    ProjectListSupervisePresenter projectListSupervisePresenter;

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        ProjectSupervise projectSupervise = (ProjectSupervise) adapterView.getItemAtPosition(position);
        ProjectDetailSuperviseActivity.goActivity(this,projectSupervise.getProjectId());
    }

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
        projectListSupervisePresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "项目列表";
    }

    @Override
    protected void initView() {

    }
    @Override
    protected void initData() {
        plv.setAdapter(adapter = new ProjectSuperviseAdapter(this, null));
        rsrl.stepRefresh(this);
    }

    @Override
    public void initNet() {
        projectListSupervisePresenter.getProjectSuperviseList(Constants.PAGE_SIZE_100, Constants.PAGE_FIRST, 0);
    }

    @Override
    public void onGetProjectSuperviseListSuccess(ProjectSuperviseInfo projectSuperviseInfo) {
        //TODO 分页
        setListView(projectSuperviseInfo.getResultList(), adapter);
    }
}
