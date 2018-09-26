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
    private int airCurrentNodeType;
    @Inject
    PublicityListPresenter publicityListPresenter;

    public static PublicityListFragment newInstance(int airCurrentNodeType) {
        PublicityListFragment fragment = new PublicityListFragment();
        Bundle args = new Bundle();
        args.putInt("airCurrentNodeType", airCurrentNodeType);
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
            airCurrentNodeType = getArguments().getInt("airCurrentNodeType", 0);
        }

        for (int i = 0; i < 10; i++) {
            publicityItems.add(new PublicityItem());
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
//                helper.setText(R.id.tv_publicity_number, item.getProjectName());
//                helper.setText(R.id.tv_publicity_count, String.valueOf(item.getYear()));
//                helper.setText(R.id.tv_publicity_operater, item.getAddress());
//                helper.setText(R.id.tv_publicity_date, item.getRealName());
//                helper.setText(R.id.tv_publicity_des, item.getRealName());
//                helper.setBackgroundResource(iv_publicity_hasImg,)
            }
        });
    }

    @Override
    public void onGetPublicityListSuccess(List<PublicityItem> publicityItems) {

    }

    @Override
    protected void initData() {
        srl.setOnRefreshListener(this);
    }

    @Override
    protected void initNet() {
        publicityListPresenter.getPublicityList(SpSir.getInstance().getProjectId(), 1);
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
