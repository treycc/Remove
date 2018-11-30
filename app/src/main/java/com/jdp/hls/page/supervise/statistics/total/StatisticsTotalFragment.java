package com.jdp.hls.page.supervise.statistics.total;

import android.os.Bundle;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.KeyValue;
import com.jdp.hls.view.FixedListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Description:TODO
 * Create Time:2018/11/27 0027 下午 4:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsTotalFragment extends BaseFragment implements StatisticsTotalContract.View {

    @BindView(R.id.flv)
    FixedListView flv;
    private int buildingType;
    private CommonAdapter adapter;
    @Inject
    StatisticsTotalPresenter statisticsTotalPresenter;
    private String projectId;

    public static StatisticsTotalFragment newInstance(String projectId, int buildingType) {
        StatisticsTotalFragment fragment = new StatisticsTotalFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.Extra.BUILDING_TYPE, buildingType);
        args.putString(Constants.Extra.PROJECTID, projectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            buildingType = getArguments().getInt(Constants.Extra.BUILDING_TYPE);
            projectId = getArguments().getString(Constants.Extra.PROJECTID);
        }
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        statisticsTotalPresenter.attachView(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        flv.setAdapter(adapter = new CommonAdapter<KeyValue>(getActivity(), new ArrayList<>(), R.layout
                .item_statistics_total) {
            @Override
            public void convert(ViewHolder helper, KeyValue item) {
                helper.setText(R.id.tv_key, item.getKey());
                helper.setText(R.id.tv_value, item.getValue());
            }
        });
    }

    @Override
    public void initNet() {
        statisticsTotalPresenter.getStatisticsTotal(projectId, buildingType);
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_statistics_total;
    }

    @Override
    public void onGetStatisticsTotalSuccess(List<KeyValue> keyValueList) {
        setListView(keyValueList, adapter);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
