package com.jdp.hls.page.airphoto.list;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.AirPhotoListAdapter;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.callback.EmptyCallback;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddAirPhotoEvent;
import com.jdp.hls.event.RemoveAirPhotoEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AirPhotoItem;
import com.jdp.hls.model.entiy.ModifyAirPhotoEvent;
import com.jdp.hls.page.airphoto.detail.AirPhotoDetailActivity;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshableSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
public class AirPhotoListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        AirPhotoListContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshableSwipeRefreshLayout srl;
    private List<AirPhotoItem> airPhotoItems = new ArrayList<>();
    private AirPhotoListAdapter adapter;
    private String taskType;
    @Inject
    AirPhotoListPresenter airPhotoListPresenter;

    public static AirPhotoListFragment newInstance(String taskType) {
        AirPhotoListFragment fragment = new AirPhotoListFragment();
        Bundle args = new Bundle();
        args.putString("taskType", taskType);
        fragment.setArguments(args);
        return fragment;
    }

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        AirPhotoItem airPhotoItem = (AirPhotoItem) adapterView.getItemAtPosition(position);
        AirPhotoDetailActivity.goActivity(getActivity(), String.valueOf(airPhotoItem.getAirCheckId()), airPhotoItem
                .getCheckType());

    }

    @Override
    protected void initVariable() {
        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            taskType = getArguments().getString("taskType");
        }
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
        airPhotoListPresenter.attachView(this);
        adapter = new AirPhotoListAdapter(getActivity(), airPhotoItems, R.layout.item_airphoto);
        plv.setAdapter(adapter);
    }

    @Override
    public void onGetAirPhotoListSuccess(List<AirPhotoItem> airPhotoItems) {
        this.airPhotoItems = airPhotoItems;
        checkListsize(airPhotoItems);
    }

    private void checkListsize(List<AirPhotoItem> airPhotoItems) {
        if (airPhotoItems != null && airPhotoItems.size() > 0) {
            showSuccessCallback();
            adapter.setData(airPhotoItems);
        } else {
            mBaseLoadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    protected void initData() {
        srl.setOnRefreshListener(this);
    }

    @Override
    public void initNet() {
        LogUtil.e(TAG, "initNet");
        airPhotoListPresenter.getAirPhotoList("-1", taskType);
    }

    @Override
    protected int getContentId() {
        return R.layout.common_lv_sl;
    }

    @Override
    public void onRefresh() {
        srl.setRefreshing(false);
        initNet();
    }

    public void search(String keyword) {
        adapter.search(keyword);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addAirPhoto(AddAirPhotoEvent event) {
        if (taskType.equals(event.getAirPhotoType())) {
            showSuccessCallback();
            adapter.addFirst(event.getAirPhotoItem());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void removeAirPhoto(RemoveAirPhotoEvent event) {
        if (taskType.equals(event.getAirPhotoType())) {
            adapter.remove(event.getAirCheckId());
            checkListsize(adapter.getData());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyAirPhoto(ModifyAirPhotoEvent event) {
        if (taskType.equals(Constants.AirPhotoType.TODO)) {
            adapter.modify(event.getAirPhotoItem());
        }
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
