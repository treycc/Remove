package com.jdp.hls.page.rosterlist;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.event.RefreshRostersEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.page.rosterdetail.RosterDetailActivity;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 下午 2:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterListFragment extends BaseFragment implements GetRostersByTypeContract.View, SwipeRefreshLayout
        .OnRefreshListener {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    private List<Roster> rosters = new ArrayList<>();
    private CommonAdapter adapter;
    private int isEnterprise;
    @Inject
    GetRostersByTypePresenter getRostersByTypePresenter;

    public static RosterListFragment newInstance(List<Roster> rosters, int isEnterprise) {
        RosterListFragment fragment = new RosterListFragment();
        Bundle args = new Bundle();
        args.putSerializable("rosters", (Serializable) rosters);
        args.putInt("isEnterprise", isEnterprise);
        fragment.setArguments(args);
        return fragment;
    }

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Roster roster = (Roster) adapterView.getItemAtPosition(position);
        RosterDetailActivity.goActivity(getActivity(), roster);
    }

    @Override
    protected void initVariable() {
        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            rosters = (List<Roster>) getArguments().getSerializable("rosters");
            isEnterprise = getArguments().getInt("isEnterprise");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        getRostersByTypePresenter.attachView(this);
        plv.setAdapter(adapter = new CommonAdapter<Roster>(getActivity(), rosters, R.layout.item_roster) {
                    @Override
                    public void convert(ViewHolder helper, Roster item) {
                        helper.setText(R.id.tv_roster_address, item.getHouseAddress());
                        helper.setText(R.id.tv_roster_name, item.getRealName());
                        helper.setText(R.id.tv_roster_phone, item.getMobilePhone());
                        helper.setBackgroundResource(R.id.iv_roster_isMeasure, item.isMeasured() ? R.mipmap
                                .ic_measure_action : R.mipmap
                                .ic_measure_nor);
                        helper.setBackgroundResource(R.id.iv_roster_isEvaluated, item.isEvaluated() ? R.mipmap
                                .ic_evaluate_action : R.mipmap
                                .ic_evaluate_nor);
                    }
                }
        );
    }

    @Override
    protected void initDate() {
        srl.setOnRefreshListener(this);
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.common_lv;
    }

    @Override
    public void onGetRosterListByTypeSuccess(List<Roster> rosters) {
        adapter.setData(rosters);
    }

    public void refreshData(List<Roster> rosters) {
        adapter.setData(rosters);
    }

    @Override
    public void showLoading() {
        srl.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srl.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        getRostersByTypePresenter.getRosterListByType(SpSir.getInstance().getProjectId(), SpSir.getInstance()
                .getEmployeeId(), isEnterprise);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshRosters(RefreshRostersEvent event) {
        if (event.getIsEnterprisep() == isEnterprise) {
            getRostersByTypePresenter.getRosterListByType(SpSir.getInstance().getProjectId(), SpSir.getInstance()
                    .getEmployeeId(), isEnterprise);
        }
    }
}
