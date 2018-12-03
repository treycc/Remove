package com.jdp.hls.page.supervise.statistics.progress.detail.linechart;


import android.os.Bundle;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.LineChartItem;

import java.util.List;

import javax.inject.Inject;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsProgressLineFragment extends BaseFragment implements StatisticsProgressLineChartContract.View {

    private int itemType;
    private int dateType;
    private String beginDate;
    private String endDate;
    @Inject
    StatisticsProgressLineChartPresenter statisticsProgressLineChartPresenter;

    public static StatisticsProgressLineFragment newInstance(int itemType, int dateType, String beginDate, String
            endDate) {
        StatisticsProgressLineFragment fragment = new StatisticsProgressLineFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.Extra.ItemType, itemType);
        args.putInt(Constants.Extra.DateType, dateType);
        args.putString(Constants.Extra.BeginDate, beginDate);
        args.putString(Constants.Extra.EndDate, endDate);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            itemType = getArguments().getInt(Constants.Extra.ItemType);
            dateType = getArguments().getInt(Constants.Extra.DateType);
            beginDate = getArguments().getString(Constants.Extra.BeginDate, "");
            endDate = getArguments().getString(Constants.Extra.EndDate, "");
        }
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        statisticsProgressLineChartPresenter.attachView(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_linechart;
    }

    @Override
    public void onGetLineChartSuccess(List<LineChartItem> lineChartItemList) {

    }
}
