package com.jdp.hls.page.module;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ModuleDetail;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModulePresenter implements ModuleContract.Presenter {
    private UserApi mApi;
    private ModuleContract.View mView;

    @Inject
    public ModulePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getModuleDetail(String routeId) {
        mApi.getApiService().getModuleDetail(routeId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<ModuleDetail>(mView) {
                    @Override
                    protected void onSuccess(ModuleDetail moduleDetail) {
                        mView.onGetModuleDetailSuccess(moduleDetail);
                    }
                });
    }


    @Override
    public void attachView(@NonNull ModuleContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}