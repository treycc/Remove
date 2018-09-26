package com.jdp.hls.page.business.basic.personla;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.BaiscPersonal;
import com.jdp.hls.model.entiy.LoadSirObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BasicPersonalPresenter implements BaiscPersonalContract.Presenter {
    private UserApi mApi;
    private BaiscPersonalContract.View mView;

    @Inject
    public BasicPersonalPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getPersonalBasic(String buildingId) {
        mApi.getApiService().getPersonalBasic(buildingId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<BaiscPersonal>(mView) {
                    @Override
                    protected void onSuccess(BaiscPersonal baiscPersonal) {
                        mView.onGetPersonalBasicSuccess(baiscPersonal);
                    }
                });
    }


    @Override
    public void attachView(@NonNull BaiscPersonalContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}