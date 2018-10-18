package com.jdp.hls.page.publicity.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.PublicityItem;
import com.jdp.hls.page.publicity.detail.PublicityDetailActivity;
import com.jdp.hls.util.DateUtil;
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
                helper.setText(R.id.tv_publicity_count, String.valueOf(item.getTotalQuantity()));
                helper.setText(R.id.tv_publicity_operater, SpSir.getInstance().getRealName());
                helper.setText(R.id.tv_publicity_startDate, DateUtil.getShortDate(item.getStartDate()));
                helper.setText(R.id.tv_publicity_endDate, DateUtil.getShortDate(item.getEndDate()));
                helper.setText(R.id.tv_publicity_des, item.getDescriptiton());
                helper.setVisibility(R.id.iv_publicity_hasImg, false);
                helper.setBackgroundResource(R.id.iv_publicity_buildingType, (item.getBuildingType() == Status
                        .BuildingType.PERSONAL) ? R.mipmap.ic_buildingtype_personal : R.mipmap.ic_buildingtype_company);
            }
        });
    }

    @Override
    public void onGetPublicityListSuccess(List<PublicityItem> publicityItems) {
        this.publicityItems = publicityItems;
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
        return R.layout.common_lv_sl;
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
