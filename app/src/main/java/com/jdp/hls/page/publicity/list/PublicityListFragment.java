package com.jdp.hls.page.publicity.list;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.PublicityItem;
import com.jdp.hls.page.publicity.detail.PublicityDetailActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

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
    RefreshSwipeRefreshLayout srl;
    private List<PublicityItem> publicityItems = new ArrayList<>();
    private CommonAdapter adapter;
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
        GoUtil.goActivity(getActivity(), PublicityDetailActivity.class);
    }

    @Override
    protected void initVariable() {
//        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            publicityType = getArguments().getInt("publicityType", 0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
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
        plv.setAdapter(adapter = new CommonAdapter<PublicityItem>(getActivity(), publicityItems, R.layout
                .item_publicity) {
            @Override
            public void convert(ViewHolder helper, PublicityItem item) {
                helper.setText(R.id.tv_publicity_number, item.getBatchName());
                helper.setText(R.id.tv_publicity_count, String.valueOf(item.getBuildingCount()));
                helper.setText(R.id.tv_publicity_operater, item.getOperatorId());
                helper.setText(R.id.tv_publicity_startDate, item.getStartDate());
                helper.setText(R.id.tv_publicity_endDate, item.getEndDate());
                helper.setText(R.id.tv_publicity_des, item.getDescriptiton());
                helper.setVisibility(R.id.iv_publicity_hasImg,false);
            }
        });
    }

    @Override
    public void onGetPublicityListSuccess(List<PublicityItem> publicityItems) {
        if (publicityItems != null && publicityItems.size() > 0) {
            adapter.setData(publicityItems);
        }

    }

    @Override
    protected void initData() {
        srl.setOnRefreshListener(this);
    }

    @Override
    protected void initNet() {
        publicityListPresenter.getPublicityList(SpSir.getInstance().getProjectId(), publicityType);
    }

    @Override
    protected int getContentId() {
        return R.layout.common_lv;
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
        initNet();
    }
}
