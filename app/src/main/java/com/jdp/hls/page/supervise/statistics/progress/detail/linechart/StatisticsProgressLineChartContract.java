package com.jdp.hls.page.supervise.statistics.progress.detail.linechart;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.LineChartItem;
import com.jdp.hls.model.entiy.StatisticsProgressDetail;

import java.util.List;

import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface StatisticsProgressLineChartContract {
    interface View extends BaseView {
        void onGetLineChartSuccess(List<LineChartItem> lineChartItemList);
    }

    interface Presenter extends BasePresenter<View> {
        void getLineChart(int itemType,int dateType,String beginDate,String endDate);
    }
}
