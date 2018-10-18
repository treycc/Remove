package com.jdp.hls.page.deed.personal.land;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DeedPersonalLand;
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
public class DeedPersonalLandPresenter implements DeedPersonalLandContract.Presenter {
    private UserApi mApi;
    private DeedPersonalLandContract.View mView;

    @Inject
    public DeedPersonalLandPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDeedPersonalLand(String houseId) {
        mApi.getApiService().getDeedPersonalLand(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DeedPersonalLand>(mView) {
                    @Override
                    protected void onSuccess(DeedPersonalLand deedPersonalLand) {
                        mView.onGetDeedPersonalLandSuccess(deedPersonalLand);
                    }
                });
    }

    @Override
    public void addDeedPersonalLand(RequestBody rosterBody) {
        mApi.getApiService().addDeedPersonalLand(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onAddDeedPersonalLandSuccess();
                    }
                });
    }

    @Override
    public void modifyDeedPersonalLand(RequestBody rosterBody) {
        mApi.getApiService().modifyDeedPersonalLand(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyDeedPersonalLandSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull DeedPersonalLandContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}