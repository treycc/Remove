package com.jdp.hls.page.airphoto.building;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.AirPhotoBuilding;
import com.jdp.hls.model.entiy.LoadSirObserver;
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
public class AirPhotoBuildingPresenter implements AirPhotoBuildingContract.Presenter {
    private UserApi mApi;
    private AirPhotoBuildingContract.View mView;

    @Inject
    public AirPhotoBuildingPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getAirPhotoBuildings( String buildingType) {
        mApi.getApiService().getAirPhotoBuildings(  buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<AirPhotoBuilding>>(mView) {
                    @Override
                    protected void onSuccess(List<AirPhotoBuilding> airPhotoBuildingList) {
                        mView.onGetAirPhotoBuildingsSuccess(airPhotoBuildingList);
                    }
                });
    }


    @Override
    public void attachView(@NonNull AirPhotoBuildingContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}