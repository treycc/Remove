package com.jdp.hls.page.supervise.statistics.progress.home;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.StatisticsProgressItem;
import com.jdp.hls.view.FixedListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/11/27 0027 下午 3:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsProgressFragment extends BaseFragment {

    @BindView(R.id.flv)
    FixedListView flv;
    private int buildingType;

    public static StatisticsProgressFragment newInstance(int buildingType) {
        StatisticsProgressFragment fragment = new StatisticsProgressFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.Extra.BUILDING_TYPE, buildingType);
        fragment.setArguments(args);
        return fragment;
    }

    @OnItemClick({R.id.flv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {

    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            buildingType = getArguments().getInt(Constants.Extra.BUILDING_TYPE);
        }
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initView() {

    }

    private List<StatisticsProgressItem> statisticsProgressList = new ArrayList<>();

    private CommonAdapter<StatisticsProgressItem> adapter;

    @Override
    protected void initData() {
        flv.setAdapter(adapter = new CommonAdapter<StatisticsProgressItem>(getActivity(), statisticsProgressList, R
                .layout.item_statistics_progress) {
            @Override
            public void convert(ViewHolder helper, StatisticsProgressItem item) {
            }
        });
    }

    @Override
    public void initNet() {
        statisticsProgressList.add(new StatisticsProgressItem());
        statisticsProgressList.add(new StatisticsProgressItem());
        statisticsProgressList.add(new StatisticsProgressItem());
        statisticsProgressList.add(new StatisticsProgressItem());
        statisticsProgressList.add(new StatisticsProgressItem());
        statisticsProgressList.add(new StatisticsProgressItem());
        statisticsProgressList.add(new StatisticsProgressItem());
        statisticsProgressList.add(new StatisticsProgressItem());
        adapter.setData(statisticsProgressList);
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_statistics_progress;
    }

}
