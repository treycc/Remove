package com.jdp.hls.page.supervise.statistics.progress.progress;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ProgressItem;
import com.jdp.hls.model.entiy.StatisticsProgressInfo;
import com.jdp.hls.page.supervise.statistics.progress.detail.head.StatisticsProgressDetailActivity;
import com.jdp.hls.util.AppUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.view.FixedListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/11/27 0027 下午 3:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsProgressInfoFragment extends BaseFragment implements StatisticsProgressInfoContract.View {

    @BindView(R.id.flv)
    FixedListView flv;
    @BindView(R.id.chart_pie)
    PieChart chartPie;
    private int buildingType;
    public static final int PADDING_TOP_DOWN = 5;
    public static final int PADDING_LEFT_RIGHT = 10;
    private int[] colorAttr = {R.color.pink, R.color.main};
    private List<ProgressItem> statisticsProgressList = new ArrayList<>();
    private CommonAdapter<ProgressItem> adapter;

    @Inject
    StatisticsProgressInfoPresenter statisticsProgressPresenter;

    public static StatisticsProgressInfoFragment newInstance(int buildingType) {
        StatisticsProgressInfoFragment fragment = new StatisticsProgressInfoFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.Extra.BUILDING_TYPE, buildingType);
        fragment.setArguments(args);
        return fragment;
    }

    @OnItemClick({R.id.flv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        GoUtil.goActivity(getActivity(), StatisticsProgressDetailActivity.class);
    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            buildingType = getArguments().getInt(Constants.Extra.BUILDING_TYPE);
        }
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        statisticsProgressPresenter.attachView(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        initchart();
        flv.setAdapter(adapter = new CommonAdapter<ProgressItem>(getActivity(), statisticsProgressList, R
                .layout.item_statistics_progress) {
            @Override
            public void convert(ViewHolder helper, ProgressItem item) {
            }
        });
    }

    @Override
    public void initNet() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) 450, "总签约户数:" + 450 + "户"));
        entries.add(new PieEntry((float) 750, "总未签约户数:" + 750 + "户"));
        setData(entries);

        statisticsProgressList.add(new ProgressItem());
        statisticsProgressList.add(new ProgressItem());
        statisticsProgressList.add(new ProgressItem());
        statisticsProgressList.add(new ProgressItem());
        statisticsProgressList.add(new ProgressItem());
        statisticsProgressList.add(new ProgressItem());
        statisticsProgressList.add(new ProgressItem());
        statisticsProgressList.add(new ProgressItem());
        adapter.setData(statisticsProgressList);
    }

    private void setData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "");
        //分割线宽度
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(12f);
        //数据和颜色
        if (entries != null && entries.size() > 0) {
            ArrayList<Integer> colors = new ArrayList<Integer>();
            for (int i = 0; i < entries.size(); i++) {
                colors.add(ContextCompat.getColor(getActivity(), colorAttr[i]));
            }
            dataSet.setColors(colors);
        }
        PieData data = new PieData(dataSet);
//        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);
        /*====================*/
//        dataSet.setValueLinePart1OffsetPercentage(80.f);
//        dataSet.setValueLinePart1Length(0.5f);
//        dataSet.setValueLinePart2Length(0.5f);
        //当值显示在界面外面的时候是否允许改变量行长度
        dataSet.setValueLineVariableLength(false);
        //设置线的宽度
        dataSet.setValueLineWidth(1);
        //设置项X值拿出去
        dataSet.setXValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
        //设置将Y轴的值拿出去
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        chartPie.setCenterText("预计总户数\n1000户");
        chartPie.setData(data);
        chartPie.highlightValues(null);
        chartPie.invalidate();
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_statistics_progress;
    }

    private void initchart() {
        chartPie.setUsePercentValues(true);//自动把数字转换百分比
        chartPie.getDescription().setEnabled(false);//是否显示备注文字
        chartPie.setRotationEnabled(false);//是否可转动
        chartPie.setHighlightPerTapEnabled(false);//是否点击饼图后扩大范围
        chartPie.setDrawEntryLabels(false);//是否显示X轴文字(非数字)
        chartPie.setDrawCenterText(true);//是否显示中心文字
        //饼图与边界间隔
        chartPie.setExtraOffsets(AppUtil.dp2px(PADDING_LEFT_RIGHT), AppUtil.dp2px(PADDING_TOP_DOWN), AppUtil.dp2px
                (PADDING_LEFT_RIGHT), AppUtil.dp2px(PADDING_TOP_DOWN));
        //去掉中间空洞
        chartPie.setDrawHoleEnabled(true);
        chartPie.setHoleRadius(66);
        chartPie.setTransparentCircleAlpha(0);//中间透明圈颜色

        Legend legend = chartPie.getLegend();//图例
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);//垂直对齐方式
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);//水平对齐方式
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//图例布局方向
//        legend.setYOffset(-10);
        legend.setForm(Legend.LegendForm.CIRCLE);//图标形状
        legend.setXEntrySpace(18f);//水平间距
        legend.setTextSize(12);//图例文字大小
        legend.setTextColor(ContextCompat.getColor(getActivity(), R.color.c_6));
        legend.setDrawInside(true);//绘制在图标外
        legend.setEnabled(true);//是否显示图例
    }

    @Override
    public void onGetStatisticsProgressSuccess(StatisticsProgressInfo statisticsProgressInfo) {

    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
