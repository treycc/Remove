package com.jdp.hls.page.deed.company.land;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DeedCompanyLand;
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
public class DeedCompanyLandPresenter implements DeedCompanyLandContract.Presenter {
    private UserApi mApi;
    private DeedCompanyLandContract.View mView;

    @Inject
    public DeedCompanyLandPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDeedCompanyLandDetail(int certId) {
        mApi.getApiService().getDeedCompanyLandDetail(certId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DeedCompanyLand>(mView) {
                    @Override
                    protected void onSuccess(DeedCompanyLand deedCompanyLand) {
                        mView.onGetDeedCompanyLandSuccess(deedCompanyLand);
                    }
                });
    }

    @Override
    public void saveDeedCompanyLand(RequestBody rosterBody) {
        mApi.getApiService().saveDeedCompanyLand(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<DeedItem>(mView) {
                    @Override
                    protected void onSuccess(DeedItem deedItem) {
                        mView.onSaveDeedCompanyLandSuccess(deedItem);
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