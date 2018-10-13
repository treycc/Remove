package com.jdp.hls.page.airphoto.add;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
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
public class AirPhotoApplyPresenter implements AirPhotoApplyContract.Presenter {
    private UserApi mApi;
    private AirPhotoApplyContract.View mView;

    @Inject
    public AirPhotoApplyPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void applyAirPhoto(RequestBody rosterBody) {
        mApi.getApiService().applyAirPhoto(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onApplyAirPhotoSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull AirPhotoApplyContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}