package com.jdp.hls.page.supervise.statistics.total.pay;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;
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
public class StatisticsPayListPresenter implements StatisticsPayListContract.Presenter {
    private UserApi mApi;
    private StatisticsPayListContract.View mView;

    @Inject
    public StatisticsPayListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getSupervisePayList(RequestBody requestBody) {
        mApi.getApiService().getSupervisePayList( requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<SupervisePayInfo>(mView) {
                    @Override
                    protected void onSuccess(SupervisePayInfo supervisePayInfo) {
                        mView.onGetSupervisePayListSuccess(supervisePayInfo);
                    }
                });
    }


    @Override
    public void attachView(@NonNull StatisticsPayListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}