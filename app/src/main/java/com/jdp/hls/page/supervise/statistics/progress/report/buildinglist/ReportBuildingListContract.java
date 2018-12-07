package com.jdp.hls.page.supervise.statistics.progress.report.buildinglist;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ReportBuilding;
import com.jdp.hls.model.entiy.ReportResult;

import java.util.List;

import okhttp3.RequestBody;

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
        void getReportBuildingList();
    }
}
