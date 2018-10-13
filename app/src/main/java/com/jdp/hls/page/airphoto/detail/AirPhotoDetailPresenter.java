package com.jdp.hls.page.airphoto.detail;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.AirPhotoItem;
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
public class AirPhotoDetailPresenter implements AirPhotoDetailContract.Presenter {
    private UserApi mApi;
    private AirPhotoDetailContract.View mView;

    @Inject
    public AirPhotoDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getAirPhotoDetail(String airCheckProId) {
        mApi.getApiService().getAirPhotoDetail(airCheckProId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<AirPhotoItem>(mView) {
                    @Override
                    protected void onSuccess(AirPhotoItem airPhotoItem) {
                        mView.onGetAirPhotoDetailSuccess(airPhotoItem);
                    }
                });
    }

    @Override
    public void modifyAirPhotoDetail(RequestBody rosterBody) {
        mApi.getApiService().modifyAirPhotoDetail(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<AirPhotoItem>(mView) {
                    @Override
                    protected void onSuccess(AirPhotoItem airPhotoItem) {
                        mView.onModifyAirPhotoDetailSuccess(airPhotoItem);
                    }
                });
    }


    @Override
    public void attachView(@NonNull AirPhotoDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}