package com.jdp.hls.page.supervise.statistics.progress.report.buildinglist;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ReportBuilding;

import java.util.List;

import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ReportBuildingListContract {
    interface View extends BaseView {
        void onGetReportBuildingListSuccess(List<ReportBuilding> reportBuildingList);
    }

    interface Presenter extends BasePresenter<View> {
        void getReportBuildingList(String projectId, int ReportType, String startDate, String endDate);
    }
}
