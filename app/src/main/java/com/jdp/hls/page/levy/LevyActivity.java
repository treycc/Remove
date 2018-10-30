package com.jdp.hls.page.levy;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.GridView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.CommonPositionAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.RefreshTaskEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.BusinessAction;
import com.jdp.hls.model.entiy.LevyInfo;
import com.jdp.hls.model.entiy.Task;
import com.jdp.hls.page.airphoto.list.AirphotoListActivity;
import com.jdp.hls.page.business.list.BusinessListActivity;
import com.jdp.hls.page.map.RosterActivity;
import com.jdp.hls.page.publicity.PublicityListActivity;
import com.jdp.hls.page.statistics.StatisticsActivity;
import com.jdp.hls.page.table.list.TableListActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description:征收系统
 * Create Time:2018/9/5 0005 上午 9:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LevyActivity extends BaseTitleActivity implements TaskContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.gv_task)
    GridView gvTask;
    @Inject
    TaskPresenter taskPresenter;
    @BindView(R.id.gv_businessAction)
    GridView gvBusinessAction;
    @BindView(R.id.gv_statisticsAction)
    GridView gvStatisticsAction;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    private CommonAdapter taskAdapter;
    private CommonPositionAdapter businessActionAdapter;
    private CommonPositionAdapter statisticsActionAdapter;
    private List<Task> tasks = new ArrayList<>();
    private List<BusinessAction> businessActions = new ArrayList<>();
    private List<BusinessAction> statisticsActions = new ArrayList<>();
    private int[] businessActionIcons = {R.mipmap.ic_nav_roster, R.mipmap.ic_publicity_manage, R.mipmap
            .ic_aerial_photograph, R.mipmap.ic_list_table};
    private int[] statisticsActionsIcons = {R.mipmap.ic_contract_count, R.mipmap.ic_surveying, R.mipmap
            .ic_mapping, R.mipmap.ic_agelimit_appraisal, R.mipmap.ic_nav_evaluate, R.mipmap.ic_protocol_generation, R
            .mipmap
            .ic_aerial_photograph_count, R.mipmap.ic_publicity_count};


    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_levy;
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
        srl.setOnRefreshListener(this);
        gvTask.setAdapter(taskAdapter = new CommonAdapter<Task>(this, tasks, R.layout.item_task) {
            @Override
            public void convert(ViewHolder helper, Task item) {
                helper.setText(R.id.stv_taskCount, item.getCount());
                helper.setText(R.id.tv_taskName, item.getTaskTypeName());
            }
        });
        gvBusinessAction.setAdapter(businessActionAdapter = new CommonPositionAdapter<BusinessAction>(this,
                businessActions, R.layout.item_business_action) {
            @Override
            public void convert(ViewHolder helper, BusinessAction item, int position) {
                helper.setBackgroundResource(R.id.iv_businessAction, businessActionIcons[position]);
                helper.setText(R.id.tv_taskName, item.getActionName());
            }
        });
        gvStatisticsAction.setAdapter(statisticsActionAdapter = new CommonPositionAdapter<BusinessAction>(this,
                statisticsActions, R.layout.item_business_action) {
            @Override
            public void convert(ViewHolder helper, BusinessAction item, int position) {
                helper.setBackgroundResource(R.id.iv_businessAction, statisticsActionsIcons[position]);
                helper.setText(R.id.tv_taskName, item.getActionName());
            }
        });
        gvTask.setOnItemClickListener((parent, view, position, id) -> {
            Task task = (Task) parent.getItemAtPosition(position);
            BusinessListActivity.GoActivity(LevyActivity.this, task.getTaskType(), task.getTaskTypeName());
        });
        gvBusinessAction.setOnItemClickListener((parent, view, position, id) -> {
            BusinessAction businessAction = (BusinessAction) parent.getItemAtPosition(position);
            switch (businessAction.getActionId()) {
                case Status.BusinessActionType.ROSTER:
                    /*花名册*/
                    GoUtil.goActivity(this, RosterActivity.class);
                    break;
                case Status.BusinessActionType.PUBLICITY:
                    /*公示管理*/
                    GoUtil.goActivity(this, PublicityListActivity.class);
                    break;
                case Status.BusinessActionType.AIRPHOTO:
                    /*航拍复查*/
                    GoUtil.goActivity(this, AirphotoListActivity.class);
                    break;
                case Status.BusinessActionType.TABLE:
                    /*一览表*/
                    GoUtil.goActivity(this, TableListActivity.class);
                    break;
                default:
                    break;
            }
        });
        gvStatisticsAction.setOnItemClickListener((parent, view, position, id) -> {
            BusinessAction businessAction = (BusinessAction) parent.getItemAtPosition(position);
            StatisticsActivity.goActivity(LevyActivity.this, String.valueOf(businessAction.getStatisId()),
                    businessAction.getActionName());
        });
    }

    @Override
    protected void initNet() {
        taskPresenter.getTask(SpSir.getInstance().getProjectId(), -1);
    }

    @Override
    public void onGetTaskSuccess(LevyInfo levyInfo) {
        List<Task> taskList = levyInfo.getLstTaskTypeCount();
        if (taskList != null && taskList.size() > 0) {
            if (taskList.size() == 2) {
                gvTask.setNumColumns(4);
            }
            taskAdapter.setData(taskList);
        }
        List<BusinessAction> businessActionList = levyInfo.getLstAppAction();
        if (businessActionList != null && businessActionList.size() > 0) {
            businessActionAdapter.setData(businessActionList);
        }
        List<BusinessAction> statisticsActionList = levyInfo.getLstStatisAction();
        if (statisticsActionList != null && statisticsActionList.size() > 0) {
            statisticsActionAdapter.setData(statisticsActionList);
        }
    }

    private List getAvailableActionList(List<BusinessAction> businessActionList) {
        List<BusinessAction> actionList = new ArrayList<>();
        for (BusinessAction businessAction : businessActionList) {
            if (businessAction.isAvailable()) {
                actionList.add(businessAction);
            }
        }
        return actionList;
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshTaskCount(RefreshTaskEvent event) {
        initNet();
    }

    @Override
    public void onRefresh() {
        taskPresenter.refreshTask(SpSir.getInstance().getProjectId(), -1);
    }

    @Override
    public void showLoading() {
        srl.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srl.setRefreshing(false);
    }
}
