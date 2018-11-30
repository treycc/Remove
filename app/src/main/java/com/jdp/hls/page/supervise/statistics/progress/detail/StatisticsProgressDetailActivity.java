package com.jdp.hls.page.supervise.statistics.progress.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.supervise.statistics.progress.report.daylist.ReportListDayActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:TODO
 * Create Time:2018/11/28 0028 上午 8:49
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsProgressDetailActivity extends BaseTitleActivity {
    @BindView(R.id.lineChart)
    LineChart lineChart;

    @Override
    public void initVariable() {

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
        setRightClick("查看报表", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                GoUtil.goActivity(StatisticsProgressDetailActivity.this, ReportListDayActivity.class);
            }
        });

    }

    @Override
    public void initNet() {

        //1.设置x轴和y轴的点
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 30; i++)
            entries.add(new Entry(i, new Random().nextInt(300)));
//        3 .把数据赋值到你的线条
        LineDataSet dataSet = new LineDataSet(entries, "折线图"); // 折线图数据及提示文字
        dataSet.setColor(ContextCompat.getColor(this, R.color.main));//线条颜色
        dataSet.setCircleColor(ContextCompat.getColor(this, R.color.main));//圆点颜色
        dataSet.setCircleRadius(4);
        dataSet.setDrawCircleHole(false);
        dataSet.setLineWidth(1f);//线条宽度
        dataSet.setDrawValues(false);//是否绘制线条上的文字
        dataSet.setHighlightEnabled(false);//是否禁用点击高亮线

        //3.chart设置数据
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        //设置Y轴
        lineChart.getAxisRight().setEnabled(false);//设置图表右边的y轴禁用
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setZeroLineWidth(0);
        leftAxis.setTextColor(ContextCompat.getColor(this, R.color.c_9));
        leftAxis.setTextSize(12f);
        leftAxis.setAxisMinimum(0);
        leftAxis.setAxisMaximum(300);

        //设置x轴
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextColor(ContextCompat.getColor(this, R.color.c_9));
        xAxis.setTextSize(12f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf((int) value + 1).concat("月");
            }
        });
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setGranularity(1f);//禁止放大后x轴标签重绘

        //透明化图例
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.NONE);
        legend.setTextColor(Color.WHITE);

        //隐藏x轴描述
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);

        //设置滚动
        lineChart.setDragXEnabled(true);
        lineChart.setDragYEnabled(false);
        lineChart.setScaleEnabled(false);
        //设置一页最大显示个数为6，超出部分就滑动
        float ratio = (float)30/(float) 6;
        //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
        lineChart.zoom(ratio,1f,0,0);

        lineChart.invalidate(); // refresh

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
