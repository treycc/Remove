package com.jdp.hls.page.deed.company.property;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DeedCompanyProperty;
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
public class DeedCompanyPropertyPresenter implements DeedCompanyPropertyContract.Presenter {
    private UserApi mApi;
    private DeedCompanyPropertyContract.View mView;

    @Inject
    public DeedCompanyPropertyPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDeedCompanyProperty(String houseId) {
        mApi.getApiService().getDeedCompanyProperty(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DeedCompanyProperty>(mView) {
                    @Override
                    protected void onSuccess(DeedCompanyProperty deedCompanyProperty) {
                        mView.onGetDeedCompanyPropertySuccess(deedCompanyProperty);
                    }
                });
    }

    @Override
    public void addDeedCompanyProperty(RequestBody rosterBody) {
        mApi.getApiService().addDeedCompanyProperty(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onAddDeedCompanyPropertySuccess();
                    }
                });
    }

    @Override
    public void modifyDeedCompanyProperty(RequestBody rosterBody) {
        mApi.getApiService().modifyDeedCompanyProperty(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyDeedCompanyPropertySuccess();
                    }
                });
    }

    @Override
    public void attachView(@NonNull DeedCompanyPropertyContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}