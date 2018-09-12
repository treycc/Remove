package com.jdp.hls.page.businesslist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.event.AddRostersEvent;
import com.jdp.hls.event.ModifyRostersEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Business;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.page.rosterdetail.RosterDetailActivity;
import com.jdp.hls.page.rosterlist.GetRostersByTypeContract;
import com.jdp.hls.page.rosterlist.GetRostersByTypePresenter;
import com.jdp.hls.util.LogUtil;
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
    RefreshSwipeRefreshLayout srl;
    private List<Business> business = new ArrayList<>();
    private BusinessListAdapter adapter;
    private int buildingType;
    @Inject
    BusinessPresenter businessPresenter;
    private int taskType;

    public static BusinessListFragment newInstance(List<Business> business, int taskType, int buildingType) {
        BusinessListFragment fragment = new BusinessListFragment();
        Bundle args = new Bundle();
        args.putSerializable("business", (Serializable) business);
        args.putInt("taskType", taskType);
        args.putInt("buildingType", buildingType);
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
            business = (List<Business>) getArguments().getSerializable("business");
            buildingType = getArguments().getInt("buildingType", 0);
            taskType = getArguments().getInt("taskType", 0);
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
        adapter = new BusinessListAdapter(getActivity(), business, R.layout.item_business);
        plv.setAdapter(adapter);
    }

    @Override
    public void onGetBusinessSuccess(List<Business> businesses) {

    }

    class BusinessListAdapter extends CommonAdapter<Business> {
        public BusinessListAdapter(Context context, List<Business> datas, int itemLayoutId) {
            super(context, datas, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder helper, Business item) {
            helper.setText(R.id.tv_business_address, item.getHouseAddress());
            helper.setText(R.id.tv_business_number, item.getSysCode());
            helper.setText(R.id.tv_business_name, item.getRealName());
            helper.setText(R.id.tv_business_mobile, item.getMobilePhone());
//            helper.setBackgroundResource(R.id.iv_roster_hasLocation, (item.getLatitude() == 0 || item.getLongitude()
//                    == 0) ? R.mipmap
//                    .ic_has_location_nor : R.mipmap
//                    .ic_has_location_sel);
//            helper.setBackgroundResource(R.id.iv_roster_isMeasure, item.isMeasured() ? R.mipmap
//                    .ic_measure_action : R.mipmap
//                    .ic_measure_nor);
//            helper.setBackgroundResource(R.id.iv_roster_isEvaluated, item.isEvaluated() ? R.mipmap
//                    .ic_evaluate_action : R.mipmap
//                    .ic_evaluate_nor);
        }

        public void modifyData(Roster roster) {
//            for (Roster mData : this.mDatas) {
//                if (mData.getHouseId().equals(roster.getHouseId())) {
//                    mData.setLongitude(roster.getLongitude());
//                    mData.setLatitude(roster.getLatitude());
//                    mData.setEnterprise(roster.isEnterprise());
//                    mData.setEvaluated(roster.isEvaluated());
//                    mData.setMeasured(roster.isMeasured());
//                    mData.setRealName(roster.getRealName());
//                    mData.setMobilePhone(roster.getMobilePhone());
//                    mData.setHouseAddress(roster.getHouseAddress());
//                }
//            }
            notifyDataSetChanged();
        }
    }

    @Override
    protected void initData() {
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
//        adapter.setData(business);
    }

    public void refreshData(List<Roster> rosters) {
//        adapter.setData(business);
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
        businessPresenter.getBusinessList(SpSir.getInstance().getProjectId(),buildingType,taskType);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshRosters(AddRostersEvent event) {
        int enterprise = event.getRoster().isEnterprise() ? 1 : 0;
        if (enterprise == buildingType) {
//            adapter.addData(event.getRoster());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyRosters(ModifyRostersEvent event) {
        int enterprise = event.getRoster().isEnterprise() ? 1 : 0;
        LogUtil.e(TAG, "enterprise:" + enterprise + " buildingType:" + buildingType);
        if (enterprise == buildingType) {
//            adapter.modifyData(event.getRoster());
        }
    }
}
