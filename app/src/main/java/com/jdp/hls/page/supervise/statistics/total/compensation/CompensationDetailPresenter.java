package com.jdp.hls.page.supervise.statistics.total.compensation;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.TitleValue;

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
public class CompensationDetailPresenter implements CompensationDetailContract.Presenter {
    private UserApi mApi;
    private CompensationDetailContract.View mView;

    @Inject
    public CompensationDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getCompensationDetail( int itemId) {
        mApi.getApiService().getCompensationDetail(  itemId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<TitleValue>>(mView) {
                    @Override
                    protected void onSuccess(List<TitleValue> titleValueList) {
                        mView.onGetCompensationDetailSuccess(titleValueList);
                    }
                });
    }


    @Override
    public void attachView(@NonNull CompensationDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}