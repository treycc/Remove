package com.jdp.hls.page.statistics;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonPositionAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.StatisticsDetail;
import com.jdp.hls.model.entiy.StatisticsItem;
import com.jdp.hls.page.table.list.TableListActivity;
import com.jdp.hls.util.AppUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.FixedListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

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
    @BindView(R.id.lv_statistics)
    FixedListView lvStatistics;
    private String statisType;
    private String title;
    private CommonPositionAdapter statisticsAdapter;
    private List<StatisticsItem> statisticsItemList = new ArrayList<>();
    private int[] colorAttr = {R.color.pie_red, R.color.pie_yellow, R.color.pie_cyan, R.color.pie_green};
    public static final int PADDING = 15;

    @OnItemClick({R.id.lv_statistics})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        StatisticsItem statisticsItem = (StatisticsItem) adapterView.getItemAtPosition(position);
        TableListActivity.goActivity(this, String.valueOf(statisticsItem.getStatisItemTypeId()));
    }

    @Override
    public void initVariable() {
        statisType = getIntent().getStringExtra(Constants.Extra.STATIS_TYPE);
        title = getIntent().getStringExtra(Constants.Extra.TITLE);
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
        return TextUtils.isEmpty(title) ? "统计" : title;
    }

    @Override
    protected void initView() {
        statisticsPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        initchart();
        lvStatistics.setAdapter(statisticsAdapter = new CommonPositionAdapter<StatisticsItem>(this,
                statisticsItemList, R.layout.item_statistics_des) {
            @Override
            public void convert(ViewHolder helper, StatisticsItem item, int position) {
                helper.setBackgroundColor(R.id.v_item_color, ContextCompat.getColor(StatisticsActivity.this,
                        colorAttr[position]));
                helper.setText(R.id.tv_statisticsDes, String.format("%s：%d户", item.getStatisItemTypeName(), item
                        .getQuantity()));
            }
        });
    }

    private void initchart() {
        chartPie.setUsePercentValues(true);//自动把数字转换百分比
        chartPie.getDescription().setEnabled(false);//是否显示备注文字
        chartPie.setRotationEnabled(false);//是否可转动
        chartPie.setHighlightPerTapEnabled(false);//是否点击饼图后扩大范围
        chartPie.setDrawEntryLabels(false);//不显示X轴文字
        //饼图与边界间隔
        chartPie.setExtraOffsets(AppUtil.dp2px(PADDING), AppUtil.dp2px(PADDING), AppUtil.dp2px(PADDING), AppUtil
                .dp2px(PADDING));
        //去掉中间空洞
        chartPie.setDrawHoleEnabled(false);
        chartPie.getLegend().setEnabled(false);//是否显示说明文字，带色块
        //说明文字，带色块
//        Legend l = chartPie.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        //数据块水平
//        l.setXEntrySpace(2f);
//        //数据块垂直间距
//        l.setYEntrySpace(0f);
//        l.setYOffset(0f);
//        l.setXOffset(2f);
//        l.setTextSize(12);
//        //绘制在图标外
//        l.setDrawInside(false);
//        //显示说明文字
//        l.setEnabled(false);
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
                colors.add(ContextCompat.getColor(this, colorAttr[i]));
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


        chartPie.setData(data);
        chartPie.highlightValues(null);
        chartPie.invalidate();
    }

    @Override
    protected void initNet() {
        statisticsPresenter.getStatistics(SpSir.getInstance().getProjectId(), statisType, "-1");
    }

    @Override
    public void onGetStatisticsSuccess(StatisticsDetail statisticsDetail) {
        List<StatisticsItem> statisticsItems = statisticsDetail.getLstStatis();
        if (statisticsItems != null && statisticsItems.size() > 0) {
            statisticsAdapter.setData(statisticsItems);
            ArrayList<PieEntry> entries = new ArrayList<>();
            for (StatisticsItem statisticsItem : statisticsItems) {
                entries.add(new PieEntry((float) statisticsItem.getQuantity(), statisticsItem.getStatisItemTypeName()));
            }
            setData(entries);
        }
    }

    public static void goActivity(Context context, String statisType, String title) {
        Intent intent = new Intent(context, StatisticsActivity.class);
        intent.putExtra(Constants.Extra.STATIS_TYPE, statisType);
        intent.putExtra(Constants.Extra.TITLE, title);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
