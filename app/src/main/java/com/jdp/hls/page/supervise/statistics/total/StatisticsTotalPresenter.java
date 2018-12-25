package com.jdp.hls.page.supervise.statistics.total;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.KeyValue;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.model.entiy.StatisticsTotalInfo;
import com.jdp.hls.model.entiy.TitleItem;

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
public class StatisticsTotalPresenter implements StatisticsTotalContract.Presenter {
    private UserApi mApi;
    private StatisticsTotalContract.View mView;

    @Inject
    public StatisticsTotalPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getStatisticsTotal(int buildingType) {
        mApi.getApiService().getStatisticsTotal( buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<TitleItem>>(mView) {
                    @Override
                    protected void onSuccess(List<TitleItem> titleItemList) {
                        mView.onGetStatisticsTotalSuccess(titleItemList);
                    }
                });
    }


    @Override
    public void attachView(@NonNull StatisticsTotalContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}