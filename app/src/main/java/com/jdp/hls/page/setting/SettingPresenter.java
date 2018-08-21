package com.jdp.hls.page.setting;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.rx.ResultObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SettingPresenter implements SettingContract.Presenter {
    private UserApi mApi;
    private SettingContract.View mView;

    @Inject
    public SettingPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void logout() {
        mApi.getApiService().logout().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object o) {
                        mView.onLogoutSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull SettingContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}