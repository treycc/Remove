package com.jdp.hls.page.supervise.statistics.progress.report.daylist;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ReportDayResult;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ReportDayListContract {
    interface View extends BaseView {
        void onGetDayReportSuccess(ReportDayResult reportResult);
    }

    interface Presenter extends BasePresenter<View> {
        void getDayReport(RequestBody requestBody);
    }
}
