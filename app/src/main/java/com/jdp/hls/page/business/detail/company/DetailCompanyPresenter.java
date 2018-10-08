package com.jdp.hls.page.business.detail.company;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DetailCompany;
import com.jdp.hls.model.entiy.LoadSirObserver;

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
public class DetailCompanyPresenter implements DetailCompanyContract.Presenter {
    private UserApi mApi;
    private DetailCompanyContract.View mView;

    @Inject
    public DetailCompanyPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getCompanyDetail(String buildingId) {
        mApi.getApiService().getCompanyDetail(buildingId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DetailCompany>(mView) {
                    @Override
                    protected void onSuccess(DetailCompany detailCompany) {
                        mView.onGetCompanyDetailSuccess(detailCompany);
                    }
                });
    }

    @Override
    public void modifyCompanyDetail(RequestBody rosterBody) {
        mApi.getApiService().modifyCompanyDetail(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyCompanyDetailSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull DetailCompanyContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}