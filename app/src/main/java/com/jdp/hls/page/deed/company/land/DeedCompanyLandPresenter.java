package com.jdp.hls.page.deed.company.land;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DeedCompanyLand;
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
public class DeedCompanyLandPresenter implements DeedCompanyLandContract.Presenter {
    private UserApi mApi;
    private DeedCompanyLandContract.View mView;

    @Inject
    public DeedCompanyLandPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDeedCompanyLand(String houseId) {
        mApi.getApiService().getDeedCompanyLand(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DeedCompanyLand>(mView) {
                    @Override
                    protected void onSuccess(DeedCompanyLand deedCompanyLand) {
                        mView.onGetDeedCompanyLandSuccess(deedCompanyLand);
                    }
                });
    }

    @Override
    public void addDeedCompanyLand(RequestBody rosterBody) {
        mApi.getApiService().addDeedCompanyLand(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onAddDeedCompanyLandSuccess();
                    }
                });
    }

    @Override
    public void modifyDeedCompanyLand(RequestBody rosterBody) {
        mApi.getApiService().modifyDeedCompanyLand(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyDeedCompanyLandSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull DeedCompanyLandContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}