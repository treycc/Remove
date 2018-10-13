package com.jdp.hls.page.statistics;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.StatisticsDetail;
import com.jdp.hls.model.entiy.StatisticsItem;
import com.jdp.hls.util.SpSir;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/9/5 0005 下午 2:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsActivity extends BaseTitleActivity implements StatisticsContract.View {
    @BindView(R.id.chart_pie)
    PieChart chartPie;

    @Inject
    StatisticsPresenter statisticsPresenter;
    private String statisType;

    @Override
    public void initVariable() {
        statisType = getIntent().getStringExtra(Constants.Extra.STATIS_TYPE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_statistics;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "统计";
    }

    @Override
    protected void initView() {
        statisticsPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        //去掉右下角描述
        chartPie.getDescription().setEnabled(false);
        chartPie.setRotationEnabled(false);
        //饼图与边界间隔
        chartPie.setExtraOffsets(10, 10, 10, 10);
        //去掉中间空洞
        chartPie.setDrawHoleEnabled(false);
        //模拟数据
//        ArrayList<PieEntry> entries = new ArrayList<>();
//        entries.add(new PieEntry(50.00f, "已丈量"));
//        entries.add(new PieEntry(30.00f, "已调查未丈量"));
//        entries.add(new PieEntry(20.00f, "未调查"));
//        //设置数据
//        setData(entries);
        //说明文字
        Legend l = chartPie.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        //数据块水平
        l.setXEntrySpace(2f);
        //数据块垂直间距
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        l.setXOffset(2f);
        l.setTextSize(12);
        //绘制在图标外
        l.setDrawInside(false);
        //显示说明文字
        l.setEnabled(true);
    }

    private void setData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "");
        //分割线宽度
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(12f);
        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(ContextCompat.getColor(this, R.color.pie_red));
        colors.add(ContextCompat.getColor(this, R.color.pie_yellow));
        colors.add(ContextCompat.getColor(this, R.color.pie_cyan));
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

//        dataSet.setValueLinePart1OffsetPercentage(80.f);
//        dataSet.setValueLinePart1Length(0.2f);
//        dataSet.setValueLinePart2Length(0.4f);
        //dataSet.setUsingSliceColorAsValueLineColor(true);

        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        //绘制百分比位置
//        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        dataSet.setXValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(18f);
        data.setValueTextColor(Color.WHITE);
        chartPie.setData(data);
        chartPie.highlightValues(null);
        //刷新
        chartPie.invalidate();
    }

    @Override
    protected void initNet() {
        statisticsPresenter.getStatistics(SpSir.getInstance().getProjectId(),statisType,"-1");
    }

    @Override
    public void onGetStatisticsSuccess(StatisticsDetail statisticsDetail) {
        List<StatisticsItem> statisticsItems = statisticsDetail.getLstStatis();
        if (statisticsItems != null && statisticsItems.size() > 0) {
            ArrayList<PieEntry> entries = new ArrayList<>();
            for (StatisticsItem statisticsItem : statisticsItems) {
                entries.add(new PieEntry(statisticsItem.getQuantity(), statisticsItem.getStatisTypeName()));
            }
            setData(entries);
        }
    }
    public static void goActivity(Context context, String statisType) {
        Intent intent = new Intent(context, StatisticsActivity.class);
        intent.putExtra(Constants.Extra.STATIS_TYPE, statisType);
        context.startActivity(intent);
    }

}
