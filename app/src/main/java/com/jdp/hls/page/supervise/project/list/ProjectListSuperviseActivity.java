package com.jdp.hls.page.supervise.project.list;

import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ProjectSupervise;
import com.jdp.hls.page.supervise.project.detail.ProjectDetailSuperviseActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/11/27 0027 下午 1:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectListSuperviseActivity extends BaseTitleActivity {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.rsrl)
    RefreshSwipeRefreshLayout rsrl;
    private CommonAdapter<ProjectSupervise> adapter;

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        GoUtil.goActivity(this, ProjectDetailSuperviseActivity.class);
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
    }

    @Override
    protected String getContentTitle() {
        return "项目列表";
    }

    @Override
    protected void initView() {

    }

    private List<ProjectSupervise> projectSuperviseList = new ArrayList<>();

    @Override
    protected void initData() {
        plv.setAdapter(adapter = new CommonAdapter<ProjectSupervise>(this, projectSuperviseList, R.layout
                .item_supervise_project) {
            @Override
            public void convert(ViewHolder helper, ProjectSupervise item) {
            }
        });

    }

    @Override
    public void initNet() {
        projectSuperviseList.add(new ProjectSupervise());
        projectSuperviseList.add(new ProjectSupervise());
        projectSuperviseList.add(new ProjectSupervise());
        projectSuperviseList.add(new ProjectSupervise());
        adapter.setData(projectSuperviseList);

    }
}
