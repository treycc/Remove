package com.jdp.hls.page.statistics;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.model.entiy.StatisticsDetail;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsPresenter implements StatisticsContract.Presenter {
    private UserApi mApi;
    private StatisticsContract.View mView;

    @Inject
    public StatisticsPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getStatistics(String ProjectId, String StatisType, String BuildingType) {
        mApi.getApiService().getStatistics(ProjectId, StatisType, BuildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<StatisticsDetail>(mView) {
                    @Override
                    protected void onSuccess(StatisticsDetail statisticsDetail) {
                        mView.onGetStatisticsSuccess(statisticsDetail);
                    }
                });
    }


    @Override
    public void attachView(@NonNull StatisticsContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}