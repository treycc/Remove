package com.jdp.hls.page.business.detail.personal;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DetailPersonal;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;

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
public class DetailPersonalPresenter implements DetailPersonalContract.Presenter {
    private UserApi mApi;
    private DetailPersonalContract.View mView;

    @Inject
    public DetailPersonalPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getPersonalDetail(String buildingId) {
        mApi.getApiService().getPersonalDetail(buildingId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DetailPersonal>(mView) {
                    @Override
                    protected void onSuccess(DetailPersonal detailPersonal) {
                        mView.onGetPersonalDetailSuccess(detailPersonal);
                    }
                });
    }

    @Override
    public void modifyPersonalDetail(RequestBody rosterBody) {
        mApi.getApiService().modifyPersonalDetail( rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyPersonalDetailSuccess();
                    }
                });
    }

    @Override
    public void attachView(@NonNull DetailPersonalContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}