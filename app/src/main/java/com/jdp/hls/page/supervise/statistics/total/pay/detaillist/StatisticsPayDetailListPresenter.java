package com.jdp.hls.page.supervise.statistics.total.pay.detaillist;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.SupervisePayDetailInfo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsPayDetailListPresenter implements StatisticsPayDetailListContract.Presenter {
    private UserApi mApi;
    private StatisticsPayDetailListContract.View mView;

    @Inject
    public StatisticsPayDetailListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getSupervisePayDetailList() {
        mApi.getApiService().getSupervisePayDetailList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<SupervisePayDetailInfo>(mView) {
                    @Override
                    protected void onSuccess(SupervisePayDetailInfo supervisePayDetailInfo) {
                        mView.onGetSupervisePayDetailListSuccess(supervisePayDetailInfo);
                    }
                });
    }


    @Override
    public void attachView(@NonNull StatisticsPayDetailListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}