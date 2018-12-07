package com.jdp.hls.page.supervise.statistics.progress.detail.head;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.adapter.LineChartPageAdapter;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ProgressItem;
import com.jdp.hls.model.entiy.StatisticsProgressDetail;
import com.jdp.hls.page.supervise.statistics.progress.detail.linechart.StatisticsProgressLineFragment;
import com.jdp.hls.page.supervise.statistics.progress.report.daylist.ReportDayListActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.NoScrollViewPager;
import com.jdp.hls.view.StringTextView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/11/28 0028 上午 8:49
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsProgressDetailActivity extends BaseTitleActivity implements StatisticsProgressDetailContract
        .View {
    @Inject
    StatisticsProgressDetailPresenter statisticsProgressDetailPresenter;
    @BindView(R.id.tv_quantityFinished)
    StringTextView tvQuantityFinished;
    @BindView(R.id.tv_quantityUnfinished)
    StringTextView tvQuantityUnfinished;
    @BindView(R.id.tv_ratio)
    StringTextView tvRatio;
    @BindView(R.id.tv_ratioName)
    StringTextView tvRatioName;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    NoScrollViewPager vp;

    private int[] tabImgs = {R.drawable.selector_tab_statistics_day, R.drawable.selector_tab_statistics_week, R
            .drawable.selector_tab_statistics_month, R.drawable.selector_tab_statistics_date};
    private BaseFragment mFragmentArr[] = new BaseFragment[4];
    private ProgressItem progressItem;

    @Override
    public void initVariable() {
        progressItem = (ProgressItem) getIntent().getSerializableExtra(Constants.Extra.ProgressItem);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_statistics_progress_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        statisticsProgressDetailPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "统计详情";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        if (progressItem != null) {
            tvQuantityFinished.setString(progressItem.getFinishedQty());
            tvQuantityUnfinished.setString(progressItem.getUnfinishedQty());
            tvRatio.setString(progressItem.getPercentDesc());
            tvRatioName.setString(progressItem.getRatioName());
        }


        setRightClick("查看报表", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                ReportDayListActivity.goActivity(StatisticsProgressDetailActivity.this,progressItem.getItemType());
            }
        });

        mFragmentArr[0] = StatisticsProgressLineFragment.newInstance(progressItem.getItemType(), Status.DateType.DAY, "", "");
        mFragmentArr[1] = StatisticsProgressLineFragment.newInstance(progressItem.getItemType(), Status.DateType.WEEK, "", "");
        mFragmentArr[2] = StatisticsProgressLineFragment.newInstance(progressItem.getItemType(), Status.DateType.MONTH, "", "");
        mFragmentArr[3] = StatisticsProgressLineFragment.newInstance(progressItem.getItemType(), Status.DateType.DATE, "", "");
        LineChartPageAdapter lineChartPageAdapter = new LineChartPageAdapter(this, mFragmentArr, tabImgs);
        vp.setAdapter(lineChartPageAdapter);
        vp.setOffscreenPageLimit(4);
        tab.setupWithViewPager(vp);
        for (int i = 0; i < tab.getTabCount(); i++) {
            TabLayout.Tab t = tab.getTabAt(i);
            t.setCustomView(lineChartPageAdapter.getTabView(i));
        }

    }

    @Override
    public void initNet() {

    }

    @Override
    public void onGetStatisticsProgressDetailSuccess(StatisticsProgressDetail statisticsProgressDetail) {

    }

    public static void goActivity(Context context, ProgressItem progressItem) {
        Intent intent = new Intent(context, StatisticsProgressDetailActivity.class);
        intent.putExtra(Constants.Extra.ProgressItem, progressItem);
        context.startActivity(intent);
    }
}
