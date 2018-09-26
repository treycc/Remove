package com.jdp.hls.page.deed.personal.immovable;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DeedPersonalImmovable;
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
public class DeedPersonalImmovablePresenter implements DeedPersonalImmovableContract.Presenter {
    private UserApi mApi;
    private DeedPersonalImmovableContract.View mView;

    @Inject
    public DeedPersonalImmovablePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDeedPersonalImmovable(String houseId) {
        mApi.getApiService().getDeedPersonalImmovable(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<DeedPersonalImmovable>(mView) {
                    @Override
                    protected void onSuccess(DeedPersonalImmovable deedPersonalImmovable) {
                        mView.onGetDeedPersonalImmovableSuccess(deedPersonalImmovable);
                    }
                });
    }

    @Override
    public void addDeedPersonalImmovable(RequestBody rosterBody) {
        mApi.getApiService().addDeedPersonalImmovable(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onAddDeedPersonalImmovableSuccess();
                    }
                });
    }

    @Override
    public void modifyDeedPersonalImmovable(RequestBody rosterBody) {
        mApi.getApiService().modifyDeedPersonalImmovable(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyDeedPersonalImmovableSuccess();
                    }
                });
    }

    @Override
    public void attachView(@NonNull DeedPersonalImmovableContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}