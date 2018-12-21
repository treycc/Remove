package com.jdp.hls.page.supervise.statistics.total.taotype;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.TaoType;

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
public class StatisticsTaotypeListPresenter implements StatisticsTaotypeListContract.Presenter {
    private UserApi mApi;
    private StatisticsTaotypeListContract.View mView;

    @Inject
    public StatisticsTaotypeListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getSuperviseTaotypeList() {
        mApi.getApiService().getSuperviseTaoTypeList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<TaoType>>(mView) {
                    @Override
                    protected void onSuccess(List<TaoType> taoTypeList) {
                        mView.onGetSuperviseTaotypeListSuccess(taoTypeList);
                    }
                });
    }


    @Override
    public void attachView(@NonNull StatisticsTaotypeListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}