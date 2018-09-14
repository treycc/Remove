package com.jdp.hls.page.airphoto.person;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.AirPhotoPerson;
import com.jdp.hls.model.entiy.LoadSirObserver;

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
public class AirPhotoPersonPresenter implements AirPhotoPersonContract.Presenter {
    private UserApi mApi;
    private AirPhotoPersonContract.View mView;

    @Inject
    public AirPhotoPersonPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getAirPhotoPersons(String projectId) {
        mApi.getApiService().getAirPhotoPersons(projectId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<AirPhotoPerson>>(mView) {
                    @Override
                    protected void onSuccess(List<AirPhotoPerson> airPhotoPersonList) {
                        mView.onGetAirPhotoPersonsSuccess(airPhotoPersonList);
                    }
                });
    }


    @Override
    public void attachView(@NonNull AirPhotoPersonContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}