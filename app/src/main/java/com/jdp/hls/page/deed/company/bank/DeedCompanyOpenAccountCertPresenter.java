package com.jdp.hls.page.deed.company.bank;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DeedCompanyOpenAccountCert;
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
public class DeedCompanyOpenAccountCertPresenter implements DeedCompanyOpenAccountCertContract.Presenter {
    private UserApi mApi;
    private DeedCompanyOpenAccountCertContract.View mView;

    @Inject
    public DeedCompanyOpenAccountCertPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDeedCompanyOpenAccountCert(String houseId) {
        mApi.getApiService().getDeedCompanyOpenAccountCert(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DeedCompanyOpenAccountCert>(mView) {
                    @Override
                    protected void onSuccess(DeedCompanyOpenAccountCert deedCompanyOpenAccountCert) {
                        mView.onGetDeedCompanyOpenAccountCertSuccess(deedCompanyOpenAccountCert);
                    }
                });
    }

    @Override
    public void addDeedCompanyOpenAccountCert(RequestBody rosterBody) {
        mApi.getApiService().addDeedCompanyOpenAccountCert(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onAddDeedCompanyOpenAccountCertSuccess();
                    }
                });
    }

    @Override
    public void modifyDeedCompanyOpenAccountCert(RequestBody rosterBody) {
        mApi.getApiService().modifyDeedCompanyOpenAccountCert(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyDeedCompanyOpenAccountCertSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull DeedCompanyOpenAccountCertContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}