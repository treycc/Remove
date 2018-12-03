package com.jdp.hls.page.supervise.statistics.progress.detail.head;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.StatisticsProgressDetail;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsProgressDetailPresenter implements StatisticsProgressDetailContract.Presenter {
    private UserApi mApi;
    private StatisticsProgressDetailContract.View mView;

    @Inject
    public StatisticsProgressDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getStatisticsProgressDetail(int itemType) {
        mApi.getApiService().getStatisticsProgressDetail(itemType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<StatisticsProgressDetail>(mView) {
                    @Override
                    protected void onSuccess(StatisticsProgressDetail statisticsProgressDetail) {
                        mView.onGetStatisticsProgressDetailSuccess(statisticsProgressDetail);
                    }
                });
    }


    @Override
    public void attachView(@NonNull StatisticsProgressDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}