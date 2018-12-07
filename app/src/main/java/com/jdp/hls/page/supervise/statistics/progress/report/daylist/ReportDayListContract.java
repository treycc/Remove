package com.jdp.hls.page.supervise.statistics.progress.report.daylist;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ReportItem;
import com.jdp.hls.model.entiy.ReportResult;
import com.jdp.hls.model.entiy.StatisticsProgressInfo;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ReportDayListContract {
    interface View extends BaseView {
        void onGetDayReportSuccess(ReportResult reportResult);
    }

    interface Presenter extends BasePresenter<View> {
        void getDayReport(RequestBody requestBody);
    }
}
