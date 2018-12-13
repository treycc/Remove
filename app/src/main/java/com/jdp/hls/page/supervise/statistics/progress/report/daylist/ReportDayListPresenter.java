package com.jdp.hls.page.supervise.statistics.progress.report.daylist;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ReportDayResult;

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
public class ReportDayListPresenter implements ReportDayListContract.Presenter {
    private UserApi mApi;
    private ReportDayListContract.View mView;

    @Inject
    public ReportDayListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDayReport(RequestBody requestBody) {
        mApi.getApiService().getDayReport(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<ReportDayResult>(mView) {
                    @Override
                    protected void onSuccess(ReportDayResult reportResult) {
                        mView.onGetDayReportSuccess(reportResult);
                    }
                });
    }


    @Override
    public void attachView(@NonNull ReportDayListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}