package com.jdp.hls.page.deed.company.license;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DeedCompanyLicense;
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
public class DeedCompanyLicensePresenter implements DeedCompanyLicenseContract.Presenter {
    private UserApi mApi;
    private DeedCompanyLicenseContract.View mView;

    @Inject
    public DeedCompanyLicensePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDeedCompanyLicense(String houseId) {
        mApi.getApiService().getDeedCompanyLicense(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DeedCompanyLicense>(mView) {
                    @Override
                    protected void onSuccess(DeedCompanyLicense deedCompanyLicense) {
                        mView.onGetDeedCompanyLicenseSuccess(deedCompanyLicense);
                    }
                });
    }

    @Override
    public void addDeedCompanyLicense(RequestBody rosterBody) {
        mApi.getApiService().addDeedCompanyLicense(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onAddDeedCompanyLicenseSuccess();
                    }
                });
    }

    @Override
    public void modifyDeedCompanyLicense(RequestBody rosterBody) {
        mApi.getApiService().modifyDeedCompanyLicense(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyDeedCompanyLicenseSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull DeedCompanyLicenseContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}