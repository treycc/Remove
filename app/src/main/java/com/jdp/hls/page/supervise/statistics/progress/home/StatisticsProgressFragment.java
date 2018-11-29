package com.jdp.hls.page.supervise.statistics.progress.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.CommonPositionAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.BusinessAction;
import com.jdp.hls.model.entiy.Statistics;
import com.jdp.hls.model.entiy.StatisticsProgressItem;
import com.jdp.hls.page.statistics.StatisticsActivity;
import com.jdp.hls.page.supervise.statistics.progress.detail.StatisticsProgressDetailActivity;
import com.jdp.hls.util.AppUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.view.FixedGridView;
import com.jdp.hls.view.FixedListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;

/**
 * Description:TODO
 * Create Time:2018/11/27 0027 下午 3:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsProgressFragment extends BaseFragment {

    @BindView(R.id.flv)
    FixedListView flv;
    @BindView(R.id.chart_pie)
    PieChart chartPie;
    @BindView(R.id.gv)
    FixedGridView gv;
    Unbinder unbinder;
    private int buildingType;
    public static final int PADDING_TOP_DOWN = 5;
    public static final int PADDING_LEFT_RIGHT = 10;
    private int[] colorAttr = {R.color.pink, R.color.main};
    private int[] backgroundAttr = {R.drawable.shape_circle_pink, R.drawable.shape_circle_blue};

    public static StatisticsProgressFragment newInstance(int buildingType) {
        StatisticsProgressFragment fragment = new StatisticsProgressFragment();
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
    }

    @Override
    protected void initView() {

    }

    private List<StatisticsProgressItem> statisticsProgressList = new ArrayList<>();

    private CommonAdapter<StatisticsProgressItem> adapter;

    private CommonPositionAdapter commonPositionAdapter;
    private List<Statistics> statisticsList = new ArrayList<>();

    @Override
    protected void initData() {
        initchart();
        flv.setAdapter(adapter = new CommonAdapter<StatisticsProgressItem>(getActivity(), statisticsProgressList, R
                .layout.item_statistics_progress) {
            @Override
            public void convert(ViewHolder helper, StatisticsProgressItem item) {
            }
        });
        statisticsList.add(new Statistics("总签约户数", 450));
        statisticsList.add(new Statistics("总未签约户数", 750));
        gv.setAdapter(commonPositionAdapter = new CommonPositionAdapter<Statistics>(getActivity(),
                statisticsList, R.layout.item_piechat) {
            @Override
            public void convert(ViewHolder helper, Statistics item, int position) {
                helper.setBackgroundResource(R.id.iv_circle, backgroundAttr[position]);
                helper.setText(R.id.tv_desText, item.getName() + ":" + item.getQuantity() + "户");
                helper.setTextColor(R.id.tv_desText, ContextCompat.getColor(getActivity(), colorAttr[position]));
            }
        });
    }

    @Override
    public void initNet() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) 450, "总签约户数"));
        entries.add(new PieEntry((float) 750, "总未签约户数"));
        setData(entries);

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
