package com.jdp.hls.page.airphoto.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.AirPhotoItem;
import com.jdp.hls.model.entiy.ResultObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AirPhotoListPresenter implements AirPhotoListContract.Presenter {
    private UserApi mApi;
    private AirPhotoListContract.View mView;

    @Inject
    public AirPhotoListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getAirPhotoList(String buildingType, String taskType) {
        mApi.getApiService().getAirPhotoList(buildingType, taskType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<AirPhotoItem>>(mView) {
                    @Override
                    protected void onSuccess(List<AirPhotoItem> airPhotoItems) {
                        mView.onGetAirPhotoListSuccess(airPhotoItems);
                    }
                });
    }


    @Override
    public void attachView(@NonNull AirPhotoListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}