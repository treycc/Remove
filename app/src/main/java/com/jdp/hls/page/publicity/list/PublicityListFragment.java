package com.jdp.hls.page.publicity.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.PublicityListAdapter;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddPublicityEvent;
import com.jdp.hls.event.ModifyPublicityEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.PublicityItem;
import com.jdp.hls.page.publicity.detail.PublicityDetailActivity;
import com.jdp.hls.util.SpSir;
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
 * Description:公示列表
 * Create Time:2018/7/27 0027 下午 2:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PublicityListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        PublicityListContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshableSwipeRefreshLayout srl;
    private List<PublicityItem> publicityItems = new ArrayList<>();
    private PublicityListAdapter adapter;
    private int publicityType;
    @Inject
    PublicityListPresenter publicityListPresenter;

    public static PublicityListFragment newInstance(int publicityType) {
        PublicityListFragment fragment = new PublicityListFragment();
        Bundle args = new Bundle();
        args.putInt("publicityType", publicityType);
        fragment.setArguments(args);
        return fragment;
    }

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        PublicityItem publicityItem = (PublicityItem) adapterView.getItemAtPosition(position);
        PublicityDetailActivity.goActivity(this, publicityItem.getPubId(), publicityItem.getPubStatus(), position);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == Constants.RequestCode.PUBLICITY_DETAIL) {
                int position = data.getIntExtra(Constants.Extra.POSITION, 0);
                String batchName = data.getStringExtra(Constants.Extra.BATCH_NAME);
                String publicityDes = data.getStringExtra(Constants.Extra.PUBLICITY_DES);
                publicityItems.get(position).setBatchName(batchName);
                publicityItems.get(position).setDescriptiton(publicityDes);
                adapter.setData(publicityItems);
            }
        }
    }

    @Override
    protected void initVariable() {
        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            publicityType = getArguments().getInt("publicityType", 0);
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
        publicityListPresenter.attachView(this);
        plv.setAdapter(adapter = new PublicityListAdapter(getActivity(), publicityItems, R.layout
                .item_publicity));
    }

    @Override
    public void onGetPublicityListSuccess(List<PublicityItem> publicityItems) {
        this.publicityItems = publicityItems;
        checkListSize(publicityItems);

    }

    private void checkListSize(List<PublicityItem> publicityItems) {
        if (publicityItems != null && publicityItems.size() > 0) {
            showSuccessCallback();
            adapter.setData(publicityItems);
        } else {
            showEmptyCallback();
        }
    }

    @Override
    protected void initData() {
        srl.setOnRefreshListener(this);
    }

    @Override
    public void initNet() {
        srl.setRefreshing(false);
        publicityListPresenter.getPublicityList(SpSir.getInstance().getProjectId(), publicityType);
    }

    @Override
    protected int getContentId() {
        return R.layout.common_lv_sl;
    }


    @Override
    public void onRefresh() {
        initNet();
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshPublicityEvent(AddPublicityEvent event) {
        PublicityItem publicityItem = event.getPublicityItem();
        if (publicityItem != null && publicityType == publicityItem.getPubType()) {
            showSuccessCallback();
            adapter.addFirst(publicityItem);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyPublicityEvent(ModifyPublicityEvent event) {
        PublicityItem publicityItem = event.getPublicityItem();
        if (publicityItem != null && publicityType == publicityItem.getPubType()) {
            adapter.modify(publicityItem);
        }
    }
}
