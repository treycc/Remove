package com.jdp.hls.page.levy;

import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.activity.AirphotoListActivity;
import com.jdp.hls.activity.StatisticsActivity;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Task;
import com.jdp.hls.page.business.list.BusinessListActivity;
import com.jdp.hls.page.map.RosterActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.SpSir;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/9/5 0005 上午 9:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LevyActivity extends BaseTitleActivity implements TaskContract.View {
    @BindView(R.id.ll_operate_roster)
    LinearLayout llOperateRoster;
    @BindView(R.id.ll_operate_publicity)
    LinearLayout llOperatePublicity;
    @BindView(R.id.ll_operate_plane)
    LinearLayout llOperatePlane;
    @BindView(R.id.ll_operate_detail)
    LinearLayout llOperateDetail;
    @BindView(R.id.ll_statistics_sign)
    LinearLayout llStatisticsSign;
    @BindView(R.id.ll_statistics_measure)
    LinearLayout llStatisticsMeasure;
    @BindView(R.id.ll_statistics_measureImg)
    LinearLayout llStatisticsMeasureImg;
    @BindView(R.id.ll_statistics_age)
    LinearLayout llStatisticsAge;
    @BindView(R.id.ll_statistics_evaluate)
    LinearLayout llStatisticsEvaluate;
    @BindView(R.id.ll_statistics_protocol)
    LinearLayout llStatisticsProtocol;
    @BindView(R.id.ll_statistics_plane)
    LinearLayout llStatisticsPlane;
    @BindView(R.id.ll_statistics_publicity)
    LinearLayout llStatisticsPublicity;
    @BindView(R.id.gv_task)
    GridView gvTask;
    @Inject
    TaskPresenter taskPresenter;


    private CommonAdapter adapter;
    private List<Task> tasks = new ArrayList<>();

    @OnClick({R.id.ll_operate_roster, R.id.ll_operate_publicity, R.id.ll_operate_plane, R.id
            .ll_operate_detail, R.id.ll_statistics_sign, R.id.ll_statistics_measure, R.id.ll_statistics_measureImg, R.id
            .ll_statistics_age, R.id.ll_statistics_evaluate, R.id.ll_statistics_protocol, R.id.ll_statistics_plane, R.id
            .ll_statistics_publicity})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_operate_roster:
                /*花名册*/
                GoUtil.goActivity(this, RosterActivity.class);
                break;
            case R.id.ll_operate_publicity:
                /*公示管理*/
                break;
            case R.id.ll_operate_plane:
                /*航拍复查*/
                GoUtil.goActivity(this, AirphotoListActivity.class);
                break;
            case R.id.ll_operate_detail:
                /*一览表*/
                break;
            case R.id.ll_statistics_sign:
                /*签约统计*/
                GoUtil.goActivity(this, StatisticsActivity.class);
                break;
            case R.id.ll_statistics_measure:
                /*入户丈量*/
                break;
            case R.id.ll_statistics_measureImg:
                /*测绘出图*/
                break;
            case R.id.ll_statistics_age:
                /*年限鉴定*/
                break;
            case R.id.ll_statistics_evaluate:
                /*入户评估*/
                break;
            case R.id.ll_statistics_protocol:
                /*协议生成*/
                break;
            case R.id.ll_statistics_plane:
                /*航拍统计*/
                break;
            case R.id.ll_statistics_publicity:
                /*公示统计*/
                break;
        }
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.levy_activity;
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
        return "征收业务系统";
    }

    @Override
    protected void initView() {
        taskPresenter.attachView(this);
    }


    @Override
    protected void initData() {
        gvTask.setAdapter(adapter = new CommonAdapter<Task>(this, tasks, R.layout.item_task) {
            @Override
            public void convert(ViewHolder helper, Task item) {
                helper.setText(R.id.stv_taskCount, item.getCount());
                helper.setText(R.id.tv_taskName, item.getTaskTypeName());
            }
        });
        gvTask.setOnItemClickListener((parent, view, position, id) -> {
            Task task = (Task) parent.getItemAtPosition(position);
            BusinessListActivity.GoActivity(LevyActivity.this, task.getTaskType(), task.getTaskTypeName());
        });
    }

    @Override
    protected void initNet() {
        taskPresenter.getTask(SpSir.getInstance().getProjectId(), -1);
    }

    @Override
    public void onGetTaskSuccess(List<Task> tasks) {
        if (tasks != null) {
            adapter.setData(tasks);
        }
    }

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }
}
