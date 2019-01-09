package com.jdp.hls.page.supervise.statistics.progress.progress;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.StatisticsProgressInfo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsProgressInfoPresenter implements StatisticsProgressInfoContract.Presenter {
    private UserApi mApi;
    private StatisticsProgressInfoContract.View mView;

    @Inject
    public StatisticsProgressInfoPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getStatisticsProgress(int buildingType) {
        mApi.getApiService().getStatisticsProgress(buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<StatisticsProgressInfo>(mView) {
                    @Override
                    protected void onSuccess(StatisticsProgressInfo statisticsProgressInfo) {
                        mView.onGetStatisticsProgressSuccess(statisticsProgressInfo);
                    }
                });
    }


    @Override
    public void attachView(@NonNull StatisticsProgressInfoContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}