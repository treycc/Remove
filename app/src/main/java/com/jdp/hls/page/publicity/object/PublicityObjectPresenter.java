package com.jdp.hls.page.publicity.object;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.PublicityObject;
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
public class PublicityObjectPresenter implements PublicityObjectContract.Presenter {
    private UserApi mApi;
    private PublicityObjectContract.View mView;

    @Inject
    public PublicityObjectPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getPublicityObject(int buildingType, int publicityType) {
        mApi.getApiService().getPublicityObject(buildingType, publicityType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<PublicityObject>>(mView) {
                    @Override
                    protected void onSuccess(List<PublicityObject> publicityObjects) {
                        mView.onGetPublicityObjectSuccess(publicityObjects);
                    }
                });
    }

    @Override
    public void attachView(@NonNull PublicityObjectContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}