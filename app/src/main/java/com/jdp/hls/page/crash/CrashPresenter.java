package com.jdp.hls.page.crash;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ResultObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CrashPresenter implements CrashContract.Presenter {
    private UserApi mApi;
    private CrashContract.View mView;

    @Inject
    public CrashPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void uploadError(String iP, String applicationType, String oSversion, String exceptionType, String
            exceptionMsg,
                            String employeeId) {
        mApi.getApiService().uploadError(iP, applicationType, oSversion, exceptionType, exceptionMsg,
                employeeId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onUploadErrorSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull CrashContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}