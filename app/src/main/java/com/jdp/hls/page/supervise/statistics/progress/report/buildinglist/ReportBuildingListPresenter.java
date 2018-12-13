package com.jdp.hls.page.supervise.statistics.progress.report.buildinglist;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ReportBuilding;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReportBuildingListPresenter implements ReportBuildingListContract.Presenter {
    private UserApi mApi;
    private ReportBuildingListContract.View mView;

    @Inject
    public ReportBuildingListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getReportBuildingList(String projectId, int ReportType, String startDate, String endDate) {
        mApi.getApiService().getReportBuildingList(projectId, ReportType, startDate, endDate).subscribeOn(Schedulers
                .io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<ReportBuilding>>(mView) {
                    @Override
                    protected void onSuccess(List<ReportBuilding> reportBuildingList) {
                        mView.onGetReportBuildingListSuccess(reportBuildingList);
                    }
                });
    }


    @Override
    public void attachView(@NonNull ReportBuildingListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}