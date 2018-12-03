package com.jdp.hls.page.supervise.statistics.progress.progress;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.StatisticsProgressInfo;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface StatisticsProgressInfoContract {
    interface View extends BaseView {
        void onGetStatisticsProgressSuccess(StatisticsProgressInfo statisticsProgressInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void getStatisticsProgress(String projectId, int buildingType);
    }
}
