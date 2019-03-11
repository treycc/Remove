package com.jdp.hls.page.deed.company.immovable;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DeedCompanyImmovable;
import com.jdp.hls.model.entiy.DeedItem;
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
public class DeedCompanyImmovablePresenter implements DeedCompanyImmovableContract.Presenter {
    private UserApi mApi;
    private DeedCompanyImmovableContract.View mView;

    @Inject
    public DeedCompanyImmovablePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDeedCompanyImmovableDetail(int  certId) {
        mApi.getApiService().getDeedCompanyImmovableDetail(certId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DeedCompanyImmovable>(mView) {
                    @Override
                    protected void onSuccess(DeedCompanyImmovable deedCompanyImmovable) {
                        mView.onGetDeedCompanyImmovableSuccess(deedCompanyImmovable);
                    }
                });
    }

    @Override
    public void saveDeedCompanyImmovable(RequestBody rosterBody) {
        mApi.getApiService().saveDeedCompanyImmovable(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<DeedItem>(mView) {
                    @Override
                    protected void onSuccess(DeedItem deedItem) {
                        mView.onSaveDeedCompanyImmovableSuccess(deedItem);
                    }
                });
    }


    @Override
    public void attachView(@NonNull DeedCompanyImmovableContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}