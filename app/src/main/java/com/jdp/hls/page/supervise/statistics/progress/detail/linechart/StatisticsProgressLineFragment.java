package com.jdp.hls.page.supervise.statistics.progress.detail.linechart;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.jdp.hls.R;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.LineChartItem;
import com.jdp.hls.page.supervise.statistics.progress.progress.StatisticsProgressInfoActivity;
import com.jdp.hls.page.supervise.statistics.total.StatisticsTotalActivity;
import com.jdp.hls.page.table.list.TableListActivity;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.StringTextView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.kingja.supershapeview.view.SuperShapeRelativeLayout;
import com.kingja.supershapeview.view.SuperShapeTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MultipartBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsProgressLineFragment extends BaseFragment implements StatisticsProgressLineChartContract.View {

    @BindView(R.id.lineChart)
    LineChart lineChart;
    @BindView(R.id.tv_startDate)
    StringTextView tvStartDate;
    @BindView(R.id.srl_startDate)
    SuperShapeRelativeLayout srlStartDate;
    @BindView(R.id.tv_endDate)
    StringTextView tvEndDate;
    @BindView(R.id.srl_endDate)
    SuperShapeRelativeLayout srlEndDate;
    @BindView(R.id.ll_dateSelector)
    LinearLayout llDateSelector;
    private TimePickerDialog startDateSelector;
    private TimePickerDialog endDateSelector;
    private int itemType;
    private int dateType;
    private String startDatetime;
    private String endDatetime;
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

    @OnClick({R.id.srl_startDate, R.id.srl_endDate, R.id.tv_confirm})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.srl_startDate:
                startDateSelector = new TimePickerDialog.Builder()
                        .setType(Type.YEAR_MONTH_DAY)
                        .setThemeColor(ContextCompat.getColor(getActivity(), R.color.main))
                        .setWheelItemTextSize(15)
                        .setCurrentMillseconds(!TextUtils.isEmpty(startDatetime) ? DateUtil.getMillSeconds
                                (startDatetime,
                                        "yyyy-MM-dd") : System.currentTimeMillis())
                        .setMaxMillseconds(!TextUtils.isEmpty(endDatetime) ? DateUtil.getMillSeconds(endDatetime,
                                "yyyy-MM-dd") : System.currentTimeMillis())
                        .setTitleStringId("请选择起始日期")
                        .setCallBack((timePickerView, millseconds) -> {
                            startDatetime = DateUtil.getDateString(millseconds);
                            tvStartDate.setText(startDatetime);
                        })
                        .build();

                startDateSelector.show(getActivity().getSupportFragmentManager(), String.valueOf(srlStartDate.hashCode
                        ()));
                break;
            case R.id.srl_endDate:
                endDateSelector = new TimePickerDialog.Builder()
                        .setType(Type.YEAR_MONTH_DAY)
                        .setThemeColor(ContextCompat.getColor(getActivity(), R.color.main))
                        .setWheelItemTextSize(15)
                        .setTitleStringId("请选择结束日期")
                        .setMinMillseconds(!TextUtils.isEmpty(startDatetime) ? DateUtil.getMillSeconds(startDatetime,
                                "yyyy-MM-dd") : System.currentTimeMillis())
                        .setCurrentMillseconds(!TextUtils.isEmpty(endDatetime) ? DateUtil.getMillSeconds(endDatetime,
                                "yyyy-MM-dd") : (!TextUtils.isEmpty(startDatetime) ? DateUtil.getMillSeconds
                                (startDatetime, "yyyy-MM-dd") : System.currentTimeMillis()))
                        .setCallBack((timePickerView, millseconds) -> {
                            endDatetime = DateUtil.getDateString(millseconds);
                            tvEndDate.setText(endDatetime);
                        })
                        .build();
                endDateSelector.show(getActivity().getSupportFragmentManager(), String.valueOf(srlEndDate.hashCode()));
                break;
            case R.id.tv_confirm:
                callNet();
                break;
        }
    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            itemType = getArguments().getInt(Constants.Extra.ItemType, 1);
            dateType = getArguments().getInt(Constants.Extra.DateType, 1);
            startDatetime = getArguments().getString(Constants.Extra.BeginDate, "");
            endDatetime = getArguments().getString(Constants.Extra.EndDate, "");
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


        lineChart.setNoDataText("暂无数据");
        lineChart.setNoDataTextColor(ContextCompat.getColor(getActivity(), R.color.main));
        //禁用Y轴
        lineChart.getAxisRight().setEnabled(false);
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

    }

    @Override
    protected void initData() {
        if (dateType == Status.DateType.DATE) {
            startDatetime = DateUtil.getFirstDayOfMonth();
            endDatetime   = DateUtil.getNowDate();
            tvStartDate.setString(startDatetime);
            tvEndDate.setString(endDatetime);
            llDateSelector.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void initNet() {
        callNet();
    }

    private void callNet() {
        statisticsProgressLineChartPresenter.getLineChart(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("ProjectId", SpSir.getInstance().getProjectId())
                .addFormDataPart("ItemType", String.valueOf(itemType))
                .addFormDataPart("DateType", String.valueOf(dateType))
                .addFormDataPart("StartDatetime", startDatetime)
                .addFormDataPart("EndDatetime", endDatetime)
                .build());
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_linechart;
    }

    @Override
    public void onGetLineChartSuccess(List<LineChartItem> lineChartItemList) {

        if (lineChartItemList != null && lineChartItemList.size() > 0) {
            List<Entry> entries = new ArrayList<>();
            String [] dates=new String [lineChartItemList.size()];
            int maxValue = 0;
            int minValue = 0;
            for (int i = 0; i < lineChartItemList.size(); i++) {
                int quantity = lineChartItemList.get(i).getQuantity();
                if (quantity > maxValue) {   // 判断最大值
                    maxValue = quantity;
                }
                if (quantity < minValue) {   // 判断最小值
                    minValue = quantity;
                }
                entries.add(new Entry(i, quantity));
                dates[i]=DateUtil.getLineChartDate(lineChartItemList.get(i).getTimestamp());
            }
            maxValue += maxValue;
            LogUtil.e(TAG,"maxValue:"+maxValue);
//            minValue = minValue - 2 >= 0 ? minValue - 2 : 0;
            //设置折线
            LineDataSet dataSet = new LineDataSet(entries, "折线图"); // 折线图数据及提示文字
            dataSet.setColor(ContextCompat.getColor(getActivity(), R.color.main));//线条颜色
            //圆点外圈
            dataSet.setCircleColor(ContextCompat.getColor(getActivity(), R.color.main_light));//圆点颜色
            dataSet.setCircleRadius(8);
            //圆点内圈
            dataSet.setDrawCircleHole(true);
            dataSet.setCircleColorHole(ContextCompat.getColor(getActivity(), R.color.main));//圆点颜色
            dataSet.setCircleHoleRadius(4);

            dataSet.setLineWidth(1f);//线条宽度
            dataSet.setDrawValues(true);//是否绘制线条上的文字
            dataSet.setValueTextSize(10);//数字文字的大小
            dataSet.setValueTextColor(ContextCompat.getColor(getActivity(), R.color.c_9));//数字文字的颜色
            dataSet.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> (int) value + "户");
            dataSet.setHighlightEnabled(false);//是否禁用点击高亮线
            //3.chart设置数据
            LineData lineData = new LineData(dataSet);
//            lineData.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> String.valueOf((int) value));
            lineChart.setData(lineData);

            YAxis leftAxis = lineChart.getAxisLeft();
            leftAxis.setDrawZeroLine(true);
            leftAxis.setDrawAxisLine(false);
//        leftAxis.setZeroLineWidth(0);
            leftAxis.setTextColor(ContextCompat.getColor(getActivity(), R.color.c_9));
            leftAxis.setTextSize(12f);
            leftAxis.setAxisMinimum(minValue);
            leftAxis.setAxisMaximum(maxValue);
            leftAxis.setLabelCount(6, false);
            //设置Y轴网格线
//            leftAxis.enableGridDashedLine(10f, 10f, 0f);
//            leftAxis.setValueFormatter((value, axis) -> String.valueOf((int) value));
            //设置x轴
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setTextColor(ContextCompat.getColor(getActivity(), R.color.c_9));
            xAxis.setTextSize(12f);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(dates));
            xAxis.setDrawAxisLine(true);//是否绘制轴线
            xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
            xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
            xAxis.setGranularityEnabled(true);
            xAxis.setGranularity(1f);//禁止放大后x轴标签重绘


            //设置一页最大显示个数为6，超出部分就滑动
            float ratio = (float) entries.size() / (float) 6;
            //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
            lineChart.zoom(ratio, 1f, 0, 0);
            lineChart.invalidate(); // refresh
        }
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
