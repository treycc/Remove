package com.jdp.hls.page.business.list;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.BusinessAdapter;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.ModifyBusinessEvent;
import com.jdp.hls.event.ModifyMainContactsEvent;
import com.jdp.hls.event.RefreshReminderEvent;
import com.jdp.hls.i.OnBusinessItemSelectedListener;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Business;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.model.entiy.TaskInfo;
import com.jdp.hls.page.business.basic.company.BasicCompanyActivity;
import com.jdp.hls.page.business.basic.personla.BasicPersonalActivity;
import com.jdp.hls.page.rosterlist.GetRostersByTypeContract;
import com.jdp.hls.util.CollectionUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshableSwipeRefreshLayout;

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
 * Description:业务列表
 * Create Time:2018/7/27 0027 下午 2:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BusinessListFragment extends BaseFragment implements GetRostersByTypeContract.View, SwipeRefreshLayout
        .OnRefreshListener, BussinessContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshableSwipeRefreshLayout srl;
    private List<Business> business = new ArrayList<>();
    private BusinessAdapter adapter;
    private int buildingType;
    @Inject
    BusinessPresenter businessPresenter;
    private int taskType;
    private boolean checkable;
    private OnBusinessItemSelectedListener onBusinessSelectedListener;

    public static BusinessListFragment newInstance(List<Business> business, int taskType, int buildingType, boolean
            checkable) {
        BusinessListFragment fragment = new BusinessListFragment();
        Bundle args = new Bundle();
        args.putSerializable("business", (Serializable) business);
        args.putInt("taskType", taskType);
        args.putInt("buildingType", buildingType);
        args.putBoolean("getCheckable", checkable);
        fragment.setArguments(args);
        return fragment;
    }

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Business business = (Business) adapterView.getItemAtPosition(position);
        String buildingId = business.getBuildingId();
        if (business.getBuildingType() == Status.BuildingId.PERSONAL) {
            BasicPersonalActivity.goActivity(getActivity(), buildingId);
        } else {
            BasicCompanyActivity.goActivity(getActivity(), buildingId);
        }
    }

    @Override
    protected void initVariable() {
        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            business = (List<Business>) getArguments().getSerializable("business");
            buildingType = getArguments().getInt("buildingType", 0);
            taskType = getArguments().getInt("taskType", 0);
            checkable = getArguments().getBoolean("getCheckable");
            LogUtil.e(TAG, "business:" + business.size());
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
        businessPresenter.attachView(this);
        adapter = new BusinessAdapter(getActivity(), business, checkable);
        plv.setAdapter(adapter);
        checkListSize(business);
    }

    @Override
    public void onGetBusinessSuccess(TaskInfo taskInfo) {

    }

    @Override
    protected void initData() {
        srl.setOnRefreshListener(this);
        adapter.setOnBusinessSelectedListener(onBusinessSelectedListener);
    }

    public void setOnBusinessSelectedListener(OnBusinessItemSelectedListener onBusinessSelectedListener) {
        this.onBusinessSelectedListener = onBusinessSelectedListener;
    }

    @Override
    public void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.common_lv_sl;
    }

    @Override
    public void onGetRosterListByTypeSuccess(List<Roster> rosters) {
        adapter.setData(business);
    }

    public void refreshData(List<Business> businesses) {
        checkListSize(businesses);
    }

    private void checkListSize(List<Business> businesses) {
        if (businesses != null && businesses.size() > 0) {
            showSuccessCallback();
            adapter.setData(businesses);
        } else {
            showEmptyCallback();
        }
    }

    public void checkAll(boolean checked) {
        adapter.checkAll(checked);
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
        srl.setRefreshing(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyBusinessEvent(ModifyBusinessEvent event) {
        if (event.getBuildingType() == buildingType) {
            adapter.modify(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshReminder(RefreshReminderEvent event) {
        List<String> buildingIdList = CollectionUtil.getBuildingIdList(event.getBuildingIds());
        if (buildingIdList.size() > 0) {
            adapter.refreshReminder(buildingIdList);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyMainContacts(ModifyMainContactsEvent event) {
        int buildingType = event.getRoster().isEnterprise() ? 1 : 0;
        if (buildingType == this.buildingType) {
            adapter.modifyMainContacts(event.getRoster());
        }
    }
    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
