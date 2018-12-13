package com.jdp.hls.page.supervise.statistics.progress.report.hourlist;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ReportDayResult;
import com.jdp.hls.model.entiy.ReportHourResult;

import okhttp3.RequestBody;
import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ReportHourListContract {
    interface View extends BaseView {
        void onGetHourReportSuccess(ReportHourResult reportResult);
    }

    interface Presenter extends BasePresenter<View> {
        void getHourReport(String projectId, int ReportType, String startDate, String endDate);
    }
}
