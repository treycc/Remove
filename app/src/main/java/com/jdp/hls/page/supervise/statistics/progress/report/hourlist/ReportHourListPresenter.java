package com.jdp.hls.page.supervise.statistics.progress.report.hourlist;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ReportDayResult;
import com.jdp.hls.model.entiy.ReportHourResult;
import com.jdp.hls.page.supervise.statistics.progress.report.daylist.ReportDayListContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReportHourListPresenter implements ReportHourListContract.Presenter {
    private UserApi mApi;
    private ReportHourListContract.View mView;

    @Inject
    public ReportHourListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getHourReport(String projectId, int ReportType, String startDate, String endDate) {
        mApi.getApiService().getHourReport(projectId, ReportType, startDate, endDate).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<ReportHourResult>(mView) {
                    @Override
                    protected void onSuccess(ReportHourResult reportResult) {
                        mView.onGetHourReportSuccess(reportResult);
                    }
                });
    }


    @Override
    public void attachView(@NonNull ReportHourListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}