package com.jdp.hls.page.statistics;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.StatisticsDetail;

import okhttp3.RequestBody;
import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface StatisticsContract {
    interface View extends BaseView {
        void onGetStatisticsSuccess(StatisticsDetail statisticsDetail);
    }

    interface Presenter extends BasePresenter<View> {
        void getStatistics(String ProjectId, String StatisType, String BuildingType);
    }
}
