package com.jdp.hls.page.supervise.statistics.progress.detail.head;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.StatisticsProgressDetail;
import com.jdp.hls.model.entiy.StatisticsProgressInfo;

import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface StatisticsProgressDetailContract {
    interface View extends BaseView {
        void onGetStatisticsProgressDetailSuccess(StatisticsProgressDetail statisticsProgressDetail);
    }

    interface Presenter extends BasePresenter<View> {
        void getStatisticsProgressDetail(int itemType);
    }
}
