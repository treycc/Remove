package com.jdp.hls.page.business.basic.company;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.BasicCompany;
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
public class BasicCompanyPresenter implements BaiscCompanyContract.Presenter {
    private UserApi mApi;
    private BaiscCompanyContract.View mView;

    @Inject
    public BasicCompanyPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getCompanyBasic(String buildingId) {
        mApi.getApiService().getCompanyBasic(buildingId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<BasicCompany>(mView) {
                    @Override
                    protected void onSuccess(BasicCompany bussinessBaiscPersonal) {
                        mView.onGetCompanyBasicSuccess(bussinessBaiscPersonal);
                    }
                });
    }


    @Override
    public void attachView(@NonNull BaiscCompanyContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}