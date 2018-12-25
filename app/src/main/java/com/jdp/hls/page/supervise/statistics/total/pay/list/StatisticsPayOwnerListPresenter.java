package com.jdp.hls.page.supervise.statistics.total.pay.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.PayOwnerListInfo;
import com.jdp.hls.model.entiy.SupervisePayInfo;

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
public class StatisticsPayOwnerListPresenter implements StatisticsPayOwnerListContract.Presenter {
    private UserApi mApi;
    private StatisticsPayOwnerListContract.View mView;

    @Inject
    public StatisticsPayOwnerListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getPayOwnList(int BuildingType, int UseItemId) {
        mApi.getApiService().getPayOwnList( BuildingType,  UseItemId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<PayOwnerListInfo>(mView) {
                    @Override
                    protected void onSuccess(PayOwnerListInfo payOwnerListInfo) {
                        mView.onGetPayOwnListListSuccess(payOwnerListInfo);
                    }
                });
    }


    @Override
    public void attachView(@NonNull StatisticsPayOwnerListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}