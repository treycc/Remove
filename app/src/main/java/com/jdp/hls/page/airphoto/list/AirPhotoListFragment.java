package com.jdp.hls.page.airphoto.list;

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
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AirPhotoItem;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

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
    RefreshSwipeRefreshLayout srl;
    private List<AirPhotoItem> airPhotoItems = new ArrayList<>();
    private AirPhotoListAdapter adapter;
    private int airCurrentNodeType;
    @Inject
    AirPhotoListPresenter airPhotoListPresenter;

    public static AirPhotoListFragment newInstance(int airCurrentNodeType) {
        AirPhotoListFragment fragment = new AirPhotoListFragment();
        Bundle args = new Bundle();
        args.putInt("airCurrentNodeType", airCurrentNodeType);
        fragment.setArguments(args);
        return fragment;
    }

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
    }

    @Override
    protected void initVariable() {
//        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            airCurrentNodeType = getArguments().getInt("airCurrentNodeType", 0);
        }

        for (int i = 0; i < 10; i++) {
            airPhotoItems.add(new AirPhotoItem());
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
        airPhotoListPresenter.attachView(this);
        adapter = new AirPhotoListAdapter(getActivity(), airPhotoItems, R.layout.item_airphoto);
        plv.setAdapter(adapter);
    }

    @Override
    public void onGetAirPhotoListSuccess(List<AirPhotoItem> airPhotoItems) {

    }


    class AirPhotoListAdapter extends CommonAdapter<AirPhotoItem> {
        public AirPhotoListAdapter(Context context, List<AirPhotoItem> datas, int itemLayoutId) {
            super(context, datas, itemLayoutId);
        }


        @Override
        public void convert(ViewHolder helper, AirPhotoItem item) {
//            helper.setText(R.id.tv_airphoto_address, item.getAddress());
//            helper.setText(R.id.tv_airphoto_use, item.getLandUseTypeName());
//            helper.setText(R.id.tv_airphoto_structure, item.getStructureTypeName());
//            helper.setText(R.id.tv_airphoto_name, item.getRealName());
//            helper.setText(R.id.tv_airphoto_mobile, item.getMobilePhone());

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
        airPhotoListPresenter.getAirPhotoList(SpSir.getInstance().getProjectId(), airCurrentNodeType);
    }

    @Override
    protected int getContentId() {
        return R.layout.common_lv;
    }


    public void refreshData(List<Roster> rosters) {
//        adapter.setData(airPhotoItems);
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
